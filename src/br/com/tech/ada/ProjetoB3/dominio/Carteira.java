package br.com.tech.ada.ProjetoB3.dominio;

import java.util.ArrayList;
import java.util.List;

public class Carteira {
    private List<Investimento> investimentos;

    public Carteira() {
        this.investimentos = new ArrayList<>();
    }

    public void adicionarInvestimento(Investimento investimento) {
        this.investimentos.add(investimento);
    }

    public double calcularValorTotal() {
        double valorTotal = 0.0;
        for (Investimento investimento : investimentos) {
            valorTotal += investimento.getValor();
        }
        return valorTotal;
    }
}
