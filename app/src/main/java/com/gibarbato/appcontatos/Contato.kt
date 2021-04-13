package com.gibarbato.appcontatos

data class Contato (
    val nome: String,
    val telefone: String,
    val email: String,
    val id:Int? = null,

    val onClick: ((Int?) -> Unit)? = null
        )

