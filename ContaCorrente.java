package Banco;
public class ContaCorrente extends Conta {
    private float taxaManutencao = 50f;

    public ContaCorrente(int numAgencia, int numConta, float saldo, Cliente cliente) {
        super(numAgencia, numConta, saldo, cliente);
    }

    public float simularOperacao(int meses) {
        float taxa;
        taxa = this.taxaManutencao * meses;
        setSaldo(getSaldo() - taxa);
        return getSaldo();
    }
}
