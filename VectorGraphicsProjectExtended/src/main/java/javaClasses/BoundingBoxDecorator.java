package javaClasses;

import javafx.scene.canvas.GraphicsContext;

import java.util.List;

public class BoundingBoxDecorator extends BaseDecorator implements SingletonItem{
    private boolean drawBoundingBox;
    public BoundingBoxDecorator(Item item) {
        this.item = item;
    }

    @Override
    public void translate(Point point, GraphicsContext gc) {
        item.translate(point, gc);
    }

    @Override
    public void createBoundingBox() {
        item.createBoundingBox();
    }

    @Override
    public List<Point> getBoundingBox() {
        return item.getBoundingBox();
    }

    @Override
    public void draw(GraphicsContext gc) {
        item.draw(gc);
        if(drawBoundingBox){
            drawBoundingBox(gc);
        }
    }

    @Override
    public void drawBoundingBox(GraphicsContext gc) {
        item.drawBoundingBox(gc);
    }

    public boolean isDrawBoundingBox() {
        return drawBoundingBox;
    }

    public void setDrawBoundingBox(boolean drawBoundingBox) {
        this.drawBoundingBox = drawBoundingBox;
    }

    @Override
    public boolean createInstance(List<Item> itemList, SingletonItem newSingleton, SingletonItem singleton) {
        if(this.item instanceof SingletonItem){
            return ((SingletonItem) this.item).createInstance(itemList, newSingleton, singleton);
        }
        else return false;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }


}
