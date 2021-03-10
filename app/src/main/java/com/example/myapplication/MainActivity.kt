package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().add(R.id.main_fragmentos,FirstFragment(),"first").commit()
        if(findViewById<FrameLayout>(R.id.main_apaisado)!=null)
            supportFragmentManager.beginTransaction().add(R.id.main_apaisado,BlankFragment(),"blank").commit()
    }

    fun delPrimeroAlSegundo(){
        var fragment=supportFragmentManager.findFragmentByTag("second")
        if (fragment==null) fragment=SecondFragment()
        if(findViewById<FrameLayout>(R.id.main_apaisado)!=null)
            supportFragmentManager.beginTransaction().replace(R.id.main_apaisado,fragment,"second").addToBackStack(null).commit()
        else
            supportFragmentManager.beginTransaction().replace(R.id.main_fragmentos,fragment,"second").addToBackStack(null).commit()
    }

    fun delSegundoAlPrimero(){
        if(findViewById<FrameLayout>(R.id.main_apaisado)!=null){
            var fragment=supportFragmentManager.findFragmentByTag("blank")
            if (fragment==null) fragment=BlankFragment()
            supportFragmentManager.beginTransaction().replace(R.id.main_apaisado,fragment,"blank").addToBackStack(null).commit()
        }
        else{
            var fragment=supportFragmentManager.findFragmentByTag("first")
            if (fragment==null) fragment=FirstFragment()
            supportFragmentManager.beginTransaction().replace(R.id.main_apaisado,fragment,"first").addToBackStack(null).commit()
        }
    }
}