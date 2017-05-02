package game;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.layout.StackPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static game.ItemOfMenu.ItemOfMenuType.GRENADE;

/**
 * Created by kiarash on 4/28/17.
 */
public class ItemOfMenu extends StackPane {
    public enum ItemOfMenuType {
        GRENADE, RPG, TANK, CANNON, MISSILE, BOMB
    }
    private ItemOfMenuType type;
    private Image mainImage;
    private Image clickedImage;
    private ImageView imageView = new ImageView();
    private Label label;
    private int cost;
    private int power;

    public ItemOfMenu ( ItemOfMenuType type, int paneWidth ) throws FileNotFoundException {
        this.type = type;
        setMinSize(paneWidth/15, paneWidth/15);
        setStyle("-fx-background-color: #96fde9");
        switch ( type ) {
            case GRENADE:
                this.power = 10;
                this.cost = 100;
                label = new Label("100$");
                mainImage = new Image(new FileInputStream( "src/game/icons/Grenade/1.png"));
                clickedImage = new Image(new FileInputStream( "src/game/icons/Grenade/2.png"));
                break;
            case RPG:
                this.power = 20;
                this.cost = 200;
                label = new Label("200$");
                mainImage = new Image(new FileInputStream( "src/game/icons/RPG/1.png"));
                clickedImage = new Image(new FileInputStream( "src/game/icons/RPG/2.png"));
                break;
            case TANK:
                this.power = 40;
                this.cost = 400;
                label = new Label("400$");
                mainImage = new Image(new FileInputStream( "src/game/icons/Tank/1.png"));
                clickedImage = new Image(new FileInputStream( "src/game/icons/Tank/2.png"));
                break;
            case CANNON:
                this.power = 60;
                this.cost = 600;
                label = new Label("600$");
                mainImage = new Image(new FileInputStream( "src/game/icons/Cannon/1.png"));
                clickedImage = new Image(new FileInputStream( "src/game/icons/Cannon/2.png"));
                break;
            case MISSILE:
                this.power = 90;
                this.cost = 900;
                label = new Label("900$");
                mainImage = new Image(new FileInputStream( "src/game/icons/Missile/1.png"));
                clickedImage = new Image(new FileInputStream( "src/game/icons/Missile/2.png"));
                break;
            case BOMB:
                this.power = 120;
                this.cost = 1200;
                label = new Label("1200$");
                mainImage = new Image(new FileInputStream( "src/game/icons/Bomb/1.png"));
                clickedImage = new Image(new FileInputStream( "src/game/icons/Bomb/2.png"));
                break;
        }

        changeImage ( mainImage, paneWidth );
        getChildren().addAll(imageView, label);
        setAlignment(label, Pos.TOP_RIGHT);
    }

    public void changeImage ( Image image, int paneWidth ) {
        imageView.setImage(image);
        if ( type == GRENADE ) {
            imageView.setFitHeight(paneWidth / 15 -15);
            imageView.setFitWidth(paneWidth / 15 -10);
            setAlignment(imageView, Pos.BOTTOM_CENTER);
        }
        else {
            imageView.setFitHeight(paneWidth / 15);
            imageView.setFitWidth(paneWidth / 15);
        }
    }

    public void showClickedImage ( int paneWidth) {
        changeImage(clickedImage, paneWidth);
    }

    public void showMainImage ( int paneWidth) {
        changeImage(mainImage, paneWidth);
    }
}
