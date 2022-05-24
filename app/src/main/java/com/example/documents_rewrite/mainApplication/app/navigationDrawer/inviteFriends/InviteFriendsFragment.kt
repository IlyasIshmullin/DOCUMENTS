package com.example.documents_rewrite.mainApplication.app.navigationDrawer.inviteFriends

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.ContactsContract.Intents.Insert.ACTION
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.documents_rewrite.R
import com.example.documents_rewrite.databinding.AboutDocumentsFragmentBinding
import com.example.documents_rewrite.databinding.InviteFriensFragmentBinding
import com.example.documents_rewrite.mainApplication.app.MainActivity
import java.util.jar.Manifest

class InviteFriendsFragment : Fragment(), View.OnClickListener {

    private var _binding: InviteFriensFragmentBinding? = null
    private val binding get() = _binding!!


    companion object {
        fun newInstance() = InviteFriendsFragment()
    }

    private lateinit var viewModel: InviteFriendsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = InviteFriensFragmentBinding.inflate(inflater, container,false)
        setHasOptionsMenu(true)

        val subject = "Join to ${"Documents"} "
        val message = "Lets downlaod Documendts!"

        binding.whatappConstraint.setOnClickListener(this)
        binding.emailConstraint.setOnClickListener(this)
        binding.smsConstraint.setOnClickListener(this)
        binding.telegramConstraint.setOnClickListener(this)
        binding.inviteByAnyConstraint.setOnClickListener(this)
        return binding.root
    }


    override fun onClick(v: View?) {
        val text = "Lets download Documents!"
        val subject = "Join to ${"Documents"} "
        var intent = Intent(Intent.ACTION_SEND).apply {
            putExtra(Intent.EXTRA_TEXT, text)
            putExtra(Intent.EXTRA_SUBJECT, subject)
        }
        when(v?.id) {
            binding.whatappConstraint.id -> intentWhatsappInvite(intent)
            binding.emailConstraint.id -> intentEmailInvite(intent)
            binding.smsConstraint.id -> intentSmsInvite()
            binding.telegramConstraint.id -> intentTelegram(intent)
            binding.inviteByAnyConstraint.id -> intentInviteByAny(intent)

        }
    }

    private fun intentTelegram(intent: Intent) {
        val appName = "org.telegram.messenger"
        intent.apply {
            type = "text/plain"
            setPackage(appName)
        }
        try {
            startActivity(intent)
        } catch (e : java.lang.Exception) {
            e.printStackTrace()
        }
    }


    private fun intentWhatsappInvite(intent: Intent) {
        try {
            intent.apply {
                //putExtra("jid", "${data.phone}@s.whatsapp.net")
                type = "text/plain"
                setPackage("com.whatsapp")
            }
            startActivity(intent)
        }catch (e: Exception){
            e.printStackTrace()
            val appPackageName = "com.whatsapp"
            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName")))
            } catch (e :android.content.ActivityNotFoundException) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")))
            }
        }
    }


    private fun intentEmailInvite(intent: Intent) {
        try {
            intent.apply {
                action = Intent.ACTION_VIEW
                //type = "*/*"
                data = Uri.parse("mailto:") // only email apps should handle this
            }
            startActivity(intent)
        } catch (e : Exception) {
            e.printStackTrace()
        }

    }


    private fun intentSmsInvite() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", "", null)).apply {
            putExtra("sms_body", "Welcome to Documents")
        }
        try {
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun intentInviteByAny(intent: Intent) {
        intent.apply {
            type = "*/*"
        }
        try {
            startActivity(intent)
        } catch (e:Exception) {
            e.printStackTrace()
        }
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