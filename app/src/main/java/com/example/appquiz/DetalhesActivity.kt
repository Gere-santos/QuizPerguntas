package com.example.appquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class DetalhesActivity : AppCompatActivity() {

    private lateinit var textResultado: TextView
    private lateinit var btn_reiniciar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes)

        textResultado = findViewById(R.id.textResultado)
        btn_reiniciar = findViewById(R.id.btn_reiniciar)

        val bundle = intent.extras
        val resultado = bundle?.getInt("respostascertas")

        textResultado.text = "Você acertou ${resultado.toString()} perguntas!"

        btn_reiniciar.setOnClickListener {

            startActivity(Intent(this, MainActivity::class.java))
        }


    }
}