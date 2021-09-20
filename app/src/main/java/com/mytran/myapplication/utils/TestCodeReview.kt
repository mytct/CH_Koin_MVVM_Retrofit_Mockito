package com.mytran.myapplication.utils

import java.io.IOException

class OutOfOrder {
    companion object {
        const val IMPORTANT_VALUE = 3
    }

    fun returnX(): Int {
        return x
    }

    private val x = 2
}
class TestCodeReview {
    override fun toString(): String {
        throw IllegalStateException() // exception should not be thrown here
    }
    fun foo() {
        try {
            // ... do some I/O
        } catch(e: IOException) {
            if (e is NoSuchMethodException || (e as NoSuchMethodException) != null) { }
        }
        (1..10).forEach {
            println(it)
        }
        (1 until 10).forEach {
            println(it)
        }
        (10 downTo 1).forEach {
            println(it)
        }
    }
    var myList = mutableListOf(1,2,3)
    var mySet = mutableSetOf(1,2,3)
    var myMap = mutableMapOf("answer" to 42)
    fun fooA(a: Any?) {
        val x: String? = a as String? // If 'a' is not String, ClassCastException will be thrown.
    }
}
