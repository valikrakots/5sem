package Controller;

import Objects.Element;
import Objects.PunctuationMark;
import Objects.Sentence;
import Objects.Word;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SentenceParser extends BaseParser {

    public SentenceParser() {
        next = null;
    }

    public SentenceParser(Parser next) {
        this.next = next;
    }


    /**
     * Метод, разбирающий предложение на слова и знаки препинания
     * @param l список предложений
     * @return разобранный список предложений
     */
    @Override
    public List<Element> parse(List<Element> l) {
        Pattern sentToken = Pattern.compile("("
                + Word.getPattern().pattern()
                + "|"
                + PunctuationMark.getPattern().pattern()
                + ")");
        for (Element el : l) {
            if (el.getClass() == Sentence.class) {
                Matcher matcher = sentToken.matcher(((Sentence) el).getSentence());
                while (matcher.find()) {
                    String found = matcher.group();
                    if (PunctuationMark.getPattern().matcher(found).matches()) {
                        ((Sentence) el).add(new PunctuationMark(found));
                    } else {
                        ((Sentence) el).add(new Word(found));
                    }
                }

            }
        }
        if (next != null) return next.parse(l);
        else return l;
    }
}
