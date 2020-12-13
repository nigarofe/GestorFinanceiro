package com.ifmg.carteiramensal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

import static com.ifmg.carteiramensal.MainActivity.cal;

public class VisualizarEventos extends AppCompatActivity {

    private TextView tituloTxt;
    private TextView totalTxt;
    private ListView listaEventos;
    private Button cancelarBtn;
    private Button novoBtn;
    private int operacao = -1;

    private ArrayList<Evento> eventos;
    private ItemListaEventos adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_eventos);

        tituloTxt = (TextView) findViewById(R.id.tituloTxt);
        totalTxt = (TextView) findViewById(R.id.valorTotalTxt);
        listaEventos = (ListView) findViewById(R.id.listaEventos);
        novoBtn = (Button) findViewById(R.id.novoBtn);
        cancelarBtn = (Button) findViewById(R.id.cancelarBtn);

        Intent intencao = getIntent();
        operacao = intencao.getIntExtra("acao", -1);
        ajustaOperacao();
        cadastrarEventos();

        carregaEventosLista();
    }

    private void carregaEventosLista(){
        //        Eventos teste
        //        eventos = new ArrayList<>();
        //        eventos.add(new Evento("Padaria", 10.50, new Date(), new Date(), new Date(), null));
        //        eventos.add(new Evento("Tráfico", 103240.12, new Date(), new Date(), new Date(), null));
        //        eventos.add(new Evento("Tráfico", 103240.12, new Date(), new Date(), new Date(), null));
        //        eventos.add(new Evento("Tráfico", 103240.12, new Date(), new Date(), new Date(), null));
        //        eventos.add(new Evento("Tráfico", 103240.12, new Date(), new Date(), new Date(), null));

        // Busca no banco de dados
        EventosBD db = new EventosBD(this);
        eventos = db.buscaEventos(operacao, MainActivity.cal);

        adapter = new ItemListaEventos(getApplicationContext(), eventos);
        listaEventos.setAdapter(adapter);

        double total = 0.0;
        for(int i = 0; i < eventos.size(); i++){
            total += eventos.get(i).getValor();
        }
        totalTxt.setText(total + "");
    }

    private void cadastrarEventos(){
        novoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent troca = new Intent(VisualizarEventos.this, CadastroEdicaoEvento.class);
                troca.putExtra("operacao", operacao);
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