import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Estoque{
    private Map<String, Produto> produtos;

    public Estoque() {
        this.produtos = new HashMap<>();
    }

    public boolean adicionarProduto(Produto produto){
        if(produtos.containsKey(produto.getCodigo())){
            return false;
        }

        produtos.put(produto.getCodigo(), produto);
        return true;
    }

    public boolean removerProduto (String codigo){
        return produtos.remove(codigo) != null;
    }

    public List<Produto> filtrarPorNome(String nome){
        return produtos.values().stream()
                .filter(p -> p.getModelo().toLowerCase().contains(nome.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Produto> filtrarPorCor(String cor){
        return produtos.values().stream()
                .filter(p -> p.getCor().toLowerCase().contains(cor.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Produto> listarProdutos(){
        return new ArrayList<>(produtos.values());
    }

    public static void main(String[] args) {
        Estoque estoque = new Estoque();

        Produto p1 = new Produto("001", "Saia Floral", "Vermelho", "M", 5);
        Produto p2 = new Produto("002", "Saia Jeans", "Azul", "P", 10);
        Produto p3 = new Produto("003", "Vestido Longo", "Preto", "G", 4);
        Produto p4 = new Produto("001", "Vestido Curto", "Branco", "M", 5); // código repetido

        // Adiciona produtos
        System.out.println("Adicionando produtos:");
        if (estoque.adicionarProduto(p1)) {
            System.out.println("Produto adicionado: " + p1);
        } else {
            System.out.println("ERRO: Este produto já está cadastrado: " + p1.getCodigo());
        }

        if (estoque.adicionarProduto(p2)) {
            System.out.println("Produto adicionado: " + p2);
        } else {
            System.out.println("ERRO: Este produto já está cadastrado: " + p2.getCodigo());
        }

        if (estoque.adicionarProduto(p3)) {
            System.out.println("Produto adicionado: " + p3);
        } else {
            System.out.println("ERRO: Este produto já está cadastrado: " + p3.getCodigo());
        }

        if (estoque.adicionarProduto(p4)) {
            System.out.println("Produto adicionado: " + p4);
        } else {
            System.out.println("ERRO: Este produto já está cadastrado: " + p4.getCodigo());
        }

    }
}