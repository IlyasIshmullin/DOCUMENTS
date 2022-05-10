package com.example.documents_rewrite.mainApplication.navigationDrawer.aboutDocuments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.documents_rewrite.R

class AboutDocumentsFragment : Fragment() {

    companion object {
        fun newInstance() = AboutDocumentsFragment()
    }

    private lateinit var viewModel: AboutDocumentsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.about_documents_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "About Documents"
        viewModel = ViewModelProvider(this).get(AboutDocumentsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}