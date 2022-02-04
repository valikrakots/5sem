package runner;

import controller.Controller;
import exception.ParserException;
import exception.XMLValidatorException;
import model.ParsingModeEnum;
import model.catalog.Catalog;
import model.catalog.CatalogManager;
import model.tovars.Tovar;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Objects;

/**
 * Runner class which show work with parsers
 * @version 1.0.0
 */
public class Runner {

    private static final String FILE_XML = "tovars.xml";
    private static final String FILE_XSD = "xsd_schema.xsd";

    /**
     * logging via log4j
     */
    private static final Logger logger = LogManager.getLogger(Runner.class.getName());

    /**
     * The Client method
     *
     * @param args command line parameters
     */
    public static void main(String[] args) {
        logger.info("Start work");


        var controller = new Controller(new Catalog(new CatalogManager()));
        logger.info("Create Catalog");

        /*controller.writeStAX(FILE_XML);
        printTovarsList("List of tovars: ", controller.getTovars());
        logger.info("Files written with StAX");*/

        controller.writeDOM(FILE_XML);
        printTovarsList("List of tovars: ", controller.getTovars());
        logger.info("Files written with DOM");

        boolean result = false;
        try {
            result = controller.validate(FILE_XML, FILE_XSD);
        } catch (XMLValidatorException e) {
            logger.warn(e);
        }
        if (result) {
            logger.info("Valid xml");
        } else {
            logger.info("Failed to validate xml");
            return;
        }

        ParsingModeEnum mode = null;


        //mode=ParsingModeEnum.StAX;
        mode=ParsingModeEnum.DOM;
        List<Tovar> tovars;
        logger.info("Try to parse xml");
        try {
            tovars = controller.parseTovarList(FILE_XML, mode);
        } catch (ParserException e) {
            logger.warn(e);
            return;
        }
        logger.info("Add parsed tovars to catalog");
        controller.getTovars().clear();
        for (Tovar t : Objects.requireNonNull(tovars)) {
            controller.addTovar(t);
        }

        printTovarsList("List of parsed: ", controller.getTovars());

    }


    public static void printTovarsList(String textBefore, List<Tovar> list) {
        logger.info("\n" + "-----------" + textBefore + "---------");
        if (list.size() > 0) {
            for (Tovar t : list) {
                logger.info("\n" + t.getInfo());
            }
        } else {
            logger.info("No items");
        }
    }

}
