package javaClasses.Shapes;

import javaClasses.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Triangle extends Shape implements SingletonItem {
    private Point point1;
    private Point point2;
    private Point point3;

    public Triangle(Point point1, Point point2, Point point3, boolean filled){
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
        this.filled = filled;
        this.boundingBox = new ArrayList<>();
        createBoundingBox();
    }

    @Override
    public void translate(Point point, GraphicsContext gc) {
        point1.setX(point1.getX() + point.getX());
        point1.setY(point1.getY() + point.getY());

        point2.setX(point2.getX() + point.getX());
        point2.setY(point2.getY() + point.getY());

        point3.setX(point3.getX() + point.getX());
        point3.setY(point3.getY() + point.getY());

        createBoundingBox();
        gc.setStroke(Color.BLACK);
        draw(gc);
    }

    @Override
    public void createBoundingBox() {
        boundingBox.clear();
        int x1 = Math.min(Math.min(point1.getX(), point2.getX()), point3.getX());
        int y1 = Math.min(Math.min(point1.getY(), point2.getY()), point3.getY());
        int x2 = Math.max(Math.max(point1.getX(), point2.getX()), point3.getX());
        int y2 = Math.max(Math.max(point1.getY(), point2.getY()), point3.getY());

        Point topLeft = new Point(x1, y1);
        Point bottomRight = new Point(x2, y2);

        boundingBox.add(topLeft);
        boundingBox.add(bottomRight);
    }

    @Override
    public void draw(GraphicsContext gc) {
        double[] xPoints = { point1.getX(), point2.getX(), point3.getX() };
        double[] yPoints = { point1.getY(), point2.getY(), point3.getY() };
        int numPoints = 3;

        gc.setStroke(Color.BLACK);
        if(filled){
            gc.fillPolygon(xPoints, yPoints, numPoints);
        }
        else{
            gc.strokePolygon(xPoints, yPoints, numPoints);
        }
    }

    @Override
    public void drawBoundingBox(GraphicsContext gc) {
        int width = Math.abs(getBoundingBox().get(0).getX() - getBoundingBox().get(1).getX());
        int height = Math.abs(getBoundingBox().get(0).getY() - getBoundingBox().get(1).getY());

        gc.setStroke(Color.BLACK);
        gc.setLineDashes(5);
        gc.strokeRect(getBoundingBox().get(0).getX(), getBoundingBox().get(0).getY(), width, height);
        gc.setLineDashes(0);
        gc.setStroke(Color.BLACK);
    }

    @Override
    public boolean createInstance(List<Item> itemList, SingletonItem newSingleton, SingletonItem singleton) {
        if(singleton!=null){
            for(int i=0; i<itemList.size(); i++){
                if(itemList.get(i) instanceof BoundingBoxDecorator){
                    if(((BoundingBoxDecorator) itemList.get(i)).getItem()==singleton){
                        itemList.remove(itemList.get(i));
                    }
                }
            }
            itemList.add(new BoundingBoxDecorator((Item)newSingleton));
            return true;
        }
        else{
            itemList.add((Item)newSingleton);
            return false;
        }
    }

    public Point getPoint1() {
        return point1;
    }

    public void setPoint1(Point point1) {
        this.point1 = point1;
    }

    public Point getPoint2() {
        return point2;
    }

    public void setPoint2(Point point2) {
        this.point2 = point2;
    }

    public Point getPoint3() {
        return point3;
    }

    public void setPoint3(Point point3) {
        this.point3 = point3;
    }

    @Override
    public List<Point> getBoundingBox() {
        return boundingBox;
    }
}
