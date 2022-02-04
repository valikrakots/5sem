package Controller;

import Objects.Code;
import Objects.Element;
import Objects.Sentence;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class TextParser extends BaseParser {
    public TextParser(Parser next) {
        this.next = next;
    }

    /**
     * Метод, разбирающий входной текст на предложения и блоки кода
     * @param toParse исходный текст
     * @return список элементов, состоящий из предложений и блоков кода
     */
    public List<Element> parse(String toParse) {
        Pattern sentPattern = Sentence.getPattern();

        Pattern codePattern = Code.getPattern();
        Matcher codeMatcher = codePattern.matcher(toParse);

        ArrayList<Element> res = new ArrayList<Element>();

        int start = 0;
        while (codeMatcher.find()) {
            String beforeCode = toParse.substring(start, codeMatcher.start());
            Matcher sentMatcher = sentPattern.matcher(beforeCode);
            while (sentMatcher.find()) {
                res.add(
                        new Sentence(
                                beforeCode.substring
                                        (sentMatcher.start(), sentMatcher.end())));
            }

            res.add(new Code(toParse.substring(codeMatcher.start(), codeMatcher.end())));
            start = codeMatcher.end();
        }
        String left = toParse.substring(start);

        Matcher sentMatcher = Sentence.getPattern().matcher(left);

        while (sentMatcher.find()) {
            res.add(new Sentence(left.substring(sentMatcher.start(), sentMatcher.end())));
        }

        if (next != null) return next.parse(res);
        else return res;
    }

    @Override
    public List<Element> parse(List<Element> l) {
        return null;
    }
}
