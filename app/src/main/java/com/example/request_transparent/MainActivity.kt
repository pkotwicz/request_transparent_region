package com.example.request_transparent

import android.graphics.Color
import android.widget.FrameLayout
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var layout1 = FrameLayout(this)
        layout1.setBackgroundColor(Color.BLUE)

        var layout2 = FrameLayout(this)
        layout2.setBackgroundColor(Color.GREEN)

        var layout3 = FrameLayout(this)
        layout3.setBackgroundColor(Color.LTGRAY)

        var container = FrameLayout(this)
        container.addView(layout1)
        container.addView(layout2)
        container.addView(layout3)
        setContentView(container)

        Handler().postDelayed(object:Runnable{
            override fun run() {
                var i = getWindow().getAttributes().flags and  WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED
                android.util.Log.e("ABCD", "Flags " + i)
                container.requestTransparentRegion(layout2)
                layout2.invalidate()
                layout3.invalidate()
                container.invalidate()
            }
        }, 2000)
        Handler().postDelayed(object:Runnable{
            override fun run() {
                container.removeView(layout3)
            }
        }, 4000)
    }

    override fun onAttachedToWindow() {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
    }
}
