package com.ifmg.carteiramensal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

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
    private Calendar cal;
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
    }

    private void atualizarMesAno() {
        String nomeMes[] = {"JAN", "FEV", "MAR", "ABR", "MAI", "JUN", "JUL", "AGO", "SET", "OUT", "NOV", "DEZ"};
        int mes = cal.get(Calendar.MONTH);
        int ano = cal.get(Calendar.YEAR);

        tituloMainTxt.setText(nomeMes[mes] + "/" + ano);
    }

    private void atualizarMes(int quantidade){
        cal.add(Calendar.MONTH, quantidade);
        if (cal.after(hoje)){
            atualizarMes(-quantidade);
        } else {
            // Atualizar no BD
        }

        atualizarMesAno();
    }

    private void cadastrarEventos(){
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
    }
}



















