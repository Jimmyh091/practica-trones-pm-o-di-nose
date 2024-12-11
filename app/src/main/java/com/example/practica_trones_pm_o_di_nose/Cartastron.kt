package com.example.practica_trones_pm_o_di_nose

import android.content.Context
import android.media.Image
import android.os.Bundle
import android.os.CountDownTimer
import android.renderscript.ScriptGroup.Binding
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.delay

class Cartastron : AppCompatActivity() {

    private var vidas = 3
    private lateinit var textVidas : TextView

    private lateinit var reset : Button
    private lateinit var cartas : MutableList<ImageView>
    private lateinit var casillas : MutableList<Int>

    private var parejas = mutableListOf(0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6)

    private var primerClick = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cartastron)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        textVidas = findViewById(R.id.vidas)

        reset = findViewById(R.id.reset)

        reset.setOnClickListener { recreate() }

        cartas = mutableListOf()
        casillas = mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)

        parejas.shuffle()

        for (it in 0 until 12){

            cartas.add((findViewById(resources.getIdentifier("carta${it + 1}", "id", packageName))))

            val num = it
            cartas[it].setOnClickListener{
                casillas[num] = 2

                casillas[num] = 2

                when (parejas[num]) {
                    0 -> cartas[num].setImageResource(R.drawable.cartatomate)
                    1 -> cartas[num].setImageResource(R.drawable.cartapera)
                    2 -> cartas[num].setImageResource(R.drawable.cartalimon)
                    3 -> cartas[num].setImageResource(R.drawable.cartacereza)
                    4 -> cartas[num].setImageResource(R.drawable.cartanaranja)
                    5 -> cartas[num].setImageResource(R.drawable.cartanaranjasilla)
                }

                cartas[num].isEnabled = false

                if (primerClick){
                    primerClick = false
                }else{
                    primerClick = true

                    var cartasIntentadas = comprobarVueltas()

                    if (parejas[cartasIntentadas[0]] == parejas[cartasIntentadas[1]]){

                        casillas[cartasIntentadas[0]] = 1
                        casillas[cartasIntentadas[1]] = 1

                    }else{

                        object : CountDownTimer(1000, 1000){

                            override fun onTick(millisUntilFinished: Long) {

                            }

                            override fun onFinish() {
                                Log.v("erssssfdssgfdsgre", "fddrdrtfhgtfv")
                                casillas[cartasIntentadas[0]] = 0
                                casillas[cartasIntentadas[1]] = 0
                            }

                        }.start()

                        vidas--

                        darVueltas(cartasIntentadas[0], cartasIntentadas[1])

                        cartas[cartasIntentadas[0]].isEnabled = true
                        cartas[cartasIntentadas[1]].isEnabled = true
                    }

                }

                if (comprobarFin()) {
                    ejecutarFin()
                }

                textVidas.text = "Vidas: $vidas"
            }

            casillas[it] = 0

        }

    }

    fun comprobarVueltas() : List<Int> {

        var num1 = -1
        var num2 = -1

        for (it in 0 until casillas.size){

            if (casillas[it] == 2){

                if (num1 == -1){
                    num1 = it
                }else{
                    num2 = it
                }

            }
        }

        return listOf(num1, num2)
    }

    fun darVueltas (carta1 : Int, carta2 : Int) {

        cartas[carta1].setImageResource(R.drawable.cartaabajo)
        cartas[carta2].setImageResource(R.drawable.cartaabajo)

    }

    fun comprobarFin() : Boolean{ //??? esta to mal hecho
        if (vidas == 0){
            textVidas.text = "Has perdido"
            return true
        }else{
            for (it in casillas){
                if (it == 0){
                    return false
                }
            }
        }
        textVidas.text = "Has ganado!!!!!!!!!!!!!!!"
        return true
    }

    fun ejecutarFin(){
        for (it in 0 until casillas.size){
            cartas[it].isEnabled = false
        }
    }
}