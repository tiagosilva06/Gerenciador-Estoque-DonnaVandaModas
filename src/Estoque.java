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
}