package javaClasses.Shapes;

import javaClasses.Point;
import javaClasses.Primitive;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Segment extends Primitive{
    private int length;
    private Point start;
    private Point end;

    public Segment(Point start, Point end) {
        this.start = start;
        this.end = end;
        this.length = calculateSegmentLen();
        this.boundingBox = new ArrayList<>();
        createBoundingBox();
    }

    @Override
    public void translate(Point point, GraphicsContext gc) {
        start.setX(start.getX() + point.getX());
        start.setY(start.getY() + point.getY());

        end.setX(end.getX() + point.getX());
        end.setY(end.getY() + point.getY());

        createBoundingBox();
        gc.setStroke(Color.BLACK);
        draw(gc);
    }

    @Override
    public void createBoundingBox() {
        boundingBox.clear();
        int minX = Math.min(start.getX(), end.getX());
        int minY = Math.min(start.getY(), end.getY());
        int maxX = Math.max(start.getX(), end.getX());
        int maxY = Math.max(start.getY(), end.getY());

        boundingBox.add(new Point(minX, minY));
        boundingBox.add(new Point(maxX, maxY));
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setStroke(Color.BLACK);
        gc.strokeLine(start.getX(), start.getY(), end.getX(), end.getY());
    }

    @Override
    public void drawBoundingBox(GraphicsContext gc) {
        List<Point> boundingBox = getBoundingBox();
        int width = Math.abs(boundingBox.get(0).getX() - boundingBox.get(1).getX());
        int height = Math.abs(boundingBox.get(0).getY() - boundingBox.get(1).getY());

        gc.setStroke(Color.BLACK);
        gc.setLineDashes(5);
        gc.strokeRect(boundingBox.get(0).getX(), boundingBox.get(0).getY(), width, height);
        gc.setLineDashes(0);
    }

    public int calculateSegmentLen(){
        int dx = start.getX()-end.getX();
        int dy = start.getY()-end.getY();
        return (int)Math.sqrt(dx * dx + dy * dy);
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Point getStart() {
        return start;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

    @Override
    public List<Point> getBoundingBox() {
        return boundingBox;
    }
}
