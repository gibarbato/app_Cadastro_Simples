package com.gibarbato.appcontatos

import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ContatoViewHolder(view:View):RecyclerView.ViewHolder(view) {

    private val  textViewNome = itemView.findViewById<TextView>(R.id.textViewNome)
    private val  textViewTelefone = itemView.findViewById<TextView>(R.id.textViewTelefone)

    fun bind(item: Contato){
        textViewNome.text = item.nome
        textViewTelefone.text = item.telefone

        itemView.setOnClickListener {
            item.onClick?.invoke(item.id)
        }
    }
}