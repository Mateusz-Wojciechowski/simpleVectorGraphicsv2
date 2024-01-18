package javaClasses.Shapes;

import javaClasses.Point;
import javaClasses.Shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

public class Star extends Shape {
    private Point center;
    private int numPoints;
    private int outerRadius;
    private int innerRadius;

    public Star(Point center, int numPoints, int outerRadius, int innerRadius) {
        this.center = center;
        this.numPoints = numPoints;
        this.outerRadius = outerRadius;
        this.innerRadius = innerRadius;
        this.boundingBox = new ArrayList<>();
        createBoundingBox();
    }

    @Override
    public void draw(GraphicsContext gc) {
        List<Point> starPoints = calculateStarPoints(center, numPoints, outerRadius, innerRadius);

        double[] xPoints = starPoints.stream().mapToDouble(Point::getX).toArray();
        double[] yPoints = starPoints.stream().mapToDouble(Point::getY).toArray();

        gc.setStroke(Color.BLACK);
        gc.strokePolygon(xPoints, yPoints, starPoints.size());
    }

    @Override
    public void translate(Point point, GraphicsContext gc) {
        center.setX(center.getX() + point.getX());
        center.setY(center.getY() + point.getY());
        createBoundingBox();
        gc.setStroke(Color.BLACK);
        draw(gc);
    }

    @Override
    public void createBoundingBox() {
        boundingBox.clear();
        List<Point> starPoints = calculateStarPoints(center, numPoints, outerRadius, innerRadius);

        int minX = starPoints.stream().mapToInt(Point::getX).min().orElse(center.getX());
        int minY = starPoints.stream().mapToInt(Point::getY).min().orElse(center.getY());
        int maxX = starPoints.stream().mapToInt(Point::getX).max().orElse(center.getX());
        int maxY = starPoints.stream().mapToInt(Point::getY).max().orElse(center.getY());

        boundingBox.add(new Point(minX, minY)); // Lewy gorny rog
        boundingBox.add(new Point(maxX, maxY)); // Prawy dolny rog
    }

    private List<Point> calculateStarPoints(Point center, int spikes, int outerRadius, int innerRadius) {
        List<Point> points = new ArrayList<>();
        double angle = Math.PI / spikes;

        for (int i = 0; i < spikes * 2; i++) {
            int radius = (i % 2 == 0) ? outerRadius : innerRadius;
            double x = center.getX() + Math.cos(i * angle) * radius;
            double y = center.getY() + Math.sin(i * angle) * radius;
            points.add(new Point((int)x, (int)y));
        }

        return points;
    }

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
        gc.setLineDashes(0);  // Resetowanie stylu linii
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public int getNumPoints() {
        return numPoints;
    }

    public void setNumPoints(int numPoints) {
        this.numPoints = numPoints;
    }

    public int getOuterRadius() {
        return outerRadius;
    }

    public void setOuterRadius(int outerRadius) {
        this.outerRadius = outerRadius;
    }

    public int getInnerRadius() {
        return innerRadius;
    }

    public void setInnerRadius(int innerRadius) {
        this.innerRadius = innerRadius;
    }

    @Override
    public List<Point> getBoundingBox() {
        return boundingBox;
    }

}

