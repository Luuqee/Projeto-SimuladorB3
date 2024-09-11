package br.com.tech.ada.ProjetoB3.servico;

import br.com.tech.ada.ProjetoB3.dominio.Acao;
import br.com.tech.ada.ProjetoB3.dominio.Investimento;
import br.com.tech.ada.ProjetoB3.dominio.Usuario;
import br.com.tech.ada.ProjetoB3.dominio.Carteira;
import java.time.LocalDate;

public class ServicoInvestimento {

    private Carteira carteira;

    public ServicoInvestimento(Carteira carteira) {
        this.carteira = carteira;
    }

    public Investimento criarInvestimento(double valor, LocalDate data, Usuario usuario, String ticker, double valorAtual) {
        return new Acao("Nome da Ação", valor, ticker, data, usuario);
    }

    public void atualizarValorInvestimento(Acao acao, double novoValor) {
        acao.setValorAtual(novoValor);
    }

    public void adicionarInvestimento(Investimento investimento) {
        carteira.adicionarInvestimento(investimento);
    }

    public String verificarSituacaoInvestimento(Acao acao) {

        return acao.getValorAtual() > acao.getValor() ? "Valorização" : "Queda";
    }

    public double calcularLucro(Investimento investimento, double valorInicial, LocalDate dataInicial) {
        double valorAtual = investimento.getValorAtual();

        return valorAtual - valorInicial;
    }
}
