package com.buchinhocheio.restaurante;

import java.text.DecimalFormat;
import java.util.Locale;

public class Comanda {
    private String consumo;
    private double valor;

    public void setConsumo(String consumo) {
        this.consumo = consumo;
    }
    public String getConsumo() {
        return consumo;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    public double getvalor() {
        return valor;
    }
    public void listarConsumo() {
        System.out.println(String.format(Locale.forLanguageTag("pt-BR"), "%s\nTotalizando %.2f", this.consumo, DecimalFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR")).format(this.valor)));
    }
    public double calcular10porcento() {
        return (this.valor * 0.1);
    }
    public double dividirConta(int nClientes) {
        return (this.valor / nClientes);
    }
}
