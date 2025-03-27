import java.util.Scanner;

class Produto {
    private String codigo;
    private String nome;
    private String tamanhoOuPeso;
    private String cor;
    private double valor;
    private int quantidadeEstoque;

    public Produto(String codigo, String nome, String tamanhoOuPeso, String cor, double valor, int quantidadeEstoque) {
        this.codigo = codigo;
        this.nome = nome;
        this.tamanhoOuPeso = tamanhoOuPeso;
        this.cor = cor;
        this.valor = valor;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public void cadastrarProduto() {
        Scanner input = new Scanner(System.in);
        System.out.print("Digite o código do produto: ");
        this.codigo = input.nextLine();
        System.out.print("Digite o nome do produto: ");
        this.nome = input.nextLine();
        System.out.print("Digite o tamanho/peso do produto: ");
        this.tamanhoOuPeso = input.nextLine();
        System.out.print("Digite a cor do produto: ");
        this.cor = input.nextLine();
        System.out.print("Digite o valor do produto: ");
        this.valor = input.nextDouble();
        System.out.print("Digite a quantidade em estoque: ");
        this.quantidadeEstoque = input.nextInt();
        System.out.println("Produto cadastrado com sucesso!");
    }

    public void exibirProduto() {
        System.out.println("Código: " + codigo);
        System.out.println("Nome: " + nome);
        System.out.println("Tamanho/Peso: " + tamanhoOuPeso);
        System.out.println("Cor: " + cor);
        System.out.println("Valor: R$ " + valor);
        System.out.println("Quantidade em Estoque: " + quantidadeEstoque);
    }

    public boolean venderProduto(int quantidade) {
        if (quantidade <= quantidadeEstoque) {
            quantidadeEstoque -= quantidade;
            return true;
        } else {
            System.out.println("Estoque insuficiente!");
            return false;
        }
    }

    public double calcularTotalVenda(int quantidade) {
        return valor * quantidade;
    }

    public void adicionarEstoque(int quantidade) {
        quantidadeEstoque += quantidade;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }
}

class Pagamento {
    public void realizarPagamento(String metodo, double valorTotal) {
        Scanner input = new Scanner(System.in);
        switch (metodo.toLowerCase()) {
            case "pix":
            case "espécie":
            case "transferência":
            case "débito":
                valorTotal *= 0.95;
                System.out.println("Total com 5% de desconto: R$ " + valorTotal);
                if (metodo.equalsIgnoreCase("espécie")) {
                    System.out.print("Digite o valor pago: R$ ");
                    double valorPago = input.nextDouble();
                    if (valorPago >= valorTotal) {
                        double troco = valorPago - valorTotal;
                        System.out.println("Troco: R$ " + troco);
                    } else {
                        System.out.println("Valor pago insuficiente!");
                    }
                } else {
                    System.out.println("Pagamento realizado com sucesso via " + metodo + "!");
                }
                break;
            case "crédito":
                System.out.println("Parcelamento em até 3x sem juros.");
                double parcela = valorTotal / 3;
                System.out.println("3x de R$ " + parcela);
                break;
            default:
                System.out.println("Método de pagamento inválido.");
                break;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Produto produto = new Produto("001", "Produto Teste", "500g", "Azul", 50.0, 10);

        produto.exibirProduto();

        System.out.print("Digite a quantidade a ser vendida: ");
        int quantidade = input.nextInt();

        if (produto.venderProduto(quantidade)) {
            double valorTotal = produto.calcularTotalVenda(quantidade);
            System.out.println("Total da venda: R$ " + valorTotal);

            System.out.print("Digite o método de pagamento (Pix, Espécie, Transferência, Débito ou Crédito): ");
            String metodoPagamento = input.next();

            Pagamento pagamento = new Pagamento();
            pagamento.realizarPagamento(metodoPagamento, valorTotal);
        }

        System.out.println("Estoque atual: " + produto.getQuantidadeEstoque() + " unidades.");
        input.close();
    }
}
