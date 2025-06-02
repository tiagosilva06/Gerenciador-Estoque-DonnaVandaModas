import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class EstoqueGUI extends JFrame {
    private Estoque estoque = new Estoque();

    private JTextField codigoField = new JTextField(10);
    private JTextField modeloField = new JTextField(10);
    private JTextField corField = new JTextField(10);
    private JTextField tamanhoField = new JTextField(10);
    private JTextField quantidadeField = new JTextField(5);
    private JTable tabelaProdutos;
    private DefaultTableModel tableModel;

    public EstoqueGUI() {
        setTitle("Controle de Estoque - Donna Vanda Modas");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        criarComponentes();
        setVisible(true);
    }

    private void criarComponentes() {
        JPanel painelTopo = new JPanel(new GridLayout(2, 5, 10, 10));
        painelTopo.setBorder(BorderFactory.createTitledBorder("Dados do Produto"));

        painelTopo.add(new JLabel("Código:"));
        painelTopo.add(new JLabel("Modelo:"));
        painelTopo.add(new JLabel("Cor:"));
        painelTopo.add(new JLabel("Tamanho:"));
        painelTopo.add(new JLabel("Quantidade:"));

        painelTopo.add(codigoField);
        painelTopo.add(modeloField);
        painelTopo.add(corField);
        painelTopo.add(tamanhoField);
        painelTopo.add(quantidadeField);

        JPanel painelBotoes = new JPanel(new FlowLayout());
        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnRemover = new JButton("Remover");
        JButton btnListar = new JButton("Listar");
        JButton btnFiltrarNome = new JButton("Filtrar por Nome");
        JButton btnFiltrarCor = new JButton("Filtrar por Cor");

        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnRemover);
        painelBotoes.add(btnListar);
        painelBotoes.add(btnFiltrarNome);
        painelBotoes.add(btnFiltrarCor);

        String[] colunas = {"Código", "Modelo", "Cor", "Tamanho", "Quantidade"};
        tableModel = new DefaultTableModel(colunas, 0);
        tabelaProdutos = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(tabelaProdutos);

        // Eventos
        btnAdicionar.addActionListener(e -> adicionarProduto());
        btnRemover.addActionListener(e -> removerProduto());
        btnListar.addActionListener(e -> atualizarTabela(estoque.listarProdutos()));
        btnFiltrarNome.addActionListener(e -> {
            String nome = JOptionPane.showInputDialog("Digite o nome:");
            if (nome != null) {
                atualizarTabela(estoque.filtrarPorNome(nome));
            }
        });
        btnFiltrarCor.addActionListener(e -> {
            String cor = JOptionPane.showInputDialog("Digite a cor:");
            if (cor != null) {
                atualizarTabela(estoque.filtrarPorCor(cor));
            }
        });

        setLayout(new BorderLayout());
        add(painelTopo, BorderLayout.NORTH);
        add(painelBotoes, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
    }

    private void adicionarProduto() {
        try {
            String codigo = codigoField.getText();
            String modelo = modeloField.getText();
            String cor = corField.getText();
            String tamanho = tamanhoField.getText();
            int quantidade = Integer.parseInt(quantidadeField.getText());

            Produto produto = new Produto(codigo, modelo, cor, tamanho, quantidade);
            if (estoque.adicionarProduto(produto)) {
                JOptionPane.showMessageDialog(this, "Produto adicionado com sucesso!");
                limparCampos();
                atualizarTabela(estoque.listarProdutos());
            } else {
                JOptionPane.showMessageDialog(this, "Código já existente!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Quantidade inválida", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removerProduto() {
        String codigo = JOptionPane.showInputDialog("Digite o código do produto:");
        if (codigo != null && estoque.removerProduto(codigo)) {
            JOptionPane.showMessageDialog(this, "Produto removido com sucesso!");
            atualizarTabela(estoque.listarProdutos());
        } else {
            JOptionPane.showMessageDialog(this, "Produto não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarTabela(java.util.List<Produto> lista) {
        tableModel.setRowCount(0);
        for (Produto p : lista) {
            tableModel.addRow(new Object[]{
                    p.getCodigo(), p.getModelo(), p.getCor(), p.getTamanho(), p.getQuantidade()
            });
        }
    }

    private void limparCampos() {
        codigoField.setText("");
        modeloField.setText("");
        corField.setText("");
        tamanhoField.setText("");
        quantidadeField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(EstoqueGUI::new);
    }
}