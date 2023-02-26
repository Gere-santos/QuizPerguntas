package com.example.appquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var editnome: TextView
    private lateinit var btn_iniciar: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editnome = findViewById(R.id.edit_nome)
        btn_iniciar = findViewById(R.id.button)

        btn_iniciar.setOnClickListener {

            val nome = editnome.text.toString()
          if(nome.isNotEmpty()){
              intent = Intent(this, PerguntasActivity::class.java)
              intent.putExtra("nome_usuario",nome )
              startActivity(intent)
          }else{
              Toast.makeText(this, "Preencha o nome", Toast.LENGTH_SHORT).show()
          }
        }

    }
}