package com.example.practica_trones_pm_o_di_nose

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
    private var porcentaje : Float = 0f

    private var aciertostotales : Int = 0
    private var fallostotales : Int = 0
    private var porcentajetotal : Float = 0f
    
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
        actualizar()
    }
    override fun onResume() {
        super.onResume()
        actualizar()
    }

    fun recopilarDatos(){
        aciertos = intent.getIntExtra("aciertos", 0)
        fallos = intent.getIntExtra("fallos", 0)

        porcentaje = ((aciertos * aciertos + fallos) / 100).toFloat()

        aciertostotales += aciertos
        fallostotales += fallos

        porcentajetotal = ((aciertostotales * aciertostotales + fallostotales) / 100).toFloat()
    }

    fun actualizar(){
        bind.aciertos.setText("Aciertos: $aciertos")
        bind.fallos.setText("Fallos: $fallos")
        bind.porcentaje.setText("Porcentaje: $porcentaje")
        bind.aciertostotales.setText("Aciertos: $aciertostotales")
        bind.fallostotales.setText("Aciertos: $fallos")
        bind.porcentajetotal.setText("Aciertos: $porcentajetotal")
    }
}