package com.mytran.myapplication.utils

import java.io.IOException

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
    fun fooA(a: Any?) {
        val x: String? = a as String? // If 'a' is not String, ClassCastException will be thrown.
    }
}
