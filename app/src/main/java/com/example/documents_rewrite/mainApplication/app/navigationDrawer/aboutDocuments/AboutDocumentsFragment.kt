package com.example.documents_rewrite.mainApplication.app.navigationDrawer.aboutDocuments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.documents_rewrite.R
import com.example.documents_rewrite.databinding.AboutDocumentsFragmentBinding
import com.example.documents_rewrite.databinding.ProfileFragmentBinding
import com.example.documents_rewrite.mainApplication.app.MainActivity

class AboutDocumentsFragment : Fragment() {

    private var _binding: AboutDocumentsFragmentBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = AboutDocumentsFragment()
    }

    private lateinit var viewModel: AboutDocumentsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AboutDocumentsFragmentBinding.inflate(inflater, container,false)

        setHasOptionsMenu(true)


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AboutDocumentsViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.nav_search).setVisible(false)
    }
}