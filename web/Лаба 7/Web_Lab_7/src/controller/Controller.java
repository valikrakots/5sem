package controller;

import exception.ParserException;
import exception.XMLValidatorException;
import model.ParsingModeEnum;
import model.catalog.Catalog;
import model.tovars.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import util.XMLValidator;
import util.parser.DOMParser;
import util.parser.SAXParser;
import util.parser.StAXParser;
import util.writers.WriterDOM;
import util.writers.WriterStAX;

import java.util.List;


/**
 * Basic class demonstrates functions of classes
 *
 * @version 1.0.0
 */
public class Controller {

    /**
     * logging via log4j
     */
    private static final Logger logger = LogManager.getLogger(Controller.class.getName());

    /**
     * Catalog
     */
    private Catalog catalog;


    public Catalog getCatalog() { return catalog;}



    /*
     * Constructor that create controller
     *
     * @param Catalog catalog
     */
    public Controller(Catalog catalog) {
        this.catalog = catalog;
    }


    public List<Tovar> parseTovarList(String file, ParsingModeEnum mode) throws ParserException {
        switch (mode) {
            case DOM:
                return (new DOMParser()).parse(file);
            case SAX:
                return (new SAXParser()).parse(file);
            case StAX:
                return (new StAXParser()).parse(file);
            default:
                return null;
        }
    }

    /**
     * Validates XML file with given schema
     * @param file XML file
     * @param schema XSD file
     * @return true if validation succeed, otherwise false
     */
    public boolean validate(String file, String schema) throws XMLValidatorException {
        boolean result = XMLValidator.validate(file, schema);
        if (result) {
            logger.info("Validation succeed");
        } else {
            logger.info("Validation failed");
        }
        return result;
    }

    /**
     * Method to add new tovar
     */
    public void addTovar(Tovar s) {
        catalog.addTovar(s);
    }

    /**
     * Method to demonstrate work with  classes
     *
     * @return some work result to show it on view
     */
    public List<Tovar> getTovars() {
        return catalog.getTovars();
    }


    public void writeDOM(String path){
        WriterDOM writerDOM = new WriterDOM();
        catalog.addTovar(new Lastics("Factory 1", "mapped", 12., "silicon"));
        catalog.addTovar((new Pens("Factory 2", "pen1", 14., "green")));
        catalog.addTovar(new Pencils("Factory3", "pencil1", 19.,12));
        writerDOM.xmlWriterDOM(path, this.getTovars());
    }

    public void writeStAX(String path){
        WriterStAX writerStAX = new WriterStAX();
        catalog.addTovar(new Lastics("Factory 1", "mapped", 12., "silicon"));
        catalog.addTovar((new Pens("Factory 2", "pen1", 14., "green")));
        catalog.addTovar(new Pencils("Factory3", "pencil1", 19.,12));
        writerStAX.xmlWriterStAX(path, this.getTovars());
    }
}