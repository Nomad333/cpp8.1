package com.nomad.kpp8

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.transition.Visibility
import com.nomad.kpp8.databinding.ActivityMainBinding
import java.util.concurrent.ThreadLocalRandom

class MainActivity : AppCompatActivity() {
    private var secretNumber = 0
    private var userAttempts = 0
    private var _mainBinding : ActivityMainBinding? = null
    private val mainBinding
        get() = _mainBinding ?: throw IllegalStateException("_mainBinding is null")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainBinding.includeSetup.btnStart.setOnClickListener {
            val editTextMin = mainBinding.includeSetup.etRangeMin
            val editTextMax = mainBinding.includeSetup.etRangeMax
            val min = editTextMin.text.toString().toInt()
            val max = editTextMax.text.toString().toInt()

            mainBinding.tvWrong.isGone = true
            mainBinding.tvSuccess.isGone = true
            resetAttempts()
            setNewSecretNumber(min, max)
            lockSetupFragment()
            unlockGuessFragment()
        }

        mainBinding.includeGuess.btnRandom.setOnClickListener {
            val editTextMin = mainBinding.includeSetup.etRangeMin
            val editTextMax = mainBinding.includeSetup.etRangeMax
            val editTextInput = mainBinding.includeGuess.etGuessInput
            val min = editTextMin.text.toString().toInt()
            val max = editTextMax.text.toString().toInt()

            editTextInput.setText(rndInt(min,max).toString())
        }

        mainBinding.includeGuess.btnSubmitGuess.setOnClickListener {
            checkUserNumber()
            incrementAttempts()
        }
    }

    private fun setNewSecretNumber(min: Int, max: Int){
        if (max <= min) {
            Log.e("RangeError", "$min => $max")
            return
        }
        secretNumber = rndInt(min,max)
    }

    private fun rndInt(min: Int, max: Int) : Int {
        return ThreadLocalRandom.current().nextInt(min,max)
    }

    private fun lockSetupFragment(){
        with(mainBinding.includeSetup){
            btnStart.isGone = true
            etRangeMin.isEnabled = false
            etRangeMax.isEnabled = false
        }
    }

    private fun unlockSetupFragment(){
        with(mainBinding.includeSetup){
            btnStart.isGone = false
            etRangeMin.isEnabled = true
            etRangeMax.isEnabled = true
        }
    }


    private fun lockGuessFragment(){
        with(mainBinding.includeGuess){
            root.isGone = true
            btnSubmitGuess.isGone = true
            btnRandom.isGone = true
            etGuessInput.isEnabled = false
        }
    }

    private fun unlockGuessFragment(){
        with(mainBinding.includeGuess){
            root.isGone = false
            btnSubmitGuess.isGone = false
            btnRandom.isGone = false
            etGuessInput.isEnabled = true
        }
    }

    private fun incrementAttempts() {
        userAttempts++
        mainBinding.includeGuess.tvAttempts.text ="Attempts left: " + userAttempts.toString()
    }
    private fun resetAttempts() {
        userAttempts = 0
        mainBinding.includeGuess.tvAttempts.text ="Attempts left: " + userAttempts.toString()
    }

    private fun checkUserNumber(){
        val x = mainBinding.includeGuess.etGuessInput.text.toString().toIntOrNull()?:-1

        when (x) {
            secretNumber -> {
                mainBinding.tvWrong.isGone = true
                mainBinding.tvSuccess.isGone = false

                unlockSetupFragment()

                mainBinding.includeGuess.btnSubmitGuess.isGone = true
                mainBinding.includeGuess.btnRandom.isGone = true
                mainBinding.includeGuess.etGuessInput.isEnabled = false
            }
            else -> {
                mainBinding.tvWrong.isGone = false
                mainBinding.tvSuccess.isGone = true
            }
        }
    }
}