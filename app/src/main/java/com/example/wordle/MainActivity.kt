package com.example.wordle

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private var wordToGuess : String = FourLetterWordList.FourLetterWordList.getRandomFourLetterWord()
    var numOfGuesses = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val editText = findViewById<EditText>(R.id.editText)
        val guesses = findViewById<TextView>(R.id.guess)
        val answer = findViewById<TextView>(R.id.answer)
        val submitBtn = findViewById<Button>(R.id.submitBtn)
        val resetBtn = findViewById<Button>(R.id.resetBtn)


        submitBtn.setOnClickListener {
            numOfGuesses++
            if( numOfGuesses > 2){
                    submitBtn.visibility = View.INVISIBLE
                    resetBtn.visibility = View.VISIBLE
                    answer.text = wordToGuess.toString()
                    answer.visibility = View.VISIBLE
                    editText.visibility = View.INVISIBLE
            }

                val check = checkGuess(editText.text.toString().uppercase())
                val guess = concat("Guess #$numOfGuesses" ,editText.text.toString())
                val checkGuess = concat("\n Guess #$numOfGuesses Check","$check\n")
                val value = concat(guess.toString(), checkGuess.toString())
                guesses.text = concat(guesses.text.toString(), value.toString())
                guesses.visibility = View.VISIBLE

                if(check.equals("OOOO")){
                    val toast = Toast.makeText(applicationContext, "YOU DID IT!!", Toast.LENGTH_SHORT)
                    toast.setGravity(Gravity.CENTER,0,0)
                    toast.show()
                    editText.visibility = View.INVISIBLE
                    submitBtn.visibility = View.INVISIBLE
                    resetBtn.visibility = View.VISIBLE
                }
                editText.text.clear()
            }


            resetBtn.setOnClickListener {
                numOfGuesses=0
                wordToGuess = FourLetterWordList.FourLetterWordList.getRandomFourLetterWord()
                guesses.text = ""
                editText.visibility=View.VISIBLE
                submitBtn.visibility=View.VISIBLE
                guesses.visibility=View.INVISIBLE
                answer.visibility = View.INVISIBLE
                resetBtn.visibility=View.INVISIBLE
            }

    }


    private fun checkGuess(guess: String) : String {
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            }
            else if (guess[i] in wordToGuess) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }

    fun concat (str1: String, str2: String): String{
        return "$str1 $str2"
    }
}



