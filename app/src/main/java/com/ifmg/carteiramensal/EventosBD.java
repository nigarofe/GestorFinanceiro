package com.ifmg.carteiramensal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EventosBD extends SQLiteOpenHelper {

    private Context contexto;

    public EventosBD(Context cont) {
        super(cont, "evento", null, 1);
        contexto = cont;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String sql = "CREATE TABLE IF NOT EXISTS evento(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TXT, valor REAL, imagem TEXT, dataocorreu DATE, datacadastro DATE, datavalida DATE)";

        db.execSQL(sql);
    }

    public void insereEvento(String nome, int valor) {
        try (SQLiteDatabase db = this.getWritableDatabase()) {
            String sql = "INSERT INTO evento (nome, valor) VALUES(" + nome + "," + valor + ")";
        } catch (SecurityException ex) {
            ex.printStackTrace();
        }
    }

    public void atualizaEvento() {
        try (SQLiteDatabase db = this.getWritableDatabase()) {

        } catch (SecurityException ex) {

        }
    }

    public void buscaEventos() {
        try (SQLiteDatabase db = this.getWritableDatabase()) {

        } catch (SecurityException ex) {

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
