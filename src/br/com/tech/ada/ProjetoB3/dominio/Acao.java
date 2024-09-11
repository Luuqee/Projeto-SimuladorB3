package br.com.tech.ada.ProjetoB3.dominio;

import java.time.LocalDate;

public class Acao extends Investimento {

    private String ticker;
    private double valorAtual;
    private double[] variacaoMensal;

    public Acao(String ticker, double valorAtual, double[] variacaoMensal, LocalDate data, Usuario usuario) {
        super(valorAtual, data, usuario);
        this.ticker = ticker;
        this.valorAtual = valorAtual;
        this.variacaoMensal = variacaoMensal;
    }

    public String getTicker() {
        return ticker;
    }

    public double getValorAtual() {
        return valorAtual;
    }

    public double[] getVariacaoMensal() {
        return variacaoMensal;
    }

    public void setValorAtual(double valorAtual) {
        this.valorAtual = valorAtual;
    }
}
