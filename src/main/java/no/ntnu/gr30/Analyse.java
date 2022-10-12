package no.ntnu.gr30;

public class Analyse {
    private final static String STATEMENT = "statement";
    private final static String QUESTION = "question";

    public static String analyseString(String string) {
        boolean isQuestion;
        String resultPart2;

        char[] chars = string.toCharArray();
        isQuestion = '?' == chars[chars.length - 1];
        String sentenceWithoutLastChar = string.substring(0, string.length()-1);

        String resultPart1 = isQuestion ? QUESTION : STATEMENT;
        if (sentenceWithoutLastChar.isBlank()){
            resultPart2 = "0";
        } else {
            String[] words = sentenceWithoutLastChar.split("\s");
            resultPart2 = String.valueOf(words.length);
        }

        return resultPart1 + " " + resultPart2;
    }
}
