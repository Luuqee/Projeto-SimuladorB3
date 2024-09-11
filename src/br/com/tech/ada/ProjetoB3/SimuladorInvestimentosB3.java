package br.com.tech.ada.ProjetoB3;

import br.com.tech.ada.ProjetoB3.dominio.*;
import br.com.tech.ada.ProjetoB3.servico.ServicoInvestimento;

import java.time.LocalDate;
import java.util.Scanner;

public class SimuladorInvestimentosB3 {

    private static Usuario usuarioAtual;
    private static Empresa empresaAtual;
    private static Carteira carteiraAtual = new Carteira();
    private static Scanner scanner = new Scanner(System.in);
    private static ServicoInvestimento servicoInvestimento = new ServicoInvestimento();

    public static void main(String[] args) {
        while (true) {
            mostrarMenu();
            int opcao = scanner.nextInt();
            scanner.nextLine();

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
                    simularValorFuturo();
                    break;
                case 9:
                    mostrarInformacoesAcao();
                    break;
                case 10:
                    mostrarAjuda();
                    break;
                case 11:
                    System.out.println("Saindo...");
                    return;
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
        System.out.println("8. Simular Valor Futuro da Ação");
        System.out.println("9. Informações sobre Ação");
        System.out.println("10. Ajuda");
        System.out.println("11. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void cadastrarUsuario() {
        System.out.print("Digite o nome do usuário: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o saldo inicial do usuário: ");
        double saldo = scanner.nextDouble();
        scanner.nextLine();
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
        System.out.print("Digite o valor da ação (em reais): ");
        double valor = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Digite o ticker da ação: ");
        String ticker = scanner.nextLine();
        System.out.print("Digite a variação percentual dos últimos 3 meses (separada por vírgulas): ");
        String variacoes = scanner.nextLine();
        double[] variacaoMensal = parseVariacoes(variacoes);
        Acao acao = servicoInvestimento.criarAcao(ticker, valor, variacaoMensal, usuarioAtual);
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
        scanner.nextLine();
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
        System.out.print("Digite o valor a investir: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();
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

    private static void simularValorFuturo() {
        if (usuarioAtual == null) {
            System.out.println("Você precisa cadastrar um usuário primeiro.");
            return;
        }
        if (empresaAtual == null) {
            System.out.println("Você precisa cadastrar uma empresa primeiro.");
            return;
        }
        System.out.print("Digite o ticker da ação para simular: ");
        String ticker = scanner.nextLine();
        Acao acao = empresaAtual.getAcao(ticker);
        if (acao == null) {
            System.out.println("Ação não encontrada.");
            return;
        }
        System.out.print("Digite o número de meses para simular o investimento: ");
        int meses = scanner.nextInt();
        scanner.nextLine();
        double valorFuturo = servicoInvestimento.simularValorFuturo(acao, meses);
        System.out.println("Valor estimado da ação em " + meses + " meses: R$ " + valorFuturo);
    }

    private static void mostrarInformacoesAcao() {
        System.out.println("Informações sobre Ação:");
        System.out.println("Uma ação é uma unidade de propriedade de uma empresa. Ao comprar uma ação, você se torna acionista e tem direito a uma parte dos lucros e ativos da empresa.");
        System.out.println("O valor de uma ação pode variar com base no desempenho da empresa e nas condições do mercado.");
    }

    private static void mostrarAjuda() {
        System.out.println("Ajuda:");
        System.out.println("1. Cadastrar Usuário: Adiciona um novo usuário ao sistema.");
        System.out.println("2. Cadastrar Empresa: Adiciona uma nova empresa ao sistema.");
        System.out.println("3. Cadastrar Ação: Adiciona uma nova ação à empresa cadastrada.");
        System.out.println("4. Registrar Variação de Preço: Registra uma variação de preço para uma ação.");
        System.out.println("5. Verificar Situação da Empresa: Mostra a situação atual da empresa com base na variação das ações.");
        System.out.println("6. Investir em Ações: Permite ao usuário investir em uma ação específica.");
        System.out.println("7. Verificar e Gerenciar Investimentos: Mostra os investimentos atuais do usuário e permite verificar o valor de uma ação na carteira.");
        System.out.println("8. Simular Valor Futuro da Ação: Simula o valor futuro de uma ação com base nas variações passadas.");
        System.out.println("9. Informações sobre Ação: Fornece informações gerais sobre ações.");
        System.out.println("10. Ajuda: Mostra este menu de ajuda.");
    }

    private static double[] parseVariacoes(String variacoes) {
        String[] partes = variacoes.split(",");
        double[] resultado = new double[partes.length];
        for (int i = 0; i < partes.length; i++) {
            resultado[i] = Double.parseDouble(partes[i].trim());
        }
        return resultado;
    }
}
