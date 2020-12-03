package com.ifmg.carteiramensal;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

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

    public void insereEvento(Evento novoEvento) {
        try (SQLiteDatabase db = this.getWritableDatabase()) {
            ContentValues valores = new ContentValues();
            valores.put("nome", novoEvento.getNome());
            valores.put("valor", novoEvento.getValor());
            valores.put("imagem", novoEvento.getCaminhoFoto());
            valores.put("dataocorreu", novoEvento.getOcorreu().getTime());
            valores.put("datacadastro", new Date().getTime());
            valores.put("datavalida", novoEvento.getValida().getTime());
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
