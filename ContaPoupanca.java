package Banco;
public class ContaPoupanca extends Conta {
    private float taxaRendimento = 0.05f;

    public ContaPoupanca(int numAgencia, int numConta, float saldo, Cliente cliente) {
        super(numAgencia, numConta, saldo, cliente);
    }

    public float simularOperacao(int meses) {
        float rendimento;
        rendimento = (getSaldo() + (this.taxaRendimento * getSaldo())) * meses;
        setSaldo(getSaldo() + rendimento);
        return getSaldo();
    }
}
