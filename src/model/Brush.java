package model;

public class Brush {
    private int size;
    private BrushType type;

    public enum BrushType {
        CIRCULAR,
        SQUARE
    }

    public Brush(int size, BrushType type) {
        this.size = size;
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public BrushType getType() {
        return type;
    }

    public void setType(BrushType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Brush{" +
                "size=" + size +
                ", type=" + type +
                '}';
    }
}
