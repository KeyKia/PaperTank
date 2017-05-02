package game;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by kiarash on 4/28/17.
 */
public class CirclePane extends StackPane {
    private int x;
    private int y;
    private int r;
    private Circle c;
    private Image itemImage;
    private static Image explosionImage;
    private ImageView imageView = new ImageView();
    private Label label = new Label("");

    public CirclePane ( int x, int y, int r, Image itemImage ) throws FileNotFoundException {
        this.r = r;
        explosionImage = new Image (new FileInputStream( "src/game/icons/Explosion/1.png"));
        c = new Circle(r);
        c.setStroke(Color.BLACK);
        c.setFill(null);
        this.itemImage = itemImage;
        changeImage(itemImage);
        setMinSize(2*r+30, 2*r+30);
        setMaxSize(2*r+30, 2*r+30);
        getChildren().addAll(imageView, c, label);
        setAlignment(label, Pos.TOP_CENTER);
        setLayoutX(x);
        setLayoutY(y);
    }

    public void setLabelText ( String txt ) {
        label.setText(txt);
    }

    public void changeImage ( Image image ) {
        imageView.setImage(image);
        imageView.setFitHeight(1.6*r);
        imageView.setFitWidth(1.6*r);
    }

    public void showExplosion () {
        changeImage(explosionImage);
    }

    public void endExplosion () {
        changeImage(itemImage);
    }

    public Label getLabel() {
        return label;
    }
}









