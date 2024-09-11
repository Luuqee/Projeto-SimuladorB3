package br.com.tech.ada.ProjetoB3.servico;

import br.com.tech.ada.ProjetoB3.dominio.Acao;
import br.com.tech.ada.ProjetoB3.dominio.Usuario;

import java.time.LocalDate;

public class ServicoInvestimento {

    /**
     * Cria uma nova ação com as informações fornecidas.
     *
     * @param ticker            o ticker da ação
     * @param valorInicial      o valor inicial da ação em reais
     * @param variacaoMensal    o array com as variações percentuais dos últimos três meses
     * @param usuario           o usuário associado à ação
     * @return                  uma nova instância de Acao
     */
    public Acao criarAcao(String ticker, double valorInicial, double[] variacaoMensal, Usuario usuario) {
        // Cria uma nova ação com as variações mensais e outros detalhes fornecidos
        return new Acao(ticker, valorInicial, variacaoMensal, LocalDate.now(), usuario);
    }

    /**
     * Simula o valor futuro da ação baseado nas variações mensais passadas.
     *
     * @param acao              a ação que será simulada
     * @param meses             o número de meses para simular
     * @return                  o valor futuro estimado da ação
     */
    public double simularValorFuturo(Acao acao, int meses) {
        double[] variacaoMensal = acao.getVariacaoMensal();
        double mediaVariacao = calcularMedia(variacaoMensal);
        double valorAtual = acao.getValorAtual();

        // Calcula o valor futuro com base na média da variação mensal
        for (int i = 0; i < meses; i++) {
            valorAtual += valorAtual * (mediaVariacao / 100);
        }

        return valorAtual;
    }

    /**
     * Calcula a média das variações percentuais.
     *
     * @param variacaoMensal    o array com as variações percentuais
     * @return                  a média das variações percentuais
     */
    private double calcularMedia(double[] variacaoMensal) {
        double soma = 0;
        for (double variacao : variacaoMensal) {
            soma += variacao;
        }
        return soma / variacaoMensal.length;
    }
}
