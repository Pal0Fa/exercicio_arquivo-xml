import java.io.File;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

public class XMLManager {

    private File arquivo = new File("jogos.xml");

    private Document carregarDocumento() throws Exception {

        DocumentBuilderFactory factory =
                DocumentBuilderFactory.newInstance();

        DocumentBuilder builder =
                factory.newDocumentBuilder();

        return builder.parse(arquivo);
    }

    private void salvarDocumento(Document doc) throws Exception {

        TransformerFactory tf =
                TransformerFactory.newInstance();

        Transformer transformer =
                tf.newTransformer();

        transformer.transform(
                new DOMSource(doc),
                new StreamResult(arquivo));
    }

    // PASSO 1
    public void criarXML() throws Exception {

        if (!arquivo.exists()) {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.newDocument();

            Element raiz = doc.createElement("torneio");

            doc.appendChild(raiz);

            salvarDocumento(doc);

            System.out.println("XML criado.");
        } else {
            System.out.println("Arquivo já existe.");
        }
    }

    // PASSO 8
    public boolean idExiste(int id) throws Exception {

        Document doc = carregarDocumento();

        NodeList lista = doc.getElementsByTagName("jogo");

        for (int i = 0; i < lista.getLength(); i++) {

            Element jogo = (Element) lista.item(i);

            int idAtual = Integer.parseInt(jogo.getAttribute("id"));

            if (idAtual == id) {
                return true;
            }
        }

        return false;
    }

    // PASSO 2
    public void adicionarJogo() throws Exception {

        Scanner sc = new Scanner(System.in);

        System.out.print("ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        if (idExiste(id)) {
            System.out.println(
                    "ERRO: Já existe um jogo com este ID.");
            return;
        }

        System.out.print("Jogador 1: ");
        String j1 = sc.nextLine();

        System.out.print("Jogador 2: ");
        String j2 = sc.nextLine();

        System.out.print("Placar: ");
        String placar = sc.nextLine();

        System.out.print("Quadra: ");
        String quadra = sc.nextLine();

        Document doc = carregarDocumento();

        Element raiz = doc.getDocumentElement();

        Element jogo = doc.createElement("jogo");
        jogo.setAttribute("id", String.valueOf(id));

        Element jogador1 = doc.createElement("jogador1");
        jogador1.setTextContent(j1);

        Element jogador2 = doc.createElement("jogador2");
        jogador2.setTextContent(j2);

        Element placarTag = doc.createElement("placar");
        placarTag.setTextContent(placar);

        Element quadraTag = doc.createElement("quadra");
        quadraTag.setTextContent(quadra);

        jogo.appendChild(jogador1);
        jogo.appendChild(jogador2);
        jogo.appendChild(placarTag);
        jogo.appendChild(quadraTag);

        raiz.appendChild(jogo);

        salvarDocumento(doc);

        System.out.println("Jogo adicionado.");
    }

    // PASSO 3
    public void listarJogos() throws Exception {

        Document doc = carregarDocumento();

        NodeList lista = doc.getElementsByTagName("jogo");

        for (int i = 0; i < lista.getLength(); i++) {

            Element jogo = (Element) lista.item(i);

            System.out.println("\nID: " + jogo.getAttribute("id"));

            System.out.println("Jogador 1: " +
                    jogo.getElementsByTagName("jogador1").item(0).getTextContent());

            System.out.println("Jogador 2: " +
                    jogo.getElementsByTagName("jogador2").item(0).getTextContent());

            System.out.println("Placar: " +
                    jogo.getElementsByTagName("placar").item(0).getTextContent());

            System.out.println("Quadra: " +
                    jogo.getElementsByTagName("quadra").item(0).getTextContent());
        }
    }

    // PASSO 4
    public void alterarPlacar(int id, String novoPlacar)
            throws Exception {

        Document doc = carregarDocumento();

        NodeList lista = doc.getElementsByTagName("jogo");

        for (int i = 0; i < lista.getLength(); i++) {

            Element jogo = (Element) lista.item(i);

            if (Integer.parseInt(jogo.getAttribute("id")) == id) {

                jogo.getElementsByTagName("placar").item(0).setTextContent(novoPlacar);

                salvarDocumento(doc);

                System.out.println("Placar alterado.");
                return;
            }
        }
    }

    // PASSO 5
    public void alterarQuadra(int id, String novaQuadra)
            throws Exception {

        Document doc = carregarDocumento();

        NodeList lista = doc.getElementsByTagName("jogo");

        for (int i = 0; i < lista.getLength(); i++) {

            Element jogo = (Element) lista.item(i);

            if (Integer.parseInt(
                    jogo.getAttribute("id")) == id) {

                jogo.getElementsByTagName("quadra").item(0).setTextContent(novaQuadra);

                salvarDocumento(doc);

                System.out.println("Quadra alterada.");
                return;
            }
        }
    }

    // PASSO 6
    public void removerJogo(int id) throws Exception {

        Document doc = carregarDocumento();

        NodeList lista = doc.getElementsByTagName("jogo");

        for (int i = 0; i < lista.getLength(); i++) {

            Element jogo = (Element) lista.item(i);

            if (Integer.parseInt(jogo.getAttribute("id")) == id) {

                jogo.getParentNode().removeChild(jogo);

                salvarDocumento(doc);

                System.out.println("Jogo removido.");
                return;
            }
        }
    }

    // PASSO 7
    public void buscarPorId(int id) throws Exception {

        Document doc = carregarDocumento();

        NodeList lista = doc.getElementsByTagName("jogo");

        for (int i = 0; i < lista.getLength(); i++) {

            Element jogo = (Element) lista.item(i);

            if (Integer.parseInt(jogo.getAttribute("id")) == id) {

                System.out.println("Jogador 1: " + jogo.getElementsByTagName("jogador1").item(0).getTextContent());

                System.out.println("Jogador 2: " + jogo.getElementsByTagName("jogador2").item(0).getTextContent());

                return;
            }
        }

        System.out.println("Não encontrado.");
    }

    // PASSO 9
    public void contarJogos() throws Exception {

        Document doc = carregarDocumento();

        NodeList lista = doc.getElementsByTagName("jogo");

        System.out.println("Total de jogos: " + lista.getLength());
    }

    // PASSO 10
    public void filtrarQuadra(String quadra)
            throws Exception {

        Document doc = carregarDocumento();

        NodeList lista = doc.getElementsByTagName("jogo");

        for (int i = 0; i < lista.getLength(); i++) {

            Element jogo = (Element) lista.item(i);

            String q =
                    jogo.getElementsByTagName("quadra").item(0).getTextContent();

            if (q.equalsIgnoreCase(quadra)) {

                System.out.println("ID: " +jogo.getAttribute("id"));
            }
        }
    }

        // PASSO 11
    public void backup() throws Exception {

        Document doc = carregarDocumento();

        TransformerFactory tf =
                TransformerFactory.newInstance();

        Transformer transformer =
                tf.newTransformer();

        DOMSource source = new DOMSource(doc);

        StreamResult result =
                new StreamResult(new File("jogos_backup.xml"));

        transformer.transform(source, result);

        System.out.println("Backup criado.");
    }

    // PASSO 12
    public void ordenarPorID() throws Exception {

        Document doc = carregarDocumento();

        NodeList lista = doc.getElementsByTagName("jogo");

        Element[] jogos = new Element[lista.getLength()];

        for(int i = 0; i < lista.getLength(); i++) {
            jogos[i] = (Element) lista.item(i);
        }

        for(int i = 0; i < jogos.length - 1; i++) {

            int menor = i;

            for(int j = i + 1; j < jogos.length; j++) {

                int idAtual = Integer.parseInt(jogos[j].getAttribute("id"));

                int idMenor = Integer.parseInt(jogos[menor].getAttribute("id"));

                if(idAtual < idMenor) {
                    menor = j;
                }
            }

            Element aux = jogos[i];
            jogos[i] = jogos[menor];
            jogos[menor] = aux;
        }

        System.out.println("\nJogos ordenados:\n");

        for(Element jogo : jogos) {

            System.out.println("ID: " + jogo.getAttribute("id"));
        }
    }
}