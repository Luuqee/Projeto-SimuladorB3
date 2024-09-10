package br.com.tech.ada.ProjetoB3.dominio;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Empresa {
    private String nome;
    private Map<String, Acao> acoes = new HashMap<>();
    private Map<String, Map<LocalDate, Double>> variacoesPreco = new HashMap<>();

    public Empresa(String nome) {
        this.nome = nome;
    }

    public void adicionarAcao(Acao acao) {
        acoes.put(acao.getTicker(), acao);
    }

    public Acao getAcao(String ticker) {
        return acoes.get(ticker);
    }

    public void registrarVariacaoPreco(String ticker, LocalDate data, double valor) {
        variacoesPreco.putIfAbsent(ticker, new HashMap<>());
        variacoesPreco.get(ticker).put(data, valor);
    }

    public String verificarSituacao(String ticker) {
        Map<LocalDate, Double> variacoes = variacoesPreco.get(ticker);
        if (variacoes == null || variacoes.size() < 2) {
            return "Dados insuficientes para análise";
        }

        LocalDate hoje = LocalDate.now();
        LocalDate tresMesesAtras = hoje.minusMonths(3);

        double precoInicial = -1;
        double precoFinal = -1;

        for (Map.Entry<LocalDate, Double> entry : variacoes.entrySet()) {
            if (entry.getKey().isBefore(tresMesesAtras)) {
                precoInicial = entry.getValue();
            } else if (entry.getKey().isAfter(tresMesesAtras) && entry.getKey().isBefore(hoje)) {
                precoFinal = entry.getValue();
            }
        }

        if (precoInicial == -1 || precoFinal == -1) {
            return "Dados insuficientes para análise";
        }

        double variacao = ((precoFinal - precoInicial) / precoInicial) * 100;

        if (variacao > 10) {
            return "Valorização";
        } else if (variacao < -10) {
            return "Queda";
        } else {
            return "Estável";
        }
    }
}
