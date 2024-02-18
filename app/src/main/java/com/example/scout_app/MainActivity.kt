package com.example.scout_app

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.scout_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    //private val url: String = "https://script.google.com/macros/s/AKfycbzJxXokP3FDnbTHX0h6VxO7-JNXQiezFZekZP_8Zi8z2YJgWEGLU5A2WeGBtrOlpTK0/exec"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //region Botões para adicionar dados

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
        //endregion

        binding.buttonEnviarDados.setOnClickListener {
            enviarDados()
        }
    }

    private fun enviarDados() {
        val numeroDaPartida = binding.editNumeroPartida.text.toString()
        val numeroDoTime = binding.editNumeroTime.text.toString()

        val autonomoSpeaker = binding.editAutoSpeaker.text
        val autonomoAmp = binding.editAutoAmp.text
        val community = binding.checkboxCommunity.isActivated.toString()

        val teleoperadoSpeaker = binding.editTeleopSpeaker.text
        val teleoperadoAmp = binding.editTeleopAmp.text

        val park = binding.checkboxPark.isActivated.toString()
        val onStage = binding.checkboxOnStage.isActivated.toString()
        val noteInTrap = binding.checkboxNoteTrap.isActivated.toString()
        val harmony = binding.checkboxHarmony.isActivated.toString()

        val url = "https://script.google.com/macros/s/AKfycbwB61WAIdgdBI7229RqQRKDNNUR50Tn_SVwfXjsZebxRAkmZRoh4KRh70mcRE26cl9YNQ/exec"
        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            Response.Listener {
                Toast.makeText(applicationContext, it.toString(), Toast.LENGTH_SHORT).show()
            },
            Response.ErrorListener {
                Toast.makeText(applicationContext, it.toString(), Toast.LENGTH_SHORT).show()
            }){
            override fun getParams(): MutableMap<String, String>? {
                val params = HashMap<String, String>()

                params["numeroDaPartida"] = numeroDaPartida
                params["numeroDoTime"] = numeroDoTime

                params["autonomoSpeaker"] = autonomoSpeaker.toString()
                params["autonomoAmp"] = autonomoAmp.toString()
                params["community"] = community

                params["teleoperadoSpeaker"] = teleoperadoSpeaker.toString()
                params["teleoperadoAmp"] = teleoperadoAmp.toString()

                params["park"] = park
                params["onStage"] = onStage
                params["noteInTrap"] = noteInTrap
                params["harmony"] = harmony

                return params
            }
        }
        val queue = Volley.newRequestQueue(applicationContext)
        queue.add(stringRequest)

    }

    //region Funções para interagir com a tela

    private fun decreaseNumber(editText: EditText) {
        if (editText.text.isNotEmpty() || editText.text.toString() != "0") {
            editText.setText((editText.text.toString().toInt()-1).toString())
        }
    }

    private fun addNumber(editText: EditText) {
        editText.setText((editText.text.toString().toInt()+1).toString())
    }
    //endregion
    
    
    

}