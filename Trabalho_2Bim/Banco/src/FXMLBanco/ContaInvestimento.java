package FXMLBanco;


import static java.lang.Math.pow;

/**
 *
 * @author 14.03521-9
 */
public class ContaInvestimento extends Conta {

    private double SaldoP;
    private double SaldoDI;
    private double SaldoT;
    private final double Limite;

    public ContaInvestimento(double Limite) {
        super(Limite);
        this.Limite = Limite;
    }

    //Rende    
    @Override
    public void getSaldo() {
        System.out.println("Saldo Poupança: " + this.SaldoP);
        System.out.println("Saldo DI: " + this.SaldoDI);
        System.out.println("Saldo Tesouro Direto: " + this.SaldoT);
        System.out.println("Limite: "+ this.Limite); 
    }

    public void Deposito(double valor, double tipo) {
        if (tipo == 1) {
            this.SaldoP += valor;
        }
        if (tipo == 2) {
            this.SaldoDI += valor;
        }
        if (tipo == 3) {
            this.SaldoT += valor;
        }
    }

    public boolean Saque(double Valor, double Tipo) {
        if (Tipo == 1) {
            System.out.println("Saque");
            if (Valor > (this.Limite - this.SaldoP)) {
                System.out.println("Valor acima do limite!");
                return false;
            } else {
                this.SaldoP -= Valor;
                System.out.println("Novo saldo: " + this.SaldoP);
                return true;
            }
        }
        if (Tipo == 2) {
            System.out.println("Saque");
            if (Valor > (this.Limite - this.SaldoDI)) {
                System.out.println("Valor acima do limite!");
                return false;
            } else {
                this.SaldoDI -= Valor;
                System.out.println("Novo saldo: " + this.SaldoDI);
                return true;
            }
        }
        if (Tipo == 3) {
            System.out.println("Saque");
            if (Valor > (this.Limite - this.SaldoT)) {
                System.out.println("Valor acima do limite!");
                return false;
            } else {
                this.SaldoT -= Valor;
                System.out.println("Novo saldo: " + this.SaldoT);
                return true;
            }
        }
        return false;
    }
    public void Rendimento(double rend, double tempo, double Tipo){
        double a;
        if(Tipo == 1){
            a = this.SaldoP * pow((1 + (rend/100)), tempo);             
            System.out.println("Capital: " + this.SaldoP);
            System.out.println("Rendimento de " + rend + "%");
            System.out.println("Montante: " + String.format("%.02f", a)); 
            System.out.println("Rende: " + String.format("%.02f", ((a - this.SaldoP)/this.SaldoP)*100) + "%");
        }
        if(Tipo == 2){
            a = this.SaldoDI * pow((1 + (rend/100)), tempo);             
            System.out.println("Capital: " + this.SaldoDI);
            System.out.println("Rendimento de " + rend);
            System.out.println("Montante: " + String.format("%.02f", a)); 
            System.out.println("Rende: " + String.format("%.02f", ((a - this.SaldoP)/this.SaldoP)*100) + "%");
        }
        if(Tipo == 3){
            a = this.SaldoT * pow((1 + (rend/100)), tempo);             
            System.out.println("Capital: " + this.SaldoT);
            System.out.println("Rendimento de " + rend);
            System.out.println("Montante: " + String.format("%.02f", a)); 
            System.out.println("Rende: " + String.format("%.02f", ((a - this.SaldoP)/this.SaldoP)*100) + "%");
        }
    }
}
