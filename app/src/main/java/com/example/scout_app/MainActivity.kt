package com.example.scout_app

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.scout_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.buttonAutoRemoveAmp.setOnClickListener {
            decreaseNumber(binding.editAutoAmp)
        }
        binding.buttonAutoAddAmp.setOnClickListener {
            addNumber(binding.editAutoAmp)
        }

        binding.buttonAutoRemoveSpeaker.setOnClickListener {
            decreaseNumber(binding.editAutoSpeaker)
        }
        binding.buttonAutoAddSpeaker.setOnClickListener {
            addNumber(binding.editAutoSpeaker)
        }

        binding.buttonTeleopRemoveAmp.setOnClickListener {
            decreaseNumber(binding.editTeleopAmp)
        }
        binding.buttonTeleopAddAmp.setOnClickListener {
            addNumber(binding.editTeleopAmp)
        }

        binding.buttonTeleopRemoveSpeaker.setOnClickListener {
            decreaseNumber(binding.editTeleopSpeaker)
        }
        binding.buttonTeleopAddSpeaker.setOnClickListener {
            addNumber(binding.editTeleopSpeaker)
        }
    }

    private fun decreaseNumber(editText: EditText) {
        var valor = editText.text.toString()
        if (valor == "") {valor = "0"}

        if (valor != "0") {
            editText.setText("${valor.toInt()-1}")
        }
    }
    private fun addNumber(editText: EditText) {
        var valor = editText.text.toString()
        if (valor == "") {valor = "0"}

        editText.setText("${valor.toInt()+1}")

        //Toast.makeText(applicationContext, "valor adicionado", Toast.LENGTH_SHORT).show()

    }
}