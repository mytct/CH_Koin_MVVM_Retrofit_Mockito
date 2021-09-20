package com.mytran.myapplication.utils

class TestCodeReview {
    override fun toString(): String {
        throw IllegalStateException() // exception should not be thrown here
    }
}
