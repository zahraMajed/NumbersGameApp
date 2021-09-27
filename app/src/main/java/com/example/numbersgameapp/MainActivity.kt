package com.example.numbersgameapp

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

// ask the user to guess a number between 0 and 10 ----------------
// generate a random number between 0 and 10
// take user input in the Edit Text field
// log user input and display whether or not the guess was correct
// only allow the user to enter numbers

class MainActivity : AppCompatActivity() {
    lateinit var NumField: EditText //etGuessdNum
    lateinit var butGuess: Button //butGuess

    var guessCount = 4
    var randomNum = Random.nextInt(11)///
    var msgArray: ArrayList<String> = arrayListOf()///


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NumField=findViewById(R.id.etGuessdNum)
        butGuess=findViewById(R.id.butGuess)

        rv_main.adapter = RecyclerAdapter(msgArray)
        rv_main.layoutManager = LinearLayoutManager(this)

        butGuess.setOnClickListener { showStatus() }//end clicl listener
    }//end onCreate

    private fun showStatus(){
        val userGuess = NumField.text.toString()
        if (userGuess.isNotEmpty()) {
            if (guessCount > 0) {
                if (userGuess.toInt() == randomNum) {
                    display()
                    showAlertDialog("You win! Play again?")
                } else {
                    guessCount--
                    msgArray.add("You guessed $userGuess")
                    msgArray.add("you have $guessCount left")
                }
            }
            if (guessCount == 0) {
                display()
                msgArray.add("You lose, the correct answer was $randomNum . \n\n Game over!")
                showAlertDialog("You lose!, The correct answer was $randomNum. Play again?")
            }
            NumField.text.clear()
            NumField.clearFocus()
            rv_main.adapter?.notifyDataSetChanged()
        } else {
            Snackbar.make(findViewById<ConstraintLayout>(R.id.CL),
                "please enter a number",
                Snackbar.LENGTH_LONG).show()
        }
    }
    private fun display() {
        butGuess.isEnabled = false
        butGuess.isClickable = false
        NumField.isEnabled = false
        NumField.isClickable = false
    }

    private fun showAlertDialog(msg:String){

        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setMessage(msg)
            .setCancelable(false)
            .setPositiveButton("yes",DialogInterface.OnClickListener{
                    dialog, id -> this.recreate() }
            )//setPostiveButton
            .setNegativeButton("No",DialogInterface.OnClickListener{
                    dialog, id -> dialog.cancel()}
            )//setNigativeButton

        //create dailog box
        val alert =dialogBuilder.create()
        //
        alert.setTitle("Game over!")
        alert.show()


    }
}
