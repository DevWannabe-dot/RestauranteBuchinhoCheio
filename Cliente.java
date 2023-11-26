package com.buchinhocheio.restaurante;

public class Cliente {
    private String nome;
    private String email;
    private ComandaComida comanda_comida;
    private ComandaBebida comanda_bebida;

    public void setComandaComida(ComandaComida comanda_comida) {
        this.comanda_comida = comanda_comida;
    }
    public ComandaComida getComandaComida() {
        ComandaComida comandaCopy = new ComandaComida();
        
        comandaCopy.setConsumo(comanda_comida != null ? comanda_comida.getConsumo() : null);
        comandaCopy.setValor(comanda_comida != null ? comanda_comida.getValor() : 0);

        return comandaCopy; /* Retorna cópia modificável do objeto */
    }
    public void setComandaBebida(ComandaBebida comanda_bebida) {
        this.comanda_bebida = comanda_bebida;
    }
    public ComandaBebida getComandaBebida() {
        ComandaBebida comandaCopy = new ComandaBebida();
        
        comandaCopy.setConsumo(comanda_bebida != null ? comanda_bebida.getConsumo() : null);
        comandaCopy.setValor(comanda_bebida != null ? comanda_bebida.getValor() : 0);

        return comandaCopy; /* Retorna cópia modificável do objeto */
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
