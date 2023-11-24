package com.buchinhocheio.restaurante;

import java.text.DecimalFormat;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

public class Cardapio {
    private Map<String, Number> itens;

    public void adicionaItem(String consumo, Number valor) {
        itens.put(consumo, valor);
    }
    public void removeItem(String consumo) {
        itens.remove(consumo);
    }
    public Map<String, Number> getItem() {
        return itens;
    }
    public void imprimeCardapio() {
        for(Map.Entry<String, Number> entry : getItem().entrySet()) {
            System.out.println(entry.getKey() + ": " + DecimalFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR")).format(entry.getValue()));
        }
    }
    public Cardapio() {
        this.itens = new TreeMap<>();
    }
}
