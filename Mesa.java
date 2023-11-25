package com.buchinhocheio.restaurante;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Mesa {
    private int numeroMesa;
    private String data;
    private boolean reserva;
    private List<Cliente> clientes;
    private Comanda comanda;

    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
    }
    public Comanda getComanda() {
        Comanda comandaCopy = new Comanda();
        
        comandaCopy.setConsumo(comanda != null ? comanda.getConsumo() : null);
        comandaCopy.setValor(comanda != null ? comanda.getValor() : 0);

        return new Comanda(); /* Retorna cópia modificável do objeto */
    }
    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
    public List<Cliente> getClientes() {
        List<Cliente> clientesCopy = (clientes != null ? new ArrayList<Cliente>(this.clientes.size()) : null);

        if(clientesCopy != null) clientesCopy.addAll(clientes);
        
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
    public boolean reservar(boolean reserva) {
        if(!this.reserva){
            this.reserva = reserva;
            return true; // foi reservada
        }
        return false; // não foi reservada
    }
    public boolean isReserva() {
        return reserva;
    }
    public Mesa() {}
    public Mesa(int numeroMesa) {
        ZoneId timezoneLocal = ZoneId.of("America/Sao_Paulo");

        this.setNumeroMesa(numeroMesa);
        this.setData(String.format("%s", LocalDate.now(timezoneLocal).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
    }
}
