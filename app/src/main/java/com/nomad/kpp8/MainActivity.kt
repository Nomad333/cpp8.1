package com.nomad.kpp8

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true) // Оптимизирует переходы между фрагментами
                .replace(R.id.fragmentContainer, SetupFragment()) // Здесь указываем ваш класс
                .commit()
        }
    }
}