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
    public double getValor() {
        return valor;
    }
    public void listarConsumo() {
        if(this.consumo == null || this.valor == 0) {
            System.out.printf("\t\t%s\n\t\t> Totalizando %s\n", this.consumo, this.valor);
        } else {
            System.out.printf("%s\n\t\t> Totalizando %s\n", this.consumo, DecimalFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR")).format(this.valor));
        }
    }
    public double calcular10porcento() {
        return (this.valor * 0.1);
    }
    public double dividirConta(int nClientes) {
        return (this.valor / nClientes);
    }
}
