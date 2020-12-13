package com.ifmg.carteiramensal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Calendar;
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

            db.insert("evento", null, valores);
        } catch (SecurityException ex) {
            ex.printStackTrace();
        }
    }

    public void atualizaEvento() {
        try (SQLiteDatabase db = this.getWritableDatabase()) {

        } catch (SecurityException ex) {

        }
    }

    public ArrayList<Evento> buscaEventos(int operacao, Calendar data) {
        System.out.println("goiaba");
        ArrayList<Evento> resultado = new ArrayList<>();
        // 26/11/2020
        // 01/11/2020 - 30/11/2020
        Calendar primeiroDia = Calendar.getInstance();
        primeiroDia.setTime(data.getTime());
        primeiroDia.set(Calendar.DAY_OF_MONTH, 1);

        Calendar ultimoDia = Calendar.getInstance();
        ultimoDia.setTime(data.getTime());
        ultimoDia.set(Calendar.DAY_OF_MONTH, ultimoDia.getActualMaximum(Calendar.DAY_OF_MONTH));


//        String sql = "SELECT * FROM evento";

        String sql = "Select * from evento WHERE " +
                "dataocorreu <= " + ultimoDia.getTime().getTime() + " " +
                "AND dataocorreu >= " + primeiroDia.getTime().getTime() + " " +
                "AND valor ";

        if (operacao == 0) {
            // Entradas
            sql += " >= 0";
        } else {
            // Saídas
            sql += " < 0";
        }

        try (SQLiteDatabase db = this.getWritableDatabase()) {
            Cursor tuplas = db.rawQuery(sql, null);
            if (tuplas.moveToFirst()) {
                do {
                    int id = tuplas.getInt(0);
                    String nome = tuplas.getString(1);
                    double valor = tuplas.getDouble(2);
                    if (valor < 0) {
                        valor *= -1;
                    }
                    String urlFoto = tuplas.getString(3);
                    Date dataOcorreu = new Date(tuplas.getLong(4));
                    Date dataCadastro = new Date(tuplas.getLong(5));
                    Date dataValida = new Date(tuplas.getLong(6));

                    Evento temporario = new Evento(id, nome, valor, dataCadastro, dataValida, dataOcorreu, urlFoto);
                    resultado.add(temporario);
                } while (tuplas.moveToNext());
            }
        } catch (SQLException ex) {
            System.err.println("Erro no EventosDB -> BuscaEventos     aipuwb2o834y");
            ex.printStackTrace();
        }

        return resultado;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
