package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Numbers
        tvOne.setOnClickListener{appendOnEkspresstion("1",true)}
        tvTwo.setOnClickListener{appendOnEkspresstion("2",true)}
        tvThree.setOnClickListener{appendOnEkspresstion("3",true)}
        tvFour.setOnClickListener{appendOnEkspresstion("4",true)}
        tvFive.setOnClickListener{appendOnEkspresstion("5",true)}
        tvSix.setOnClickListener{appendOnEkspresstion("6",true)}
        tvSeven.setOnClickListener{appendOnEkspresstion("7",true)}
        tvEight.setOnClickListener{appendOnEkspresstion("8",true)}
        tvNine.setOnClickListener{appendOnEkspresstion("9",true)}
        tvZero.setOnClickListener{appendOnEkspresstion("0",true)}
        tvDot.setOnClickListener{appendOnEkspresstion(".",true)}


        //Operations
        tvPlus.setOnClickListener{appendOnEkspresstion("+",false)}
        tvMinus.setOnClickListener{appendOnEkspresstion("-",false)}
        tvMul.setOnClickListener{appendOnEkspresstion("*",false)}
        tvDivide.setOnClickListener{appendOnEkspresstion("/",false)}
        tvOpen.setOnClickListener{appendOnEkspresstion("(",false)}
        tvClose.setOnClickListener{appendOnEkspresstion(")",false)}

        tvClear.setOnClickListener {
            tvExpression.text = ""
            tvResult.text = ""
        }

        tvBack.setOnClickListener{
            val string = tvExpression.text.toString()
            if (string.isNotEmpty()){
                tvExpression.text = string.substring(0,string.length-1)
            }
            tvResult.text = ""
        }

        tvEquals.setOnClickListener{
            try {

                val expression = ExpressionBuilder(tvExpression.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if (result == longResult.toDouble())
                    tvResult.text = longResult.toString()
                else
                    tvResult.text = result.toString()

            }catch (e:Exception){
                Log.d("Exception","message : " + e.message)
            }
        }
    }

    fun appendOnEkspresstion( string: String, canClear : Boolean){

        if(tvResult.text.isNotEmpty()){
            tvExpression.text = ""
        }

        if (canClear){
            tvResult.text = ""
            tvExpression.append(string)
        }else{
            tvExpression.append(tvResult.text)
            tvExpression.append(string)
            tvResult.text = ""
        }
    }
}