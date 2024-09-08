package br.com.tech.ada.ProjetoB3.dominio;

public class Usuario {
    private String nome;
    private Carteira carteira;

    public Usuario(String nome) {
        this.nome = nome;
        this.carteira = new Carteira();
    }

    public Carteira getCarteira() {
        return carteira;
    }

    // Getter e Setter para nome
}
