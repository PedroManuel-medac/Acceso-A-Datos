
import javax.xml.parsers.*;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.*;
import java.io.*;


public class XPathDOM {
  public static void main(String[] args) {
    try {
            // Crear una instancia de DocumentBuilderFactory.
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            // Configurar la factoria para que el fichero que se carga está bien validado e ignora espacios en blanco.
            factory.setValidating(true);
            factory.setIgnoringElementContentWhitespace(true);

            //se crea un objeto DocumentBuilder por medio de la factoría creada previamente.
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Especificar el archivo XML que deseas analizar.
            File file = new File("C:\\Users\\PC204\\Desktop\\2DAM\\Acceso a datos\\fichero.xml");

            // Parsear (analizar) el archivo XML y obtener un objeto Document.
            Document doc = builder.parse(file);  
            doc.getDocumentElement().normalize();

            //Crear un objeto XPath para consultar el documento XML
            XPath xPath = XPathFactory.newInstance().newXPath();

            //Definir la expresión Xpath para obtener todas las bibliotecas
            String expresion = "/libraries/library";

            //Evaluar la expresión y obtener una lista de nodos de bibliotecas
            NodeList nodelist = (NodeList) xPath.compile(expresion).evaluate(doc, XPathConstants.NODESET);

            //Itera a través de la lista de nodos de bibliotecas
            for(int i = 0; i < nodelist.getLength(); i++){
              Node nNode = nodelist.item(i);
              System.out.println("\nElemento actual: " + nNode.getNodeName());
              System.out.println("\nElemento padre: " + nNode.getParentNode());

              if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elemento = (Element) nNode;
                System.out.println("Nombre: " + elemento.getElementsByTagName("name").item(0).getTextContent());
                System.out.println("Ciudad: " + elemento.getAttribute("location"));
                
              }

              //Definir la expresión Xpath para obtener todas las bibliotecas
            String expresion2 = "//books";

            //Evaluar la expresión y obtener una lista de nodos de bibliotecas
            NodeList nodelist2 = (NodeList) xPath.compile(expresion2).evaluate(nNode, XPathConstants.NODESET);

            //Itera a través de la lista de nodos de bibliotecas
            for(int j = 0; j < nodelist2.getLength(); j++){
              Node node2 = nodelist2.item(j);
              System.out.println("\nElemento actual: " + node2.getNodeName());

              if (node2.getNodeType() == Node.ELEMENT_NODE) {
                Element elemento2 = (Element) node2;
                System.out.println("titulo: " + elemento2.getElementsByTagName("title").item(j).getTextContent());
                System.out.println("autor: " + elemento2.getElementsByTagName("author").item(j).getTextContent());
                System.out.println("genero: " + elemento2.getElementsByTagName("genre").item(j).getTextContent());
                System.out.println("año: " + elemento2.getElementsByTagName("year").item(j).getTextContent());

                
              }
            }
            }


            

            

    } catch (Exception e) {
        e.printStackTrace();
    }
  }
}
