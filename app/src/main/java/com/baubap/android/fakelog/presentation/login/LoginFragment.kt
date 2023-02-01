package com.baubap.android.fakelog.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.baubap.android.fakelog.R
import com.baubap.android.fakelog.databinding.FragmentLoginBinding
import com.baubap.android.fakelog.framework.common.showAlertMessage
import com.baubap.android.fakelog.framework.common.statusBarSize
import com.baubap.android.fakelog.usecase.event.LoginFormEvent
import com.baubap.android.fakelog.usecase.event.ValidationEvent

// Fragment that shows the login screen
class LoginFragment : Fragment() {

    // ViewModel associated to the current screen
    private val viewModel: LoginViewModel by viewModels()

    // ViewBinding associated to the current screen
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    // Fragment lifecycle functions
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        initActions()
        initComponents()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Detonates the screen init actions
    private fun initActions() {
        // Collects the viewmodel event channel
        lifecycleScope.launchWhenStarted {
            viewModel.validationEvents.collect { event ->
                when (event) {
                    is ValidationEvent.Success -> {
                        showAlertMessage(
                            context,
                            getString(R.string.login_success_title),
                            getString(R.string.login_success_message)
                        )
                    }
                    is ValidationEvent.Error -> {
                        showAlertMessage(
                            context,
                            getString(R.string.login_error_title),
                            viewModel.state.value.curpError
                                .ifEmpty { viewModel.state.value.nipError }
                        )
                    }
                }
            }
        }
    }

    // Inits the current screen UI components
    private fun initComponents() {
        // Set the status bar padding to the logo
        binding.loginImageViewLogo.setPadding(0, statusBarSize, 0, 0)
        // Adds the clear buttons actions
        binding.loginButtonCurpClear.setOnClickListener {
            binding.loginEditTextCurp.setText("")
        }
        binding.loginButtonNipClear.setOnClickListener {
            binding.loginEditTextNip.setText("")
        }
        // Sets the curp and nip text change listeners
        binding.loginEditTextCurp.doOnTextChanged { text, _, _, _ ->
            viewModel.onEvent(LoginFormEvent.CurpChanged(text.toString()), requireContext())
            if (text.isNullOrEmpty()) {
                binding.loginButtonCurpClear.visibility = View.INVISIBLE
            } else {
                binding.loginButtonCurpClear.visibility = View.VISIBLE
            }
        }
        binding.loginEditTextNip.doOnTextChanged { text, _, _, _ ->
            viewModel.onEvent(LoginFormEvent.NipChanged(text.toString()), requireContext())
            if (text.isNullOrEmpty()) {
                binding.loginButtonNipClear.visibility = View.INVISIBLE
            } else {
                binding.loginButtonNipClear.visibility = View.VISIBLE
            }
        }
        // Configs the nip help link
        binding.loginTextViewNipLink.let {
            it.setLinkTextColor(ContextCompat.getColor(requireContext(), R.color.link))
            it.setOnClickListener {
                showAlertMessage(
                    context = requireContext(),
                    title = getString(R.string.login_nip_help_title),
                    message = getString(R.string.login_nip_help_message)
                )
            }
        }
        // Sets the submit button action for the login form
        binding.loginButtonPrimary.setOnClickListener {
            viewModel.onEvent(LoginFormEvent.Submit, requireContext())
        }
    }

}