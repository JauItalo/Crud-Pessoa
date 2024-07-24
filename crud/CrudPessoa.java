



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CrudPessoa {
    private static List<Pessoa> pessoas = new ArrayList<>();

    public static void main(String[] args) {
        carregarDados();
        while (true) {
            String[] options = {"Cadastrar", "Pesquisar", "Excluir", "Sair"};
            int choice = JOptionPane.showOptionDialog(null, "Escolha uma operação", "CRUD de Pessoas",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            switch (choice) {
                case 0:
                    cadastrarPessoa();
                    break;
                case 1:
                    pesquisarPessoa();
                    break;
                case 2:
                    excluirPessoa();
                    break;
                case 3:
                    salvarDados();
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }

    private static void cadastrarPessoa() {
        String nome = JOptionPane.showInputDialog("Digite o nome:");
        String sexo = JOptionPane.showInputDialog("Digite o sexo (M/F):");
        int idade = Integer.parseInt(JOptionPane.showInputDialog("Digite a idade:"));

        Pessoa pessoa = new Pessoa(nome, sexo, idade);
        pessoas.add(pessoa);
        JOptionPane.showMessageDialog(null, "Pessoa cadastrada com sucesso!");
    }

    private static void pesquisarPessoa() {
        String nome = JOptionPane.showInputDialog("Digite o nome da pessoa que deseja pesquisar:");
        for (Pessoa pessoa : pessoas) {
            if (pessoa.getNome().equalsIgnoreCase(nome)) {
                JOptionPane.showMessageDialog(null, "Pessoa encontrada: " + pessoa.getNome() +
                        ", Sexo: " + pessoa.getSexo() + ", Idade: " + pessoa.getIdade());
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Pessoa não encontrada.");
    }

    private static void excluirPessoa() {
        String nome = JOptionPane.showInputDialog("Digite o nome da pessoa que deseja excluir:");
        for (Pessoa pessoa : pessoas) {
            if (pessoa.getNome().equalsIgnoreCase(nome)) {
                pessoas.remove(pessoa);
                JOptionPane.showMessageDialog(null, "Pessoa excluída com sucesso!");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Pessoa não encontrada.");
    }

    private static void salvarDados() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("pessoas.txt"))) {
            for (Pessoa pessoa : pessoas) {
                writer.write(pessoa.toString());
                writer.newLine();
            }
            JOptionPane.showMessageDialog(null, "Dados salvos com sucesso!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar os dados.");
        }
    }

    private static void carregarDados() {
        try (BufferedReader reader = new BufferedReader(new FileReader("pessoas.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    String nome = data[0];
                    String sexo = data[1];
                    int idade = Integer.parseInt(data[2]);
                    pessoas.add(new Pessoa(nome, sexo, idade));
                }
            }
        } catch (IOException e) {
        }
    }
}
