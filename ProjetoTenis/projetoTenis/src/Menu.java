import java.util.Scanner;

public class Menu {

    private XMLManager xml = new XMLManager();
    private Scanner sc = new Scanner(System.in);

    public void executar() {

        int opcao;

        do {

            System.out.println(
                "===== TORNEIO DE TENIS =====\n" +
                "\n" +
                "1 - Criar XML\n" +
                "2 - Adicionar jogo\n" +
                "3 - Listar jogos\n" +
                "4 - Alterar placar\n" +
                "5 - Alterar quadra\n" +
                "6 - Remover jogo\n" +
                "7 - Buscar por ID\n" +
                "8 - Contar jogos\n" +
                "9 - Filtrar por quadra\n" +
                "10 - Backup\n" +
                "0 - Sair\n"
            );

            opcao = sc.nextInt();
            sc.nextLine();

            try {

                switch(opcao) {

                    case 1:
                        xml.criarXML();
                        break;

                    case 2:
                        xml.adicionarJogo();
                        break;

                    case 3:
                        xml.listarJogos();
                        break;

                    case 4:
                        System.out.print("ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Novo placar: ");
                        String placar = sc.nextLine();

                        xml.alterarPlacar(id, placar);
                        break;

                    case 0:
                        System.out.println("Encerrando...");
                        break;
                }

            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }

        } while(opcao != 0);
    }
}