import java.io.File;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int escolha; 
        
        File arquivo = new File("alunos.xml");
        

        if(!(arquivo.exists())){
            // Builds the document
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.newDocument();

            Element raiz = doc.createElement("alunos");

            doc.appendChild(raiz);


            // Turns this tree into a tangible file
            TransformerFactory tf = TransformerFactory.newInstance();

            Transformer transformer = tf.newTransformer();

            DOMSource source = new DOMSource(doc);

            StreamResult result = new StreamResult(arquivo);

            transformer.transform(source, result);

        }

            
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(arquivo);
        NodeList lista;
        int i;
        

            
            
            
        do {
            
            System.out.println("========= MENU =========\r\n" + 
            "1 - Cadastrar aluno\r\n" + 
            "2 - Exibir relatorio\r\n" + 
            "3 - Mostrar media\r\n" +
            "4 - Exibir maior nota\r\n" +
            "5 - Quantos alunos com nota >= 7.0" +
            "0 - Sair\r\n");
            System.out.print("Escolha uma opcao: ");
            escolha = sc.nextInt();
            
            switch (escolha){
                case 1:
                    
                    Element raiz = doc.getDocumentElement();
                    System.out.print("\nNome do aluno: ");    
                    String nomeString = sc.next();
                    System.out.print("\nNota do aluno (Max: 10): ");
                    double notaDouble = sc.nextDouble();
                    if(notaDouble > 10){
                        notaDouble = 10.0;
                    }
                    String notaString = String.valueOf(notaDouble);

                    Element aluno = doc.createElement("aluno");
                    Element nome = doc.createElement("nome");
                    Element nota = doc.createElement("nota");

                    raiz.appendChild(aluno);
                    aluno.appendChild(nome);
                    aluno.appendChild(nota);


                    nome.setTextContent(nomeString);
                    nota.setTextContent(notaString);

                    TransformerFactory tf = TransformerFactory.newInstance();

                    Transformer transformer = tf.newTransformer();

                    DOMSource source = new DOMSource(doc);

                    StreamResult result = new StreamResult(arquivo);

                    transformer.transform(source, result);

                    break;

                case 2:

                    if(!(arquivo.exists())){
                        System.out.println("Arquivo nao existe!");
                        break;
                    }

                    doc.getDocumentElement().normalize();

                    lista = doc.getElementsByTagName("aluno");
                    
                    for(i = 0; i < lista.getLength(); i++){
                        Node node = lista.item(i);

                        if(node.getNodeType() == Node.ELEMENT_NODE){
                            aluno = (Element) node;
                            
                            String nomeLista = aluno.getElementsByTagName("nome").item(0).getTextContent();

                            String notaLista = aluno.getElementsByTagName("nota").item(0).getTextContent();
                            
                            System.out.println("Aluno: " + nomeLista + " - Nota: " + notaLista);
                            System.out.println("----//----//----//----//----//----//----//----//----//");
                        }

                    }

                    break;
                
                case 3:

                    double media;
                    double soma = 0;
                    
                    if(!(arquivo.exists())){
                        System.out.println("Arquivo nao existe!");
                        break;
                    }

                    doc.getDocumentElement().normalize();

                    lista = doc.getElementsByTagName("aluno");

                    for(i = 0; i < lista.getLength(); i++){
                        Node node = lista.item(i);

                        if(node.getNodeType() == Node.ELEMENT_NODE){
                            aluno = (Element) node;

                            soma += Double.parseDouble(aluno.getElementsByTagName("nota").item(0).getTextContent());

                        }
                    }
                    media = soma / i;                    
                    System.out.println("Media das notas: " + media);

                    break;

                case 4:
                    
                    double maior = -1;
                    String aluno_maior = null;
                    if(!(arquivo.exists())){
                        System.out.println("Arquivo nao existe!");
                        break;
                    }

                    doc.getDocumentElement().normalize();

                    lista = doc.getElementsByTagName("aluno");

                    for(i = 0; i < lista.getLength(); i++){
                        Node node = lista.item(i);

                        if(node.getNodeType() == Node.ELEMENT_NODE){
                            aluno = (Element) node;

                            if(Double.parseDouble(aluno.getElementsByTagName("nota").item(0).getTextContent()) > maior){
                                maior = Double.parseDouble(aluno.getElementsByTagName("nota").item(0).getTextContent());
                                aluno_maior = aluno.getElementsByTagName("nome").item(0).getTextContent();

                            }

                        }
                        
                    }
                    System.out.println("Aluno com maior nota: " + aluno_maior + " - Nota: " + maior);
                    break;

                case 5:
                    int qtdAcima7 = 0;
                    if(!(arquivo.exists())){
                        System.out.println("Arquivo nao existe!");
                        break;
                    }

                    doc.getDocumentElement().normalize();

                    lista = doc.getElementsByTagName("aluno");

                    for(i = 0; i < lista.getLength(); i++){
                        Node node = lista.item(i);

                        if(node.getNodeType() == Node.ELEMENT_NODE){
                            aluno = (Element) node;
                            
                            if(Double.parseDouble(aluno.getElementsByTagName("nota").item(0).getTextContent()) >= 7.0){
                                qtdAcima7++;
                            }
                            
                            
                        
                        }

                    }
                    System.out.println(qtdAcima7 + " Alunos passaram com nota 7 ou acima.");
                    break;
                
                case 0:
                    System.out.println("Bye bye!");
                    break;

                default:
                    System.out.println("Numero Invalido");
                    break;
        }

    } while (escolha != 0);

    sc.close();
    }
}
