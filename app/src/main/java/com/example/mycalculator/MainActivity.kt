package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var lastnumeric : Boolean =false
    var lastdot : Boolean =false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun onDigit(view: View){
    val textview1=findViewById<TextView>(R.id.text1)
        textview1.append((view as Button).text)
        lastnumeric=true

    }
    fun onClear(view: View){
        val textview1=findViewById<TextView>(R.id.text1)
        textview1.text=""
        lastnumeric=false
        lastdot=false
    }
    fun onDecimal(view: View){
        if(lastnumeric&&!lastdot)
        {
            val textview1=findViewById<TextView>(R.id.text1)
            textview1.append(".")
            lastnumeric=false
            lastdot=false
        }
    }
    fun onEqual(view: View){
        if(lastnumeric){
            val textview1=findViewById<TextView>(R.id.text1)
            var a=textview1.text.toString()
            var prefix=""
            if(a.startsWith("-"))
            {
                prefix="-"
                a=a.substring(1)
            }
            if(a.contains("-"))
            {
                val splitValue =a.split("-")
                var one =splitValue[0]
                var two =splitValue[1]
                if(!prefix.isEmpty())
                {
                    one =prefix+one
                }
                textview1.text=removezero((one.toDouble()-two.toDouble()).toString())
            }
            if(a.contains("+"))
            {
                val splitValue =a.split("+")
                var one =splitValue[0]
                var two =splitValue[1]
                textview1.text=removezero((one.toDouble()+two.toDouble()).toString())
            }
            if(a.contains("*"))
            {
                val splitValue =a.split("*")
                var one =splitValue[0]
                var two =splitValue[1]
                textview1.text=removezero((one.toDouble()*two.toDouble()).toString())
            }
            if(a.contains("/"))
            {
                val splitValue =a.split("/")
                var one =splitValue[0]
                var two =splitValue[1]
                textview1.text=removezero((one.toDouble()/two.toDouble()).toString())
            }

        }
    }
    fun onOperator(view: View){
        val textview1=findViewById<TextView>(R.id.text1)
        if(lastnumeric&&!isOperatorAdded(textview1.text.toString()))
        {
            textview1.append((view as Button).text)
            lastnumeric=false
        }
    }
    private fun removezero(result: String):String{
        var value=result
        if(result.contains(".0"))
        {
            value=result.substring(0,result.length-2)
        }
        return value
    }
   private fun isOperatorAdded(value: String):Boolean{
       return if (value.startsWith("-"))
       {
           false
       }else{
           value.contains("/")||value.contains("*")||value.contains("+")||value.contains("-")

       }
   }
}