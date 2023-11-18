package restaurante;

import java.util.List;
import java.util.ArrayList;

public class Restaurante {
    private String nome;
    private String endereco;
    private List<Mesa> mesas = new ArrayList<Mesa>(1);  // 1..* mesas

    public void setMesas(List<Mesa> mesas) {
        this.mesas = mesas;
    }
    public List<Mesa> getMesas() {
        List<Mesa> mesasCopy = new ArrayList<Mesa>(this.mesas.size());

        mesasCopy.addAll(mesas);
        
        return mesasCopy;   /* Retorna cópia modificável do objeto, não a referência */
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public String getEndereco() {
        return endereco;
    }
    public Restaurante(String nome, String endereco, List<Mesa> mesas) {
        this.nome = nome;
        this.endereco = endereco;
        this.mesas = mesas;
    }
    public Restaurante() {}
}
