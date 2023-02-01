package com.baubap.android.fakelog.presentation.sign

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.baubap.android.fakelog.databinding.FragmentSignBinding

// Fragment that shows the signIn screen (not working)
class SignFragment : Fragment() {

    // ViewBinding associated to the current screen
    private var _binding: FragmentSignBinding? = null
    private val binding get() = _binding!!

    // Fragment lifecycle functions
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}