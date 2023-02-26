package com.example.appquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast

class PerguntasActivity : AppCompatActivity() {
    private lateinit var editnome: TextView
    private lateinit var textContadorPerguntas: TextView
    private lateinit var text_pergunta: TextView
    private lateinit var btn_confirmar_respostas: Button
    private lateinit var radioGoupPerguntas: RadioGroup
    private lateinit var radio_pergunta_01: RadioButton
    private lateinit var radio_pergunta_02: RadioButton
    private lateinit var radio_pergunta_03: RadioButton
    private lateinit var listaPerguntas: Array<Pergunta>
    var indicePergunta = 1
    var totalPerguntasCorretas = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perguntas)
        inicializar()

        listaPerguntas = DadosFicticios.recuperarPerguntas()

        val bundle = intent.extras
        val nome = bundle?.getString("nome_usuario") ?: "Nome não identificado"
        editnome.text = "Olá, $nome"
        exibirPerguntaAtual()
        btn_confirmar_respostas.setOnClickListener {
            salvarRespostaCorreta()
            indicePergunta++
            if (indicePergunta <= listaPerguntas.size) {
                if (validarPerguntasRespostaAtual()) {
                    exibirPerguntaAtual()
                }


            }else{

                intent = Intent(this,DetalhesActivity::class.java)
                intent.putExtra("respostascertas", totalPerguntasCorretas )
                startActivity(intent)
                finish()
            }
        }

    }

    private fun salvarRespostaCorreta() {
        val pergunta = listaPerguntas[indicePergunta - 1]
        val resCerta = pergunta.respostacerta

        var respostaSelecionada = if (radio_pergunta_01.isChecked){
            1
        }else if(radio_pergunta_02.isChecked){
            2
        }else{
            3
        }
        if (respostaSelecionada == resCerta){

            totalPerguntasCorretas++


        }


    }


    private fun validarPerguntasRespostaAtual(): Boolean {
        val resposta01 = radio_pergunta_01.isChecked
        val resposta02 =  radio_pergunta_02.isChecked
        val resposta03 = radio_pergunta_03.isChecked

        if (resposta01 || resposta02 || resposta03){
            radioGoupPerguntas.clearCheck()
            return true
        }
        Toast.makeText(this, "Escolha uma resposta", Toast.LENGTH_SHORT).show()
        return false
    }

    private fun exibirPerguntaAtual() {
        val perguntaAtual = listaPerguntas[indicePergunta - 1]
        text_pergunta.text = perguntaAtual.titulo
        radio_pergunta_01.text = perguntaAtual.resposta01
        radio_pergunta_02.text = perguntaAtual.resposta02
        radio_pergunta_03.text = perguntaAtual.resposta03

        val totalPerguntas = listaPerguntas.size
        textContadorPerguntas.text = "$indicePergunta º pergunta de $totalPerguntas"


    }

    private fun inicializar() {
        editnome = findViewById(R.id.textViewNome)
        textContadorPerguntas = findViewById(R.id.text_contador_perguntas)
        text_pergunta = findViewById(R.id.text_pergunta)
        btn_confirmar_respostas = findViewById(R.id.button_confirmar)
        radioGoupPerguntas = findViewById(R.id.radio_group_respostas)
        radio_pergunta_01 = findViewById(R.id.radio_pergunta_01)
        radio_pergunta_02 = findViewById(R.id.radio_pergunta_02)
        radio_pergunta_03 = findViewById(R.id.radio_pergunta_03)

    }
}