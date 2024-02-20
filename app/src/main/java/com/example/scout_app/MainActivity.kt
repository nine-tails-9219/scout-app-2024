package com.example.scout_app

import android.os.Bundle
import android.text.Editable
import android.widget.CheckBox
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
        val community = binding.checkboxCommunity

        val teleoperadoSpeaker = binding.editTeleopSpeaker.text
        val teleoperadoAmp = binding.editTeleopAmp.text

        val park = binding.checkboxPark
        val onStage = binding.checkboxOnStage
        val noteInTrap = binding.checkboxNoteTrap
        val harmony = binding.checkboxHarmony


        val totalAutonomo = calcularTotalAutonomo(autonomoSpeaker, autonomoAmp, community)
        val totalTeleoperado = calcularTotalTeleoperado(teleoperadoSpeaker, teleoperadoAmp)
        val totalEndGame = calcularTotalEndGame(park, onStage, noteInTrap, harmony)

        val url = "https://script.google.com/macros/s/AKfycbzkGgE-_yjcXJXdUwPqQp-0a5OkFZ_Gzoro2q9orDmwqsAf8SjQCd63VqoPVPqY79YGew/exec"
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
                params["community"] = community.isChecked.toString()

                params["teleoperadoSpeaker"] = teleoperadoSpeaker.toString()
                params["teleoperadoAmp"] = teleoperadoAmp.toString()

                params["park"] = park.isChecked.toString()
                params["onStage"] = onStage.isChecked.toString()
                params["noteInTrap"] = noteInTrap.isChecked.toString()
                params["harmony"] = harmony.isChecked.toString()

                params["totalAutonomo"] = totalAutonomo.toString()
                params["totalTeleoperado"] = totalTeleoperado.toString()
                params["totalEndGame"] = totalEndGame.toString()
                params["totalGeral"] = (totalAutonomo + totalTeleoperado + totalEndGame).toString()

                return params
            }
        }
        val queue = Volley.newRequestQueue(applicationContext)
        queue.add(stringRequest)

    }

    private fun calcularTotalAutonomo(autonomoSpeaker: Editable?, autonomoAmp: Editable?, community: CheckBox): Int {
        if (community.isChecked) {
            return (autonomoSpeaker.toString().toInt() * 5) + (autonomoAmp.toString().toInt() * 2) + 2
        } else {
            return (autonomoSpeaker.toString().toInt() * 5) + (autonomoAmp.toString().toInt() * 2)
        }
    }

    private fun calcularTotalTeleoperado(teleoperadoSpeaker: Editable?, teleoperadoAmp: Editable?): Int {
        return (teleoperadoSpeaker.toString().toInt() * 3) + (teleoperadoAmp.toString().toInt())
    }

    private fun calcularTotalEndGame(park: CheckBox, onStage: CheckBox, noteInTrap: CheckBox, harmony: CheckBox): Int {
        var total = 0
        if (park.isChecked) {
            total += 1
        }
        if (onStage.isChecked) {
            total += 3
        }
        if (noteInTrap.isChecked) {
            total += 5
        }
        if (harmony.isChecked) {
            total += 2
        }
        return total
    }

    //region Funções para interagir com a tela

    private fun decreaseNumber(editText: EditText) {
        if (editText.text.isNotEmpty() && editText.text.toString().toInt() > 0) {
            editText.setText((editText.text.toString().toInt()-1).toString())
        }
    }

    private fun addNumber(editText: EditText) {
        editText.setText((editText.text.toString().toInt()+1).toString())
    }
    //endregion
    
    
    

}