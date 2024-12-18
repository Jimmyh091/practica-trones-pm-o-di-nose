package com.example.practica_trones_pm_o_di_nose

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practica_trones_pm_o_di_nose.databinding.ActivityConfiguracionBinding

class Configuracion : AppCompatActivity() {

    private lateinit var bind : ActivityConfiguracionBinding
    private lateinit var shared : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        bind = ActivityConfiguracionBinding.inflate(layoutInflater)
        setContentView(bind.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        shared = getSharedPreferences("ajustes", MODE_PRIVATE)

        bind.guardar.setOnClickListener {

            var min = bind.tietmin.text.toString().toInt() >= 0
            var max = bind.tietmax.text.toString().toInt() > 0
            var cuentaAtras = bind.tietcuentaatras.text.toString().toInt() > 0

            if (min && max && cuentaAtras){
                guardarDatos()

                var intent = Intent(this, Menu::class.java)
                startActivity(intent)
            }
        }
    }

    fun guardarDatos(){
        with(shared.edit()){
            putInt("minimo", bind.tietmin.text.toString().toInt())
            putInt("maximo", bind.tietmax.text.toString().toInt())
            putInt("cuentaatras", bind.tietcuentaatras.text.toString().toInt())
            putBoolean("suma", bind.suma.isChecked)
            putBoolean("resta", bind.resta.isChecked)
            putBoolean("multiplicacion", bind.multiplicacion.isChecked)
            //putBoolean("animacion", bind.spinner.isEnabled)
            apply()
        }

        Log.v("shared", "Guardado minimo ${shared.getInt("minimo", 0)}}")
    }
}