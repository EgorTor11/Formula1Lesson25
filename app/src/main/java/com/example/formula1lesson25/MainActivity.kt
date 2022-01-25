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
    val animator = TranslateAnimation(0f, 1200f,
        0f, -1200f)
    var rand1: Long = 0
    var rand2: Long = 0
    var rand3: Long = 0
    lateinit var buttonStart: Button
    lateinit var cvadr: LinearLayout
    lateinit var cLayout: ConstraintLayout
    lateinit var button2: Button
    lateinit var btnZapCvadr: Button
    lateinit var btnSbrosCvadr: Button
    lateinit var imageView: ImageView
    lateinit var imageView2: ImageView
    lateinit var imageView3: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonStart = findViewById(R.id.buttonStart)
        button2 = findViewById(R.id.button2)
        btnZapCvadr = findViewById(R.id.btnZapCvadr)
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

        btnZapCvadr.setOnClickListener {

            val animator =
                ValueAnimator.ofFloat(0f, cLayout.x + cLayout.width - cvadr.x - cvadr.width).apply {
                    duration = 5000
                    addUpdateListener { animation ->
                        cvadr.translationX = animation.animatedValue as Float
                        cvadr.translationY = -((animation.animatedValue) as Float)
                        if (cvadr.x == 1017.0f) {
                            Toast.makeText(this@MainActivity, "Приехали", Toast.LENGTH_LONG).show()
                        }
                    }
                    start()
                }
        }
        var valueX = 0f
        var valueY = 0f
        cvadr.setOnClickListener {
            scope.launch {
                while (cvadr.x < 1017.0f) {
                    delay(50)
                    valueX = valueX + 10f
                    valueY = valueY + 10f
                    withContext(Dispatchers.Main) {
                        cvadr.translationX = valueX
                        cvadr.translationY = -valueY
                    }
                }
                while (cvadr.y > 0f) {
                    delay(50)
                    valueX = valueX - 10f
                    valueY = valueY + 10f
                    withContext(Dispatchers.Main) {
                        cvadr.translationX = valueX
                        cvadr.translationY = -valueY
                    }
                }
                while (cvadr.x > 0f) {
                    delay(50)
                    valueX = valueX - 10f
                    valueY = valueY - 10f
                    withContext(Dispatchers.Main) {
                        cvadr.translationX = valueX
                        cvadr.translationY = -valueY
                    }
                }

            }
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