public class Produto {
    String nome;
    String descricao;
    int qtdEstoque;
    double precoUnitario;
    String categoria;
    int qtdMinima;

    public Produto(String nome, String descricao, int qtdEstoque, double precoUnitario, String categoria, int qtdMinima) {
        this.nome = nome;
        this.descricao = descricao;
        this.qtdEstoque = qtdEstoque;
        this.precoUnitario = precoUnitario;
        this.categoria = categoria;
        this.qtdMinima = qtdMinima;
    }

    public double calcularSubtotal() {
        return qtdEstoque * precoUnitario;
    }

    @Override
    public String toString() {
        return nome + " - " + qtdEstoque + " - R$" + precoUnitario;
    }
}
