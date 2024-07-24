


public class Pessoa {
    private String nome;
    private String sexo;
    private int idade;

    public Pessoa(String nome, String sexo, int idade) {
        this.nome = nome;
        this.sexo = sexo;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public String getSexo() {
        return sexo;
    }

    public int getIdade() {
        return idade;
    }

    @Override
    public String toString() {
        return nome + "," + sexo + "," + idade;
    }
}
