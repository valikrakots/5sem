package util.writers;

import model.tovars.Lastics;
import model.tovars.Pencils;
import model.tovars.Pens;
import model.tovars.Tovar;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Class WriterDOM
 *
 *
 * @version 1.0.0
 */
public class WriterDOM {

    private static Logger LOGGER = LogManager.getLogger(WriterDOM.class);

    /**
     * The method write data to XML by DOM.
     * @param pathXml - path to XML
     * @param tovars - list of tovar objects
     */
    public void xmlWriterDOM(String pathXml, List<Tovar> tovars) {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

            LOGGER.debug("The method write data to XML by DOM.");

            Element tov = document.createElement("tovars");
            document.appendChild(tov);

            for (Tovar s : tovars) {
                Element tb = document.createElement(s.getClassName());
                tov.appendChild(tb);

                Element costTariff = document.createElement("name");
                costTariff.setTextContent(""+s.getName());
                tb.appendChild(costTariff);

                Element costMinuteInNet = document.createElement("factory");
                costMinuteInNet.setTextContent(""+s.getFactoryName());
                tb.appendChild(costMinuteInNet);

                Element costSms = document.createElement("price");
                costSms.setTextContent(String.format("%s", s.getPrice()));
                tb.appendChild(costSms);



                if(s.getClassName().equals("Lastic")) {
                    Element material = document.createElement("material");
                    material.setTextContent(((Lastics)s).getMaterial()) ;
                    tb.appendChild(material);
                }
                else if(s.getClassName().equals("Pen")) {
                    Element color = document.createElement("color");
                    color.setTextContent(((Pens)s).getColor()) ;
                    tb.appendChild(color);
                }
                else if(s.getClassName().equals("Pencil")) {
                    Element width = document.createElement("width");
                    width.setTextContent(String.valueOf(((Pencils)s).getWidth())) ;
                    tb.appendChild(width);
                }
            }

            Transformer t=TransformerFactory.newInstance().newTransformer();
            t.transform(new DOMSource(document), new StreamResult(new FileOutputStream(pathXml)));

            System.out.println("Write completed.");

        } catch (ParserConfigurationException | TransformerException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
