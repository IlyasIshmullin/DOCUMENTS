package com.example.documents_rewrite.mainApplication.app.documents

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.example.documents_rewrite.R
import com.example.documents_rewrite.databinding.DocumentsFragmentBinding

class DocumentsFragment : Fragment() {

    private var _binding: DocumentsFragmentBinding? = null
    private val binding get() = _binding!!
    private val rotateOpen : Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.rotate_open_animation)}
    private val rotateClose: Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.rotate_close_animatioin) }
    private val fromBottom: Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.from_bottom_animation) }
    private val toBottom: Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.to_bottom_animation) }

    private var clicked = false

    companion object {
        fun newInstance() = DocumentsFragment()
    }

    private lateinit var viewModel: DocumentsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DocumentsFragmentBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        FAB()


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(DocumentsViewModel::class.java)
        // TODO: Use the ViewModel
    }

    private fun FAB(): Void? {

        val add_btn : View = binding.documentsAddFab
        val download_btn : View = binding.documentsDownloadFab
        val scan_btn : View = binding.documentsScanFab

        fun setClickable(clicked: Boolean) {
            if(!clicked) {
                scan_btn.isClickable = true
                download_btn.isClickable = true
            } else {
                scan_btn.isClickable = false
                download_btn.isClickable = false
            }
        }

        fun setVisiblity(clicked : Boolean) {
            if(!clicked) {
                scan_btn.visibility = View.VISIBLE
                download_btn.visibility = View.VISIBLE
            } else {
                scan_btn.visibility = View.INVISIBLE
                download_btn.visibility = View.INVISIBLE
            }
        }

        fun setAnimation(clicked : Boolean) {
            if(!clicked) {
                scan_btn.startAnimation(fromBottom)
                download_btn.startAnimation(fromBottom)
                add_btn.startAnimation(rotateOpen)
            } else {
                scan_btn.startAnimation(toBottom)
                download_btn.startAnimation(toBottom)
                add_btn.startAnimation(rotateClose)
            }
        }

        fun onAddButtonClicked() {
            setVisiblity(clicked)
            setAnimation(clicked)
            setClickable(clicked)
            clicked = !clicked
        }

        add_btn.setOnClickListener {
            onAddButtonClicked()
        }

        scan_btn.setOnClickListener {
            Log.d("Documents", "SCAN_FAB")
            Toast.makeText(context, "scanner", Toast.LENGTH_LONG).show()
        }

        download_btn.setOnClickListener {
            Log.d("Documents", "DOWNLOAD_FAB")
            Toast.makeText(context, "Download", Toast.LENGTH_LONG).show()
        }
        return null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}