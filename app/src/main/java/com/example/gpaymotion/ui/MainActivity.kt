package com.example.gpaymotion.ui

import android.graphics.Typeface
import android.os.Bundle
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.content.ContextCompat
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.core.text.inSpans
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gpaymotion.R
import com.example.gpaymotion.data.HomeDataState
import com.example.gpaymotion.ui.viewmodel.MainActivityViewModel
import com.example.gpaymotion.databinding.ActivityMainBinding
import com.example.gpaymotion.makeStatusBarTransparent
import com.example.gpaymotion.px
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinApiExtension


@KoinApiExtension
class MainActivity : AppCompatActivity(){
    lateinit var binding: ActivityMainBinding
    lateinit var bgImage: View
    lateinit var scanBtn: View
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: GpayContactsAdapter
    lateinit var motionLayout: MotionLayout
    lateinit var backBtn: View
    lateinit var rewardTV: TextView
    val vieModel: MainActivityViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        makeStatusBarTransparent()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        recyclerView = binding.rv
        bgImage = binding.imageView2
        scanBtn = binding.button
        motionLayout = binding.motionLayout
        backBtn = binding.imageView4
        rewardTV = binding.textView5
        setContentView(view)
        vieModel.homeData.observe(this, Observer {
            when(it) {
                HomeDataState.Loading -> {

                }
                is HomeDataState.Success -> {
                    adapter =
                        GpayContactsAdapter(it.result)
                    recyclerView.layoutManager = LinearLayoutManager(this)
                    recyclerView.adapter = adapter
                }
                is HomeDataState.Error -> {

                }
            }
        })
        backBtn.setOnClickListener {
            motionLayout.transitionToStart()
        }
        rewardTV.text =  buildSpannedString {
            bold {
                inSpans(ForegroundColorSpan(ContextCompat.getColor(rewardTV.context,
                    R.color.black
                ))) {
                    inSpans(StyleSpan(Typeface.BOLD)) {
                       inSpans(AbsoluteSizeSpan(20.px)) {
                           append(" ₹ 1,233")
                       }
                    }
                }

                append(" Rewards earned")
            }
        }
    }

    fun fragmentClosed() {
        Toast.makeText(this, "Fragment closed", Toast.LENGTH_LONG).show()
    }
}