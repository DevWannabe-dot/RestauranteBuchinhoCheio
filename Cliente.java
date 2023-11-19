package com.buchinhocheio.restaurante;

import java.util.Map;
import java.util.LinkedHashMap;

public class Cliente {
    private String nome;
    private String email;
    private Map<Cliente, Comanda> comandasDoCliente;

    public void setComandasDoCliente(Map<Cliente, Comanda> comandasDoCliente) {
        this.comandasDoCliente = comandasDoCliente;
    }
    public Map<Cliente, Comanda> getComandasDoCliente() {
        return new LinkedHashMap<>(comandasDoCliente != null ? comandasDoCliente : new LinkedHashMap<>()); /* Retorna cópia modificável do objeto, não a referência */
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
}
