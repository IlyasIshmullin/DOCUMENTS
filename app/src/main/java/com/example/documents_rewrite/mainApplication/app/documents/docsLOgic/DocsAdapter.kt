package com.example.documents_rewrite.mainApplication.app.documents.docsLOgic

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.documents_rewrite.R

class DocsAdapter(private val docsList : ArrayList<DocumentData>) : RecyclerView.Adapter<DocsAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_image_adapter, parent, false)
        return ViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = docsList[position]
        holder.image.setImageResource(currentItem.image)
        holder.tv.text = currentItem.name
    }

    override fun getItemCount(): Int {
        return docsList.size
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        var image : ImageView = itemView.findViewById(R.id.shapeableImageView)
        var tv : TextView = itemView.findViewById(R.id.materialTextView5)

    }

}