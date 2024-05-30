package com.example.greenmarket.ui.altro.termini_condizioni

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TermCondViewModel : ViewModel() {

    private val _text = MutableLiveData(arrayOf<String>()).apply {
        value = arrayOf("Benvenuti su GreenMarket! Prima di utilizzare i nostri servizi, vi preghiamo di leggere attentamente i seguenti Termini e Condizioni. Utilizzando l'applicazione GreenMarket, accettate di essere vincolati dai seguenti termini e condizioni:\n" +
                "\n" +
                "Account Utente\n" +
                "Per utilizzare determinate funzionalità dell'app GreenMarket, potrebbe essere necessario creare un account utente. Siete responsabili della sicurezza delle vostre credenziali di accesso e dell'uso del vostro account.\n" +
                "\n" +
                "Acquisti Online\n" +
                "L'app GreenMarket offre la possibilità di effettuare acquisti online tramite il proprio dispositivo mobile. Tutti gli acquisti sono soggetti alle condizioni di vendita e di consegna indicate nel sito web associato.\n" +
                "\n" +
                "Lista della Spesa\n" +
                "L'app GreenMarket consente agli utenti di creare e gestire liste della spesa personalizzate. Gli utenti sono responsabili dell'accuratezza delle informazioni inserite nelle liste della spesa e dell'utilizzo responsabile delle stesse.\n" +
                "\n" +
                "Codici Sconto\n" +
                "Periodicamente, GreenMarket potrebbe offrire codici sconto agli utenti per promuovere offerte speciali e sconti. I codici sconto sono soggetti a termini e condizioni specifici e possono essere utilizzati solo nelle circostanze indicate.\n" +
                "\n" +
                "Uso Responsabile\n" +
                "Gli utenti sono tenuti a utilizzare l'app GreenMarket in modo responsabile e nel rispetto delle leggi e dei regolamenti applicabili. L'abuso dell'app o dei suoi servizi potrebbe comportare la sospensione o la revoca dell'account utente.\n" +
                "\n" +
                "Limitazione di Responsabilità\n" +
                "GreenMarket non è responsabile per eventuali danni o perdite derivanti dall'uso dell'app o dall'incapacità di utilizzarla, incluse interruzioni del servizio, errori o omissioni nei contenuti, o danni causati da terze parti.\n" +
                "\n" +
                "Modifiche ai Termini e Condizioni\n" +
                "GreenMarket si riserva il diritto di modificare o aggiornare questi Termini e Condizioni in qualsiasi momento senza preavviso. Si consiglia agli utenti di rivedere periodicamente i Termini e Condizioni per essere al corrente di eventuali modifiche.\n" +
                "\n" +
                "Contatti\n" +
                "Per domande o commenti sui presenti Termini e Condizioni, vi preghiamo di contattarci all'indirizzo email [indirizzo email] o tramite il nostro servizio di assistenza clienti.\n" +
                "Accettando questi Termini e Condizioni, confermate di aver letto, compreso e accettato di essere vincolati da tutte le disposizioni qui contenute.\n" +
                "" +
                "Ultimo aggiornamento: Maggio 2024")
    }
    val text: LiveData<Array<String>> = _text

}