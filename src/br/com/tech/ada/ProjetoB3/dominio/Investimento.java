package br.com.tech.ada.ProjetoB3.dominio;

import java.time.LocalDate;

public abstract class Investimento {
    private double valor;
    private LocalDate data;
    private Usuario usuario;

    public Investimento(double valor, LocalDate data, Usuario usuario) {
        this.valor = valor;
        this.data = data;
        this.usuario = usuario;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public abstract double getValorAtual();
}
