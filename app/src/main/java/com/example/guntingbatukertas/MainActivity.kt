package com.example.guntingbatukertas

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var guntingPlayer: ImageView
    lateinit var batuPlayer: ImageView
    lateinit var kertasPlayer: ImageView
    lateinit var guntingCom: ImageView
    lateinit var batuCom: ImageView
    lateinit var kertasCom: ImageView
    lateinit var refreshButton: ImageView
    lateinit var displayWinner: TextView
    lateinit var pemain1: String
    lateinit var com: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        guntingPlayer = findViewById(R.id.guntingPlayer)
        batuPlayer = findViewById(R.id.batuPlayer)
        kertasPlayer = findViewById(R.id.kertasPlayer)

        guntingCom = findViewById(R.id.guntingCom)
        batuCom = findViewById(R.id.batuCom)
        kertasCom = findViewById(R.id.kertasCom)
        displayWinner = findViewById(R.id.dynamicText)

        refreshButton = findViewById(R.id.refresh)

        resetDisplayCom()
        guntingPlayer.setOnClickListener {
            pemain1 = "gunting"
            Log.d(MainActivity::class.java.simpleName, "Pemain 1 : " +pemain1)
            displayPlayersPick(pemain1)
            com = computerPlay()
            decideWinner(pemain1, com)
        }
        batuPlayer.setOnClickListener {
            pemain1 = "batu"
            Log.d(MainActivity::class.java.simpleName, "Pemain 1 : " + pemain1)
            displayPlayersPick(pemain1)
            com = computerPlay()
            decideWinner(pemain1, com)
        }
        kertasPlayer.setOnClickListener {
            pemain1 = "kertas"
            Log.d(MainActivity::class.java.simpleName, "Pemain 1 : " + pemain1)
            displayPlayersPick(pemain1)
            com = computerPlay()
            decideWinner(pemain1, com)
        }

        refreshButton.setOnClickListener {
            resetGame()
        }

    }


    private fun computerPlay(): String {
        var selection: Array<String> = arrayOf("gunting", "batu", "kertas")

        var randomIndex = Random.nextInt(0, 3)
        var randomSelection = selection[randomIndex]
        Log.d(MainActivity::class.java.simpleName, "com : "+ randomSelection )
        resetDisplayCom()
        displayComputersPick(randomSelection)
        return randomSelection
    }

    private fun displayComputersPick(pick: String) {
        when(pick) {
            "gunting" -> guntingCom.setBackgroundColor(Color.parseColor("#6699cc"))
            "batu" -> batuCom.setBackgroundColor(Color.parseColor("#6699cc"))
            "kertas" -> kertasCom.setBackgroundColor(Color.parseColor("#6699cc"))
        }
    }

    private fun displayPlayersPick(pick: String) {
        resetDisplayPlayer()
        when(pick) {
            "gunting" -> guntingPlayer.setBackgroundColor(Color.parseColor("#6699cc"))
            "batu" -> batuPlayer.setBackgroundColor(Color.parseColor("#6699cc"))
            "kertas" -> kertasPlayer.setBackgroundColor(Color.parseColor("#6699cc"))
        }
    }



    private fun decideWinner(pemain1: String, com: String): String {
        var winner = ""
        var playerWin = "Pemain 1\nMENANG"
        var comWin = "Pemain 2\nMENANG"
        var seri = "DRAW"
        when(pemain1) {
            "gunting" -> {
                when(com) {
                    "gunting" -> winner = seri
                    "batu" -> winner = comWin
                    "kertas" -> winner = playerWin
                }
            }
            "batu" -> {
                when(com) {
                    "gunting" -> winner = playerWin
                    "batu" -> winner = seri
                    "kertas" -> winner = comWin
                }
            }
            "kertas" -> {
                when(com) {
                    "gunting" -> winner = comWin
                    "batu" -> winner = playerWin
                    "kertas" -> winner = seri
                }
            }
        }
        Log.d(MainActivity::class.java.simpleName, "Winner: " + winner)
        displayWinner.setText(winner)
        displayWinner.setBackgroundColor(Color.parseColor("#6699cc"))
        return winner
    }

    private fun resetDisplayCom() {
        guntingCom.setBackgroundColor(Color.parseColor("#ffffff"))
        batuCom.setBackgroundColor(Color.parseColor("#ffffff"))
        kertasCom.setBackgroundColor(Color.parseColor("#ffffff"))
    }

    private fun resetDisplayPlayer() {
        guntingPlayer.setBackgroundColor(Color.parseColor("#ffffff"))
        batuPlayer.setBackgroundColor(Color.parseColor("#ffffff"))
        kertasPlayer.setBackgroundColor(Color.parseColor("#ffffff"))
    }

    private fun resetGame() {
        resetDisplayPlayer()
        resetDisplayCom()
        displayWinner.setText("VS")
    }
}