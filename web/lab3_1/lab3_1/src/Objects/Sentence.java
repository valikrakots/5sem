package Objects;

import java.util.ArrayList;

import java.util.regex.Pattern;


public class Sentence extends MultipleElements {

    static Pattern pattern = Pattern.compile("(.)+?" +
            "(\\.\\.\\.|" +                //...
            "!!!|" +                       //!!!
            "\\?\\?\\?|" +                 //???
            "\\?!|" +                      //?!
            "!\\.\\.|" +                   //!..
            "\\?\\.\\.|" +                 //?..
            "\\.|!|\\?)");                 //. or ! or ?);

    private final String sentence;

    public Sentence(String s) {
        super();
        sentence = " " + s.trim();
    }

    public static Pattern getPattern() {
        return pattern;
    }

    public String getSentence() {
        return sentence;
    }

    @Override
    public String toString() {
        return "\n\nSentence{" +
                "sentence='" + sentence + '\'' +
                ", children=" + children +
                "}";
    }
}