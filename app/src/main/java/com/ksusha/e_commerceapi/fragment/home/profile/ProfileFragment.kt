package com.ksusha.e_commerceapi.fragment.home.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ksusha.e_commerceapi.R
import com.ksusha.e_commerceapi.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private var _binding: FragmentProfileBinding? = null
    private val binding by lazy { _binding!! }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProfileBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}