package com.example.apppekus;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    private EditText et1, et2;
    private Button proximaPagina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        proximaPagina = findViewById(R.id.tabelaCalculos);

        proximaPagina.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TabelaCalculos.class);
            // start the activity connect to the specified class
            startActivity(intent);
        });

        et1 = findViewById(R.id.valor1);
        et2 = findViewById(R.id.valor2);
    }

    public static String formatDateToString(Date date, String format, String timeZone) {
        // null check
        if (date == null) return null;
        // create SimpleDateFormat object with input format
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        // default system timezone if passed null or empty
        if (timeZone == null || "".equalsIgnoreCase(timeZone.trim())) {
            timeZone = Calendar.getInstance().getTimeZone().getID();
        }
        // set timezone to SimpleDateFormat
        sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
        // return Date in required format with timezone as String
        return sdf.format(date);
    }

    public void Aviso(String titulo, String mensagem){
        AlertDialog.Builder erro = new AlertDialog.Builder(MainActivity.this);
        erro.setTitle(titulo);
        erro.setMessage(mensagem);

        erro.create().show();
    }

    public void Adicao(View v) {
        calcularResultado("+");
    }

    public void Subtracao(View v) {
        calcularResultado("-");
    }

    public void Multiplicacao(View v) {
        calcularResultado("*");
    }

    public void Divisao(View v) {
        calcularResultado("/");
    }

    private void calcularResultado(String operacao) {

        String valor1 = et1.getText().toString();
        String valor2 = et2.getText().toString();

        if(valor1.equals("") || valor2.equals("")){
            Aviso("Erro!", "Por favor não deixe nenhum campo em branco ! ");
            return;
        }

        double n1 = Double.parseDouble(valor1);
        double n2 = Double.parseDouble(valor2);

        double result = 0;

        switch (operacao) {
            case "+":
                result = n1 + n2;
                break;
            case "-":
                result = n1 - n2;
                break;
            case "*":
                result = n1 * n2;
                break;
            case "/":
                if (n2 != 0) {
                    result = n1 / n2;
                } else {
                    Aviso("Erro de operação!", "Não é possivel dividir por 0 !!!!");
                    return;
                }
                break;
        }

        Date date = new Date();
        CalculoModel calculoModel;

        try {
            calculoModel = new CalculoModel(-1,valor1,operacao,valor2,Double.toString(result),formatDateToString(date,"dd MMM yyyy hh:mm:ss a", "GMT-3"));
        }catch (Exception e){
            Aviso("Erro!", "Houve algum erro na criação do ojbeto CalculoModel");
            calculoModel = new CalculoModel(-1,"error","error","error","error","error");
        }

        DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);

        boolean sucesso = dataBaseHelper.addOne(calculoModel);

        if (sucesso) {
            Aviso("Aviso!", "Cálculo armazenado com sucesso");
        }else{
            Aviso("Erro no Banco de Dados!", "Houve um erro ao Inserir um novo calculo");
        }

        et1.setText("");
        et2.setText("");

    }
}