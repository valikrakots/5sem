package Objects;

import java.util.regex.Pattern;

/**
 * Класс Код
 *
 * @author Александра Малявко
 * @version 2020
 */

public class Code extends SingleElement {
    static Pattern pattern = Pattern.compile("<code>.+?</code>");

    String code;

    public Code(String s) {
        code = s;
    }

    public static Pattern getPattern() {
        return pattern;
    }

    @Override
    public String toString() {
        return "\n\nCode{" +
                "code='" + code + '\'' +
                '}';
    }

    @Override
    public String getValue() {
        return "\n" + code + "\n";
    }
}