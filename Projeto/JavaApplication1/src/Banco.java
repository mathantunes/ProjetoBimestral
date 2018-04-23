import java.io.*;
import java.util.*;
/**
 *
 * @author 14.03521-9
 */
//Acessar Banco com Cliente/Gerente Default -> Matheus
public class Banco {

    private static HashMap<String, Gerente> Gerentes;
    private static HashMap<String, Cliente> Clientes;
    private static HashMap<String, Conta> Corrente;
    private static HashMap<String, ContaInvestimento> Investimento;
    private static HashMap<String, List<String>> ExtratoCorrente;
    private static HashMap<String, List<String>> ExtratoInvestimento;

    public static void main(String[] args) {
        double valor;
        String name;
        String input = "new";
        Gerentes = new HashMap<>();
        Clientes = new HashMap<>();
        Corrente = new HashMap<>();
        Investimento = new HashMap<>();
        ExtratoCorrente = new HashMap<>();
        ExtratoInvestimento = new HashMap<>();

        Gerentes.clear();
        Clientes.clear();
        Corrente.clear();
        Investimento.clear();
        ExtratoCorrente.clear();
        ExtratoInvestimento.clear();

        //Primeiro Gerente
        System.out.println("Cliente/Gerente Default: Luiz");
        System.out.println("Digitar 00 encerra o programa");
        Gerente primeiroGerente = new Gerente("Luiz");
        Gerentes.put(primeiroGerente.getName(), primeiroGerente);
        Clientes.put(primeiroGerente.getName(), primeiroGerente);
        while (!input.equals("00")) {
            try {
                System.out.println("Nome do cliente:");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                input = br.readLine();
                name = input;

                //Cliente
                if (Clientes.containsKey(input)) {
                    System.out.println("1 - Gerente");
                    System.out.println("2- Saldo 3 - Deposito 4 - Saque 5 - Transferencia 6 - Extrato 7 - Rendimento");
                    input = br.readLine();
                }
                else{
                    System.out.println("Cliente nao encontrado");
                    if(!input.equals("00"))
                        input = "0";  
                }                
                //Saldo
                if ("2".equals(input)) {
                    System.out.println("1 - Conta Corrente 2 - Conta Investimento");
                    input = br.readLine();                    
                    switch (input) {
                        case "1"://Saldo CC
                            if (Corrente.containsKey(name)) {
                                Corrente.get(name).getSaldo();
                            }
                            else
                                System.out.println("Conta corrente nao encontrada");
                            break;
                        case "2"://Saldo CI
                            if (Investimento.containsKey(name)) {
                                Investimento.get(name).getSaldo();
                            }
                            else
                              System.out.println("Conta de investimento nao encontrada");
                            break;
                        default:
                            System.out.println("Comando nao reconhecido");
                            break;
                    }
                    //Reset
                    input = "0";
                }
                //Deposito
                else if ("3".equals(input)) {
                    System.out.println("1- Conta Corrente 2 - Conta Investimento");
                    input = br.readLine();
                    switch (input) {
                        case "1"://Deposito CC
                            if (Corrente.containsKey(name)) {
                                System.out.println("Valor: ");
                                valor = Double.parseDouble(br.readLine());
                                DepositoCC(name, valor);
                            }
                            else
                                System.out.println("Conta corrente nao encontrada");
                            break;
                        case "2"://Deposito CI
                            System.out.println("1- Poupança 2 - DI 3 - Tesouro Direto");
                            input = br.readLine();
                            if (Investimento.containsKey(name)) {
                                System.out.println("Valor: ");
                                valor = Double.parseDouble(br.readLine());
                                DepositoCI(name, valor, input);
                            }
                            else
                                System.out.println("Conta de investimento nao encontrada");
                            break;
                        default:
                            System.out.println("Comando nao reconhecido");
                            break;
                    }
                    //Reset
                    input = "0";
                }
                //Saque
                else if ("4".equals(input)) {
                    System.out.println("1- Conta Corrente 2 - Conta Investimento");
                    input = br.readLine();
                    switch (input) {
                        case "1"://Saque CC
                            if (Corrente.containsKey(name)) {
                                System.out.println("Valor: ");
                                valor = Double.parseDouble(br.readLine());
                                SaqueCC(name, valor);
                            }
                            else
                                System.out.println("Conta corrente nao encontrada");
                            break;
                        case "2"://Saque CI
                            System.out.println("1- Poupança 2 - DI 3 - Tesouro Direto");
                            input = br.readLine();
                            if (Investimento.containsKey(name)) {
                                System.out.println("Valor: ");
                                valor = Double.parseDouble(br.readLine());
                                SaqueCI(name, valor, input);
                            }
                            else
                                System.out.println("Conta de investimento nao encontrada");
                            break;
                        default:
                            System.out.println("Comando nao reconhecido");
                            break;
                    }
                    //Reset
                    input = "0";
                }
                //Transferencia
                else if ("5".equals(input)) {
                    System.out.println("Destinatario: ");
                    String destinatario = br.readLine();
                    if (Clientes.containsKey(destinatario)) {
                        System.out.println("Valor: ");
                        valor = Double.parseDouble(br.readLine());
                        System.out.println(valor);
                        System.out.println("1 - CC-CC/ 2 - CC/:CI 3 - CI/CI 4 - CI/CC");
                        input = br.readLine();
                        switch (input) {
                            case "1"://Transferencia CC - CC
                                if (Corrente.containsKey(name)) {
                                    if (Corrente.containsKey(destinatario))
                                        TransferenciaCCToCC(name, destinatario, valor);
                                    else
                                        System.out.println("Conta corrente do destinatario nao encontrada");
                                }
                                else
                                    System.out.println("Conta corrente de origem nao encontrada");
                                break;
                            case "2"://Transferencia CC - CI
                                System.out.println("Destinatario - 1 - Poupanca 2 - DI 3 - Tesouro");
                                input = br.readLine();
                                if (Corrente.containsKey(name)) {
                                    if (Investimento.containsKey(destinatario))
                                        TransferenciaCCToCI(name, destinatario, valor, input);
                                    else
                                        System.out.println("Conta de investimento do destinatario nao encontrada");
                                }
                                else
                                    System.out.println("Conta corrente de origem nao encontrada");
                                break;
                            case "3"://Transferencia CI - CI
                                System.out.println("Retirar de: 1 - Poupanca 2 - DI 3 - Tesouro");
                                input = br.readLine();
                                if (Investimento.containsKey(name)) {
                                    if (Investimento.containsKey(destinatario))
                                        TransferenciaCIToCI(name, destinatario, valor, input, br);
                                    else
                                        System.out.println("Conta de investimento do destinatario nao encontrada");
                                }
                                else
                                    System.out.println("Conta de investimento de origem nao encontrada");
                                break;
                            case "4"://Transferencia CI - CC
                                System.out.println("Retirar de: 1 - Poupanca 2 - DI 3 - Tesouro");
                                input = br.readLine();
                                if (Investimento.containsKey(name)) {
                                    if (Corrente.containsKey(destinatario))
                                        TransferenciaCIToCC(name, destinatario, valor, input);
                                    else
                                        System.out.println("Conta corrente do destinatario nao encontrada");
                                }
                                else
                                    System.out.println("Conta de investimento de origem nao encontrada");
                                break;
                            default:
                                System.out.println("Comando nao reconhecido");
                                break;
                        }
                    }
                    //Reset
                    input = "0";
                }
                //Extrato
                else if ("6".equals(input)) {
                    System.out.println("1- Conta Corrente 2 - Conta Investimento");
                    input = br.readLine();
                    switch (input) {
                        case "1"://Extrato CC
                            if(ExtratoCorrente.containsKey(name))
                                Extrato(ExtratoCorrente.get(name));
                            else
                                System.out.println("Conta corrente nao encontrada");
                            break;
                        case "2"://Extrato CI
                            if(ExtratoInvestimento.containsKey(name))
                                Extrato(ExtratoInvestimento.get(name));
                            else
                                System.out.println("Conta de investimento nao encontrada");
                            break;
                        default:
                            System.out.println("Comando nao reconhecido");
                            break;
                    }
                    //Reset
                    input = "0";
                }
                //Rendimento
                else if ("7".equals(input)) {
                    if (Investimento.containsKey(name)) {
                        System.out.println("1- Poupanca 2 - DI 3 - Tesouro");
                        valor = Double.parseDouble(br.readLine());
                        System.out.println("Poupanca - 0.525(%) ao mes; DI - 0.5763(%) ao mes; Tesouro - 1.03(%) ao mes");
                        System.out.println("Digite Rendimento (%) e Duracao (Sendo ambos na mesma escala de tempo)");
                        Investimento.get(name).Rendimento(Double.parseDouble(br.readLine()), Double.parseDouble(br.readLine()), valor);
                    }
                    else
                        System.out.println("Conta de investimento nao encontrada");
                    //Reset
                    input="0";
                }
                else if ("1".equals(input)) {
                    System.out.println("Nome do gerente:");
                    input = br.readLine();
                    String nameGerente = input;

                    if (Gerentes.containsKey(input)) {
                        System.out.println("1 - Cria Conta Corrente  2 - Cria Conta Investimento");
                        System.out.println("3 - Cria Gerente 4 - Remove Gerente 5 - Altera Gerente");
                        System.out.println("6 - Cria Cliente 7 - Remove Cliente 8 - Altera Cliente");
                        System.out.println("9 - Ver Clientes 10 - Ver Gerentes");
                        input = br.readLine();
                        switch (input) {
                            case "1"://Cria CC
                                System.out.println("Nome na Conta:");
                                name = br.readLine();
                                if (Corrente.containsKey(name)) {
                                    System.out.println("Conta Corrente ja existe");
                                } else if (Clientes.containsKey(name)) {
                                    System.out.println("Limite: ");                                                                       
                                    Corrente.put(name, Gerentes.get(nameGerente).CriaConta(Double.parseDouble(br.readLine())));
                                    ExtratoCorrente.put(name, new ArrayList());
                                    System.out.println("Conta Corrente criada");
                                }
                                else
                                    System.out.println("Cliente nao encontrado");
                                break;
                            case "2"://Cria CI
                                System.out.println("Nome na Conta:");
                                name = br.readLine();
                                if (Investimento.containsKey(name)) {
                                    System.out.println("Conta Investimento ja existe");
                                } else if(Clientes.containsKey(name)){
                                    System.out.println("Limite: ");
                                    ContaInvestimento contaI = Gerentes.get(nameGerente).CriaContaInvestimento(Double.parseDouble(br.readLine()));
                                    Investimento.put(name, contaI);
                                    ExtratoInvestimento.put(name, new ArrayList());
                                    System.out.println("Conta Investimento criada");
                                }
                                else
                                    System.out.println("Cliente nao encontrado");
                                break;
                            case "3"://Cria Gerente                            
                                System.out.println("Nome Novo Gerente:");
                                name = br.readLine();
                                Gerente novoGerente = Gerentes.get(nameGerente).criaGerente(name);
                                Gerentes.put(name, novoGerente);
                                Cliente novoCliente = Gerentes.get(nameGerente).criaCliente(name);
                                Clientes.put(name, novoCliente);
                                    break;
                            case "4"://Remove Gerente
                                System.out.println("Remove Gerente:");
                                input = br.readLine();
                                if (Gerentes.containsKey(input)) {
                                    Gerentes.get(nameGerente).removeGerente(input, Gerentes);
                                    Corrente.remove(input);
                                    Investimento.remove(input);
                                }
                                else
                                    System.out.println("Gerente nao encontrado");  
                                break;
                            case "5"://Altera Gerente
                                System.out.println("Altera Gerente:");
                                input = br.readLine();
                                if (Gerentes.containsKey(input)) {
                                    System.out.println("Novo nome:");   
                                    name = br.readLine();
                                    Gerentes.get(nameGerente).alteraGerente(name, input, Gerentes, Clientes);                                    
                                } else
                                    System.out.println("Gerente nao encontrado");
                                break;
                            case "6"://Cria Cliente
                                System.out.println("Nome Novo Cliente:");
                                input = br.readLine();
                                novoCliente = Gerentes.get(nameGerente).criaCliente(input);
                                Clientes.put(input, novoCliente);
                                break;                                
                            case "7"://Remove Cliente
                                System.out.println("Remove Cliente:");
                                input = br.readLine();
                                if (Clientes.containsKey(input)) {
                                    Gerentes.get(nameGerente).removeCliente(input, Clientes);
                                    Corrente.remove(input);
                                    Investimento.remove(input);
                                }   break;
                            case "8"://Altera Cliente
                                System.out.println("Altera Cliente:");
                                String input1 = br.readLine();
                                System.out.println("Novo Nome:");
                                String input2 = br.readLine();
                                Gerentes.get(nameGerente).alteraCliente(input2, input1, Clientes);
                                break;
                            case "9"://Lista de Clientes
                                Gerentes.get(nameGerente).getClientes(Clientes);
                                break;
                            case "10"://Lista de Gerentes
                                Gerentes.get(nameGerente).getGerentes(Gerentes);
                                break;
                            default:
                                System.out.println("Comando nao reconhecido");
                                break;
                        }
                        //Reset
                        input = "0";
                    }
                    else{
                        System.out.println("Gerente nao encontrado");
                        input = "0";
                    }
                }
            }
            catch(IOException | NumberFormatException | NullPointerException ex)
            {
                System.out.println(ex);
            }
        } 
    }

    public static void WriteContaToFile(Conta conta) {
        try {
            File arquivo = new File("D:\\Aula-JAVA\\Projeto\\Conta.txt");
            FileOutputStream fos = new FileOutputStream(arquivo);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(conta);
            oos.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public Conta ReadContaFromFile() {
        try {
            File arquivo = new File("D:\\Aula-JAVA\\Projeto\\Conta.txt");
            FileInputStream fis = new FileInputStream(arquivo);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Conta conta = (Conta) ois.readObject();
            return conta;
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public static void Extrato(List<String> Extrato) {
        for (int i = 0; i < Extrato.size(); i++) {
            System.out.println(Extrato.get(i));
        }
    }

    public static void DepositoCC(String name, double valor) {
        Corrente.get(name).Deposito(valor);
        ExtratoCorrente.get(name).add("Deposito de: " + valor);
        System.out.println("Deposito CC realizado");
    }

    public static void DepositoCI(String name, double valor, String input) {
        Investimento.get(name).Deposito(valor, Double.parseDouble(input));
        ExtratoInvestimento.get(name).add("Deposito de: " + valor + " em "
                + ((input.equals("1") ? "Poupanca" : ""))
                + ((input.equals("2") ? "DI" : ""))
                + ((input.equals("3") ? "Tesouro Direto" : "")));
        System.out.println("Deposito CI realizado");
    }

    public static void SaqueCC(String name, double valor) {
        if(Corrente.get(name).Saque(valor)){
            ExtratoCorrente.get(name).add("Saque de: " + valor);
            System.out.println("Saque CC realizado");
        }
        else {
            System.out.println("Saque CC nao realizado");
        }
    }

    public static void SaqueCI(String name, double valor, String input) {
        if(Investimento.get(name).Saque(valor, Double.parseDouble(input))){
        ExtratoInvestimento.get(name).add("Saque de: " + valor + " em "
                + ((input.equals("1") ? "Poupanca" : ""))
                + ((input.equals("2") ? "DI" : ""))
                + ((input.equals("3") ? "Tesouro Direto" : "")));
        System.out.println("Saque CI realizado");
        }        
        else {
            System.out.println("Saque CI nao realizada");
        }
    }

    public static void TransferenciaCCToCC(String name, String destinatario, double valor) {
        if (Corrente.get(name).Saque(valor)) {
            ExtratoCorrente.get(name).add("Saque de: " + valor);
            Corrente.get(destinatario).Deposito(valor);
            ExtratoCorrente.get(destinatario).add("Deposito de: " + valor);
            System.out.println("Transferencia CC - CC realizada");
        } else {
            System.out.println("Transferencia CC - CC nao realizada");
        }
    }

    public static void TransferenciaCCToCI(String name, String destinatario, double valor, String input) {
        if (Corrente.get(name).Saque(valor)) {
            ExtratoCorrente.get(name).add("Saque de: " + valor);
            Investimento.get(destinatario).Deposito(valor, Double.parseDouble(input));
            if (ExtratoInvestimento.containsKey(name)) {
                ExtratoInvestimento.get(name).add("Deposito de: " + valor + " em "
                        + ((input.equals("1") ? "Poupanca" : ""))
                        + ((input.equals("2") ? "DI" : ""))
                        + ((input.equals("3") ? "Tesouro Direto" : "")));
            }
            System.out.println("Transferencia CC - CI realizada");
        } else {
            System.out.println("Transferencia CC - CI nao realizada");
        }
    }

    public static void TransferenciaCIToCI(String name, String destinatario, double valor, String input, BufferedReader br) {
        try {
            if (Investimento.get(name).Saque(valor, Double.parseDouble(input))) {
                ExtratoInvestimento.get(name).add("Saque de: " + valor + " em "
                        + ((input.equals("1") ? "Poupanca" : ""))
                        + ((input.equals("2") ? "DI" : ""))
                        + ((input.equals("3") ? "Tesouro Direto" : "")));
                System.out.println("Destinatario - 1 - Poupanca 2 - DI 3 - Tesouro");
                input = br.readLine();
                Investimento.get(destinatario).Deposito(valor, Double.parseDouble(input));
                ExtratoInvestimento.get(destinatario).add("Deposito de: " + valor + " em "
                        + ((input.equals("1") ? "Poupanca" : ""))
                        + ((input.equals("2") ? "DI" : ""))
                        + ((input.equals("3") ? "Tesouro Direto" : "")));
                System.out.println("Transferencia CI - CI realizada");
            } else {
                System.out.println("Transferencia CI - CI nao realizada");
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public static void TransferenciaCIToCC(String name, String destinatario, double valor, String input) {
        if (Investimento.get(name).Saque(valor, Double.parseDouble(input))) {
            ExtratoInvestimento.get(name).add("Saque de: " + valor + " em "
                    + ((input.equals("1") ? "Poupanca" : ""))
                    + ((input.equals("2") ? "DI" : ""))
                    + ((input.equals("3") ? "Tesouro Direto" : "")));
            Corrente.get(destinatario).Deposito(valor);
            ExtratoCorrente.get(destinatario).add("Deposito de: " + valor);
            System.out.println("Transferencia CI - CC realizada");
        } else {
            System.out.println("Transferencia CI - CC nao realizada");
        }
    }
}
