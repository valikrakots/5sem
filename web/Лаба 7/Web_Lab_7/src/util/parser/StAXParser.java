package util.parser;

import exception.ParserException;


import model.tovars.Lastics;
import model.tovars.Pencils;
import model.tovars.Pens;
import model.tovars.Tovar;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * StAX parser of XML
 *
 * @version 1.0.0
 */

public class StAXParser implements TovarParser {

    private static final Logger logger = LogManager.getLogger("Parser");


    /**
     * Parse XML file to collective using DOM parser
     *
     * @param fileName name of the file that contains tovars stored in XML format
     * @return parsed tovars list
     * @throws ParserException if some error occurred while parsing XML file
     */
    @Override
    public List<Tovar> parse(String fileName) throws ParserException {
        logger.info("Starting Stax parsing");
        List<Tovar> tovars = new ArrayList<>();

        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader eventReader;
        try {
            eventReader = factory.createXMLEventReader(new FileReader(fileName));
        } catch (XMLStreamException | FileNotFoundException e) {
            throw new ParserException("Configuration StAX parser error", e);
        }
        String currentElement = "";
        boolean parsed;
        parsed = true;
        String name = "";
        String factoryName = "";
        String price = "";
        String par = "";


        while (eventReader.hasNext()) {
            XMLEvent event = null;
            try {
                event = eventReader.nextEvent();
            } catch (XMLStreamException e) {
                throw new ParserException("Fail to get eventReader", e);
            }
            String qName = "";

            switch (event.getEventType()) {
                case XMLStreamConstants.START_ELEMENT:
                    StartElement startElement = event.asStartElement();
                    qName = startElement.getName().getLocalPart();
                    currentElement = qName;
                    if (qName.equals("name") ||
                            qName.equals("factory") ||
                            qName.equals("price")||
                            qName.equals("width") ||
                            qName.equals("color")||
                            qName.equals("material")
                    ) {
                        parsed = false;
                    }
                    break;
                case XMLStreamConstants.CHARACTERS:
                    String value = event.asCharacters().getData();
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
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    EndElement endElement = event.asEndElement();
                    qName = endElement.getName().getLocalPart();
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
                    break;
            }
        }


        return tovars;
    }
}
