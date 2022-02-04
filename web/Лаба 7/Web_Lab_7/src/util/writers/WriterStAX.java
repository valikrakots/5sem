package util.writers;

import model.tovars.Lastics;
import model.tovars.Pencils;
import model.tovars.Pens;
import model.tovars.Tovar;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.xml.stream.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Class WriterStAX
 *
 * @version 1.0.0
 */
public class WriterStAX {

    private static Logger LOGGER = LogManager.getLogger(WriterStAX.class);

    /**
     * The method write data to XML by StAX.
     * @param pathXml - path to XML
     * @param tovars - list of tovar objects
     */
    public void xmlWriterStAX(String pathXml, List<Tovar> tovars) {
        try{
            XMLOutputFactory output = XMLOutputFactory.newInstance();
            XMLStreamWriter writer = output.createXMLStreamWriter(new FileWriter(pathXml));

            LOGGER.debug("Write data to XML by StAX.");
            writer.writeStartDocument("1.0");
            writer.writeStartElement("tovars");

            for (Tovar s : tovars) {
                writer.writeStartElement(s.getClassName());

                // name
                writer.writeStartElement("name");
                writer.writeCharacters("" + s.getName());
                writer.writeEndElement();

                // factory
                writer.writeStartElement("factory");
                writer.writeCharacters("" + s.getFactoryName());
                writer.writeEndElement();

                // price
                writer.writeStartElement("price");
                writer.writeCharacters(String.format("%s", s.getPrice()));
                writer.writeEndElement();


                if(s.getClassName().equals("Lastic"))
                {
                    writer.writeStartElement("material");
                    Lastics s1 = (Lastics)s;
                    writer.writeCharacters(s1.getMaterial());
                    writer.writeEndElement();
                }
                else if(s.getClassName().equals("Pen"))
                {
                    Pens s1 = (Pens) s;
                    writer.writeStartElement("color");
                    writer.writeCharacters(s1.getColor());
                    writer.writeEndElement();
                }
                else if(s.getClassName().equals("Pencil"))
                {
                    Pencils s1 = (Pencils) s;
                    String par = String.valueOf(s1.getWidth());
                    writer.writeStartElement("width");
                    writer.writeCharacters(par);
                    writer.writeEndElement();

                }

                writer.writeEndElement();
            }
            writer.writeEndElement();

            writer.writeEndDocument();
            writer.flush();
            writer.close();
        } catch(XMLStreamException | IOException e){
            e.printStackTrace();
        }
    }
}
