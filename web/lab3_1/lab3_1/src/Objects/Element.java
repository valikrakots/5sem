package Objects;

import java.util.regex.Pattern;



public interface Element {
    int count();

    static Pattern getPattern() {
        return null;
    }

    String getValue();
}
