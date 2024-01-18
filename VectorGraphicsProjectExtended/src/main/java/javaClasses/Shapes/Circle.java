package javaClasses.Shapes;

import javaClasses.Point;
import javaClasses.Shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Circle extends Shape {

    private int radius;

    public Circle(int radius, Point position, boolean filled) {
        this.position = position;
        this.radius = radius;
        this.boundingBox = new ArrayList<>();
        this.filled = filled;
        createBoundingBox();
    }

    @Override
    public void translate(Point point, GraphicsContext gc) {
        position.setX(position.getX() + point.getX());
        position.setY(position.getY() + point.getY());

        createBoundingBox();
        gc.setStroke(Color.BLACK);
        draw(gc);
    }

    @Override
    public void createBoundingBox() {
        boundingBox.clear();
        this.boundingBox.add(position);
        this.boundingBox.add(new Point(position.getX() + 2 * radius, position.getY() + 2 * radius));
    }


    @Override
    public void draw(GraphicsContext gc){
        int x = boundingBox.get(0).getX();
        int y = boundingBox.get(0).getY();
        if(filled){
            gc.fillOval(x, y, 2 * radius, 2 * radius);
        }
        else{
            gc.strokeOval(x, y, 2 * radius, 2 * radius);
        }
    }

    @Override
    public void drawBoundingBox(GraphicsContext gc) {
        Point topLeft = boundingBox.get(0);
        Point bottomRight = boundingBox.get(1);
        int width = bottomRight.getX() - topLeft.getX();
        int height = bottomRight.getY() - topLeft.getY();

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        gc.setLineDashes(5);
        gc.strokeRect(topLeft.getX(), topLeft.getY(), width, height);
        gc.setLineDashes(0);
        gc.setStroke(Color.BLACK);
    }


    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public List<Point> getBoundingBox() {
        return boundingBox;
    }
}
