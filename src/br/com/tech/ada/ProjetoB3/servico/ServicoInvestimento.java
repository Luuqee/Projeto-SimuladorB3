package br.com.tech.ada.ProjetoB3.servico;

import br.com.tech.ada.ProjetoB3.dominio.Investimento;
import br.com.tech.ada.ProjetoB3.dominio.Usuario;

public class ServicoInvestimento {

    private Usuario usuario;

    public ServicoInvestimento(Usuario usuario) {
        this.usuario = usuario;
    }

    public void adicionarInvestimento(Investimento investimento) {
        usuario.getCarteira().adicionarInvestimento(investimento);
    }

    public double calcularValorTotalCarteira() {
        return usuario.getCarteira().calcularValorTotal();
    }
}
