package com.example.greenmarket

import com.example.greenmarket.ui.lista_spesa.ProdottoInListaModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.math.BigDecimal
import java.math.RoundingMode

class ConfermaOrdineUnitTest {

    //aggiornaSaldo
    private var saldoTesseraPunti: Float = 0.0F
    private var prezzoScontato: Float = 0.0F

    //valoreSconto
    private var prezzoNoSconto: Float = 0.0f

    //listaProdotto
    private lateinit var mappaProdotti: Map<String, List<Float>>

    @Before
    fun setUp() {
        //aggiornaSaldo
        saldoTesseraPunti = 20.45f
        prezzoScontato = 8.19f

        //valoreSconto
        prezzoNoSconto = 49.67f

        //listaProdotti
        mappaProdotti = mapOf(
            "Mela" to listOf(2.0f, 7.0f, 14.0f),
            "Pera" to listOf(3.0f, 4.0f, 12.0f),
            "Banana" to listOf(5.0f, 6.0f, 30.0f)
        )
    }

    @Test
    fun aggiornaSaldoTest() {
        val nuovoSaldo = prezzoScontato.plus(saldoTesseraPunti)
        assertEquals(28.64f, nuovoSaldo)
    }

    @Test
    fun valoreScontoTest() {
        val valoreSconto = (prezzoNoSconto.times(5)).div(100)
        val valoreScontoArrotondato = BigDecimal(valoreSconto.toString()).setScale(2, RoundingMode.HALF_EVEN).toFloat()
        assertEquals(2.48f, valoreScontoArrotondato)

        val nuovoPrezzo = prezzoNoSconto.minus(valoreScontoArrotondato)
        assertEquals(47.19f, nuovoPrezzo)
    }

    @Test
    fun mapToListTest() {
        val listaProdotti = mutableListOf<ProdottoInListaModel>()
        mappaProdotti.forEach { (key, value) ->
            val prodotto =
                key.let {
                    ProdottoInListaModel(
                        it,
                        value[0],
                        value[1],
                        value[2]
                    )
                }
            listaProdotti.add(prodotto)
        }
        assertEquals(listaProdotti.size, mappaProdotti.size)
        for (i in 0..<listaProdotti.size) {
            assertEquals(listaProdotti[i].nome, mappaProdotti.keys.toList()[i])
            assertEquals(listaProdotti[i].quantita, mappaProdotti.values.toList()[i][0])
            assertEquals(listaProdotti[i].prezzo, mappaProdotti.values.toList()[i][1])
            assertEquals(listaProdotti[i].prezzoTotale, mappaProdotti.values.toList()[i][2])
        }
    }

}