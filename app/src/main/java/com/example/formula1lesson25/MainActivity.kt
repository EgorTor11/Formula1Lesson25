package com.example.formula1lesson25

import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.TranslateAnimation
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.coroutines.*
import kotlin.concurrent.thread
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private val scope = CoroutineScope(Dispatchers.IO)
    private lateinit var job: Job
    val animator = TranslateAnimation(0f, 1200f,
        0f, -1200f)
    var rand1: Long = 0
    var rand2: Long = 0
    var rand3: Long = 0
    lateinit var buttonStart: Button
    lateinit var cvadr: LinearLayout
    lateinit var cLayout: ConstraintLayout
    lateinit var button2: Button


    lateinit var btnSbrosCvadr: Button
    lateinit var imageView: ImageView
    lateinit var imageView2: ImageView
    lateinit var imageView3: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonStart = findViewById(R.id.buttonStart)
        button2 = findViewById(R.id.button2)

        btnSbrosCvadr = findViewById(R.id.btnSbrosCvadr)

        imageView = findViewById(R.id.imageView)
        imageView2 = findViewById(R.id.imageView2)
        imageView3 = findViewById(R.id.imageView3)
        cvadr = findViewById(R.id.cvadr)
        cLayout = findViewById(R.id.cLayout)

        buttonStart.setOnClickListener {
            rand1 = (1000..8000).random().toLong()
            rand2 = (1000..8000).random().toLong()
            rand3 = (1000..8000).random().toLong()
            imageView.startAnimation(TranslateAnimation(0f, 0f,
                0f, -1200f)
                .apply {
                    duration = rand1
                    fillAfter = true
                })
            imageView2.startAnimation(TranslateAnimation(0f, 0f,
                0f, -1200f)
                .apply {
                    duration = rand2
                    fillAfter = true
                })
            imageView3.startAnimation(TranslateAnimation(0f, 0f,
                0f, -1200f)
                .apply {
                    duration = rand3
                    fillAfter = true
                })



            if (rand1 < rand2 && rand1 < rand3) {
                Run.after(rand1) {
                    Toast.makeText(this,
                        "Победил первый Самолет, для формулы1 - это чудо, я считаю",
                        Toast.LENGTH_LONG).show()
                }
            } else if (rand1 > rand2 && rand2 < rand3) {
                Run.after(rand2) {
                    Toast.makeText(this,
                        "Победил второй Самолет, для формулы1 - это чудо, я считаю",
                        Toast.LENGTH_LONG).show()
                }
            } else if (rand1 > rand3 && rand2 > rand3) {
                Run.after(rand3) {
                    Toast.makeText(this,
                        "Победил третий Самолет, для формулы1 - это чудо, я считаю",
                        Toast.LENGTH_LONG).show()
                }
            }
        }
        button2.setOnClickListener {

            imageView.clearAnimation()
            imageView2.clearAnimation()
            imageView3.clearAnimation()

        }

        var bul = true
        var valueX = 0f
        var valueY = 0f
        cvadr.setOnClickListener {
            bul=true
            job = scope.launch {
                while (bul) {
                    if (cvadr.x<cLayout.width-cvadr.width){
                    while (cvadr.x < cLayout.width - cvadr.width) {
                        delay(50)
                        valueX = valueX + 10f
                        valueY = valueY + 10f
                        withContext(Dispatchers.Main) {
                            cvadr.translationX = valueX
                            cvadr.translationY = -valueY
                        }
                    }
                    }else{
                        while (cvadr.x > 0&&cvadr.y>cLayout.height-cvadr.height) {
                            delay(50)
                            valueX = valueX - 10f
                            valueY = valueY + 10f
                            withContext(Dispatchers.Main) {
                                cvadr.translationX = valueX
                                cvadr.translationY = -valueY
                            }
                        }
                    }

                    while (cvadr.y > 0f && cvadr.x > 0) {
                        delay(50)
                        valueX = valueX - 10f
                        valueY = valueY + 10f
                        withContext(Dispatchers.Main) {
                            cvadr.translationX = valueX
                            cvadr.translationY = -valueY
                        }
                    }
                    if (cvadr.x > 0f) {
                        while (cvadr.x > 0f) {
                            delay(50)
                            valueX = valueX - 10f
                            valueY = valueY - 10f
                            withContext(Dispatchers.Main) {
                                cvadr.translationX = valueX
                                cvadr.translationY = -valueY
                            }
                        }
                    } else {
                        while (cvadr.y > 0f && cvadr.x < cLayout.width) {
                            delay(50)
                            valueX = valueX + 10f
                            valueY = valueY + 10f
                            withContext(Dispatchers.Main) {
                                cvadr.translationX = valueX
                                cvadr.translationY = -valueY
                            }
                        }
                    }
                    while (cvadr.y < cLayout.height - cvadr.height && cvadr.x < cLayout.width - cvadr.height) {
                        delay(50)
                        valueX = valueX + 10f
                        valueY = valueY - 15f
                        withContext(Dispatchers.Main) {
                            cvadr.translationX = valueX
                            cvadr.translationY = -valueY
                        }

                    }

                }
            }


        }
        btnSbrosCvadr.setOnClickListener {
            job.cancel()
            bul=false
            cvadr.translationX = 0f
            cvadr.translationY = 0f
            valueX=0f
            valueY=0f
        }


    }

    class Run {
        companion object {
            fun after(delay: Long, process: () -> Unit) {
                Handler().postDelayed({
                    process()
                }, delay)
            }
        }
    }
}