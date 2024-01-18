package com.example.apppekus;

import java.util.Date;

public class CalculoModel {
    private int id;
    private String valor1;
    private String operacao;
    private String valor2;
    private String resultado;
    private String data;

    public CalculoModel(int id, String valor1, String operacao, String valor2, String resultado, String data) {
        this.id = id;
        this.valor1 = valor1;
        this.operacao = operacao;
        this.valor2 = valor2;
        this.resultado = resultado;
        this.data = data;
    }

    public CalculoModel() {
    }

    @Override
    public String toString() {
        return "CalculoModel{" +
                "id=" + id +
                ", valor1='" + valor1 + '\'' +
                ", operacao='" + operacao + '\'' +
                ", valor2='" + valor2 + '\'' +
                ", resultado='" + resultado + '\'' +
                ", data=" + data +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValor1() {
        return valor1;
    }

    public void setValor1(String valor1) {
        this.valor1 = valor1;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public String getValor2() {
        return valor2;
    }

    public void setValor2(String valor2) {
        this.valor2 = valor2;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
