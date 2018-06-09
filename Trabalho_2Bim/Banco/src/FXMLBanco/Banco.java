package FXMLBanco;

import com.sun.scenario.effect.InvertMask;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 *
 * @author 12.02859-2
 */
public class Banco extends Application {
    Stage stage;
    
    private static HashMap<String, Gerente> Gerentes;
    private static HashMap<String, Cliente> Clientes;
    private static HashMap<String, Conta> Corrente;
    private static HashMap<String, ContaInvestimento> Investimento;
    private static HashMap<String, List<String>> ExtratoCorrente;
    private static HashMap<String, List<String>> ExtratoInvestimento;
    @Override
    public void start(Stage stage) throws Exception{
        this.stage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("view/FXML.fxml"));           
        Scene scene = new Scene(root);
        stage.setScene(scene);

        Gerentes = new HashMap<>();
        Clientes = new HashMap<>();
        Corrente = new HashMap<>();
        Investimento = new HashMap<>();
        ExtratoCorrente = new HashMap<>();
        ExtratoInvestimento = new HashMap<>();

        Gerentes.clear();
        Clientes.clear();
        Gerente luiz = new Gerente("Luiz");
        luiz.setAgencia(new SimpleStringProperty("123"));
        Gerentes.put("Luiz", new Gerente("Luiz"));
        Clientes.put("Luiz", new Cliente("Luiz"));
        addCC("Luiz", "Luiz");
        addCI("Luiz", "Luiz");
        
        
        stage.show();              
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }    
    
    public Boolean addCliente(String name, String agencia, String cpf,
            String cidade, String end, String rg, String tel, String nameGerente){
        if(Clientes.containsKey(name))
            return false;
        else{            
            Cliente novoCliente = Gerentes.get(nameGerente).criaCliente(name);
            novoCliente.setAgencia(new SimpleStringProperty(agencia));
            novoCliente.setCPF(new SimpleStringProperty(cpf));
            novoCliente.setCidade(new SimpleStringProperty(cidade));
            novoCliente.setEnd(new SimpleStringProperty(end));
            novoCliente.setRG(new SimpleStringProperty(rg));
            novoCliente.setTelefone(new SimpleStringProperty(tel));            
            Clientes.put(name, novoCliente);
            return true;
        }
    }
    public void removeCliente(String name, String gerentename){
        if (Clientes.containsKey(name)) {
            Gerentes.get(gerentename).removeCliente(name, Clientes);
            Corrente.remove(name);
            Investimento.remove(name);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Banco");
            alert.setContentText("Cliente removido");
            alert.show();
        } 
    }
    public void updateCliente(String name, String oldname, String gerentename){
        Gerentes.get(gerentename).alteraCliente(name, oldname, Clientes);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Banco");
        alert.setContentText("Cliente alterado");
        alert.show();
    }
    public void depositoCI(String name, double valor, String input){ 
        if (Investimento.containsKey(name)) {
            System.out.println("Valor: ");
            DepositoCI(name, valor, input);
        }
        else
            System.out.println("Conta de investimento nao encontrada");
    }
    public static void DepositoCI(String name, double valor, String input) {
        Investimento.get(name).Deposito(valor, Double.parseDouble(input));
        ExtratoInvestimento.get(name).add("Deposito de: " + valor + " em "
                + ((input.equals("1") ? "Poupanca" : ""))
                + ((input.equals("2") ? "DI" : ""))
                + ((input.equals("3") ? "Tesouro Direto" : "")));
        System.out.println("Deposito CI realizado");
    }
    public int verificaUsuario(String name){
        System.out.println(name);
        if(Gerentes.containsKey(name))
            return 1;
        else{
            if(Clientes.containsKey(name))
                return 2;
            else return 0;
        }            
    }
    public Cliente getCliente(String name){
        return Clientes.get(name);
    }
    public Gerente getGerente(String name){
        return Gerentes.get(name);
    }
    public Cliente pesquisa(String name){
        if(Gerentes.containsKey(name)){
            return Gerentes.get(name);
        }else{
            if(Clientes.containsKey(name)){
                return Clientes.get(name);
            }else return null;
        }
    }
    public void addCC(String name, String gerentename){
    if (Corrente.containsKey(name)) {
            System.out.println("Conta Corrente ja existe");
            Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Banco");
                alert.setContentText("CC ja existe");
                alert.show();
        } else if (Clientes.containsKey(name)) {
            System.out.println("Limite: ");                                                                       
            Corrente.put(name, Gerentes.get(gerentename).CriaConta(10000));
            ExtratoCorrente.put(name, new ArrayList());
            System.out.println("Conta Corrente criada");
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Banco");
            alert.setContentText("CC registrado");
            alert.show();
        }
    }
     public void addCI(String name, String gerentename){
    if (Investimento.containsKey(name)) {
            System.out.println("Conta Corrente ja existe");
            Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Banco");
                alert.setContentText("CI ja existe");
                alert.show();
        } else if (Clientes.containsKey(name)) {
            System.out.println("Limite: ");                                                                       
            Investimento.put(name, Gerentes.get(gerentename).CriaContaInvestimento(10000));
            ExtratoInvestimento.put(name, new ArrayList());
            System.out.println("Conta Corrente criada");
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Banco");
            alert.setContentText("CI registrado");
            alert.show();
        }
    }
     public ArrayList getClientes(String nameGerente){
        return Gerentes.get(nameGerente).getClientes(Clientes);
     }
     public ArrayList getGerentes(String nameGerente){
        return Gerentes.get(nameGerente).getClientes(Gerentes);
     }
     //CC
    public void DepositoCC(String name, double valor) {
        Corrente.get(name).Deposito(valor);
        ExtratoCorrente.get(name).add("Deposito de: " + valor);
        System.out.println("Deposito CC realizado");
    }
    public Boolean SaqueCC(String name, double valor){
        if(Corrente.get(name).Saque(valor)){
            ExtratoCorrente.get(name).add("Saque de: " + valor);
            System.out.println("Saque CC realizado");
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Banco");
            alert.setContentText("Saque realizado");
            alert.show();
            return true;
        }
        else {
            System.out.println("Saque CC nao realizado");
            Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Banco");
                alert.setContentText("Saque nao realizado");
                alert.show();
                return false;
        }
    }
     public Boolean TransferenciaCCToCC(String name, String destinatario, double valor) {
        if (Corrente.get(name).Saque(valor)) {
            ExtratoCorrente.get(name).add("Saque de: " + valor);
            Corrente.get(destinatario).Deposito(valor);
            ExtratoCorrente.get(destinatario).add("Deposito de: " + valor);
            System.out.println("Transferencia CC - CC realizada");
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Banco");
            alert.setContentText("Transferencia realizada");
            alert.show();
            return true;
        } else {
            System.out.println("Transferencia CC - CC nao realizada");
            Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Banco");
                alert.setContentText("Transferencia nao realizada");
                alert.show();
            return false;
        }
    }
    public Boolean TransferenciaCCToCI(String name, String destinatario, double valor, double ci) {
        if (Corrente.get(name).Saque(valor)) {
            ExtratoCorrente.get(name).add("Saque de: " + valor);
            Investimento.get(destinatario).Deposito(valor, ci);
            if (ExtratoInvestimento.containsKey(name)) {
                ExtratoInvestimento.get(name).add("Deposito de: " + valor + " em Tesouro Direto" );
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Banco");
                alert.setContentText("Transferencia realizada");
                alert.show();
                return true;
            }else return false;
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Banco");
            alert.setContentText("Transferencia nao realizada");
            alert.show();
            return false;
        }
    }
    public void DepositoCI(String name, double valor, double input) {
        Investimento.get(name).Deposito(valor, input);
        ExtratoInvestimento.get(name).add("Deposito de: " + Double.toHexString(valor));
        System.out.println("Deposito CI realizado");
    }
     public Boolean TransferenciaCIToCC(String name, String destinatario, double valor, double input) {
        if (Investimento.get(name).Saque(valor, input)) {
            ExtratoInvestimento.get(name).add("Saque de: " + valor);
            Corrente.get(destinatario).Deposito(valor);
            ExtratoCorrente.get(destinatario).add("Deposito de: " + valor);
            System.out.println("Transferencia CI - CC realizada");
            return true;
        } else {
            System.out.println("Transferencia CI - CC nao realizada");
            return false;
        }
    }
      public Boolean TransferenciaCIToCI(String name, String destinatario, double valor, double ciinput, double ciout) {
        if (Investimento.get(name).Saque(valor, ciinput)) {
            ExtratoInvestimento.get(name).add("Saque de: " + valor);
            System.out.println("Destinatario - 1 - Poupanca 2 - DI 3 - Tesouro");
            Investimento.get(destinatario).Deposito(valor, ciout);
            ExtratoInvestimento.get(destinatario).add("Deposito de: " + valor);
            System.out.println("Transferencia CI - CI realizada");
            return true;
        } else {
            System.out.println("Transferencia CI - CI nao realizada");
            return false;
        }
    }
    public Boolean SaqueCI(String name, double valor, double input) {
      if(Investimento.get(name).Saque(valor, input)){
        ExtratoInvestimento.get(name).add("Saque de: " + valor );
        System.out.println("Saque CI realizado");
        return true;
      }        
      else {
          System.out.println("Saque CI nao realizada");
          return false;
      }
    }
}
