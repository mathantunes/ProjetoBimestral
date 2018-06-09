package FXMLBanco;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 *
 * @author 14.03521-9
 */


public class Gerente extends Cliente{    

    public StringProperty getAgencia() {
        return Agencia;
    }

    public void setAgencia(StringProperty Agencia) {
        this.Agencia = Agencia;
    }

    public StringProperty getCPF() {
        return CPF;
    }

    public void setCPF(StringProperty CPF) {
        this.CPF = CPF;
    }

    public StringProperty getRG() {
        return RG;
    }

    public void setRG(StringProperty RG) {
        this.RG = RG;
    }

    public StringProperty getEnd() {
        return End;
    }

    public void setEnd(StringProperty End) {
        this.End = End;
    }

    public StringProperty getCidade() {
        return Cidade;
    }

    public void setCidade(StringProperty Cidade) {
        this.Cidade = Cidade;
    }

    public StringProperty getTelefone() {
        return Telefone;
    }

    public void setTelefone(StringProperty Telefone) {
        this.Telefone = Telefone;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }
    private StringProperty Agencia;
    private StringProperty CPF;
    private StringProperty RG;
    private StringProperty End;
    private StringProperty Cidade;
    private StringProperty Telefone;
    private String Nome;
    public Gerente(String Nome) {
        super(Nome);
        this.Nome = Nome;
        this.Agencia = new SimpleStringProperty("");
        this.CPF = new SimpleStringProperty("");
        this.RG = new SimpleStringProperty("");
        this.End = new SimpleStringProperty("");
        this.Cidade = new SimpleStringProperty("");
        this.Telefone = new SimpleStringProperty("");
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
    public ArrayList getClientes(Map Clientes){
    ArrayList temp = new ArrayList();
    Iterator it = Clientes.entrySet().iterator();
        while (it.hasNext()) {
            temp.add(it.next());
            //System.out.println(it.next());    
        }
       return temp; 
    }
    public ArrayList getGerentes(Map Gerentes){
        ArrayList temp = new ArrayList();
        Iterator it = Gerentes.entrySet().iterator();
        while (it.hasNext()) {
          temp.add(it.next());
        }  
        return temp;
    }   
}
