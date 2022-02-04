package Objects;

import java.util.List;
import java.util.regex.Pattern;



public class Text extends MultipleElements {

    static Pattern pattern = Pattern.compile(".+");

    public Text(List<Element> children) {
        super(children);
    }

    public static Pattern getPattern() {
        return pattern;
    }

    @Override
    public String toString() {
        return "Text{" +
                "children=" + children +
                '}';
    }
}
