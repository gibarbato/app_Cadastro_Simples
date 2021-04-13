package com.gibarbato.appcontatos

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gibarbato.appcontatos.database.ContatosDatabase
import com.gibarbato.appcontatos.database.inserirContato

class CadastroActivity : AppCompatActivity() {

    private lateinit var editTextNome: EditText
    private lateinit var editTextTelefone: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var buttonSalvar: Button

    private lateinit var database: ContatosDatabase
    private var idContato: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        editTextNome = findViewById(R.id.editTextNome)
        editTextTelefone = findViewById(R.id.editTextTelefone)
        editTextEmail = findViewById(R.id.editTextEmail)
        buttonSalvar = findViewById(R.id.buttonSalvar)

        database = ContatosDatabase(this)

        buttonSalvar.setOnClickListener {
            salvarContato()
        }
        idContato = intent.getIntExtra("ID_CONTATO", -1)
        Toast.makeText(this, "ID: ${idContato}", Toast.LENGTH_SHORT).show()


    }

    private fun salvarContato(){
        val contato = Contato(
            nome = editTextNome.text.toString(),
            telefone = editTextTelefone.text.toString(),
            email = editTextEmail.text.toString()
        )

        val idContato = database.inserirContato(contato)
        if (idContato == -1L){
            Toast.makeText(this,"Erro ao inserir", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "Contato inserido com sucesso id: $idContato", Toast.LENGTH_SHORT).show()
        }
    }
}