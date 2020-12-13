package com.ifmg.carteiramensal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ifmg.carteiramensal.EventosBD;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TextView tituloMainTxt;
    private TextView entradaTxt;
    private TextView saidaTxt;
    private TextView saldoTxt;
    private ImageButton saidaBtn;
    private ImageButton entradaBtn;
    private Button anteriorBtn;
    private Button novoBtn;
    private Button proximoBtn;
    static Calendar cal;
    private Calendar hoje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tituloMainTxt = (TextView) findViewById(R.id.tituloMainTxt);
        entradaTxt = (TextView) findViewById(R.id.entradaTxt);
        saidaTxt = (TextView) findViewById(R.id.saidaTxt);
        saldoTxt = (TextView) findViewById(R.id.saldoTxt);
        saidaBtn = (ImageButton) findViewById(R.id.saidaBtn);
        entradaBtn = (ImageButton) findViewById(R.id.entradaBtn);
        anteriorBtn = (Button) findViewById(R.id.anteriorBtn);
        novoBtn = (Button) findViewById(R.id.novoBtn);
        proximoBtn = (Button) findViewById(R.id.proximoBtn);

        cadastrarEventos();

        hoje = Calendar.getInstance();
        cal = Calendar.getInstance();
        atualizarMesAno();
        atualizaValores();
    }

    private void atualizarMesAno() {
        String nomeMes[] = {"JAN", "FEV", "MAR", "ABR", "MAI", "JUN", "JUL", "AGO", "SET", "OUT", "NOV", "DEZ"};
        int mes = cal.get(Calendar.MONTH);
        int ano = cal.get(Calendar.YEAR);

        tituloMainTxt.setText(nomeMes[mes] + "/" + ano);
    }

    private void atualizarMes(int quantidade) {
        cal.add(Calendar.MONTH, quantidade);
        if (cal.after(hoje)) {
            atualizarMes(-quantidade);
        } else {
            // Atualizar no BD
        }

        atualizarMesAno();
    }

    private void cadastrarEventos() {
        anteriorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizarMes(-1);
            }
        });
        proximoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizarMes(1);
            }
        });
        novoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                EventosBD db = new EventosBD(MainActivity.this);
//                db.insereEvento("First", 42);
//                Toast.makeText(MainActivity.this, db.getDatabaseName(), Toast.LENGTH_LONG).show();
            }
        });

        entradaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent troca = new Intent(MainActivity.this, VisualizarEventos.class);
                troca.putExtra("acao", 0);
                startActivity(troca);
            }
        });

        saidaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent troca = new Intent(MainActivity.this, VisualizarEventos.class);
                troca.putExtra("acao", 1);
                startActivity(troca);
            }
        });
    }

    private void atualizaValores() {
        EventosBD db = new EventosBD(MainActivity.this);
        ArrayList<Evento> listaEventosSaida = db.buscaEventos(1, cal);
        ArrayList<Evento> listaEventosEntrada = db.buscaEventos(0, cal);

        double entradaTotal = 0.0;
        double saidaTotal = 0.0;
        double saldoTotal = 0.0;

        for (int i = 0; i < listaEventosEntrada.size(); i++) {
            entradaTotal += listaEventosEntrada.get(i).getValor();
        }
        for (int i = 0; i < listaEventosSaida.size(); i++) {
            saidaTotal += listaEventosSaida.get(i).getValor();
        }
        saldoTotal = entradaTotal - saidaTotal;

        entradaTxt.setText(Math.round(entradaTotal) + "");
        saidaTxt.setText(Math.round(saidaTotal) + "");
        saldoTxt.setText(Math.round(saldoTotal) + "");
    }
}



















