package com.example.vectorgraphicproject;

import javaClasses.*;
import javaClasses.Shapes.*;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HelloController {
    @FXML
    private Button translateButton;
    @FXML
    private Canvas canvas;
    @FXML
    private Pane canvasPane;
    @FXML
    private CheckBox circleCheckBox;
    @FXML
    private CheckBox rectCheckBox;
    @FXML
    private CheckBox segmentCheckBox;
    @FXML
    private CheckBox starCheckBox;
    @FXML
    private CheckBox triangleCheckBox;
    @FXML
    private CheckBox complexItemCheckBox;
    @FXML
    private CheckBox textItemCheckBox;
    private List<Item> itemList = new ArrayList<>();

    @FXML
    public void initialize() {
        drawShapes();
        VBox checkBoxContainer = new VBox(5);
        checkBoxContainer.setAlignment(Pos.TOP_LEFT);
        checkBoxContainer.getChildren().addAll(circleCheckBox, rectCheckBox, segmentCheckBox, starCheckBox, triangleCheckBox, complexItemCheckBox, textItemCheckBox);
        canvasPane.getChildren().add(checkBoxContainer);
        checkBoxContainer.setLayoutX(canvas.getWidth() + 20);
        checkBoxContainer.setLayoutY(20);

        circleCheckBox.setOnAction(event->checkBoxOnAction(circleCheckBox, Circle.class));
        rectCheckBox.setOnAction(event->checkBoxOnAction(rectCheckBox, Rect.class));
        segmentCheckBox.setOnAction(event->checkBoxOnAction(segmentCheckBox, Segment.class));
        starCheckBox.setOnAction(event->checkBoxOnAction(starCheckBox, Star.class));
        triangleCheckBox.setOnAction(event->checkBoxOnAction(triangleCheckBox, Triangle.class));
        complexItemCheckBox.setOnAction(event->checkBoxOnAction(complexItemCheckBox, ComplexItem.class));
        textItemCheckBox.setOnAction(event->checkBoxOnAction(textItemCheckBox, TextItem.class));
    }

    @FXML
    protected void checkBoxOnAction(CheckBox checkBox, Class<? extends Item> figureClass){
        if(checkBox.isSelected()){
            for(Item item : itemList){
                if(item instanceof BoundingBoxDecorator){
                    if(figureClass.isInstance(((BoundingBoxDecorator) item).getItem())){
                        ((BoundingBoxDecorator) item).setDrawBoundingBox(true);
                    }
                }
            }
            redrawScene(canvas.getGraphicsContext2D());
        }

        if(!checkBox.isSelected()){
            for(Item item : itemList){
                if(item instanceof BoundingBoxDecorator){
                    if(figureClass.isInstance(((BoundingBoxDecorator) item).getItem())){
                        ((BoundingBoxDecorator) item).setDrawBoundingBox(false);
                    }
                }
            }
            redrawScene(canvas.getGraphicsContext2D());
        }
    }

    private void drawShapes() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        SingletonMenager singletonMenager = new SingletonMenager(null, itemList, gc);

        Item circle = new Circle(50, new Point(1000 / 2 + 150, 1000 / 2 + 150), false);
        Item decoratedCircle = new BoundingBoxDecorator(circle);
        addItem(decoratedCircle, gc);

        SingletonItem triangle = new Triangle(new Point(1000 / 4, 1000 / 4), new Point(1000 / 4 + 100, 1000 / 4 - 80), new Point(1000 / 4 + 50, 1000 / 4 + 20), true);
        singletonMenager.setSingletonItem(triangle);
        SingletonItem triangle2 = new Triangle(new Point(1000 / 4 - 30, 1000 / 4 - 5), new Point(1000 / 4 , 1000 / 4 - 80), new Point(1000 / 4 + 50, 1000 / 4 + 20), false);
        singletonMenager.setSingletonItem(triangle2);

        Item rect = new Rect(100, 120, new Point(1000 / 4 + 300, 1000 / 4 + 100), false);
        Item decoratedRect = new BoundingBoxDecorator(rect);
        addItem(decoratedRect, gc);

        Item segment = new Segment(new Point(1000 / 2 + 100, 1000 / 2 + 4), new Point(1000 / 2 + 130, 1000 / 2 + 110));
        Item decoratedSegment = new BoundingBoxDecorator(segment);
        addItem(decoratedSegment, gc);

        Font font = new Font("Arial", 20);
        Item textItem = new TextItem("Hello world", new Point(1000 / 2 + 300, 1000 / 4 - 100), font);
        Item decoratedTextItem = new BoundingBoxDecorator(textItem);
        addItem(decoratedTextItem, gc);

        Item star = new Star(new Point(1000 / 2 + 50, 1000 / 2 +150), 5, 100, 50);
        Item decoratedStar = new BoundingBoxDecorator(star);
        addItem(decoratedStar, gc);

        Item snowman = createSnowman();
        Item decoratedSnowman = new BoundingBoxDecorator(snowman);
        addItem(decoratedSnowman, gc);

        drawItems(gc);
    }

    private void addItem(Item item, GraphicsContext gc) {
        if(item instanceof BoundingBoxDecorator){
            itemList.add(item);
        }
    }

    private void drawItems(GraphicsContext gc){
        for(Item item: itemList){
            item.draw(gc);
        }
    }

    private void redrawScene(GraphicsContext gc){
        clearCanvas(gc);
        drawItems(gc);
    }

    @FXML
    protected void translateButtonAction() {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        clearCanvas(gc);

        Random random = new Random();
        for (Item item : itemList) {
            item.translate(new Point(random.nextInt(-100, 100), random.nextInt(-100, 100)), gc);

            item.draw(gc);
        }
    }

    private void clearCanvas(GraphicsContext gc) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
    public ComplexItem createSnowman(){
        int bottomRadius = 100;
        int middleRadius = 70;
        int topRadius = 50;

        int bottomCenterX = 400;
        int bottomCenterY = 500;
        int middleCenterX = 400;
        int middleCenterY = bottomCenterY - bottomRadius - middleRadius;
        int topCenterX = 400;
        int topCenterY = middleCenterY - middleRadius - topRadius;

        Circle bottomBall = new Circle(bottomRadius, new Point(bottomCenterX - bottomRadius, bottomCenterY - bottomRadius), false);
        Circle middleBall = new Circle(middleRadius, new Point(middleCenterX - middleRadius, middleCenterY - middleRadius), false);
        Circle topBall = new Circle(topRadius, new Point(topCenterX - topRadius, topCenterY - topRadius), false);

        Circle leftEye = new Circle(5, new Point(topCenterX - 15 - 5, topCenterY - 10 - 5), true);
        Circle rightEye = new Circle(5, new Point(topCenterX + 15 - 5, topCenterY - 10 - 5), true);
        Triangle nose = new Triangle(new Point(topCenterX, topCenterY),
                new Point(topCenterX, topCenterY + 10),
                new Point(topCenterX + 20, topCenterY), true);

        ComplexItem snowman = new ComplexItem();
        snowman.addChild(bottomBall);
        snowman.addChild(middleBall);
        snowman.addChild(topBall);
        snowman.addChild(leftEye);
        snowman.addChild(rightEye);
        snowman.addChild(nose);
        return snowman;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
}