package com.example.documents_rewrite.mainApplication.documents

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.documents_rewrite.R

class DocumentsFragment : Fragment() {

    companion object {
        fun newInstance() = DocumentsFragment()
    }

    private lateinit var viewModel: DocumentsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.documents_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Documents"

        viewModel = ViewModelProvider(this).get(DocumentsViewModel::class.java)
        // TODO: Use the ViewModel
    }


}