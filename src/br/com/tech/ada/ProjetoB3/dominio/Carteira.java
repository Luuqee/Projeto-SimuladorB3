package br.com.tech.ada.ProjetoB3.dominio;

import java.util.ArrayList;
import java.util.List;

public class Carteira {
    private List<Investimento> investimentos = new ArrayList<>();

    public void adicionarInvestimento(Investimento investimento) {
        investimentos.add(investimento);
    }

    public void removerInvestimento(Investimento investimento) {
        investimentos.remove(investimento);
    }

    public List<Investimento> getInvestimentos() {
        return investimentos;
    }
}
