package com.example.documents_rewrite.mainApplication.app.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.documents_rewrite.R
import com.example.documents_rewrite.databinding.FragmentEditProfileBinding
import com.example.documents_rewrite.databinding.ProfileFragmentBinding


class editProfileFragment : Fragment() {

    private var _binding: FragmentEditProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditProfileBinding.inflate(inflater, container,false)
        setHasOptionsMenu(true)

        binding.nameTV.setText("iltas")


        binding.persInformationTV.setOnClickListener {
            findNavController().navigate(R.id.action_editProfileFragment_to_personalInfoProfileFragment)
        }

        // Inflate the layout for this fragment
        return binding.root
    }



    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.nav_search).setVisible(false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}