package model.entitites;

public class Word {

    private String original;
    private String answer;

    private Category category;

    public Word(String original, String answer, Category category) {
        this.original = original;
        this.answer = answer;
        this.category = category;
    }

    public String getOriginal() { return original; }

    public void setOriginal(String original) { this.original = original; }

    public String getAnswer() { return answer; }

    public void setAnswer(String answer) { this.answer = answer; }

    public Category getCategory() { return category; }

    public void setCategory(Category category) { this.category = category; }

    public boolean isEqualsAnswer(String answer) {return (this.answer).equals(answer);}
}
