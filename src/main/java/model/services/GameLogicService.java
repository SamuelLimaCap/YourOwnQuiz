package model.services;

import model.entitites.BoolBody;
import model.entitites.Category;
import model.entitites.Word;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class GameLogicService {

    private ArrayList<Word> words = new ArrayList<Word>();
    private Integer currentId;
    private ArrayList<Category> categories = new ArrayList<>();

    private int correctTimes;

    private int wrongTimes;

    private Random random = new Random();

    public ArrayList<Word> getWords() { return words; }

    public Integer getCurrentId() { return currentId; }

    public ArrayList<Category> getCategories() { return categories; }

    public int getCorrectTimes() { return correctTimes; }

    public int getWordsRemaining() { return words.size(); }

    public int getWrongTimes() { return wrongTimes; }

    public void addCorrectTime() { correctTimes++; }
    public void addWrongTime() { wrongTimes++; }

    public void loadCategories() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("options.properties"));
            String readCategory;
            while ((readCategory = br.readLine()) != null) {
                categories.add(new Category(readCategory));
            }
            br.close();
        } catch (Exception e ) { e.printStackTrace(); }
    }

    public void loadWordsQuiz() {
        try {
            for (Category category : categories) {
                BufferedReader br = new BufferedReader(
                    new FileReader(new File("options/" + category.getName() + ".txt").getCanonicalFile()));

                String readWords;
                ArrayList<Word> wordPerCategory = new ArrayList<>();
                while ((readWords = br.readLine()) != null) {
                    String[] wordsTxt = readWords.split(",");
                    Word word = new Word(wordsTxt[0], wordsTxt[1], category);
                    words.add(word);
                    wordPerCategory.add(word);
                }
                category.setWords(wordPerCategory);
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    public BoolBody<Word> chooseWord() {
        if (getWordsRemaining() != 0) {
            Random random = new Random();
            int num = random.nextInt(words.size());
            Word word = words.get(num);
            currentId = num;
            return new BoolBody(true, word);
        // TODO mandar front-end atualizar os dados
        }
        else {return new BoolBody(false, null);}
    }
}
