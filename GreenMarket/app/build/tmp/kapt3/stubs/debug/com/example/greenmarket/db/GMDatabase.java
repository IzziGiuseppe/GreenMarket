package com.example.greenmarket.db;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\fH&J\b\u0010\r\u001a\u00020\u000eH&J\b\u0010\u000f\u001a\u00020\u0010H&J\b\u0010\u0011\u001a\u00020\u0012H&\u00a8\u0006\u0014"}, d2 = {"Lcom/example/greenmarket/db/GMDatabase;", "Landroidx/room/RoomDatabase;", "()V", "CodiceScontoDao", "Lcom/example/greenmarket/db/dao/CodiceScontoDao;", "ComposizioneScontriniDao", "Lcom/example/greenmarket/db/dao/ComposizioneScontriniDao;", "ProdottiInRicetteDao", "Lcom/example/greenmarket/db/dao/ProdottiInRicetteDao;", "ProdottoDao", "Lcom/example/greenmarket/db/dao/ProdottoDao;", "RicettaDao", "Lcom/example/greenmarket/db/dao/RicettaDao;", "ScontrinoDao", "Lcom/example/greenmarket/db/dao/ScontrinoDao;", "TesseraAPuntiDao", "Lcom/example/greenmarket/db/dao/TesseraAPuntiDao;", "UtenteDao", "Lcom/example/greenmarket/db/dao/UtenteDao;", "Companion", "app_debug"})
@androidx.room.Database(entities = {com.example.greenmarket.db.model.Utente.class, com.example.greenmarket.db.model.TesseraAPunti.class, com.example.greenmarket.db.model.Scontrino.class, com.example.greenmarket.db.model.Ricetta.class, com.example.greenmarket.db.model.Prodotto.class, com.example.greenmarket.db.model.ProdottiInRicette.class, com.example.greenmarket.db.model.ComposizioneScontrini.class, com.example.greenmarket.db.model.CodiceSconto.class}, version = 3)
public abstract class GMDatabase extends androidx.room.RoomDatabase {
    @kotlin.jvm.Volatile
    @org.jetbrains.annotations.Nullable
    private static volatile com.example.greenmarket.db.GMDatabase INSTANCE;
    @org.jetbrains.annotations.NotNull
    public static final com.example.greenmarket.db.GMDatabase.Companion Companion = null;
    
    public GMDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.greenmarket.db.dao.UtenteDao UtenteDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.greenmarket.db.dao.TesseraAPuntiDao TesseraAPuntiDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.greenmarket.db.dao.ScontrinoDao ScontrinoDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.greenmarket.db.dao.RicettaDao RicettaDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.greenmarket.db.dao.ProdottoDao ProdottoDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.greenmarket.db.dao.ProdottiInRicetteDao ProdottiInRicetteDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.greenmarket.db.dao.ComposizioneScontriniDao ComposizioneScontriniDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.greenmarket.db.dao.CodiceScontoDao CodiceScontoDao();
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/example/greenmarket/db/GMDatabase$Companion;", "", "()V", "INSTANCE", "Lcom/example/greenmarket/db/GMDatabase;", "getInstance", "context", "Landroid/content/Context;", "populateDatabase", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.example.greenmarket.db.GMDatabase getInstance(@org.jetbrains.annotations.NotNull
        android.content.Context context) {
            return null;
        }
        
        public final void populateDatabase(@org.jetbrains.annotations.NotNull
        android.content.Context context) {
        }
    }
}