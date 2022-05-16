package com.example.documents_rewrite.mainApplication.app.navigationDrawer.contacts

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import com.example.documents_rewrite.R

class ContactsFragment : Fragment() {

    companion object {
        fun newInstance() = ContactsFragment()
    }

    private lateinit var viewModel: ContactsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.contacts_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(ContactsViewModel::class.java)
        // TODO: Use the ViewModel
    }
    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.nav_search).setVisible(false)
    }

}