package com.example.appmegasena

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.*
import kotlin.random.Random as Random1

class MainActivity : AppCompatActivity() {
    //Variável que grava dados no banco de dados com dados primitivos
    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContentView(R.layout.activity_main)


        //Referenciando os componentes
        val editText: EditText = findViewById(R.id.editNumber)
        val txtResult: TextView = findViewById(R.id.txtResult)
        val btnGenerate: Button = findViewById(R.id.btn_generate)
        val lastBet: TextView = findViewById(R.id.last_bet)

        // **** SALVANDO DADOS NO BANCO DE DADOS SIMPLES ****
        //Mode private diz que esse "DB" não será compartilhado em outros APPS
        preferences = getSharedPreferences("db", Context.MODE_PRIVATE)
        val result = preferences.getString(
            "result",
            null
        )//Essa variável pegará o conteúdo que está na chave  e o valor do txtResult. (Line 85)
        //O defValue é o valor default. Na primeira iniciação será NULL, pois ainda não há dados

        if (result != null) {
            lastBet.text = "Ultima Aposta: \n $result"
        }


        btnGenerate.setOnClickListener {

            val text = editText.text.toString()
            numberGenerator(text, txtResult)
        }


    }

    //Esta função irá pegar o texto digitado no edtNumber e jogar dentro do TextView (txtResult)
    private fun numberGenerator(text: String, txtResult: TextView) {
        if (text.isNotEmpty()) { //Validação de falhas no input do campo


            val qtd = text.toInt()  //Convertendo para fazer operações lógicas

            if (qtd >= 6 && qtd <= 15) {
                val numbers =
                    mutableSetOf<Int>() //Criando lista para guardar os números Inteiros que não sejam repetitivos
                val random = Random()

                while (true) {
                    val number = random.nextInt(60) //0 ... 59
                    numbers.add(number + 1) //Pois o 0 não fará parte do sorteio. (0+ 1 ou N+1)

                    if (numbers.size == qtd) {
                        //Se o "tamanho" do número gerado for = a qtd (especificada) Pare de gerar
                        break
                    }
                }

                val ordered = numbers.toList().sorted()//Criando outra lista porém ordenada

                //O txtResult receberá os números da lista ordenada e converterá para String e colocará um hífen como separador entres os números
                txtResult.text = ordered.joinToString(" - ")


                //**** SALVAMENTO NO BANCO DE DADOS ****
                val editor = preferences.edit() //Editando para gravar os dados
                editor.putString(
                    "result",
                    txtResult.text.toString()
                ) //A variável vai guardar uma String pela sua chave e valor quando buscarmos a chave retornará o valor
                editor.apply() //Aplicando o salvamento de forma assincrona (sem block na interface e sem confirmação de êxito)


            } else {
                Toast.makeText(this, "Número fora do limite especificado!", Toast.LENGTH_LONG)
                    .show()
            }


        } else {
            Toast.makeText(this, "Este campo não pode ficar vazio!", Toast.LENGTH_LONG).show()
        }
    }

}