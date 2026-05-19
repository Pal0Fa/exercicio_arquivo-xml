import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class App {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        File arquivo = new File("livros.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder = factory.newDocumentBuilder();

        Document doc = builder.parse(arquivo);

        doc.getDocumentElement().normalize();

        NodeList lista = doc.getElementsByTagName("livro");

        int i;

        double soma = 0;

        double maiorPreco = -1;

        String livroMaisCaro = "";

        ArrayList<String> autores = new ArrayList<>();


        for(i = 0; i < lista.getLength(); i++){

            Node node = lista.item(i);

            if(node.getNodeType() == Node.ELEMENT_NODE){

                Element livro = (Element) node;

                String autor = livro.getElementsByTagName("autor")
                        .item(0).getTextContent();

                double preco = Double.parseDouble(
                        livro.getElementsByTagName("preco")
                        .item(0).getTextContent());

                String titulo = livro.getElementsByTagName("titulo")
                        .item(0).getTextContent();

                soma += preco;

                if(preco > maiorPreco){
                    maiorPreco = preco;
                    livroMaisCaro = titulo;
                }

                if(!(autores.contains(autor))){
                    autores.add(autor);
                }
            }
        }


        System.out.println("Autores disponiveis:");

        for(i = 0; i < autores.size(); i++){
            System.out.println("[" + (i + 1) + "] " + autores.get(i));
        }

        System.out.print("\nEscolha o indice do autor: ");
        int escolha = sc.nextInt();

        String autorEscolhido = autores.get(escolha - 1);


        System.out.printf("\nSoma total dos precos: R$ %.2f\n", soma);


        System.out.println("\nLivros do autor " + autorEscolhido + ":");

        for(i = 0; i < lista.getLength(); i++){

            Node node = lista.item(i);

            if(node.getNodeType() == Node.ELEMENT_NODE){

                Element livro = (Element) node;

                String autor = livro.getElementsByTagName("autor")
                        .item(0).getTextContent();

                String titulo = livro.getElementsByTagName("titulo")
                        .item(0).getTextContent();

                if(autor.equals(autorEscolhido)){
                    System.out.println("- " + titulo);
                }
            }
        }


        System.out.println("\nLivro mais caro:");

        System.out.printf("%s - R$ %.2f\n",
                livroMaisCaro,
                maiorPreco);

        sc.close();
    }
}