package javaClasses;

import javaClasses.Item;
import javaClasses.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.ArrayList;

public class ComplexItem extends Item {
    private List<Item> children;

    public ComplexItem() {
        this.children = new ArrayList<>();
        this.boundingBox = new ArrayList<>();
        createBoundingBox();
    }

    public void addChild(Item child) {
        children.add(child);
        createBoundingBox();
    }

    public void removeChild(Item child) {
        children.remove(child);
        createBoundingBox();
    }

    @Override
    public void draw(GraphicsContext gc) {
        for (Item child : children) {
            child.draw(gc);
        }
    }

    @Override
    public void translate(Point point, GraphicsContext gc) {
        for (Item child : children) {
            child.translate(point, gc);
        }
        createBoundingBox();
        draw(gc);
    }

    @Override
    public void createBoundingBox() {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;

        for (Item child : children) {
            List<Point> childBoundingBox = child.getBoundingBox();
            for (Point p : childBoundingBox) {
                if (p.getX() < minX) minX = p.getX();
                if (p.getY() < minY) minY = p.getY();
                if (p.getX() > maxX) maxX = p.getX();
                if (p.getY() > maxY) maxY = p.getY();
            }
        }

        boundingBox.clear();
        boundingBox.add(new Point(minX, minY)); // Lewy gorny rog
        boundingBox.add(new Point(maxX, maxY)); // Prawy dolny rog
    }

    @Override
    public void drawBoundingBox(GraphicsContext gc) {
        List<Point> bbox = getBoundingBox();
        if (bbox.size() < 2) return;

        Point topLeft = bbox.get(0);
        Point bottomRight = bbox.get(1);
        int width = bottomRight.getX() - topLeft.getX();
        int height = bottomRight.getY() - topLeft.getY();

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        gc.setLineDashes(10);
        gc.strokeRect(topLeft.getX(), topLeft.getY(), width, height);
        gc.setLineDashes(0);
    }

    @Override
    public List<Point> getBoundingBox() {
        return boundingBox;
    }

    public List<Item> getChildren() {
        return children;
    }

    public void setChildren(List<Item> children) {
        this.children = children;
    }
}
