package com.baubap.android.fakelog.presentation.intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.baubap.android.fakelog.databinding.FragmentIntroBinding
import com.baubap.android.fakelog.framework.common.navigationBarSize
import com.baubap.android.fakelog.framework.common.statusBarSize

// Fragment that shows the intro screen
class IntroFragment : Fragment() {

    // ViewBinding associated to the current screen
    private var _binding: FragmentIntroBinding? = null
    private val binding get() = _binding!!

    // Fragment lifecycle functions
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIntroBinding.inflate(inflater, container, false)
        initComponents()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Inits the current screen UI components
    private fun initComponents() {
        // Sets the padding to the content view
        binding.introConstraintLayoutContent.setPadding(0, statusBarSize, 0, navigationBarSize)
        // Adds the screen buttons actions
        binding.introButtonPrimary.setOnClickListener {
            navToSignFragment()
        }
        binding.introButtonSecondary.setOnClickListener {
            navToLoginFragment()
        }
    }

    // Navigation associated to the current screen
    private fun navToSignFragment() {
        val destination = IntroFragmentDirections.navToSignFragment()
        findNavController().navigate(destination)
    }

    private fun navToLoginFragment() {
        val destination = IntroFragmentDirections.navToLoginFragment()
        findNavController().navigate(destination)
    }

}