package com.buchinhocheio.restaurante;

public class Cliente {
    private String nome;
    private String email;
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
