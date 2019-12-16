package com.example.calculator

import kotlin.math.*

class Calculator {
    private var currentValue: String = "0"
    private var currentOperation: Operation = Operation.Add(0.00)
    private var operationCompleted: Boolean = true
    private var operationInProgress: Boolean = false
    private var decimalJustAdded: Boolean = false

    sealed class Operation {
        class Add(val value1: Double) : Operation()
        class Subtract(val value1: Double) : Operation()
        class Multiply(val value1: Double) : Operation()
        class Divide(val value1: Double) : Operation()
        class Power(val value1: Double) : Operation()
        object Sin : Operation()
        object Cos : Operation()
        object Tan : Operation()
        object ArcSin : Operation()
        object ArcCos : Operation()
        object ArcTan : Operation()
        object Sqrt : Operation()
        object OneOver : Operation()

    }

    private fun executeOperation(value2: Double, op: Operation) = when (op) {
        is Operation.Add -> currentValue = "${op.value1 + value2}"
        is Operation.Subtract -> currentValue = "${op.value1 - value2}"
        is Operation.Multiply -> currentValue = "${op.value1 * value2}"
        is Operation.Divide -> currentValue = "${op.value1 / value2}"
        is Operation.Power -> currentValue = "${op.value1.pow(value2)}"
        is Operation.Sin -> currentValue = "${sin(Math.toRadians(value2))}"
        is Operation.Cos -> currentValue = "${cos(Math.toRadians(value2))}"
        is Operation.Tan -> currentValue = "${tan(Math.toRadians(value2))}"
        is Operation.ArcSin -> currentValue = "${asin(Math.toRadians(value2))}"
        is Operation.ArcCos -> currentValue = "${acos(Math.toRadians(value2))}"
        is Operation.ArcTan -> currentValue = "${atan(Math.toRadians(value2))}"
        is Operation.Sqrt -> currentValue = "${sqrt(value2)}"
        is Operation.OneOver -> currentValue = "${1 / value2}"
    }

    fun execute(){
        executeOperation(currentValue.toDouble(), currentOperation)
        if (currentValue.toDouble() == currentValue.dropLast(2).toDouble()){     //this checks if the decimal is necessary
            currentValue = currentValue.dropLast(2)                              //and removes it if that is the case
        }
        operationCompleted = true
        operationInProgress = false
        currentOperation = Operation.Add(0.00)    //set it back to initial state

    }

    fun appendNumber(num: String){
        if (currentValue == "0" || operationCompleted){    //if the current value is empty or operation has been completed
            currentValue = num                                     //the current value is reset
            operationCompleted = false
        }
        else{
            currentValue = currentValue.plus(num)
        }
    }

    fun appendDecimal(){
        if (operationCompleted){
        }
        else if (!(currentValue.contains("."))){
            currentValue = currentValue.plus('.')
            decimalJustAdded = true
        }
    }

    fun appendNegative(){
        if (operationCompleted){
        }
        else if (currentValue.contains("-")){
            currentValue = currentValue.removePrefix("-")
        } else {
            currentValue = "-$currentValue"
        }

    }

    fun clear(){
        currentValue = "0"
        currentOperation = Operation.Add(0.00)
    }

    fun clearEntry() {
        currentValue = "0"
    }

    fun setCurrentOperation(op: Operation){
        currentOperation = op
    }

    fun getCurrentValue() : String {
        return currentValue
    }

    fun setCurrentValue(value: String) {
        this.currentValue = value
    }

    fun getFormattedCurrentValue() : String {
        var displayValue = currentValue
        if (decimalJustAdded) {
            displayValue = displayValue.removeSuffix(".")
            displayValue = ".$displayValue"
            decimalJustAdded = false
        }
        if (displayValue.toDouble() < 0){
            displayValue = displayValue.removePrefix("-")
            displayValue = "$displayValue-"
        }
        return displayValue
    }

    fun setOperationInProgress(bool: Boolean) {
        operationInProgress = bool
    }
    fun isOperationInProgress(): Boolean {
        return operationInProgress
    }
}