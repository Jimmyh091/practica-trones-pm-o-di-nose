package com.example.practica_trones_pm_o_di_nose

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practica_trones_pm_o_di_nose.databinding.ActivityMenuBinding

class Menu : AppCompatActivity() {

    private lateinit var bind : ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bind = ActivityMenuBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(bind.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        bind.cartastron.setOnClickListener {
            val intent = Intent(this, Cartastron::class.java)
            startActivity(intent)
        }

        bind.calculatron.setOnClickListener {
            val intent = Intent(this, Calculatron::class.java)
            startActivity(intent)
        }
    }
}