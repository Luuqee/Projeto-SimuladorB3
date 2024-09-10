package br.com.tech.ada.ProjetoB3.dominio;

import java.time.LocalDate;

public class Acao extends Investimento {
    private String ticker;
    private double valorAtual;

    public Acao(String nome, double valor, String ticker, LocalDate data, Usuario usuario) {
        super(valor, data, usuario);
        this.ticker = ticker;
        this.valorAtual = valor;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    @Override
    public double getValorAtual() {
        return valorAtual;
    }

    public void setValorAtual(double valorAtual) {
        this.valorAtual = valorAtual;
    }
}
