package br.com.tech.ada.ProjetoB3;

import br.com.tech.ada.ProjetoB3.dominio.Acao;
import br.com.tech.ada.ProjetoB3.dominio.FundoImobiliario;
import br.com.tech.ada.ProjetoB3.dominio.Usuario;
import br.com.tech.ada.ProjetoB3.servico.ServicoInvestimento;

import java.time.LocalDate;

public class SimuladorInvestimentosB3 {
    public static void main(String[] args) {
        Usuario usuario = new Usuario("João");

        ServicoInvestimento servico = new ServicoInvestimento(usuario);

        Acao acao1 = new Acao("Petrobras", 30.00, "PETR4", LocalDate.now());
        FundoImobiliario fundo1 = new FundoImobiliario("Fundo XPTO", 100.00, 0.5, LocalDate.now());

        servico.adicionarInvestimento(acao1);
        servico.adicionarInvestimento(fundo1);

        double valorTotal = servico.calcularValorTotalCarteira();
        System.out.println("Valor total da carteira: R$ " + valorTotal);
    }
}
