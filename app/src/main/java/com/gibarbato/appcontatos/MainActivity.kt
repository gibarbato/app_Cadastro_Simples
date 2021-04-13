package com.gibarbato.appcontatos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gibarbato.appcontatos.database.ContatosDatabase
import com.gibarbato.appcontatos.database.selecionarContatos

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private val contatoAdapter = ContatoAdapter()
    private lateinit var database: ContatosDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = contatoAdapter

        database = ContatosDatabase(this)

        val contatos = database.selecionarContatos()
        contatoAdapter.updateItems(contatos)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()

        val contatos = database.selecionarContatos().map {
            it.copy(onClick = :: abrirDetalhes)
        }
        contatoAdapter.updateItems(contatos)
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_cadastrar ->{
                val intent = Intent(this, CadastroActivity::class.java)
                startActivity(intent)
                true
            }
            else ->{
                false
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun abrirDetalhes(idContato: Int?){
        val intent = Intent(this, CadastroActivity::class.java)
        intent.putExtra("ID_CONTATO",idContato)

        startActivity(intent)
    }
}