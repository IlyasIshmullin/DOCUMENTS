package com.example.documents_rewrite.mainApplication.app.navigationDrawer.settings

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.documents_rewrite.R
import com.example.documents_rewrite.SplashActivity
import com.example.documents_rewrite.authorization.AuthorizationActivity
import com.example.documents_rewrite.databinding.SettingsFragmentBinding

class SettingsFragment : Fragment() {

    private var _binding: SettingsFragmentBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = SettingsFragment()
    }

    private lateinit var viewModel: SettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        _binding = SettingsFragmentBinding.inflate(inflater, container, false)


        binding.settingsExitButton.setOnClickListener {
            val editor = SplashActivity.profile.edit()
            editor.putBoolean(SplashActivity.APP_PREFERENCES_IS_LOGIN, false).apply()
            val intent = Intent(context, AuthorizationActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SettingsViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.nav_search).setVisible(false)
    }
}