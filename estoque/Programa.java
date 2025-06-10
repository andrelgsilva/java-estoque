import java.util.Scanner;

public class Programa {
    static Scanner sc = new Scanner(System.in);
    static Produto[] estoque = new Produto[100];
    static int totalProdutos = 0;

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\nMenu:");
            System.out.println("1 - Cadastrar produto");
            System.out.println("2 - Listar");
            System.out.println("3 - Filtrar por categoria");
            System.out.println("4 - Ordenar por nome (BubbleSort)");
            System.out.println("5 - Remover produto");
            System.out.println("6 - Atualizar preço");
            System.out.println("7 - Subtotal por categoria");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1: cadastrar(); break;
                case 2: listar(); break;
                case 3: filtrarPorCategoria(); break;
                case 4: ordenarPorNome(); break;
                case 5: remover(); break;
                case 6: atualizarPreco(); break;
                case 7: listarSubtotalPorCategoria(); break;
                case 0: System.out.println("Encerrando..."); break;
                default: System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    public static void cadastrar() {
        if (totalProdutos >= estoque.length) {
            System.out.println("Estoque cheio.");
            return;
        }
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Descrição: ");
        String desc = sc.nextLine();
        System.out.print("Quantidade em estoque: ");
        int qtd = sc.nextInt();
        System.out.print("Preço unitário: ");
        double preco = sc.nextDouble();
        sc.nextLine();
        System.out.print("Categoria: ");
        String cat = sc.nextLine();
        System.out.print("Quantidade mínima: ");
        int qtdMin = sc.nextInt();

        estoque[totalProdutos++] = new Produto(nome, desc, qtd, preco, cat, qtdMin);
        System.out.println("Produto cadastrado com sucesso!");
    }

    public static void listar() {
        if (totalProdutos == 0) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        imprimirProdutos(estoque, totalProdutos);
    }

    public static void filtrarPorCategoria() {
        System.out.print("Digite a categoria: ");
        String cat = sc.nextLine();
        boolean achou = false;
        for (int i = 0; i < totalProdutos; i++) {
            if (estoque[i].categoria.equalsIgnoreCase(cat)) {
                System.out.println(estoque[i]);
                achou = true;
            }
        }
        if (!achou) System.out.println("Nenhum produto encontrado nessa categoria.");
    }


    public static void imprimirProdutos(Produto[] vetor, int total) {
        System.out.println("\nProdutos cadastrados:");
        for (int i = 0; i < total; i++) {
            Produto p = vetor[i];
            System.out.printf("%d - %s - %d unidades - R$ %.2f - Categoria: %s\n", i + 1, p.nome, p.qtdEstoque, p.precoUnitario, p.categoria);
        }
    }


    public static void ordenarPorNome() {
        for (int i = 0; i < totalProdutos - 1; i++) {
            for (int j = 0; j < totalProdutos - i - 1; j++) {
                if (estoque[j].nome.compareToIgnoreCase(estoque[j + 1].nome) > 0) {
                    Produto temp = estoque[j];
                    estoque[j] = estoque[j + 1];
                    estoque[j + 1] = temp;
                }
            }
        }
        System.out.println("Estoque ordenado por nome.");
    }

    public static void remover() {
        System.out.print("Nome do produto a remover: ");
        String nome = sc.nextLine();
        for (int i = 0; i < totalProdutos; i++) {
            if (estoque[i].nome.equalsIgnoreCase(nome)) {
                for (int j = i; j < totalProdutos - 1; j++) {
                    estoque[j] = estoque[j + 1];
                }
                totalProdutos--;
                System.out.println("Produto removido.");
                return;
            }
        }
        System.out.println("Produto não encontrado.");
    }

    public static void atualizarPreco() {
    System.out.print("Nome do produto: ");
    String nome = sc.nextLine();
    for (int i = 0; i < totalProdutos; i++) {
        if (estoque[i].nome.equalsIgnoreCase(nome)) {
            System.out.print("Novo preço: ");
            estoque[i].precoUnitario = sc.nextDouble();
            sc.nextLine(); // limpar o buffer após nextDouble()
            System.out.println("Preço atualizado.");
            return;
        }
    }
    System.out.println("Produto não encontrado.");
    }


    public static void listarSubtotalPorCategoria() {
        if (totalProdutos == 0) {
            System.out.println("Estoque vazio.");
            return;
        }

        String[] categorias = new String[totalProdutos];
        int catCount = 0;

        for (int i = 0; i < totalProdutos; i++) {
            boolean existe = false;
            for (int j = 0; j < catCount; j++) {
                if (estoque[i].categoria.equalsIgnoreCase(categorias[j])) {
                    existe = true;
                    break;
                }
            }
            if (!existe) {
                categorias[catCount++] = estoque[i].categoria;
            }
        }

        double totalGeral = 0;

        for (int c = 0; c < catCount; c++) {
            String categoria = categorias[c];
            System.out.println("\nCategoria: " + categoria);
            double subtotal = 0;
            for (int i = 0; i < totalProdutos; i++) {
                if (estoque[i].categoria.equalsIgnoreCase(categoria)) {
                    System.out.println(estoque[i]);
                    subtotal += estoque[i].calcularSubtotal();
                }
            }
            System.out.printf("Subtotal: R$ %.2f\n", subtotal);
            totalGeral += subtotal;
        }

        System.out.printf("\nTotal geral: R$ %.2f\n", totalGeral);
    }
}
