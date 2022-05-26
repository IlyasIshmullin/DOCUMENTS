package com.example.documents_rewrite.mainApplication.app.documents.docsLOgic

import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.documents_rewrite.R
import com.example.documents_rewrite.mainApplication.app.documents.DocumentsFragment
import java.util.*

class DocsAdapter(
    private var documentsList: ArrayList<DocumentData>,
    ) : RecyclerView.Adapter<DocsAdapter.ViewHolder>(), Filterable {



    var docsListFilter = ArrayList<DocumentData>()
    init {
        docsListFilter = documentsList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_image_adapter_var2, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = documentsList[position]
        currentItem.image?.let { holder.image.setImageResource(it) }
        holder.tv.text = currentItem.name


     }

    override fun getItemCount(): Int {
        return documentsList.size
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView), View.OnClickListener,
        PopupMenu.OnMenuItemClickListener {

        var image : ImageView = itemView.findViewById(R.id.imageOfDocumentVar2)
        var tv : TextView = itemView.findViewById(R.id.documentNameTVvar2)
        var imageButton : ImageView = itemView.findViewById(R.id.popupIcon)

        init {
            imageButton.setOnClickListener(this)
            //itemView.setOnClickListener(this)
        }
        override fun onClick(v: View) {
            Log.d("PopUP", "OnClick$adapterPosition")
            showPopupMenu(v)
        }

        private fun  showPopupMenu(view: View) {
            val popupMenu = PopupMenu(view.context, view)
            popupMenu.inflate(R.menu.popup_menu_rv)
            popupMenu.setOnMenuItemClickListener(this)
            popupMenu.show()
        }


        override fun onMenuItemClick(item: MenuItem?): Boolean {
            when(item?.itemId) {
                R.id.action_poput_delete -> {
                    Log.d("PopUP", "Deleted$adapterPosition")
                    return true
                }
            }
            return false
        }



    }

    override fun getFilter(): Filter {
        return object: Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) docsListFilter = documentsList else {
                    val resultList = ArrayList<DocumentData>()
                    documentsList
                        .filter {
                            (it.name.contains(constraint!!))
                        }
                        .forEach{ resultList.add(it) }
                    /*for (row in documentsList) {
                        if (row.name.lowercase(Locale.ROOT).contains(charSearch.lowercase(Locale.ROOT))) {
                            resultList.add(row)
                        }
                    }*/
                docsListFilter = resultList
                }
                return FilterResults().apply { values = docsListFilter }
            }

            @Suppress("UNCHEKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                docsListFilter = if (results?.values == null)
                    ArrayList()
                else
                    results.values as ArrayList<DocumentData>
                notifyDataSetChanged()
            }

        }
    }



}