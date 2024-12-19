package com.example.practica_trones_pm_o_di_nose

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import android.preference.PreferenceManager
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practica_trones_pm_o_di_nose.databinding.ActivityCalculatronBinding

class Calculatron : AppCompatActivity() {

    private lateinit var bind : ActivityCalculatronBinding
    private lateinit var shared : SharedPreferences
    private lateinit var terminar : Intent

    private var cuentaAtras : Int = 0
    private var tiempo : Long = 0
    private var min : Int = 0
    private var max : Int = 0
    private var suma : Boolean = true
    private var resta : Boolean = true
    private var multiplicacion : Boolean = true
    private var animacion : String = ""

    private lateinit var opcionesoperador : MutableList<Int>
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
        bind = ActivityCalculatronBinding.inflate(layoutInflater)
        setContentView(bind.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        shared = getSharedPreferences("ajustes", MODE_PRIVATE)

        bind.cuentaatras.text = cuentaAtras.toString()

        min = shared.getInt("minimo", 0)
        max = shared.getInt("maximo", 10)
        cuentaAtras = shared.getInt("cuentaatras", 20)
        tiempo = (cuentaAtras * 1000).toLong()
        suma = shared.getBoolean("suma", true)
        resta = shared.getBoolean("resta", true)
        multiplicacion = shared.getBoolean("multiplicacion", false)
        animacion = shared.getString("animacion", "Sin animacion")!!
        
        terminar = Intent(this, ResultadosCalculatron::class.java)

        opcionesoperador = mutableListOf()

        if (suma) opcionesoperador.add(0)
        if (resta) opcionesoperador.add(1)
        if (multiplicacion) opcionesoperador.add(2)

        generarCuenta()
        cuentaAString()
        pasarOperacion()

        generarCuenta()
        cuentaAString()
        actualizarDatos()

        object : CountDownTimer(tiempo, 1000) {
            override fun onTick(p0: Long) {
                bind.cuentaatras.text = "$cuentaAtras"
                cuentaAtras--
            }

            override fun onFinish() {
                terminar.putExtra("aciertos", aciertos)
                terminar.putExtra("fallos", fallos)
                startActivity(terminar)
            }

        }.start()

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
                textoAux += texto[it]
            }

            bind.input.text = textoAux
        }
        bind.menos.setOnClickListener {
            if (!bind.input.text.isNullOrBlank()){
                if (bind.input.text[0] != '-'){
                    bind.input.setText("-${bind.input.text}")
                }
            }
        }
        bind.botonIgual.setOnClickListener {
            pasarTurno()
        }
    }

    fun generarCuenta(){

        numero1siguiente = (min..max).random()
        numero2siguiente = (min..max).random()
        operadorsiguiente = opcionesoperador.random()
    }

    fun cuentaAString(){

        var linea = ""

        linea += "$numero1siguiente "

        when(operadorsiguiente){
            0 -> linea += "+ "
            1 -> linea += "- "
            2 -> linea += "* "
        }

        linea += "$numero2siguiente = "

        cuentaSiguiente = linea
    }

    fun pasarTurno(){
        var correcto : Boolean = true

        if (!bind.input.text.isNullOrBlank()){

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

            pasarOperacion()
            generarCuenta()
            cuentaAString()
            actualizarDatos()

            bind.input.setText("")
        }
    }

    fun pasarOperacion(){
        cuentaPasada = cuentaActual
        cuentaActual = cuentaSiguiente

        numero1 = numero1siguiente
        numero2 = numero2siguiente
        operador = operadorsiguiente
    }

    fun actualizarDatos(){
        bind.cuentapasada.setText(cuentaPasada)
        bind.cuentaactual.setText(cuentaActual)
        bind.cuentasiguiente.setText(cuentaSiguiente)

        bind.aciertos.setText("Aciertos: $aciertos")
        bind.fallos.setText("Fallos: $fallos")
    }
}