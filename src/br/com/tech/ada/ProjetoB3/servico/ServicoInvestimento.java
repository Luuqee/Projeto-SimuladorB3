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

    // Cria um novo investimento do tipo Acao
    public Investimento criarInvestimento(double valor, LocalDate data, Usuario usuario, String ticker, double valorAtual) {
        return new Acao("Nome da Ação", valor, ticker, data, usuario); // Ajuste conforme necessário
    }

    // Atualiza o valor de um investimento específico
    public void atualizarValorInvestimento(Acao acao, double novoValor) {
        acao.setValorAtual(novoValor);
    }

    // Adiciona um investimento à carteira
    public void adicionarInvestimento(Investimento investimento) {
        carteira.adicionarInvestimento(investimento);
    }

    // Verifica a situação de um investimento
    public String verificarSituacaoInvestimento(Acao acao) {
        // Exemplo: Retorne a situação de acordo com algum critério específico
        return acao.getValorAtual() > acao.getValor() ? "Valorização" : "Queda";
    }

    // Calcula o lucro baseado no valor de investimento e o tempo
    public double calcularLucro(Investimento investimento, double valorInicial, LocalDate dataInicial) {
        double valorAtual = investimento.getValorAtual();
        // Simples cálculo de lucro
        return valorAtual - valorInicial;
    }
}
