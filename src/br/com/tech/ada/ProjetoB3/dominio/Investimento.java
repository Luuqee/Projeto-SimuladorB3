package br.com.tech.ada.ProjetoB3.dominio;

public abstract class Investimento {
    private double valorAtual;

    public Investimento(double valorAtual) {
        this.valorAtual = valorAtual;
    }

    public double getValor() {
        return valorAtual;
    }

    public void setValor(double valorAtual) {
        this.valorAtual = valorAtual;
    }
}
