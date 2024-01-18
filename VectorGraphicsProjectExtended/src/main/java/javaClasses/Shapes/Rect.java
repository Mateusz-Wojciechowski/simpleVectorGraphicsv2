package javaClasses.Shapes;

import javaClasses.Point;
import javaClasses.Shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Rect extends Shape{
    private int height;
    private int width;

    public Rect(int height, int width, Point position, boolean filled){
        this.position = position;
        this.height = height;
        this.width = width;
        this.filled = filled;
        this.boundingBox = new ArrayList<>();
        createBoundingBox();
    }

    @Override
    public void translate(Point point, GraphicsContext gc) {
        position.setX(position.getX() + point.getX());
        position.setY(position.getY() + point.getY());

        createBoundingBox();
        draw(gc);
    }

    @Override
    public void createBoundingBox() {
        boundingBox.clear();
        boundingBox.add(position); // Lewy górny róg
        boundingBox.add(new Point(position.getX() + width, position.getY() + height));
    }


    @Override
    public List<Point> getBoundingBox() {
        return boundingBox;
    }

    @Override
    public void draw(GraphicsContext gc) {
        if(filled){
            gc.fillRect(getBoundingBox().get(0).getX(), getBoundingBox().get(0).getY(), width, height);
        }
        else{
            gc.strokeRect(getBoundingBox().get(0).getX(), getBoundingBox().get(0).getY(), width, height);
        }
    }

    @Override
    public void drawBoundingBox(GraphicsContext gc) {
        gc.setStroke(Color.BLACK);
        gc.setLineDashes(5);
        gc.strokeRect(getBoundingBox().get(0).getX(), getBoundingBox().get(0).getY(), width, height);
        gc.setLineDashes(0);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
