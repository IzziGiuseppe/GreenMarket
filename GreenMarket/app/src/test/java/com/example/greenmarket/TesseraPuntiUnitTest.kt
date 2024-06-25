package com.example.greenmarket

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.math.BigDecimal
import java.math.RoundingMode

class TesseraPuntiUnitTest {

    private var saldoDisponibile: Float = 0.0f
    private var puntiDisponibili: Int = 0
    private lateinit var listaCodiciSconto: MutableList<String>

    @Before
    fun setUp() {
        saldoDisponibile = 123.45f
        puntiDisponibili = 13
        listaCodiciSconto = mutableListOf("abcde", "fghil", "mnopq")
    }

    @Test
    fun saldoToPuntiTest() {
        val nuoviPunti = puntiDisponibili.plus(saldoDisponibile.div(10).toInt())
        assertEquals(25, nuoviPunti)

        var nuovoSaldo = saldoDisponibile.rem(10f)
        nuovoSaldo = BigDecimal(nuovoSaldo.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toFloat()
        assertEquals(3.45f, nuovoSaldo)
    }

    @Test
    fun puntiToCSTest() {
        val csGenerati = puntiDisponibili.div(5)
        assertEquals(2, csGenerati)

        val nuoviPunti = puntiDisponibili.rem(5)
        assertEquals(3, nuoviPunti)

        val nuovaListaCS = mutableListOf<String>()
        for(i in 1..csGenerati){
            nuovaListaCS.add(generateRandomString())
        }
        for (i in listaCodiciSconto) {
            nuovaListaCS.add(i)
        }
        assertEquals(5, nuovaListaCS.size)

    }

    private fun generateRandomString(length: Int = 5): String {
        val chars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..length)
            .map { chars.random() }
            .joinToString("")
    }

}