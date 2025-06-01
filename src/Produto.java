public class Produto{
    private String codigo;
    private String modelo;
    private String cor;
    private String tamanho;
    private int quantidade;

    public Produto(String codigo, String modelo, String cor, String tamanho, int quantidade) {
        this.codigo = codigo;
        this.modelo = modelo;
        this.cor = cor;
        this.tamanho = tamanho;
        this.quantidade = quantidade;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getModelo() {
        return modelo;
    }

    public String getCor() {
        return cor;
    }

    public String getTamanho() {
        return tamanho;
    }

    public int getQuantidade() {
        return quantidade;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "codigo='" + codigo + '\'' +
                "modelo='" + modelo + '\'' +
                ", cor='" + cor + '\'' +
                ", tamanho='" + tamanho + '\'' +
                ", quantidade='" + quantidade + '\'' +
                '}';
    }
}