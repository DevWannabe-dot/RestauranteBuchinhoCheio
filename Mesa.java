package com.buchinhocheio.restaurante;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedHashMap;

public class Mesa {
    private int numeroMesa;
    private String data;
    private boolean reserva;
    private List<Cliente> clientes;
    private Map<Mesa, Comanda> comandasDaMesa;

    public void setComandasDaMesa(Map<Mesa, Comanda> comandasDaMesa) {
        this.comandasDaMesa = comandasDaMesa;
    }
    public Map<Mesa, Comanda> getComandasDaMesa() {
        return new LinkedHashMap<>(this.comandasDaMesa);    /* Retorna cópia modificável do objeto, não a referência */
    }
    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
    public List<Cliente> getClientes() {
        List<Cliente> clientesCopy = new ArrayList<Cliente>(this.clientes.size());

        clientesCopy.addAll(this.clientes);
        
        return clientesCopy;    /* Retorna cópia modificável do objeto, não a referência */
    }
    public void setNumeroMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }
    public int getNumeroMesa() {
        return numeroMesa;
    }
    public void setData(String data) {
        this.data = data;
    }
    public String getData() {
        return data;
    }
    public void reservar(boolean reserva) {
        if(!this.reserva)   this.reserva = reserva;
    }
}
