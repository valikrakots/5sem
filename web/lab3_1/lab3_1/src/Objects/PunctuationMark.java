package Objects;

import java.util.regex.Pattern;

/**
 * Класс Знак препинания
 *
 * @author Александра Малявко
 * @version 2020
 */

public class PunctuationMark extends SingleElement {
    static Pattern pattern = Pattern.compile(
            "\\.\\.\\.|" +          //...
                    "!!!|" +        //!!!
                    "\\?\\?\\?|" +  //???
                    "\\?!|" +       //?!
                    "!\\.\\.|" +    //!..
                    "\\?\\.\\.|" +  //?..
                    "[ !\"\\#$%&()*+,\\-./:;<=>?@\\[\\\\\\]^_‘{|}~]");

    private final String mark;

    public PunctuationMark(String mark) {
        this.mark = mark;
    }

    public static Pattern getPattern() {
        return pattern;
    }

    @Override
    public String toString() {
        return "PunctuationMark{" +
                "mark='" + mark + '\'' +
                '}';
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public String getValue() {
        return mark;
    }
}