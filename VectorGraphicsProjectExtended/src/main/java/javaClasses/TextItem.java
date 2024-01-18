package javaClasses;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class TextItem extends Item{

    private String text;
    private Font font;
    private Text textNode;

    public TextItem(String text, Point position, Font font) {
        this.text = text;
        this.position = position;
        this.font = font;
        this.textNode = new Text(text);
        this.textNode.setFont(font);
        this.boundingBox = new ArrayList<>();
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
        double width = textNode.getLayoutBounds().getWidth();
        double height = textNode.getLayoutBounds().getHeight();

        boundingBox.add(position);
        boundingBox.add(new Point((int)(position.getX() + width), (int)(position.getY() + height)));
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFont(font);
        gc.fillText(this.text, position.getX(), position.getY());
    }

    @Override
    public void drawBoundingBox(GraphicsContext gc) {
        double width = textNode.getLayoutBounds().getWidth();
        double height = textNode.getLayoutBounds().getHeight();

        gc.setStroke(Color.BLACK);
        gc.setLineDashes(5);
        gc.strokeRect(position.getX(), position.getY()-height, width, height);
        gc.setLineDashes(0);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Text getTextNode() {
        return textNode;
    }

    public void setTextNode(Text textNode) {
        this.textNode = textNode;
    }

    @Override
    public List<Point> getBoundingBox() {
        return boundingBox;
    }
}
