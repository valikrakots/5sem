package util.parser;

import exception.ParserException;
import model.tovars.Tovar;

import java.util.List;

/**
 * Parsing interface
 *
 *
 * @version 1.0.0
 */

public interface TovarParser {
    List<Tovar> parse(String fileName) throws ParserException;
}
