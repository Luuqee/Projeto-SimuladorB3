package br.com.tech.ada.ProjetoB3;

import br.com.tech.ada.ProjetoB3.dominio.*;
import java.time.LocalDate;
import java.util.Scanner;

public class SimuladorInvestimentosB3 {

    private static Usuario usuarioAtual;
    private static Empresa empresaAtual;
    private static Carteira carteiraAtual = new Carteira();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            mostrarMenu();
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1:
                    cadastrarUsuario();
                    break;
                case 2:
                    cadastrarEmpresa();
                    break;
                case 3:
                    cadastrarAcao();
                    break;
                case 4:
                    registrarVariacaoPreco();
                    break;
                case 5:
                    verificarSituacaoEmpresa();
                    break;
                case 6:
                    investirEmAcoes();
                    break;
                case 7:
                    verificarGerenciarInvestimentos();
                    break;
                case 8:
                    System.out.println("Saindo...");
                    return; // Encerra o programa
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("Menu:");
        System.out.println("1. Cadastrar Usuário");
        System.out.println("2. Cadastrar Empresa");
        System.out.println("3. Cadastrar Ação");
        System.out.println("4. Registrar Variação de Preço");
        System.out.println("5. Verificar Situação da Empresa");
        System.out.println("6. Investir em Ações");
        System.out.println("7. Verificar e Gerenciar Investimentos");
        System.out.println("8. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void cadastrarUsuario() {
        System.out.print("Digite o nome do usuário: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o saldo inicial do usuário: ");
        double saldo = scanner.nextDouble();
        scanner.nextLine(); // Limpar o buffer
        usuarioAtual = new Usuario(nome, saldo);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    private static void cadastrarEmpresa() {
        System.out.print("Digite o nome da empresa: ");
        String nome = scanner.nextLine();
        empresaAtual = new Empresa(nome);
        System.out.println("Empresa cadastrada com sucesso!");
    }

    private static void cadastrarAcao() {
        if (empresaAtual == null) {
            System.out.println("Você precisa cadastrar uma empresa primeiro.");
            return;
        }
        System.out.print("Digite o nome da ação: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o valor da ação: ");
        double valor = scanner.nextDouble();
        System.out.print("Digite o ticker da ação: ");
        String ticker = scanner.next();
        scanner.nextLine(); // Limpar o buffer
        Acao acao = new Acao(nome, valor, ticker, LocalDate.now(), usuarioAtual);
        empresaAtual.adicionarAcao(acao);
        System.out.println("Ação cadastrada com sucesso!");
    }

    private static void registrarVariacaoPreco() {
        if (empresaAtual == null) {
            System.out.println("Você precisa cadastrar uma empresa primeiro.");
            return;
        }
        System.out.print("Digite o ticker da ação: ");
        String ticker = scanner.nextLine();
        System.out.print("Digite a data da variação (YYYY-MM-DD): ");
        LocalDate data = LocalDate.parse(scanner.nextLine());
        System.out.print("Digite o valor da variação: ");
        double valor = scanner.nextDouble();
        scanner.nextLine(); // Limpar o buffer
        empresaAtual.registrarVariacaoPreco(ticker, data, valor);
        System.out.println("Variação de preço registrada com sucesso!");
    }

    private static void verificarSituacaoEmpresa() {
        if (empresaAtual == null) {
            System.out.println("Você precisa cadastrar uma empresa primeiro.");
            return;
        }
        System.out.print("Digite o ticker da ação: ");
        String ticker = scanner.nextLine();
        String situacao = empresaAtual.verificarSituacao(ticker);
        System.out.println("Situação da empresa: " + situacao);
    }

    private static void investirEmAcoes() {
        if (usuarioAtual == null) {
            System.out.println("Você precisa cadastrar um usuário primeiro.");
            return;
        }
        if (empresaAtual == null) {
            System.out.println("Você precisa cadastrar uma empresa primeiro.");
            return;
        }
        System.out.print("Digite o ticker da ação para investir: ");
        String ticker = scanner.nextLine();
        Acao acao = empresaAtual.getAcao(ticker);
        if (acao == null) {
            System.out.println("Ação não encontrada.");
            return;
        }
        System.out.print("Digite a quantidade a investir: ");
        double valor = scanner.nextDouble();
        scanner.nextLine(); // Limpar o buffer
        if (usuarioAtual.getSaldo() < valor) {
            System.out.println("Saldo insuficiente.");
            return;
        }
        usuarioAtual.setSaldo(usuarioAtual.getSaldo() - valor);
        acao.setValorAtual(acao.getValorAtual() + valor);
        carteiraAtual.adicionarInvestimento(acao);
        System.out.println("Investimento realizado com sucesso!");
    }

    private static void verificarGerenciarInvestimentos() {
        if (usuarioAtual == null) {
            System.out.println("Você precisa cadastrar um usuário primeiro.");
            return;
        }
        System.out.println("Investimentos na carteira:");
        for (Investimento investimento : carteiraAtual.getInvestimentos()) {
            System.out.println(investimento.getClass().getSimpleName() + " - Valor Atual: " + investimento.getValorAtual());
        }
        System.out.print("Digite o ticker da ação para verificar: ");
        String ticker = scanner.nextLine();
        Acao acao = (Acao) carteiraAtual.getInvestimentos().stream()
                .filter(i -> i instanceof Acao && ((Acao) i).getTicker().equals(ticker))
                .findFirst()
                .orElse(null);
        if (acao == null) {
            System.out.println("Ação não encontrada na carteira.");
            return;
        }
        System.out.println("Valor Atual da Ação: " + acao.getValorAtual());
    }
}
