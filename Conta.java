package Banco;
public class Conta {
    private int numAgencia;
    private int numConta;
    private float saldo;
    private Cliente cliente;

    public Conta(int numAgencia, int numConta, float saldo, Cliente cliente) {
        this.numAgencia = numAgencia;
        this.numConta = numConta;
        this.saldo = saldo;
        this.cliente = cliente;
    }

    public int getNumAgencia() {
        return numAgencia;
    }

    public void setNumAgencia(int numAgencia) {
        this.numAgencia = numAgencia;
    }

    public int getNumConta() {
        return numConta;
    }

    public void setNumConta(int numConta) {
        this.numConta = numConta;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void deposito(float valor) {
        this.saldo += valor;
    }

    public void saque(float valor) {
        if (valor > this.saldo) {
            System.out.println("Saldo Insuficiente");
        }
        else {
            this.saldo -= valor;
        }
    }

    public void transferencia (Conta novaConta, float valor) {
        if (valor > this.saldo) {
            System.out.println("Saldo Insuficiente");
        }
        else {
            this.saldo -= valor;
            novaConta.deposito(valor);
        }
    }

    public void exibirSaldo() {
        System.out.println("Nome: " + this.cliente.getNome() + " Saldo: " + this.saldo);
    }
}
