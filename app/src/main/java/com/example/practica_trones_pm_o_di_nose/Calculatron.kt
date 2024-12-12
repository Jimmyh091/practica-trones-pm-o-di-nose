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


    }
}