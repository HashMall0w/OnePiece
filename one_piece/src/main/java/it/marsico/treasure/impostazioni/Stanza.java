package it.marsico.treasure.impostazioni;

public class Stanza {
    private String nome = null;
    private String item = null;

    public Stanza(String nome, String item) {
        this.nome = nome;
        this.item = item;
    }

    public void resetList() {
        item = "";
    }

    public String getNome() {
        return nome;
    }

    public String getItem() {
        return item;
    }
}
