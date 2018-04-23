
import java.io.Serializable;
/**
 *
 * @author 14.03521-9
 */
public class Conta implements Serializable{
    private double Saldo;
    private final double Limite;
    
    public Conta(double Limite)
    {
        this.Limite = Limite;
    }
    
    public boolean Saque(double Valor){
        System.out.println("Saque");
        if(Valor > (this.Limite - this.Saldo)){
            System.out.println("Valor acima do limite!");
            return false;
        }
        else{
            this.Saldo -= Valor;
            System.out.println("Novo saldo: " + this.Saldo);
            return true;
        }     
    }
    public void Deposito(double Valor){
        System.out.println("Depósito");
        this.Saldo += Valor;
        System.out.println("Novo saldo: " + this.Saldo);
    }
    public void getSaldo(){
        System.out.println("Saldo: " + this.Saldo);
        System.out.println("Limite: "+ this.Limite);
    }
    
     public boolean Transferencia(Conta recebe, double valor, double Tipo){
        System.out.println("Transferencia");
        //Transferencia Corrente - Corrente
        if(Tipo == 0)
        {
            if(this.Saque(valor))
            {   
                System.out.println("Transferencia feita de: " + valor);
                recebe.Deposito(valor);            
                return true;
            }
            else{
            System.out.println("Transferencia não realizada");
            return false;            
            }
        }
        //Transferencia Corrente - Poupança
        if(Tipo == 1)
        {
            
        }
        //Transferencia Corrente - DI
        if(Tipo == 2){}
        //Transferencia Corrente - Tesouro
        if(Tipo == 3){}
        return false;
    }    
}
