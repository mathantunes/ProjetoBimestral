/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLBanco.controller;

import FXMLBanco.Banco;
import FXMLBanco.Cliente;
import FXMLBanco.Gerente;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author 12.02859-2
 */
public class BancoController implements Initializable {
    private Banco banco;

    private Cliente user;
    private Gerente gerente;
    @FXML
    private TextField inputAgencia;

    @FXML
    private TextField logintext;
    
    @FXML
    private Button loginbutton;

    @FXML
    private Button logoutbutton;
    
    @FXML
    private TextField inputNome;

    @FXML
    private TextField inputCPF;

    @FXML
    private TextField inputRG;

    @FXML
    private TextField inputEnd;

    @FXML
    private TextField inputCidade;

    @FXML
    private TextField inputTelefone;

    @FXML
    private CheckBox boolCC;

    @FXML
    private CheckBox boolCI;

    @FXML
    private Label outAgencia;

    @FXML
    private Label outCC;

    @FXML
    private Label outCI;

    @FXML
    private Label outNome;

    @FXML
    private Label outCPF;

    @FXML
    private Label outRG;

    @FXML
    private Label outTelefone;

    @FXML
    private Label outCidade;

    @FXML
    private Label outEnd;

    @FXML
    private Button cadastrar;

    @FXML
    private Button cancelar;
    
    @FXML
    private Tab tabGerente;
    
    @FXML
    private Tab tabCadastro;
    
    @FXML
    private Tab tabInvest;
    
    @FXML
    private Tab tabCC;
    
    @FXML
    private TextField TextFieldPesquisaNome;

    @FXML
    private Button ButtonExcluirCadastro;

    @FXML
    private TextField TextFieldPesquisaAgencia;

    @FXML
    private TextField TextFieldPesquisaNomeNovo;

    @FXML
    private TextField TextFieldPesquisaCPF;

    @FXML
    private TextField TextFieldPesquisaRG;

    @FXML
    private TextField TextFieldPesquisaEndereco;

    @FXML
    private TextField TextFieldPesquisaCidade;

    @FXML
    private TextField TextFieldPesquisaTelefone;

    @FXML
    private Button ButtonSalvarCadastro;
    
    @FXML
    private Button buttonClientes;

    @FXML
    private Button buttonGerentes;

    @FXML
    private Label labelLista;
    
    //CC Variables
    @FXML
    private TextField TextFieldValorDepSaqCC;    
    @FXML
    private Button ButtonDepositar;
    @FXML
    private Button ButtonSaque;
    @FXML
    private TextArea textAreaCC;    
    @FXML
    private Label labelSaldoCC;    
    @FXML
    private TextField TextFieldTranferenciaValor;
    @FXML
    private TextField TextFieldTranferenciaDestinatario;
    @FXML
    private Button ButtonTransferencia;    
    @FXML
    private RadioButton radioCC;
    @FXML
    private RadioButton radioPoup;
    @FXML
    private RadioButton radioTesouro;
    @FXML
    private RadioButton radioDI;    

    //CI variables
    @FXML
    private TextField TextFieldDepositoCI;
    @FXML
    private TextField TextFieldSaqueCI;
    @FXML 
    private Button buttonCIPoupSaque;
    @FXML
    private Button buttonCITesouroSaque;
    @FXML
    private Button buttonCIDISaque;
    @FXML
    private Button buttonCIPoup;
    @FXML
    private Button buttonCITesouro;
    @FXML
    private Button buttonCIDI;
    
    @FXML
    private TextArea TextAreaExtratoInvestimentos;
    @FXML
    private Label labelSaldoP;
    @FXML
    private Label labelSaldoDI;
    @FXML
    private Label labelSaldoTes;
    @FXML
    private Button buttonCITransf;
    @FXML
    private RadioButton radioCICC;
    @FXML
    private RadioButton radioCIPoup;
    @FXML
    private RadioButton radioCITesouro;
    @FXML
    private RadioButton radioCIDI; 
    @FXML
    private RadioButton radioFromPoup;
    @FXML
    private RadioButton radioFromTesouro;
    @FXML
    private RadioButton radioFromDI;
    @FXML
    private TextField textFieldDestCI;
    @FXML
    private TextField textFieldValorCI;
    
    public BancoController() {
        this.banco = new Banco();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labelSaldoCC.setText("0");
        TextAreaExtratoInvestimentos.setText("");
        final ToggleGroup groupCI = new ToggleGroup();
        radioCICC.setToggleGroup(groupCI);
        radioCIPoup.setToggleGroup(groupCI);
        radioCITesouro.setToggleGroup(groupCI);
        radioCIDI.setToggleGroup(groupCI);
        final ToggleGroup groupCC = new ToggleGroup();
        radioCC.setToggleGroup(groupCC);
        radioPoup.setToggleGroup(groupCC);
        radioTesouro.setToggleGroup(groupCC);
        radioDI.setToggleGroup(groupCC);
        final ToggleGroup groupFrom = new ToggleGroup();
        radioFromDI.setToggleGroup(groupFrom);
        radioFromPoup.setToggleGroup(groupFrom);
        radioFromTesouro.setToggleGroup(groupFrom);
        
        //  Agencia   
        this.inputAgencia.textProperty().addListener((observable, oldValue, newValue) -> {
            this.outAgencia.setText(newValue);
        });

        //  Conta Corrente 
        this.boolCC.selectedProperty().addListener((observable, oldValue, newValue) -> {
            this.outCC.setText(newValue? "Sim" : "Não");
        });

        //  Conta Investimento 
        this.boolCI.selectedProperty().addListener((observable, oldValue, newValue) -> {
             this.outCI.setText(newValue? "Sim" : "Não");
        });

        //  Nome 
        this.inputNome.textProperty().addListener((observable, oldValue, newValue) -> {
            this.outNome.setText(newValue);
        });
        
        //  CPF
        this.inputCPF.textProperty().addListener((observable, oldValue, newValue) -> {
            this.outCPF.setText(newValue);
        });
        
        //  RG
        this.inputRG.textProperty().addListener((observable, oldValue, newValue) -> {
            this.outRG.setText(newValue);
        });
        
        //  Endereço
        this.inputEnd.textProperty().addListener((observable, oldValue, newValue) -> {
            this.outEnd.setText(newValue);
        });

        //  Cidade 
        this.inputCidade.textProperty().addListener((observable, oldValue, newValue) -> {
            this.outCidade.setText(newValue);
        });
        
        //  Telefone
        this.inputTelefone.textProperty().addListener((observable, oldValue, newValue) -> {
            this.outTelefone.setText(newValue);
        });        
        this.cancelar.setOnMousePressed((MouseEvent event) -> {
            //this.clearFields();
            clearFields();
        });
        
        this.loginbutton.setOnMousePressed((MouseEvent event) -> {
            int a = banco.verificaUsuario(logintext.getText());
            switch (a) {
                case 1:
                    gerenteLogin();
                    this.gerente = banco.getGerente(logintext.getText());
                    this.user = banco.getCliente(logintext.getText());
                    break;
                case 2:
                    clienteLogin();
                    break;
                case 0:
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Banco");
                    alert.setContentText("Cliente nao encontrado");
                    alert.show();
                    this.user = banco.getCliente(logintext.getText());
                    break;
                default:
                    break;
            }
                
        });
        this.logoutbutton.setOnMousePressed((MouseEvent event)->{
            logout();
        });
        this.TextFieldPesquisaNome.textProperty().addListener((listener, old, newval) -> {
            Cliente cli = this.banco.pesquisa(newval);
            if(cli != null){                
                this.TextFieldPesquisaAgencia.setText(cli.getAgencia().getValue().equals("")?"":cli.getAgencia().getValue());
                this.TextFieldPesquisaNomeNovo.setText(cli.getNome().getValue().equals("")?"":cli.getNome().getValue());
                this.TextFieldPesquisaCPF.setText(cli.getCPF().getValue().equals("")?"":cli.getCPF().getValue());
                this.TextFieldPesquisaRG.setText(cli.getRG().getValue().equals("")?"":cli.getRG().getValue());
                this.TextFieldPesquisaEndereco.setText(cli.getEnd().getValue().equals("")?"":cli.getEnd().getValue());
                this.TextFieldPesquisaCidade.setText(cli.getCidade().getValue().equals("")?"":cli.getCidade().getValue());
                this.TextFieldPesquisaTelefone.setText(cli.getTelefone().getValue().equals("")?"":cli.getTelefone().getValue());
 }
        } );
        
        this.cadastrar.setOnMousePressed((MouseEvent event) -> {
            if(banco.addCliente(outNome.getText(),
                                outAgencia.getText(),
                                outCPF.getText(),
                                outCidade.getText(),
                                outEnd.getText(),
                                outRG.getText(),
                                outTelefone.getText(),
                                this.gerente.getName())){
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Banco");
                alert.setContentText("Cliente registrado");
                alert.show();
                if(boolCC.isSelected())
                    banco.addCC(outNome.getText(), this.gerente.getName());
                if(boolCI.isSelected())
                    banco.addCI(outNome.getText(),this.gerente.getName());
            }
            else{
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Banco");
                alert.setContentText("Cliente ja existe");
                alert.show();
            }            
        });
        this.ButtonExcluirCadastro.setOnMousePressed((MouseEvent event)->{
            banco.removeCliente(TextFieldPesquisaNomeNovo.getText(), this.gerente.getName());
        });
        this.ButtonSalvarCadastro.setOnMousePressed((MouseEvent event)->{
            banco.updateCliente(TextFieldPesquisaNomeNovo.getText(), TextFieldPesquisaNome.getText(), this.gerente.getName());
        });
        
        labelLista.setWrapText(true);
        this.buttonGerentes.setOnMousePressed((MouseEvent event)->{
            labelLista.setText("");
            banco.getGerentes(this.gerente.getName()).forEach(cnsmr->{
                labelLista.setText(labelLista.getText() + cnsmr.toString());
            });
        });
        this.buttonClientes.setOnMousePressed((MouseEvent event)->{
            labelLista.setText("");
            banco.getClientes(this.gerente.getName()).forEach(cnsmr -> {
                labelLista.setText(labelLista.getText() + "\n" + cnsmr.toString());
            });
        });
        //CC
        textAreaCC.setText("");
        labelLista.setWrapText(true);
        this.ButtonDepositar.setOnMousePressed((MouseEvent event) ->{
            banco.DepositoCC(this.user.getNome().get(), Double.parseDouble(TextFieldValorDepSaqCC.getText()));
            textAreaCC.setText(textAreaCC.getText() + "\n" + "Deposito de " + TextFieldValorDepSaqCC.getText());
            labelSaldoCC.setText(Double.toString(Double.parseDouble(labelSaldoCC.getText()) + Double.parseDouble(TextFieldValorDepSaqCC.getText())));
        });
       
        this.ButtonSaque.setOnMousePressed((MouseEvent event) ->{
            if(banco.SaqueCC(this.user.getNome().get(), Double.parseDouble(TextFieldValorDepSaqCC.getText()))){
                textAreaCC.setText(textAreaCC.getText() + "\n" + "Saque de " + TextFieldValorDepSaqCC.getText());
                //double temp = (Double.parseDouble(labelSaldoCC.getText()) - Double.parseDouble(TextFieldValorDepSaqCC.getText()));
                //labelSaldoCC.setText(Double.toString(temp));
                labelSaldoCC.setText(Double.toString(Double.parseDouble(labelSaldoCC.getText()) - Double.parseDouble(TextFieldValorDepSaqCC.getText())));
            }
        });
        
        this.ButtonTransferencia.setOnMousePressed((MouseEvent event) ->{
            if(radioCC.isSelected()){
                if(banco.TransferenciaCCToCC(this.user.getNome().get(), TextFieldTranferenciaDestinatario.getText(), Double.parseDouble(TextFieldTranferenciaValor.getText())))
                {   
                    labelSaldoCC.setText(Double.toString(Double.parseDouble(labelSaldoCC.getText()) - Double.parseDouble(TextFieldTranferenciaValor.getText())));
                    textAreaCC.setText(textAreaCC.getText() + "\n" + "Transferencia de " + TextFieldValorDepSaqCC.getText());}
            }else if(radioTesouro.isSelected()){
                if(banco.TransferenciaCCToCI(this.user.getNome().get(), TextFieldTranferenciaDestinatario.getText(), Double.parseDouble(TextFieldTranferenciaValor.getText()),3))
                { 
                    labelSaldoCC.setText(Double.toString(Double.parseDouble(labelSaldoCC.getText()) - Double.parseDouble(TextFieldTranferenciaValor.getText())));
                    textAreaCC.setText(textAreaCC.getText() + "\n" + "Transferencia de " + TextFieldValorDepSaqCC.getText()); }
            }else if(radioPoup.isSelected()){
                if(banco.TransferenciaCCToCI(this.user.getNome().get(), TextFieldTranferenciaDestinatario.getText(), Double.parseDouble(TextFieldTranferenciaValor.getText()),1))
                {
                    labelSaldoCC.setText(Double.toString(Double.parseDouble(labelSaldoCC.getText()) - Double.parseDouble(TextFieldTranferenciaValor.getText())));
                    textAreaCC.setText(textAreaCC.getText() + "\n" + "Transferencia de " + TextFieldValorDepSaqCC.getText());}
            }else if(radioDI.isSelected()){
                if(banco.TransferenciaCCToCI(this.user.getNome().get(), TextFieldTranferenciaDestinatario.getText(), Double.parseDouble(TextFieldTranferenciaValor.getText()),2))
                {
                    labelSaldoCC.setText(Double.toString(Double.parseDouble(labelSaldoCC.getText()) - Double.parseDouble(TextFieldTranferenciaValor.getText())));
                    textAreaCC.setText(textAreaCC.getText() + "\n" + "Transferencia de " + TextFieldValorDepSaqCC.getText());}
            }
        });
        //CI
        labelSaldoP.setText("0");
        labelSaldoDI.setText("0");
        labelSaldoTes.setText("0");
        TextAreaExtratoInvestimentos.setWrapText(true);
        this.buttonCIPoup.setOnMousePressed((MouseEvent event) ->{
            banco.DepositoCI(this.user.getNome().get(), Double.parseDouble(TextFieldDepositoCI.getText()), 1);
                    TextAreaExtratoInvestimentos.setText(TextAreaExtratoInvestimentos.getText() + "\n" + "Deposito de " + TextFieldDepositoCI.getText() + "em Poupanca");
                    labelSaldoP.setText(Double.toString(Double.parseDouble(labelSaldoP.getText()) + Double.parseDouble(TextFieldDepositoCI.getText())));
        });
        this.buttonCITesouro.setOnMousePressed((MouseEvent event) ->{
                banco.DepositoCI(this.user.getNome().get(), Double.parseDouble(TextFieldDepositoCI.getText()), 3);
                    TextAreaExtratoInvestimentos.setText(TextAreaExtratoInvestimentos.getText() + "\n" + "Deposito de " + TextFieldDepositoCI.getText() + "em Tesouro");
                    labelSaldoTes.setText(Double.toString(Double.parseDouble(labelSaldoTes.getText()) + Double.parseDouble(TextFieldDepositoCI.getText())));
        });
        this.buttonCIDI.setOnMousePressed((MouseEvent event) ->{
                banco.DepositoCI(this.user.getNome().get(), Double.parseDouble(TextFieldDepositoCI.getText()), 2);
                    TextAreaExtratoInvestimentos.setText(TextAreaExtratoInvestimentos.getText() + "\n" + "Deposito de " + TextFieldDepositoCI.getText() + "em DI");
                    labelSaldoDI.setText(Double.toString(Double.parseDouble(labelSaldoDI.getText()) + Double.parseDouble(TextFieldDepositoCI.getText())));
        });
        //CI Saques
        this.buttonCIPoupSaque.setOnMousePressed((MouseEvent event) ->{
            if(banco.SaqueCI(this.user.getNome().get(), Double.parseDouble(TextFieldSaqueCI.getText()), 1)){
                TextAreaExtratoInvestimentos.setText(TextAreaExtratoInvestimentos.getText() + "\n" + "Saque de " + TextFieldSaqueCI.getText() + " em poupanca");
                labelSaldoP.setText(Double.toString(Double.parseDouble(labelSaldoP.getText()) - Double.parseDouble(TextFieldSaqueCI.getText())));
            }
        });
        this.buttonCIDISaque.setOnMousePressed((MouseEvent event) ->{
            if(banco.SaqueCI(this.user.getNome().get(), Double.parseDouble(TextFieldSaqueCI.getText()), 2)){
                TextAreaExtratoInvestimentos.setText(TextAreaExtratoInvestimentos.getText() + "\n" + "Saque de " + TextFieldSaqueCI.getText()+ " em DI");
                labelSaldoDI.setText(Double.toString(Double.parseDouble(labelSaldoDI.getText()) - Double.parseDouble(TextFieldSaqueCI.getText())));
            }
        });
        this.buttonCITesouroSaque.setOnMousePressed((MouseEvent event) ->{
            if(banco.SaqueCI(this.user.getNome().get(), Double.parseDouble(TextFieldSaqueCI.getText()), 3)){
                TextAreaExtratoInvestimentos.setText(TextAreaExtratoInvestimentos.getText() + "\n" + "Saque de " + TextFieldSaqueCI.getText() + "em Tesouro");
                labelSaldoTes.setText(Double.toString(Double.parseDouble(labelSaldoTes.getText()) - Double.parseDouble(TextFieldSaqueCI.getText())));
            }
        });
        
        //CI Transferencias
        this.buttonCITransf.setOnMousePressed((MouseEvent event) ->{
            int temp = radioFromPoup.isSelected()? 1: radioFromDI.isSelected()?2:radioFromTesouro.isSelected()?3:0;
            System.out.println(temp);
            if(temp != 0){
                if(radioCICC.isSelected()){
                    if(banco.TransferenciaCIToCC(this.user.getNome().get(), textFieldDestCI.getText(), Double.parseDouble(textFieldValorCI.getText()),temp))
                    {
                        TextAreaExtratoInvestimentos.setText(TextAreaExtratoInvestimentos.getText() + "\n" + "Transferencia de " + textFieldValorCI.getText());}
                }else if(radioCITesouro.isSelected()){
                    if(banco.TransferenciaCIToCI(this.user.getNome().get(), textFieldDestCI.getText(), Double.parseDouble(textFieldValorCI.getText()), temp, 3 ))
                    { 
                        //TextAreaExtratoInvestimentos.setText(Double.toString(Double.parseDouble(labelSaldoCC.getText()) - Double.parseDouble(TextFieldTranferenciaValor.getText())));
                        TextAreaExtratoInvestimentos.setText(TextAreaExtratoInvestimentos.getText() + "\n" + "Transferencia de " + textFieldValorCI.getText()); }
                }else if(radioCIPoup.isSelected()){
                    if(banco.TransferenciaCIToCI(this.user.getNome().get(), textFieldDestCI.getText(), Double.parseDouble(textFieldValorCI.getText()),temp, 1))
                    {
                       // TextAreaExtratoInvestimentos.setText(Double.toString(Double.parseDouble(labelSaldoCC.getText()) - Double.parseDouble(TextFieldTranferenciaValor.getText())));
                        TextAreaExtratoInvestimentos.setText(TextAreaExtratoInvestimentos.getText() + "\n" + "Transferencia de " + textFieldValorCI.getText());}
                }else if(radioCIDI.isSelected()){
                    if(banco.TransferenciaCIToCI(this.user.getNome().get(), textFieldDestCI.getText(), Double.parseDouble(textFieldValorCI.getText()),temp, 2))
                    {
                       // labelSaldoCC.setText(Double.toString(Double.parseDouble(labelSaldoCC.getText()) - Double.parseDouble(TextFieldTranferenciaValor.getText())));
                        TextAreaExtratoInvestimentos.setText(TextAreaExtratoInvestimentos.getText() + "\n" + "Transferencia de " + textFieldValorCI.getText());}
                }
            }
        });
        disableTabs();        
    }
    private void clearFields(){
        this.outAgencia.setText("");
        this.outCC.setText("");
        this.outCI.setText("");
        this.outCPF.setText("");
        this.outCidade.setText("");
        this.outEnd.setText("");
        this.outNome.setText("");
        this.outRG.setText("");
        this.outTelefone.setText("");
    }
    private void verificaUsuario(String name){
        this.banco.verificaUsuario(name);
        System.out.println("END");
    }   
    private void disableTabs(){
        this.tabCC.setDisable(true);
        this.tabCadastro.setDisable(true);
        this.tabGerente.setDisable(true);
        this.tabInvest.setDisable(true);        
    }
    private void gerenteLogin(){
        this.tabCC.setDisable(false);
        this.tabCadastro.setDisable(false);
        this.tabGerente.setDisable(false);
        this.tabInvest.setDisable(false); 
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Banco");
        alert.setContentText("Gerente Logado");
        alert.show();
    }
    private void clienteLogin(){
        this.tabCC.setDisable(false);
        this.tabCadastro.setDisable(true);
        this.tabInvest.setDisable(false);
        this.tabGerente.setDisable(true);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Banco");
        alert.setContentText("Cliente Logado");
        alert.show();
    }
    private void logout(){
        this.tabCC.setDisable(true);
        this.tabCadastro.setDisable(true);
        this.tabGerente.setDisable(true);
        this.tabInvest.setDisable(true); 
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Banco");
        alert.setContentText("Usuario Deslogado");
        alert.show();
    }
    
}
