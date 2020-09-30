package model.entitites;

public class BoolBody<T> {

    private boolean bool;
    private T t;

    public BoolBody(boolean bool, T t) {
        this.bool = bool;
        this.t = t;
    }

    public boolean isBool() { return bool; }

    public void setBool(boolean bool) { this.bool = bool; }

    public T getT() { return t; }

    public void setT(T t) { this.t = t; }
}
