package Banco;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
public class TesteOperacoes {
    private ArrayList <Cliente> listaClientes = new ArrayList<Cliente>();
    private ArrayList <Conta> listaContas = new ArrayList<Conta>();
    
    public void criarConta () {
        Scanner sc = new Scanner(System.in);
        int numAgencia = 0, numConta = 0;
        float saldo = 0;

        System.out.println("Informe o seu nome: ");
        String nome = sc.nextLine();
        System.out.println("Informe o seu endereço: ");
        String endereco = sc.nextLine();
        System.out.println("Informe a sua profissão: ");
        String profissao = sc.nextLine();

        Cliente cliente = new Cliente(nome, endereco, profissao);
        listaClientes.add(cliente);

        System.out.println("Informe o tipo de conta: ");
        String tipoConta = sc.nextLine();

        try {
            System.out.println("Informe o número da agência: ");
            numAgencia = Integer.parseInt(sc.nextLine());
            System.out.println("Informe o número da conta: ");
            numConta = Integer.parseInt(sc.nextLine());
            System.out.println("Informe o saldo da conta: ");
            saldo = Float.parseFloat(sc.nextLine());
            if (saldo < 0) {
                throw new IllegalArgumentException("O saldo precisa ser positivo");
            }
        } 
        catch (InputMismatchException e) {
            System.out.println("Digite um número inteiro para as contas");
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        if (tipoConta.equalsIgnoreCase("Poupanca")) {
            ContaPoupanca novaPoupanca = new ContaPoupanca(numAgencia, numConta, saldo, cliente);
            listaContas.add(novaPoupanca);
        }
        else if (tipoConta.equalsIgnoreCase("Corrente")) {
            ContaCorrente novaCorrente = new ContaCorrente(numAgencia, numConta, saldo, cliente);
            listaContas.add(novaCorrente);
        }
        else {
            System.out.println("Você digitou o tipo incorretamente. Tente novamente");
            listaClientes.remove(cliente);
        }
    }

    public void realizarOperacoes(int numAgencia, int numConta, int numAgenciaOutro, int numContaOutro) {
        Conta contaOrigem = null;
        Conta contaDestino = null;
        for (Conta conta : listaContas) {
            if (conta.getNumAgencia() == numAgencia && conta.getNumConta() == numConta) {
                contaOrigem = conta;
                break;
            }
        }

        for (Conta conta : listaContas) {
            if (conta.getNumAgencia() == numAgenciaOutro && conta.getNumConta() == numContaOutro) {
                contaDestino = conta;
                break;
            }
        }
        
        if (contaOrigem != null && contaDestino != null) {
            float valorTransferencia = 100.0f; // Não foi dito para o usuario informar ese valor
            contaOrigem.transferencia(contaDestino, valorTransferencia);
            System.out.println("Saldo conta origem: ");
            contaOrigem.exibirSaldo();
            System.out.println("Saldo conta destino: ");
            contaDestino.exibirSaldo();
        } 
        else {
            System.out.println("Conta de origem ou conta de destino não encontrada.");
        }
    }

    public void exibirSaldo(int numAgencia, int numConta) {
        Scanner sc = new Scanner(System.in);
        Conta contaOrigem = null;
        for (Conta conta : listaContas) {
            if (conta.getNumAgencia() == numAgencia && conta.getNumConta() == numConta) {
                contaOrigem = conta;
                break;
            }
        }

        if (contaOrigem != null) {
            System.out.println("Informe a quantidade de meses: ");
            int meses = Integer.parseInt(sc.nextLine());
            if (contaOrigem instanceof ContaPoupanca) {
                float rendimento = ((ContaPoupanca) contaOrigem).simularOperacao(meses);
                System.out.println("Rendimento após " + meses + " meses: " + rendimento);
            }
            else if (contaOrigem instanceof ContaCorrente) {
                float novoSaldo = ((ContaCorrente) contaOrigem).simularOperacao(meses);
                System.out.println("Saldo após " + meses + " meses: " + novoSaldo);
            }
            contaOrigem.exibirSaldo();
        }
        else {
            System.out.println("Conta não encontrada");
        }
    }

    public void apresentarMenu() {
        Scanner sc = new Scanner(System.in);
        int op = 1;
        while (op != 0) {
            System.out.println("1 - Criar conta");
            System.out.println("2 - Realizar operações");
            System.out.println("3 - Exibir saldo");
            System.out.println("0 - Finalizar programa");
            op = Integer.parseInt(sc.nextLine());
            switch (op) {
                case 1:
                    criarConta();
                    break;
                case 2:
                    System.out.println("Informe o seu número de conta e agência: ");
                    int numAgencia = Integer.parseInt(sc.nextLine());
                    int numConta = Integer.parseInt(sc.nextLine());
                    System.out.println("Informe o número de conta e agência de quem receberá a transferência: ");
                    int numAgenciaOutro = Integer.parseInt(sc.nextLine());
                    int numContaOutro = Integer.parseInt(sc.nextLine());
                    realizarOperacoes(numAgencia, numConta, numAgenciaOutro, numContaOutro);
                    break;
                case 3:
                    System.out.println("Informe o seu número de conta e agência: ");
                    numAgencia = Integer.parseInt(sc.nextLine());
                    numConta = Integer.parseInt(sc.nextLine());
                    exibirSaldo(numAgencia, numConta);
                    break;
                case 0:
                    System.out.println("Fim");
                    break;
                default:
                    System.out.println("Digite novamente");
            }
        }
        sc.close();
    }

    public static void main (String[] args) {
        TesteOperacoes teste = new TesteOperacoes();
        teste.apresentarMenu();
    }
}
