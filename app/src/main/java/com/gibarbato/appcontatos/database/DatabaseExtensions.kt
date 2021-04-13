package com.gibarbato.appcontatos.database

import android.content.ContentValues
import com.gibarbato.appcontatos.Contato


fun ContatosDatabase.inserirContato(item: Contato): Long {
    val idContato = writableDatabase.insert("TB_CONTATOS", null, ContentValues().apply {
        put("NOME", item.nome)
        put("TELEFONE", item.telefone)
        put("EMAIL", item.email)
    })
    return idContato
}

fun ContatosDatabase.selecionarContatos() : List<Contato> {

    val sql = "SELECT * FROM TB_CONTATOS"
    val cursor = readableDatabase.rawQuery(sql,null)
    val contatoList = mutableListOf<Contato>()

    if(cursor.count > 0) {

        while(cursor.moveToNext()){
            val contato = Contato(
                id = cursor.getInt(cursor.getColumnIndex("ID")),
                nome = cursor.getString(cursor.getColumnIndex("NOME")),
                telefone = cursor.getString(cursor.getColumnIndex("TELEFONE")),
                email = cursor.getString(cursor.getColumnIndex("EMAIL"))
            )
            contatoList.add(contato)
        }
        cursor.close()
    }
    return contatoList
}

fun ContatosDatabase.atualizarContato(item: Contato): Int{
    val valores = ContentValues().apply {
        put("NOME", item.nome)
        put("TELEFONE", item.telefone)
        put("EMAIL", item.email)
    }
    //update tb_contatos ser nome = .., tele = .. WHERE ID = ...
    return writableDatabase.update("TB_CONTATOS", valores, "ID=${item.id}", null)
}