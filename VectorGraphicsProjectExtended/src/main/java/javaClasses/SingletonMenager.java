package javaClasses;

import javafx.scene.canvas.GraphicsContext;

import java.util.List;

public class SingletonMenager {
    private BoundingBoxDecorator singletonBox;
    private List<Item> itemList;
    private GraphicsContext gc;

    public SingletonMenager(BoundingBoxDecorator singleton, List<Item> itemList, GraphicsContext gc){
        this.itemList = itemList;
        this.gc = gc;
        singletonBox = singleton;
    }

    public void menageSingleton(SingletonItem newSingletonItem){
        if(singletonBox==null){
            singletonBox = new BoundingBoxDecorator((Item) newSingletonItem);
        }
        if(newSingletonItem.createInstance(itemList, newSingletonItem, (SingletonItem) singletonBox.getItem())){
            singletonBox.setItem((Item)newSingletonItem);
        }
    }

    public void setSingletonItem(SingletonItem singletonItem) {
        menageSingleton(singletonItem);
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public GraphicsContext getGc() {
        return gc;
    }

    public void setGc(GraphicsContext gc) {
        this.gc = gc;
    }
}
