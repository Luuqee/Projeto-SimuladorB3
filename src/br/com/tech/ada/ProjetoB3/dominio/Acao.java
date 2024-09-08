package br.com.tech.ada.ProjetoB3.dominio;

import java.time.LocalDate;

public class Acao extends Investimento {
    private String ticker;
    private String nome;
    private LocalDate dataAquisicao;

    public Acao(String nome, double valorAtual, String ticker, LocalDate dataAquisicao) {
        super(valorAtual);
        this.nome = nome;
        this.ticker = ticker;
        this.dataAquisicao = dataAquisicao;
    }

    // Getters e Setters
}
