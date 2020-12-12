package com.ifmg.carteiramensal;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ItemListaEventos extends ArrayAdapter {

    Context contexto;
    ArrayList<Evento> dadosDosEventos;

    public ItemListaEventos(@NonNull Context contexto, ArrayList<Evento> dadosDosEventos) {
        super(contexto, resource);
        this.contexto = contexto;
        this.dadosDosEventos = dadosDosEventos;
    }

    private static class ViewHolder{
        private TextView nomeTxt;
        private TextView valorTxt;
        private TextView dataTxt;
        private TextView repeteTxt;
        private TextView fotoTxt;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}
