package com.smileyjoedev.data;

import java.util.Random;

public class Data extends Random{

    public static final String PHONE_NUMBER_COUTRY_RSA = "+27";

    private static final String FILE_NAMES = "names.txt";
    private static final String FILE_LOREM = "lorem.txt";
    private static final String FILE_TLD = "tld.txt";

    private static final String DELIM_NAMES = ",";
    private static final String DELIM_TLD = ",";
    private static final String DELIM_WORDS = " ";
    private static final String DELIM_LOREM = "\\\\n";

    private static final int PHONE_NUMBER_LENGTH = 10;

    private static Data data;

    private String[] names;
    private String[] paragraphs;
    private String[] words;
    private String[] tlds;

    public static Data getInstance(){
        if(data == null){
            data = new Data();
        }

        return data;
    }

    public Data() {
        names = Resource.get(FILE_NAMES).split(DELIM_NAMES);
        paragraphs = Resource.get(FILE_LOREM).split(DELIM_LOREM);
        words = (paragraphs[0] + paragraphs[1]).split(DELIM_WORDS);
        tlds = Resource.get(FILE_TLD).split(DELIM_TLD);
    }

    public String nextName(){
        return names[nextInt(names.length)];
    }

    public String nextSentence(int minWords, int maxWords){
        String sentence = null;
        int numWords = nextInt(maxWords) + minWords;

        for(int i = 0; i < numWords; i++){
            if(sentence == null){
                sentence = "";
            } else {
                sentence += " ";
            }
            sentence += nextWord();
        }
        String firstChar = sentence.substring(0,1);
        sentence = sentence.replaceFirst(firstChar, firstChar.toUpperCase());
        return sentence + ".";
    }

    public String nextWebsite(){
        return "www." + nextWord().toLowerCase() + tlds[nextInt(tlds.length)];
    }

    public String nextEmail(){
        return nextEmail(nextName());
    }

    public String nextEmail(String name){
        return name.replace(" ",".").toLowerCase() + "@" + nextWord() + tlds[nextInt(tlds.length)];
    }

    public String nextPhoneNumber(){
        return nextPhoneNumber("0");
    }

    public String nextPhoneNumber(String countryCode){
        String number = countryCode;

        for(int i = 0; i < PHONE_NUMBER_LENGTH-1; i++){
            number += Integer.toString(nextInt(10));
        }

        return number;
    }

    public String nextWord(){
        return nextWord(false);
    }

    public String nextWord(boolean forceCaps){
        String word = words[nextInt(words.length)];
        word = word.replace(".", "");
        word = word.replace(",", "");

        if(forceCaps){
            String firstChar = word.substring(0,1);
            word = word.replaceFirst(firstChar, firstChar.toUpperCase());
        }

        return word.trim();
    }

    public String nextParagraph(){
        return nextParagraph(nextInt(5));
    }

    public String nextParagraph(int numberParagraphs){
        String paragraph = "";

        for(int i = 0; i < numberParagraphs; i++){
            if(!paragraph.equals("")){
                paragraph += System.lineSeparator() + System.lineSeparator();
            }

            paragraph += paragraphs[nextInt(paragraphs.length)];
        }

        return paragraph;
    }

}
