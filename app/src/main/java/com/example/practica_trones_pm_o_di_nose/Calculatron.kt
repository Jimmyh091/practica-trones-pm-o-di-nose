package com.example.practica_trones_pm_o_di_nose

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practica_trones_pm_o_di_nose.databinding.ActivityCalculatronBinding

class Calculatron : AppCompatActivity() {

    private lateinit var bind : ActivityCalculatronBinding
    private lateinit var terminar : Intent

    private var cuentaAtras : Int = resources.getInteger(R.integer.cuentaatras)
    private var tiempo : Long = (cuentaAtras * 1000).toLong()
    private var max : Int = resources.getInteger(R.integer.maximo)
    private var min : Int = resources.getInteger(R.integer.minimo)
    private var suma : Boolean = resources.getBoolean(R.bool.suma)
    private var resta : Boolean = resources.getBoolean(R.bool.resta)
    private var multiplicacion : Boolean = resources.getBoolean(R.bool.multiplicacion)
    private var animacion : Boolean = resources.getBoolean(R.bool.animacion)

    private var aciertostotales : Int = 0
    private var fallostotales : Int = 0
    private var aciertos : Int = 0
    private var fallos : Int = 0

    private var cuentaPasada : String = ""
    private var cuentaActual : String = ""
    private var cuentaSiguiente : String = ""

    private var numero1 : Int = 0
    private var numero2 : Int = 0
    private var operador : Int = 0
    private var numero1siguiente : Int = 0
    private var numero2siguiente : Int = 0
    private var operadorsiguiente : Int = 0

    private var resultado : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(bind.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        terminar = Intent(this, ResultadosCalculatron::class.java)

        object : CountDownTimer(cuentaAtras * 1000L, tiempo) {
            override fun onTick(p0: Long) {
                cuentaAtras--
            }

            override fun onFinish() {
                terminar.putExtra("aciertos", aciertos)
                terminar.putExtra("fallos", fallos)
                startActivity(terminar)
            }

        }

        bind.boton0.setOnClickListener {
            bind.input.setText("${bind.input.text}0")
        }
        bind.boton1.setOnClickListener {
            bind.input.setText("${bind.input.text}1")
        }
        bind.boton2.setOnClickListener {
            bind.input.setText("${bind.input.text}2")
        }
        bind.boton3.setOnClickListener {
            bind.input.setText("${bind.input.text}3")
        }
        bind.boton4.setOnClickListener {
            bind.input.setText("${bind.input.text}4")
        }
        bind.boton5.setOnClickListener {
            bind.input.setText("${bind.input.text}5")
        }
        bind.boton6.setOnClickListener {
            bind.input.setText("${bind.input.text}6")
        }
        bind.boton7.setOnClickListener {
            bind.input.setText("${bind.input.text}7")
        }
        bind.boton8.setOnClickListener {
            bind.input.setText("${bind.input.text}8")
        }
        bind.boton9.setOnClickListener {
            bind.input.setText("${bind.input.text}9")
        }
        bind.botonC.setOnClickListener {
            bind.input.setText("")
        }
        bind.botonCE.setOnClickListener {
            var texto = bind.input.text

            var textoAux = ""

            for (it in 0 until texto.length - 1){
                textoAux += it
            }

            bind.input.text = textoAux
        }
        bind.menos.setOnClickListener {
            bind.input.setText("${bind.input.text}-")
        }
        bind.botonIgual.setOnClickListener {
            pasarTurno()
        }
    }

    fun generarCuenta(){

        numero1siguiente = (min..max).random()
        numero2siguiente = (min..max).random()
        operadorsiguiente = (0..2).random()
    }

    fun cuentaAString(){

        var linea = ""

        linea += "$numero1siguiente "

        when(operadorsiguiente){
            0 -> linea += "+ "
            1 -> linea += "- "
            2 -> linea += "* "
        }

        linea += "$numero2siguiente ="

        cuentaSiguiente = linea
    }

    fun pasarTurno(){
        var correcto : Boolean = true

        resultado = bind.input.text.toString().toInt()

        when(operador){
            0 -> correcto = resultado == numero1 + numero2
            1 -> correcto = resultado == numero1 - numero2
            2 -> correcto = resultado == numero1 * numero2
        }

        if(correcto){
            aciertos++
            bind.correccion.setImageResource(R.drawable.correcto)
        }else{
            fallos++
            bind.correccion.setImageResource(R.drawable.incorrecto)
        }

        cuentaPasada = cuentaActual
        cuentaActual = cuentaSiguiente
        cuentaAString()

        numero1 = numero1siguiente
        numero2 = numero2siguiente
        operador = operadorsiguiente
    }
}