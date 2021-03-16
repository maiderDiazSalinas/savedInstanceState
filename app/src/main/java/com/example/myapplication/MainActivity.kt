package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var fragment=supportFragmentManager.findFragmentByTag("first")
        if(fragment!=null) supportFragmentManager.beginTransaction().remove(fragment).commit()
        fragment=supportFragmentManager.findFragmentByTag("second")
        if(fragment!=null) supportFragmentManager.beginTransaction().remove(fragment).commit()
        fragment=supportFragmentManager.findFragmentByTag("blank")
        if(fragment!=null) supportFragmentManager.beginTransaction().remove(fragment).commit()
        fragment=supportFragmentManager.findFragmentByTag("second_apaisado")
        if(fragment!=null) supportFragmentManager.beginTransaction().remove(fragment).commit()


        supportFragmentManager.beginTransaction().add(R.id.main_fragmentos,FirstFragment(),"first").commit()
        if(findViewById<FrameLayout>(R.id.main_apaisado)!=null)
            supportFragmentManager.beginTransaction().add(R.id.main_apaisado,BlankFragment(),"blank").commit()
    }

    fun delPrimeroAlSegundo(){
        if(findViewById<FrameLayout>(R.id.main_apaisado)!=null) {
            var fragment: Fragment? = supportFragmentManager.findFragmentByTag("second_apaisado")
            if (fragment == null) fragment = SecondFragment()
            supportFragmentManager.beginTransaction().replace(R.id.main_apaisado, fragment, "second_apaisado").addToBackStack(null).commit()
        }
        else{
            var fragment:Fragment?=supportFragmentManager.findFragmentByTag("second")
            if (fragment==null) fragment = SecondFragment()
            supportFragmentManager.beginTransaction().replace(R.id.main_fragmentos,fragment,"second").addToBackStack(null).commit()
        }
    }

    fun delSegundoAlPrimero(){
        if(findViewById<FrameLayout>(R.id.main_apaisado)!=null){
            var fragment:Fragment?=supportFragmentManager.findFragmentByTag("blank")
            if (fragment==null) fragment=BlankFragment()
            supportFragmentManager.beginTransaction().replace(R.id.main_apaisado,fragment,"blank").addToBackStack(null).commit()
        }
        else{
            var fragment:Fragment?=supportFragmentManager.findFragmentByTag("first")
            if (fragment==null) fragment=FirstFragment()
            supportFragmentManager.beginTransaction().replace(R.id.main_fragmentos,fragment,"first").addToBackStack(null).commit()
        }
    }
}