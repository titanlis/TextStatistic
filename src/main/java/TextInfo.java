import java.util.*;
import java.util.stream.Collectors;

public class TextInfo {
    private final static String LATIN_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private final static String CYRILLIC_ALPHABET = "�����Ũ����������������������������������������������������������";
    private final static String NUMERIC_ALPHABET = "0123456789";
    private final static String PUNCTUATION_MARKS = "!@#$%^&*_=+-/.?<>,:;{}[]\"'\\|()�~` ";
    private final static String SPECIAL_SYMBOLS = "\n\t\r\f\b";

    private String text;
    private String letterStatString;
    private int countSymbols;
    private int countSpecialSymbols;
    private int countLetters;
    private int countNumeric;
    private int countWords;
    private Map<Character, Integer> symbolStat;
    private Boolean cyrillic;

    public static int howWords(String txt){
        int countW = 0;
        int i;
        String splits = PUNCTUATION_MARKS + SPECIAL_SYMBOLS;
        String letters = LATIN_ALPHABET + CYRILLIC_ALPHABET + NUMERIC_ALPHABET;

        if(txt.length()==0) return 0;

        for(i=0; i < txt.length() &&  splits.indexOf(txt.charAt(i)) != -1; i++);

        for(;i < txt.length(); i++){
            /* ���� ��� �����. */
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
            //���� ����� ���� � ����������
            if(symbolStat.containsKey(ch)){
                symbolStat.put(ch, symbolStat.get(ch)+1); //����������� �������� (������� ����) �� 1
            }
            else{
                symbolStat.put(ch, 1); //���� ����� �� ����, �� ���������
            }
        }
        countLetters = countCyrillic+countLatin;

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

        SortedSet<Pare> symbolStatSet = new TreeSet<Pare>();

        for(char ch : symbolStat.keySet()){
            symbolStatSet.add(new Pare(ch, symbolStat.get(ch)));
        }

        for(int i=0; i<5 && i<symbolStatSet.size(); i++){
            letterStatString+=("\'"  );
        }
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

    public Boolean getCyrillic() {
        return cyrillic;
    }
}
