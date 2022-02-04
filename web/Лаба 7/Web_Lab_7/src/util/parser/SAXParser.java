package util.parser;

import exception.ParserException;

import model.tovars.Lastics;
import model.tovars.Pencils;
import model.tovars.Pens;
import model.tovars.Tovar;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * SAX parser of XML
 *
 *
 * @version 1.0.0
 */

public class SAXParser implements TovarParser {

    private static final Logger logger = LogManager.getLogger("Parser");

    /**
     * Parse XML file to collective using DOM parser
     *
     * @param fileName name of the file that contains songs stored in XML format
     * @return parsed songs list
     * @throws ParserException if some error occurred while parsing XML file
     */
    @Override
    public List<Tovar> parse(String fileName) throws ParserException {
        logger.info("Starting SAX parsing");
        List<Tovar> tovars;

        try {
            File inputFile = new File(fileName);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            javax.xml.parsers.SAXParser saxParser = factory.newSAXParser();
            Handler handler = new Handler();
            saxParser.parse(inputFile, handler);
            tovars = handler.getTovars();
        } catch (IOException | SAXException | ParserConfigurationException e) {
            throw new ParserException("Configuration SAX parser error", e);
        }

        logger.info("Finish SAX parsing");
        return tovars;
    }

    /**
     * Tags handler
     */
    private class Handler extends DefaultHandler {


        private List<Tovar> tovars = new ArrayList<>();

        private boolean parsed = true;
        private String currentElement;

        String name = "";
        String factoryName = "";
        String price = "";
        String par = "";

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            currentElement = qName;
            if (qName.equals("name") ||
                    qName.equals("factory") ||
                    qName.equals("price")||
                    qName.equals("width") ||
                    qName.equals("color")||
                    qName.equals("material")) {
                parsed = false;
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            switch (qName) {
                case "Lastic":
                    tovars.add(new Lastics(factoryName,name,Double.parseDouble(price),par));
                    break;
                case "Pen":
                    tovars.add(new Pens(factoryName,name,Double.parseDouble(price),par));
                    break;
                case "Pencil":
                    tovars.add(new Pencils(factoryName,name,Double.parseDouble(price),Integer.parseInt(par)));
                    break;
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String value = new String(ch, start, length);
            if (!parsed) {
                switch (currentElement) {
                    case "name":
                        name = value;
                        break;
                    case "factory":
                        factoryName=value;
                        break;
                    case "price":
                        price = value;
                        break;
                    case "color":
                        par=value;
                        break;
                    case "width":
                        par = value;
                        break;
                    case "material":
                        par = value;
                        break;
                }

                parsed = true;
            }


        }


        public List<Tovar> getTovars() {
            return tovars;
        }
    }
}

