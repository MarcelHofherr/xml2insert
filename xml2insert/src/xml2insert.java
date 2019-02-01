import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;


public class xml2insert {

    public static void main(String arg[]) {


        try {


            File fXmlFile = new File("./test.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);



            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("page");

            System.out.println("----------------------------");

            for (int i = 0; i < nList.getLength(); i++) {

                Node nNode = nList.item(i);

                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    System.out.println("INSERT INTO table(title, content)" +
                            "\nVALUES('"+ eElement.getElementsByTagName("title").item(0).getTextContent() + "', '" + eElement.getElementsByTagName("text").item(0).getTextContent() + "')");
                    //System.out.println("Titel : " + eElement.getElementsByTagName("title").item(0).getTextContent());
                    //System.out.println("Text : " + eElement.getElementsByTagName("text").item(0).getTextContent());

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
