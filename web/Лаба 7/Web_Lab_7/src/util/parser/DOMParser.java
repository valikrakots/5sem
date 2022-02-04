package util.parser;

import exception.ParserException;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import model.tovars.Lastics;
import model.tovars.Pencils;
import model.tovars.Pens;
import model.tovars.Tovar;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * DOM parser of XML
 *
 * @version 1.0.0
 */

public class DOMParser implements TovarParser {

    private static final Logger logger = LogManager.getLogger("Parser");

    /**
     * Parse XML file to collective using DOM parser
     *
     * @param fileName name of the file that contains medicines stored in XML format
     * @return parsed tovars list
     * @throws ParserException if some error occurred while parsing XML file
     */
    @Override
    public List<Tovar> parse(String fileName) throws ParserException {
        logger.info("Starting DOM parsing");
        List<Tovar> tovars = new ArrayList<>();

        File inputFile = new File(fileName);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
        Document doc = null;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(inputFile);
        } catch (SAXException | IOException | ParserConfigurationException e) {
            throw new ParserException("Configuration DOM parser error", e);
        }
        doc.getDocumentElement().normalize();


        NodeList lasticNodes = doc.getElementsByTagName("Lastic");
        NodeList penNodes = doc.getElementsByTagName("Pen");
        NodeList pencilNodes = doc.getElementsByTagName("Pencil");



        for (int i = 0; i < lasticNodes.getLength(); ++i) {
            Node node = lasticNodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                String name = element.getElementsByTagName("name").item(0).getTextContent();
                String factory = element.getElementsByTagName("factory").item(0).getTextContent();
                String price = element.getElementsByTagName("price").item(0).getTextContent();
                String material = element.getElementsByTagName("material").item(0).getTextContent();

                tovars.add(new Lastics(factory, name,
                        Double.parseDouble(price),
                        material));
            }
        }

        for (int i = 0; i < penNodes.getLength(); ++i) {
            Node node = penNodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                String name = element.getElementsByTagName("name").item(0).getTextContent();
                String factory = element.getElementsByTagName("factory").item(0).getTextContent();
                String price = element.getElementsByTagName("price").item(0).getTextContent();
                String color = element.getElementsByTagName("color").item(0).getTextContent();


                tovars.add(new Pens(factory,name,Double.parseDouble(price),color));
            }
        }

        for (int i = 0; i < pencilNodes.getLength(); ++i) {
            Node node = pencilNodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                String name = element.getElementsByTagName("name").item(0).getTextContent();
                String factory = element.getElementsByTagName("factory").item(0).getTextContent();
                String price = element.getElementsByTagName("price").item(0).getTextContent();
                String width = element.getElementsByTagName("width").item(0).getTextContent();

                tovars.add(new Pencils(factory,name,Double.parseDouble(price),Integer.parseInt(width)));

            }
        }



        logger.info("Finish DOM parsing");
        return tovars;
    }

}
