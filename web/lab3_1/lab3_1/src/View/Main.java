package View;

import Controller.Exception.FileReaderException;
import Controller.Exception.LoggerException;
import Controller.FileReader;
import Controller.SentenceParser;
import Controller.TextParser;
import Controller.TextUtils;
import Objects.Element;
import Objects.Text;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;





public class Main{


    public static Locale askLocale() {
        System.out.println("Пожалуйста, выберите язык / Please select the language / Калі ласка, выберыце мову : \n1. Русский\n2. English\n3. Беларуская");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        return switch (choice) {
            case 1 -> new Locale("ru", "RU");
            case 2 -> new Locale("en", "US");
            case 3 -> new Locale("be", "BY");
            default -> Locale.getDefault();
        };
    }

    public static void main(String[] args) throws IOException, LoggerException {
        Logger logger = null;

        try(FileInputStream ins = new FileInputStream("C:\\Users\\valll\\Documents\\programming\\5 сем\\web\\lab3_1\\log.config")){
            LogManager.getLogManager().readConfiguration(ins);
            logger = Logger.getLogger(Main.class.getName());
        }catch (Exception e){
            throw new LoggerException("Logger config error: " + e.getMessage(), e);
        }

        Locale locale = askLocale();
        ResourceBundle resourceBundle =
                ResourceBundle.getBundle("Resources.lab3",locale);
        logger.log(Level.INFO,"Locale is set");



        String toParse;
        try {
            toParse = FileReader.read("src\\file.txt");
        } catch (FileReaderException exception) {
            logger.warning(exception.getMessage());
            return;
        }
        logger.log(Level.INFO,"File read" );

        SentenceParser sentenceParser = new SentenceParser();

        TextParser parser = new TextParser(sentenceParser);


        List<Element> lst = parser.parse(toParse);
        Text text = new Text(lst);
        logger.log(Level.INFO,"Text and sentences are parsed");


        System.out.println("\n----- " + resourceBundle.getString("text.parsing.result"));
        System.out.println(text);

        System.out.println("\n----- " + resourceBundle.getString("rebuilt.text"));
        System.out.println(text.getValue());
        logger.log(Level.INFO,"Main tasks are done");



        System.out.println("\n----- " + resourceBundle.getString("words.upper"));
        System.out.println(TextUtils.printSentencesWithBig(text));


        System.out.println("\n----- " + resourceBundle.getString("sequence.sorted"));
        System.out.println(TextUtils.printSequence(toParse));
        logger.log(Level.INFO,"Additional tasks are done");
    }
}