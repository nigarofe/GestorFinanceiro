package com.ifmg.carteiramensal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ItemListaEventos extends ArrayAdapter {

    Context contexto;
    ArrayList<Evento> eventos;

    public ItemListaEventos(@NonNull Context contexto, ArrayList<Evento> eventos) {
        super(contexto, R.layout.item_lista_eventos, eventos);
        this.contexto = contexto;
        this.eventos = eventos;
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
    // position = índice do item na lista (parent)
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        Evento eventoAtual = eventos.get(position);
        ViewHolder novaView;
        final View resultado;

        // Se a lista está sendo montada pela primeira vez
        if (convertView == null){
            novaView = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView=  inflater.inflate(R.layout.item_lista_eventos, parent, false);

            novaView.nomeTxt = (TextView) convertView.findViewById(R.id.nomeItem);
            novaView.valorTxt = (TextView) convertView.findViewById(R.id.valorItem);
            novaView.dataTxt = (TextView) convertView.findViewById(R.id.dataItem);
            novaView.repeteTxt = (TextView) convertView.findViewById(R.id.repeteItem);
            novaView.fotoTxt = (TextView) convertView.findViewById(R.id.fotoItem);

            resultado = convertView;
            convertView.setTag(novaView);
        } else {
            novaView = (ViewHolder) convertView.getTag();
            resultado = convertView;
        }

        // Setar campos


        novaView.nomeTxt.setText(eventoAtual.getNome());
        novaView.valorTxt.setText(eventoAtual.getValor() + "");

        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        novaView.dataTxt.setText(formatador.format(eventoAtual.getOcorreu()));

        Calendar data1 = Calendar.getInstance();
        data1.setTime(eventoAtual.getOcorreu());
        Calendar data2 = Calendar.getInstance();
        data2.setTime(eventoAtual.getValida());
        if(data1.get(Calendar.MONTH) != data2.get(Calendar.MONTH)){
            novaView.repeteTxt.setText("Sim");
        } else {
            novaView.repeteTxt.setText("Não");
        }

        novaView.fotoTxt.setText(eventoAtual.getCaminhoFoto() == null ? "Não existe!" : "Sim");

        return resultado;
    }
}
