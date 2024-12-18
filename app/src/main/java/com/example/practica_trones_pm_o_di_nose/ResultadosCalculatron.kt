package com.example.practica_trones_pm_o_di_nose

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practica_trones_pm_o_di_nose.databinding.ActivityCalculatronBinding
import com.example.practica_trones_pm_o_di_nose.databinding.ActivityResultadosCalculatronBinding

class ResultadosCalculatron : AppCompatActivity() {

    private lateinit var bind : ActivityResultadosCalculatronBinding

    private var aciertos : Int = 0
    private var fallos : Int = 0
    private var porcentaje : Int = 0

    private var aciertostotales : Int = 0
    private var fallostotales : Int = 0
    private var porcentajetotal : Int = 0
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        bind = ActivityResultadosCalculatronBinding.inflate(layoutInflater)
        setContentView(bind.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        aciertos = 0
        fallos = 0
        porcentaje = 0

        aciertostotales = 0
        fallostotales = 0
        porcentajetotal = 0

        bind.volver.setOnClickListener {
            var volverintent = Intent(this, Menu::class.java)
            startActivity(volverintent)
        }
        bind.jugar.setOnClickListener {
            var jugarintent = Intent(this, Calculatron::class.java)
            startActivity(jugarintent)
        }
    }
    override fun onResume() {
        super.onResume()
        recopilarDatos()
        actualizar()
    }

    fun recopilarDatos(){
        aciertos = intent.getIntExtra("aciertos", 0)
        fallos = intent.getIntExtra("fallos", 0)

        porcentaje = if ((aciertos + fallos) == 0) 0 else (aciertos * 100) / (aciertos + fallos)

        aciertostotales += aciertos
        fallostotales += fallos

        porcentajetotal = if ((aciertostotales + fallostotales) == 0) 0 else (aciertostotales * 100) / (aciertostotales + fallostotales)
    }

    fun actualizar(){
        bind.aciertos.setText("Aciertos: $aciertos")
        bind.fallos.setText("Fallos: $fallos")
        bind.porcentaje.setText("Porcentaje: $porcentaje%")
        bind.aciertostotales.setText("Aciertos totales: $aciertostotales")
        bind.fallostotales.setText("Fallos totales: $fallostotales")
        bind.porcentajetotal.setText("Porcentaje total: $porcentajetotal%")
    }
}