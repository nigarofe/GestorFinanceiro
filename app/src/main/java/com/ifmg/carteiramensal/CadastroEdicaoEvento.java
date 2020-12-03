package com.ifmg.carteiramensal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CadastroEdicaoEvento extends AppCompatActivity {

    private TextView tituloTxt;
    private TextView nomeTxt;
    private TextView valorTxt;
    private TextView dataTxt;
    private CheckBox repeteCheckBox;
    private Spinner mesesSpinner;
    private ImageView foto;
    private Button fotoBtn;
    private Button salvarBtn;
    private Button cancelarBtn;
    private DatePickerDialog calendario;
    private Calendar calTemp;

    // 0 - cadastrar entrada; 1 - cadastrar saida; 2 - editar entrada; 3 - editar saida
    private int operacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_edicao_evento);

        tituloTxt = (TextView) findViewById(R.id.tituloTxt);
        nomeTxt = (TextView) findViewById(R.id.nomeTxt);
        valorTxt = (TextView) findViewById(R.id.valorTxt);
        dataTxt = (TextView) findViewById(R.id.dataTxt);
        repeteCheckBox = (CheckBox) findViewById(R.id.repeteCheckbox);
        mesesSpinner = (Spinner) findViewById(R.id.mesesSpinner);
        foto = (ImageView) findViewById(R.id.foto);
        fotoBtn = (Button) findViewById(R.id.fotoBtn);
        salvarBtn = (Button) findViewById(R.id.salvarBtn);
        cancelarBtn = (Button) findViewById(R.id.cancelarBtn);


        Calendar hoje = Calendar.getInstance();
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        dataTxt.setText(formatador.format(hoje.getTime()));

        Intent intencao = getIntent();
        operacao = intencao.getIntExtra("operacao", -1);
        ajustaPorAcao();

        cadastraEventos();
    }

    private void cadastraEventos() {
        calTemp = Calendar.getInstance();
        calendario = new DatePickerDialog(CadastroEdicaoEvento.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calTemp.set(year, month, dayOfMonth);
                dataTxt.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        }, calTemp.get(Calendar.YEAR), calTemp.get(Calendar.MONTH), calTemp.get(Calendar.DAY_OF_MONTH));


        dataTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendario.show();
            }
        });

        salvarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrarNovoEvento();
            }
        });
    }

    private void ajustaPorAcao() {
        switch (operacao) {
            case 0:
                tituloTxt.setText("Cadastro Entrada");

                break;
            case 1:
                tituloTxt.setText("Cadastro Saida");

                break;
            case 2:
                tituloTxt.setText("Editar Entrada");

                break;
            case 3:
                tituloTxt.setText("Editar Saida");

                break;
            default:
        }
    }

    private void cadastrarNovoEvento() {
        String nome = nomeTxt.getText().toString();

        double valor = Double.parseDouble(valorTxt.getText().toString());
        if (operacao == 1 || operacao == 3) {
            valor *= -1;
        }

        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        String dataEmString = dataTxt.getText().toString();

        try {
            Date dia = formatador.parse(dataEmString);
            Calendar dataLimite = Calendar.getInstance();
            dataLimite.setTime(calTemp.getTime());
            dataLimite.set(Calendar.DAY_OF_MONTH, dataLimite.getActualMaximum(Calendar.DAY_OF_MONTH));

            if (repeteCheckBox.isChecked()) {

            }

            Evento novoEvento = new Evento(nome, valor, new Date(), dataLimite.getTime(), dia, null);
            EventosBD bd = new EventosBD(CadastroEdicaoEvento.this);
            bd.insereEvento(novoEvento);

            Toast.makeText(CadastroEdicaoEvento.this, "Cadastro feito com sucesso", Toast.LENGTH_LONG).show();
            finish();
        } catch (ParseException ex) {
            System.err.println("ParseException lauwbf87awr82713r");
        }
    }
}