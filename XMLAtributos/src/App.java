import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class App {
    public static void main(String[] args) throws Exception {

        File arquivo = new File("cardapio.xml");

        
        // Caso nao existe
        if(!(arquivo.exists())){
            // Builds the document

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = factory.newDocumentBuilder();

            
            TransformerFactory tf = TransformerFactory.newInstance();
            
            Transformer transformer = tf.newTransformer();
        

            Document doc = builder.newDocument();
            
            Element raiz = doc.createElement("cardapio");
            
            doc.appendChild(raiz);
            
            DOMSource source = new DOMSource(doc);
            
            StreamResult result = new StreamResult(arquivo);
            
            transformer.transform(source, result);
            
        }
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        
        DocumentBuilder builder = factory.newDocumentBuilder();
        
        
        TransformerFactory tf = TransformerFactory.newInstance();
        
        Transformer transformer = tf.newTransformer();
        
        Document doc = builder.parse(arquivo);
        
        Element raiz = doc.getDocumentElement();
        
        // ABOVE ARE FACTORIES AND BUILDERS

        Element prod1 = doc.createElement("produto");
        prod1.setAttribute("id", "1");
        prod1.setAttribute("categoria", "bebida");

        Element nome1 = doc.createElement("nome");
        Element preco1 = doc.createElement("preco");
        Element disp1 = doc.createElement("disponivel");

        nome1.setTextContent("ORANGE JOE");
        preco1.setTextContent("7.50");
        disp1.setTextContent("true");

        Element prod2 = doc.createElement("produto");
        prod2.setAttribute("id", "2");
        prod2.setAttribute("tempoPreparo", "30:00");
        prod2.setAttribute("categoria", "comida");
      
        Element nome2 = doc.createElement("nome");
        Element preco2 = doc.createElement("preco");
        Element disp2 = doc.createElement("disponivel");

        nome2.setTextContent("TOMATINATOR");
        preco2.setTextContent("11.99");
        disp2.setTextContent("true");

        Element prod3 = doc.createElement("produto");
        prod3.setAttribute("id", "3");
        prod3.setAttribute("categoria", "bebida");

        Element nome3 = doc.createElement("nome");
        Element preco3 = doc.createElement("preco");
        Element disp3 = doc.createElement("disponivel");

        nome3.setTextContent("BONK! ATOMIC PUNCH");
        preco3.setTextContent("2.50");
        disp3.setTextContent("false");

        Element prod4 = doc.createElement("produto");
        prod4.setAttribute("id", "4");
        prod4.setAttribute("tempoPreparo", "13:00");
        prod4.setAttribute("categoria", "comida");
        prod4.setAttribute("promocao", "true");
        
        Element nome4 = doc.createElement("nome");
        Element preco4 = doc.createElement("preco");
        Element disp4 = doc.createElement("disponivel");

        nome4.setTextContent("Crescent Moon Croissant");
        preco4.setTextContent("9.75");
        disp4.setTextContent("true");


        raiz.appendChild(prod1);
        raiz.appendChild(prod2);
        raiz.appendChild(prod3);
        raiz.appendChild(prod4);

        prod1.appendChild(nome1);
        prod1.appendChild(preco1);
        prod1.appendChild(disp1);

        prod2.appendChild(nome2);
        prod2.appendChild(preco2);
        prod2.appendChild(disp2);

        prod3.appendChild(nome3);
        prod3.appendChild(preco3);
        prod3.appendChild(disp3);

        prod4.appendChild(nome4);
        prod4.appendChild(preco4);
        prod4.appendChild(disp4);



        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(arquivo);

        transformer.transform(source, result);        

    }
}
