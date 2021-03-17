package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    var fragmento:Int?=1

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

        //cuando usamos la aplicacion para guardar datos
        //fragmento=(application as Aplicacion).fragmento
        //cuando usamos el savedInstanceState para guardar datos
        fragmento=savedInstanceState?.getInt("numFragment") ?:1

        if(fragmento==1){
            supportFragmentManager.beginTransaction().add(R.id.main_fragmentos,FirstFragment(),"first").commit()
            if(findViewById<FrameLayout>(R.id.main_apaisado)!=null)
                supportFragmentManager.beginTransaction().add(R.id.main_apaisado,BlankFragment(),"blank").commit()

        }
        else{
            if(findViewById<FrameLayout>(R.id.main_apaisado)!=null) {
                supportFragmentManager.beginTransaction().add(R.id.main_fragmentos, FirstFragment(), "first").commit()
                supportFragmentManager.beginTransaction().add(R.id.main_apaisado, SecondFragment(), "second_apaisado").commit()
            }
            else
                supportFragmentManager.beginTransaction().add(R.id.main_fragmentos,SecondFragment(),"second").commit()
        }

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
        fragmento=2
        //si queremos usar la variable de la aplicacion
        //(application as Aplicacion).fragmento=2
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
        fragmento=1
        //si queremos usar la variable de la aplicacion
        //(application as Aplicacion).fragmento=1
    }

    /*
    // Se llama a esta función si antes se ha usado onSaveInstanceState.
    //Se pueden recuperar los datos aqui o en el on Create
    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        fragmento=savedInstanceState?.getInt("numFragment") ?:1
    }
    */

    // se invoca cuando la actividad se destruye temporalmente y se usa para salvar los datos
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        fragmento?.let{outState.putInt("numFragment", it )}

    }
}