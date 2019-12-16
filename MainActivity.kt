package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val calculator = Calculator()
    val saveSlot1 = SaveSlot()
    val saveSlot2 = SaveSlot()
    val saveSlot3 = SaveSlot()
    val saveSlot4 = SaveSlot()
    val saveSlot5 = SaveSlot()
    val saveSlot6 = SaveSlot()
    val saveSlot7 = SaveSlot()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        refreshValue()

        one.setOnClickListener { numberPressed("1")}
        two.setOnClickListener { numberPressed("2")}
        three.setOnClickListener { numberPressed("3")}
        four.setOnClickListener { numberPressed("4")}
        five.setOnClickListener { numberPressed("5")}
        six.setOnClickListener { numberPressed("6")}
        seven.setOnClickListener { numberPressed("7")}
        eight.setOnClickListener { numberPressed("8")}
        nine.setOnClickListener { numberPressed("9")}
        zero.setOnClickListener { numberPressed("0")}
        clear.setOnClickListener{ clearAllPressed()}
        clearEntry.setOnClickListener { clearEntryPressed() }
        equals.setOnClickListener{ equalsPressed()}
        decimal.setOnClickListener { decimalPressed() }
        negative.setOnClickListener{ negativePressed()}
        add.setOnClickListener { twoNumberOp("add")}
        sub.setOnClickListener { twoNumberOp("sub") }
        mult.setOnClickListener { twoNumberOp("mult") }
        div.setOnClickListener { twoNumberOp("div") }
        power.setOnClickListener { twoNumberOp("power") }
        sin.setOnClickListener { oneNumberOp("sin") }
        cos.setOnClickListener { oneNumberOp("cos") }
        tan.setOnClickListener { oneNumberOp("tan") }
        asin.setOnClickListener { oneNumberOp("asin") }
        acos.setOnClickListener { oneNumberOp("acos") }
        atan.setOnClickListener { oneNumberOp("atan") }
        oneOver.setOnClickListener { oneNumberOp("oneOver") }
        sqrt.setOnClickListener { oneNumberOp("sqrt") }
        save1.setOnClickListener { savePressed(saveSlot1) }
        save2.setOnClickListener { savePressed(saveSlot2) }
        save3.setOnClickListener { savePressed(saveSlot3) }
        save4.setOnClickListener { savePressed(saveSlot4) }
        save5.setOnClickListener { savePressed(saveSlot5) }
        save6.setOnClickListener { savePressed(saveSlot6) }
        save7.setOnClickListener { savePressed(saveSlot7) }
    }

    fun refreshValue(){
        numberDisplay.text = calculator.getFormattedCurrentValue()
    }

    fun clearEntryPressed(){
        calculator.clearEntry()
        refreshValue()
    }

    fun clearAllPressed(){
        calculator.clear()
        refreshValue()
    }

    fun numberPressed(num: String) {
        calculator.appendNumber(num)
        refreshValue()
    }

    fun decimalPressed(){
        calculator.appendDecimal()
        refreshValue()
    }

    fun negativePressed(){
        calculator.appendNegative()
        refreshValue()
    }

    fun equalsPressed() {
        calculator.execute()
        refreshValue()
    }

    fun savePressed(slot: SaveSlot){
        if (!slot.isFull()){
            when (slot) {
                saveSlot1 -> saveSlot1.setValue(calculator.getCurrentValue())
                saveSlot2 -> saveSlot2.setValue(calculator.getCurrentValue())
                saveSlot3 -> saveSlot3.setValue(calculator.getCurrentValue())
                saveSlot4 -> saveSlot4.setValue(calculator.getCurrentValue())
                saveSlot5 -> saveSlot5.setValue(calculator.getCurrentValue())
                saveSlot6 -> saveSlot6.setValue(calculator.getCurrentValue())
                saveSlot7 -> saveSlot7.setValue(calculator.getCurrentValue())
            }
         } else {
            when (slot) {
                saveSlot1 -> calculator.setCurrentValue(saveSlot1.getValue())
                saveSlot2 -> calculator.setCurrentValue(saveSlot2.getValue())
                saveSlot3 -> calculator.setCurrentValue(saveSlot3.getValue())
                saveSlot4 -> calculator.setCurrentValue(saveSlot4.getValue())
                saveSlot5 -> calculator.setCurrentValue(saveSlot5.getValue())
                saveSlot6 -> calculator.setCurrentValue(saveSlot6.getValue())
                saveSlot7 -> calculator.setCurrentValue(saveSlot7.getValue())
            }
            refreshValue()
        }
    }

    fun twoNumberOp (op: String){
        if (calculator.isOperationInProgress()){
            calculator.execute()
            refreshValue()
        }
        when (op) {
            "add" -> calculator.setCurrentOperation(Calculator.Operation.Add(calculator.getCurrentValue().toDouble()))
            "sub" -> calculator.setCurrentOperation(Calculator.Operation.Subtract(calculator.getCurrentValue().toDouble()))
            "mult" -> calculator.setCurrentOperation(Calculator.Operation.Multiply(calculator.getCurrentValue().toDouble()))
            "div" -> calculator.setCurrentOperation(Calculator.Operation.Divide(calculator.getCurrentValue().toDouble()))
            "power" -> calculator.setCurrentOperation(Calculator.Operation.Power(calculator.getCurrentValue().toDouble()))
        }
        calculator.clearEntry()
        calculator.setOperationInProgress(true)
        //refreshValue()
    }

    fun oneNumberOp(op: String){
        when (op) {
            "sin" -> calculator.setCurrentOperation(Calculator.Operation.ArcSin)
            "cos" -> calculator.setCurrentOperation(Calculator.Operation.ArcCos)
            "tan" -> calculator.setCurrentOperation(Calculator.Operation.ArcTan)
            "asin" -> calculator.setCurrentOperation(Calculator.Operation.ArcSin)
            "acos" -> calculator.setCurrentOperation(Calculator.Operation.ArcCos)
            "atan" -> calculator.setCurrentOperation(Calculator.Operation.ArcTan)
            "oneOver" -> calculator.setCurrentOperation(Calculator.Operation.OneOver)
            "sqrt" -> calculator.setCurrentOperation(Calculator.Operation.Sqrt)
        }
        calculator.execute()
        refreshValue()
    }
}
