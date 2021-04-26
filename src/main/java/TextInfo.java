/** @file TextInfo.java
  * A simple text analyzer.
  * The program counts the total number of characters in the text, the number of words,
  * the number of letters and numbers, and the number of non-printing special characters.
  * TODO: text language detector.
  */
import java.util.*;

public class TextInfo {
    private final static String LATIN_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private final static String CYRILLIC_ALPHABET = "јЅ¬√ƒ≈®∆«»… ЋћЌќѕ–—“”‘’÷„ЎўЏџ№ЁёяабвгдеЄжзийклмнопрстуфхцчшщъыьэю€";
    private final static String NUMERIC_ALPHABET = "0123456789";
    private final static String PUNCTUATION_MARKS = "!@#$%^&*_=+-/.?<>,:;{}[]\"'\\|()є~` ";
    private final static String SPECIAL_SYMBOLS = "\n\t\r\f\b";
    private enum LangType {LATIN, CYRILLIC, ANY};

    private String text;
    private String letterStatString;
    private int countSymbols;
    private int countSpecialSymbols;
    private int countLetters;
    private int countNumeric;
    private int countWords;
    private Map<Character, Integer> symbolStat;
    private LangType langType;

    /**Word count*/
    public static int howWords(String txt){
        int countW = 0;
        int i;
        String splits = PUNCTUATION_MARKS + SPECIAL_SYMBOLS;
        String letters = LATIN_ALPHABET + CYRILLIC_ALPHABET + NUMERIC_ALPHABET;

        if(txt.length()==0) return 0;

        for(i=0; i < txt.length() &&  splits.indexOf(txt.charAt(i)) != -1; i++);

        for(;i < txt.length(); i++){
            /* If it's a letter? */
            if(letters.indexOf(txt.charAt(i)) != -1){
                countW++;
                while(i < txt.length() && letters.indexOf(txt.charAt(i++)) != -1);
            }
            else{
                while(i < txt.length() && splits.indexOf(txt.charAt(i++)) != -1);
                i--;
            }
        }
        return countW;
    }

    public TextInfo(String txt) {
        text = txt;
        countSymbols = text.length();
        countWords = howWords(text);
        countLetters = 0;
        countNumeric = 0;
        countSpecialSymbols = 0;
        int countCyrillic = 0;
        int countLatin = 0;

        symbolStat = new HashMap<Character, Integer>();

        for(char ch : text.toCharArray()){
            if(CYRILLIC_ALPHABET.indexOf(ch) != -1) countCyrillic++;
            else if(LATIN_ALPHABET.indexOf(ch) != -1) countLatin++;
            else if(NUMERIC_ALPHABET.indexOf(ch) != -1) countNumeric++;
            else if(SPECIAL_SYMBOLS.indexOf(ch) != -1) countSpecialSymbols++;
            //≈сли буква есть в статистике
            if(symbolStat.containsKey(ch)){
                symbolStat.put(ch, symbolStat.get(ch)+1); //увеличиваем значение (счетчик букв) на 1
            }
            else{
                symbolStat.put(ch, 1); //≈сли буквы не было, то добавл€ем
            }
        }
        countLetters = countCyrillic+countLatin;

        /*Class Pair : letter + number of letters in the text.*/
        class Pare{
            char ch;
            int num;

            public char getCh() {
                return ch;
            }

            public void setCh(char ch) {
                this.ch = ch;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public Pare(char ch, int num) {
                this.ch = ch;
                this.num = num;
            }
        }

        letterStatString = "The symbols that occur most often : ";

        /*A list for sorting characters by the number of occurrences in the text.*/
        ArrayList<Pare> symbolStatSet = new ArrayList<Pare>();

        for(char ch : symbolStat.keySet()){
            symbolStatSet.add(new Pare(ch, symbolStat.get(ch)));
        }

        Collections.sort(symbolStatSet, new Comparator<Pare>() {
            @Override
            public int compare(Pare o1, Pare o2) {
                return o2.num-o1.num;
            }
        });

        Iterator<Pare> it = symbolStatSet.iterator();

        Pare par;
        for(int i=0; i<5 && it.hasNext(); i++){
            par = it.next();
            letterStatString+=("\'" + par.ch + "\' - " + par.num + "  " );
        }

        if(countCyrillic > countLatin*2 ){
            langType = LangType.CYRILLIC;
        }
        else if(countLatin > countCyrillic*2){
            langType = LangType.LATIN;
        }
        else{
            langType = LangType.ANY;
        }
    }

    /*TODO: text language detector.*/
    public String howLanguage(){
        String language = "Language ";

        if(langType == LangType.CYRILLIC){
            language += "is russians.";
        }
        else if(langType == LangType.LATIN){
            language += "is english.";
        }
        else{
            language += "is not defined.";
        }
        return language;
    }

    public String getText() {
        return text;
    }

    public int getCountSymbols() {
        return countSymbols;
    }

    public int getCountSpecialSymbols() {
        return countSpecialSymbols;
    }

    public int getCountLetters() {
        return countLetters;
    }

    public int getCountNumeric() {
        return countNumeric;
    }

    public int getCountWorlds() {
        return countWords;
    }

    public Map<Character, Integer> getSymbolStat() {
        return symbolStat;
    }

    public String getLetterStatString() {
        return letterStatString;
    }

    public void setLetterStatString(String letterStatString) {
        this.letterStatString = letterStatString;
    }
}
