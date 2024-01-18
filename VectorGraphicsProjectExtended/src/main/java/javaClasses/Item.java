package javaClasses;

import javafx.scene.canvas.GraphicsContext;

import java.util.List;

public abstract class Item {
    protected Point position;
    protected List<Point> boundingBox;
    public abstract void translate(Point point, GraphicsContext gc);
    public abstract void createBoundingBox();
    public abstract List<Point> getBoundingBox();
    public abstract void draw(GraphicsContext gc);
    public abstract void drawBoundingBox(GraphicsContext gc);
}
