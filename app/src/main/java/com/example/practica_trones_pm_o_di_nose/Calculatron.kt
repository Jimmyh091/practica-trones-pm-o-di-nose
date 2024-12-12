package com.example.practica_trones_pm_o_di_nose

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practica_trones_pm_o_di_nose.databinding.ActivityCalculatronBinding

class Calculatron : AppCompatActivity() {

    private lateinit var bind : ActivityCalculatronBinding

    private var max : Int = resources.getInteger(R.integer.maximo)
    private var min : Int = resources.getInteger(R.integer.minimo)
    private var cuentaAtras : Int = resources.getInteger(R.integer.cuentaatras)
    private var suma : Boolean = resources.getBoolean(R.bool.suma)
    private var resta : Boolean = resources.getBoolean(R.bool.resta)
    private var multiplicacion : Boolean = resources.getBoolean(R.bool.multiplicacion)

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

        bind.boton0.setOnClickListener {
            bind.input.setText("0")
        }
        bind.boton1.setOnClickListener {
            bind.input.setText("1")
        }
        bind.boton2.setOnClickListener {
            bind.input.setText("2")
        }
        bind.boton3.setOnClickListener {
            bind.input.setText("3")
        }
        bind.boton4.setOnClickListener {
            bind.input.setText("4")
        }
        bind.boton5.setOnClickListener {
            bind.input.setText("5")
        }
        bind.boton6.setOnClickListener {
            bind.input.setText("6")
        }
        bind.boton7.setOnClickListener {
            bind.input.setText("7")
        }
        bind.boton8.setOnClickListener {
            bind.input.setText("8")
        }
        bind.boton9.setOnClickListener {
            bind.input.text="9"
        }
        bind.botonC.setOnClickListener {
            bind.input.text = ""
        }
        bind.botonCE.setOnClickListener {
            bind.input.text = ""
        }
        bind.menos.setOnClickListener {
            bind.input.setText("-")
        }
        bind.botonIgual.setOnClickListener {
            pasarTurno()
            generarCuenta()
        }

    }

    fun generarCuenta() : List<Int> {

        numero1 = (min..max).random()
        numero2 = (min..max).random()
        operador = (0..2).random()

        return listOf(numero1, numero2, operador)
    }

    fun pasarTurno(){
        var correcto : Boolean = true

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
    }
}