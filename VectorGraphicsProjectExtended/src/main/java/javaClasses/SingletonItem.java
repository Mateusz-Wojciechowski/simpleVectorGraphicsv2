package javaClasses;

import java.util.List;

public interface SingletonItem {
    boolean createInstance(List<Item> itemList, SingletonItem newSingleton, SingletonItem singleton);
}
