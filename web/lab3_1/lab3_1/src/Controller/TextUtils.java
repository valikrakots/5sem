package Controller;
import Objects.*;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TextUtils {




    public static String printSentencesWithBig(Text text) {
        String s = "";
        for (Element element : text.getElements()) {
            if (element.getClass() == Sentence.class) {
                for (Element element2 : ((Sentence) element).getElements()) {
                    if (element2.getClass() == Word.class) {
                        String s2 = String.valueOf(Character.toUpperCase(element2.getValue().charAt(0))) + element2.getValue().substring(1, element2.getValue().length() );
                        s += s2;
                    }
                    else{
                        s += element2.getValue();
                    }
                }
            }
            else{
                s += element.getValue();
            }
        }
        return s;
    }


    public static String printSequence(String toParse){
        Pattern seqPat = Pattern.compile("[^a-zA-zА-Яа-я ]+");
        Matcher seqMatcher = seqPat.matcher(toParse);
        int l = 0;
        int start = 0;
        String s = "";
        while (seqMatcher.find()){
            String seq = toParse.substring(seqMatcher.start(),seqMatcher.end());
            if(seq.length() > l){
                l = seq.length();
                s = seq;
            }
        }
        return s;
    }



}
