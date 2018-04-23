import java.util.Iterator;
import java.util.Map;
/**
 *
 * @author 14.03521-9
 */
public class Gerente extends Cliente{
    private String Nome;
    public Gerente(String Nome) {
        super(Nome);
        this.Nome = Nome;
    }
    
    public Conta CriaConta(double Limite){        
        Conta corrente = new Conta(Limite);
        return corrente;            
    }
    public ContaInvestimento CriaContaInvestimento(double Limite)
    {
       ContaInvestimento investimento = new ContaInvestimento(Limite);
       return investimento;
    }
    @Override
    public String getName(){
        return this.Nome;
    }        
    public Gerente criaGerente(String Nome){
        Gerente novoGerente = new Gerente(Nome);
        System.out.println("Gerente Criado");
        return novoGerente;
    }
    public void removeGerente(String nome, Map gerentes){
        System.out.println("Gerente Removido");
        gerentes.remove(nome);
    }
    public void alteraGerente(String novoNome, String antigoNome, Map gerentes, Map clientes){
        if(!gerentes.containsKey(novoNome))
        {            
            Object get = gerentes.get(antigoNome); 
            gerentes.remove(antigoNome);
            gerentes.put(novoNome, get);
            Object getC = clientes.get(antigoNome);
            clientes.remove(antigoNome);
            clientes.put(novoNome, getC);
            System.out.println("Gerente Alterado");
        }
    }
    
    public Cliente criaCliente(String Nome){
        Cliente novoCliente = new Cliente(Nome);
        System.out.println("Cliente Criado");
        return novoCliente;
    }
    public void removeCliente(String nome, Map clientes){ 
        System.out.println("Cliente removido");
        clientes.remove(nome);
    }
    public void alteraCliente(String novoNome, String antigoNome, Map clientes){
        if(!clientes.containsKey(novoNome))
        {            
            Object get = clientes.get(antigoNome); 
            clientes.remove(antigoNome);
            clientes.put(novoNome, get);
            System.out.println("Cliente Alterado");
        }
    }
    public void getClientes(Map Clientes){
    Iterator it = Clientes.entrySet().iterator();
        while (it.hasNext()) {
            System.out.println(it.next());    
        }         
    }
    public void getGerentes(Map Gerentes){
    Iterator it = Gerentes.entrySet().iterator();
        while (it.hasNext()) {
            System.out.println(it.next());    
        }         
    }   
}
