package com.example.guntingbatukertas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var guntingPlayer: ImageView
    lateinit var batuPlayer: ImageView
    lateinit var kertasPlayer: ImageView
    lateinit var guntingCom: ImageView
    lateinit var batuCom: ImageView
    lateinit var kertasCom: ImageView

    lateinit var pemain1: String
    lateinit var com: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        guntingPlayer = findViewById(R.id.guntingPlayer)
        batuPlayer = findViewById(R.id.batuPlayer)
        kertasPlayer = findViewById(R.id.kertasPlayer)



        guntingPlayer.setOnClickListener {
            pemain1 = "gunting"
            Log.d(MainActivity::class.java.simpleName, "Pemain 1 : " +pemain1)
            com = computerPlay()
            decideWinner(pemain1, com)
        }
        batuPlayer.setOnClickListener {
            pemain1 = "batu"
            Log.d(MainActivity::class.java.simpleName, "Pemain 1 : " + pemain1)
            com = computerPlay()
            decideWinner(pemain1, com)
        }
        kertasPlayer.setOnClickListener {
            pemain1 = "kertas"
            Log.d(MainActivity::class.java.simpleName, "Pemain 1 : " + pemain1)
            com = computerPlay()
            decideWinner(pemain1, com)
        }
    }


    private fun computerPlay(): String {
        var selection: Array<String> = arrayOf("gunting", "batu", "kertas")

        var randomIndex = Random.nextInt(0, 2)
        Log.d(MainActivity::class.java.simpleName, "com : "+ selection[randomIndex] )
        return selection[randomIndex]
    }

    private fun decideWinner(pemain1: String, com: String): String {
        var winner = ""
        var playerWin = "Pemain 1 MENANG"
        var comWin = "Pemain 2 MENANG"
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

        return winner
    }
}