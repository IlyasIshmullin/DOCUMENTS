package com.example.documents_rewrite.mainApplication.app.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.documents_rewrite.R
import com.example.documents_rewrite.databinding.FragmentEditProfileBinding
import com.example.documents_rewrite.databinding.FragmentPersonalInfoProfileBinding

class PersonalInfoProfileFragment : Fragment() {
    private var _binding: FragmentPersonalInfoProfileBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPersonalInfoProfileBinding.inflate(inflater, container,false)
        setHasOptionsMenu(true)

        binding.emailTV.setText("ilya.ishmullin.24.04.2006@gmail.com")


        binding.saveBTN.setOnClickListener {
            Toast.makeText(context, "Saved", Toast.LENGTH_LONG).show()
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