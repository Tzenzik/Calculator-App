package com.example.calculator

class SaveSlot {
    private var value: String = "0"
    private var full: Boolean = false

    fun getValue() : String {
        return value
    }
    fun setValue(value: String) {
        this.value = value
        this.full = true
    }

    fun isFull() : Boolean {
        return full
    }

}