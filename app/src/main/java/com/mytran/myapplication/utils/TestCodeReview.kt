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
    }
}
