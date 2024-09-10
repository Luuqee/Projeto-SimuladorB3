package br.com.tech.ada.ProjetoB3.dominio;

import java.time.LocalDate;

public class FundoImobiliario extends Investimento {
    private String nome;
    private double rendimento;
    private LocalDate dataAquisicao;

    public FundoImobiliario(String nome, double valorAtual, double rendimento, LocalDate dataAquisicao) {
        super(valorAtual);
        this.nome = nome;
        this.rendimento = rendimento;
        this.dataAquisicao = dataAquisicao;
    }

    public void atualizarValor(double novoValor) {
        setValor(novoValor);  // Método herdado de Investimento
    }

    public String getNome() {
        return nome;
    }

    public double getRendimento() {
        return rendimento;
    }

    public LocalDate getDataAquisicao() {
        return dataAquisicao;
    }
}
