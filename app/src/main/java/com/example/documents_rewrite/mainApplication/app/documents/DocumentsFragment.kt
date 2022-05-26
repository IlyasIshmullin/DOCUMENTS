package com.example.documents_rewrite.mainApplication.app.documents

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.GridLayout
//import android.widget.SearchView
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.core.content.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.documents_rewrite.R
import com.example.documents_rewrite.databinding.DocumentsFragmentBinding
import com.example.documents_rewrite.mainApplication.app.documents.docsLOgic.DocsAdapter
import com.example.documents_rewrite.mainApplication.app.documents.docsLOgic.DocumentData
import com.example.documents_rewrite.mainApplication.app.documents.docsLOgic.RealPathUtil
import java.io.BufferedInputStream
import java.io.InputStream
import java.io.OutputStream


class DocumentsFragment : Fragment(), SearchView.OnQueryTextListener {

//    private val adapter: RecyclerView.Adapter<DocsAdapter.ViewHolder>? = null
    private val documentsList = ArrayList<DocumentData>()
    private val adapter = DocsAdapter(documentsList)

    private lateinit var searchView: SearchView

    init {
        val document = DocumentData(
            R.drawable.ic_svgfolder,
            "one"
        )


        val document2 = DocumentData(
            R.drawable.ic_svgfolder,
            "two"
        )
        documentsList.add(document)
        documentsList.add(document2)
        documentsList.add(document)
        documentsList.add(document2)
        documentsList.add(document2)
        documentsList.add(document)
        documentsList.add(document)
        documentsList.add(document2)
    }
/*
    private lateinit var RecyclerView: RecyclerView
    private lateinit var RecyclerAdapter: DocsAdapter*/
    /*
    private lateinit var newArrayList: ArrayList<DocumentData>
    lateinit var imageId : MutableList<Int>
    lateinit var text: MutableList<String>*/


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

        binding.recyclerViewInDocumentsFragment.layoutManager = GridLayoutManager(context, 2)
        binding.recyclerViewInDocumentsFragment.adapter = adapter
        binding.recyclerViewInDocumentsFragment.setHasFixedSize(true)

        //recyclerViewUpdate()


        //Log.d("OPENcv", "SHESH${OpenCVLoader.initDebug()}" )
        return binding.root
    }

  /*  private fun recyclerViewUpdate(){
        newRecyclerView = binding.rvImages
        newRecyclerView.layoutManager = GridLayoutManager(context, 2)
        newRecyclerView.setHasFixedSize(true)

        newArrayList = arrayListOf<DocumentData>()
        for (i in imageId.indices) {
            val docs = DocumentData(imageId[i],text[i])
            newArrayList.add(docs)
        }
        newRecyclerView.adapter = DocsAdapter(newArrayList)
    }*/


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
            try {
                val intent = Intent(context, AppScanActivity::class.java)
                startActivity(intent)
            } catch (exception: Exception) {
                Toast.makeText(context, "SCANNING", Toast.LENGTH_SHORT).show()
            }

            onAddButtonClicked()
        }


        download_btn.setOnClickListener {
            Log.d("Documents", "DOWNLOAD_FAB")
            Toast.makeText(context, "Download", Toast.LENGTH_LONG).show()
            openDocumentPicker()

            onAddButtonClicked()
        }

        return null
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (val result = tryHandleOpenDocumentResult(requestCode, resultCode, data)) {
            OpenFileResult.DifferentResult, OpenFileResult.OpenFileWasCancelled -> {
                Toast.makeText(context, "Couldnt take a file", Toast.LENGTH_SHORT).show()
            }
            OpenFileResult.ErrorOpeningFile -> Log.e(TAG, "error opening file")
            is OpenFileResult.FileWasOpened -> fileIsOpened(result.fileName, result.content, result.Path)
        }
    }

    private fun fileIsOpened(fileName: String, content: InputStream, Path: String) {

        //val fileInfo = requireView().findViewById<TextView>(R.id.openedTextInfo)
        //fileInfo.text = "Opened file $fileName"

        //TODO: with migranov to send image to server
        //text.add(fileName)
        //val bufferedImageView = ImageI
        //imageId.add(R.drawable.ic_launcher)
        Toast.makeText(context, "File Opened Well", Toast.LENGTH_SHORT).show()
        Toast.makeText(context, Path, Toast.LENGTH_LONG).show()
        //recyclerViewUpdate()
        content.close()
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
    }



    override fun onQueryTextSubmit(query: String?): Boolean {
        Log.d("onQuery", "sumbit")
        adapter.filter.filter(query)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        Log.d("onQuery", "change")
        adapter.filter.filter(newText)
        return false
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}