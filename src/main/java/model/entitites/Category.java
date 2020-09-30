package model.entitites;

import java.util.List;

public class Category {

    private String name;
    private List<Word> words;

    public Category(String name) {
        this.name = name;

    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public List<Word> getWords() { return words; }

    public void setWords(List<Word> words) { this.words = words; }
}
