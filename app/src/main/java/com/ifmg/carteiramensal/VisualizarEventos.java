package com.ifmg.carteiramensal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class VisualizarEventos extends AppCompatActivity {

    private TextView tituloTxt;
    private TextView totalTxt;
    private ListView listaEventos;
    private Button cancelarBtn;
    private Button novoBtn;
    private int operacao = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_eventos);

        tituloTxt = (TextView) findViewById(R.id.tituloTxt);
        totalTxt = (TextView) findViewById(R.id.totalTxt);
        listaEventos = (ListView) findViewById(R.id.listaEventos);
        novoBtn = (Button) findViewById(R.id.novoBtn);
        cancelarBtn = (Button) findViewById(R.id.cancelarBtn);

        Intent intencao = getIntent();
        operacao = intencao.getIntExtra("acao", -1);
        ajustaOperacao();
        cadastrarEventos();
    }

    private void cadastrarEventos(){
        novoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent troca = new Intent(VisualizarEventos.this, cadastroEdicaoEvento.class);
                //operacao
                startActivity(troca);
            }
        });
    }

    private void ajustaOperacao(){
        switch (operacao){
            case 0: {
                tituloTxt.setText("Entradas");
                break;
            }
            case 1:{
                tituloTxt.setText("Saídas");
                break;
            }
            default: {
                tituloTxt.setText("Erro na operação");
            }
        }
    }
}