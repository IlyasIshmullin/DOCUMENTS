package com.example.documents_rewrite.mainApplication.app.navigationDrawer.inviteFriends

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import com.example.documents_rewrite.R

class InviteFriendsFragment : Fragment() {

    companion object {
        fun newInstance() = InviteFriendsFragment()
    }

    private lateinit var viewModel: InviteFriendsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.invite_friens_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(InviteFriendsViewModel::class.java)
        // TODO: Use the ViewModel
    }
    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.nav_search).setVisible(false)
    }

}