package com.example.apppekus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

public class TabelaCalculos extends AppCompatActivity {

    private Button voltarPagina;
    private TableLayout tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabela_calculos);

        voltarPagina = findViewById(R.id.voltar);

        voltarPagina.setOnClickListener(v -> {
            Intent intent = new Intent(TabelaCalculos.this, MainActivity.class);
            // start the activity connect to the specified class
            startActivity(intent);
        });

       loadData();

    }

    public void loadData(){

        DataBaseHelper dataBaseHelper = new DataBaseHelper(TabelaCalculos.this);
        List<CalculoModel> tudo = dataBaseHelper.getAll();

        for(int i = 0; i < tudo.size(); i++){
            tb = findViewById(R.id.tabela);
            TableRow tr = new TableRow(TabelaCalculos.this);
            TableLayout.LayoutParams trParams = new
                    TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                    TableLayout.LayoutParams.MATCH_PARENT);
            trParams.setMargins(0, 0, 0, 0);
            tr.setPadding(0,0,0,0);
            tr.setLayoutParams(trParams);

            TextView tvId = new TextView(TabelaCalculos.this);
                tvId.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.MATCH_PARENT));
            tvId.setPadding(8,8,8,8);

            TextView tvValor1 = new TextView(TabelaCalculos.this);
            tvValor1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.MATCH_PARENT));
            tvValor1.setPadding(8,8,8,8);

            TextView tvOperacao = new TextView(TabelaCalculos.this);
            tvOperacao.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.MATCH_PARENT));
            tvOperacao.setPadding(8,8,8,8);

            TextView tvValor2 = new TextView(TabelaCalculos.this);
            tvValor2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.MATCH_PARENT));
            tvValor2.setPadding(8,8,8,8);

            TextView tvResultado = new TextView(TabelaCalculos.this);
            tvResultado.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.MATCH_PARENT));
            tvResultado.setPadding(8,8,8,8);

            TextView tvData = new TextView(TabelaCalculos.this);
            tvData.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.MATCH_PARENT));
            tvData.setPadding(8,8,8,8);

            tvId.setText(Integer.toString(tudo.get(i).getId()));
            tvValor1.setText(tudo.get(i).getValor1());
            tvOperacao.setText(tudo.get(i).getOperacao());
            tvValor2.setText(tudo.get(i).getValor2());
            tvResultado.setText(tudo.get(i).getResultado());
            tvData.setText(tudo.get(i).getData());

            tr.addView(tvId);
            tr.addView(tvValor1);
            tr.addView(tvOperacao);
            tr.addView(tvValor2);
            tr.addView(tvResultado);
            tr.addView(tvData);

            tb.addView(tr, trParams);

        }


    }
}