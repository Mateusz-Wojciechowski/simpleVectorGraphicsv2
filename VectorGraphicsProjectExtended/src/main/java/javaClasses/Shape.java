package javaClasses;

public abstract class Shape extends Primitive{
    protected boolean filled;

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }
}
