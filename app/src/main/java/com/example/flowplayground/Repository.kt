package com.example.flowplayground

import kotlinx.coroutines.delay
import kotlin.random.Random

class Repository() {

    suspend fun apiCall(): String {

        delay(500)
        val success = Random.nextBoolean()

        if (!success) {
            throw Exception("Fehler bei String API Call")
        }

        return listOf("A", "B", "C", "D", "E").random()
    }

    suspend fun apiCallNumber(): Int {

        delay(500)
        val success = Random.nextBoolean()

        if (!success) {
            throw Exception("Fehler bei Number API Call")
        }

        return Random.nextInt(1, 10)
    }


}