package FXMLBanco;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 *
 * @author 14.03521-9
 */
public class Cliente {
    private StringProperty Nome;
    private StringProperty Agencia;
    private StringProperty CPF;
    private StringProperty RG;
    private StringProperty End;
    private StringProperty Cidade;
    private StringProperty Telefone;
    
    public Cliente(String nome)
    {
        this.Nome = new SimpleStringProperty(nome);
        this.Agencia = new SimpleStringProperty("");
        this.CPF = new SimpleStringProperty("");
        this.RG = new SimpleStringProperty("");
        this.End = new SimpleStringProperty("");
        this.Cidade = new SimpleStringProperty("");
        this.Telefone = new SimpleStringProperty("");
    }

    public void setNome(StringProperty Nome) {
        this.Nome = Nome;
    }

    public void setAgencia(StringProperty Agencia) {
        this.Agencia = Agencia;
    }

    public StringProperty getAgencia() {
        return Agencia;
    }

    public StringProperty getCPF() {
        return CPF;
    }

    public StringProperty getRG() {
        return RG;
    }

    public StringProperty getEnd() {
        return End;
    }

    public StringProperty getCidade() {
        return Cidade;
    }

    public StringProperty getTelefone() {
        return Telefone;
    }

    public void setCPF(StringProperty CPF) {
        this.CPF = CPF;
    }

    public void setRG(StringProperty RG) {
        this.RG = RG;
    }

    public void setEnd(StringProperty End) {
        this.End = End;
    }

    public void setCidade(StringProperty Cidade) {
        this.Cidade = Cidade;
    }

    public void setTelefone(StringProperty Telefone) {
        this.Telefone = Telefone;
    }

    public StringProperty getNome() {
        return Nome;
    }
}
