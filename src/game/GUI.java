package game;

import javafx.application.Application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import static game.ItemOfMenu.ItemOfMenuType.*;

/**
 * Created by kiarash on 4/29/17.
 */
public class GUI  extends Application {
    private static int money = 8000;
    private static int fortCnt = 5;
    private static int cnt = 0;
    private static int width = 1000;
    private static int height = 700;
    private static int treasuryCnt = 5;
    private static int shieldCnt = 5;
    private static Attack.AttackType itemClicked = null;
    private static ArrayList<Defense> defense = new ArrayList<>();
    private static ArrayList<Attack> attack = new ArrayList<>();

    private void initiate () {

        // First page GUI goes here ...

        VBox mainBox = new VBox (10);
        HBox wlcmeBox = new HBox ();
        Label wlcme = new Label ( "Welcome!" );
        wlcmeBox.getChildren().add(wlcme);
        wlcmeBox.setAlignment(Pos.TOP_CENTER);

        HBox widthBox = new HBox(10);
        Label widthLabel = new Label (   "Width:" );
        widthLabel.setMinWidth(100);
        TextField widthField = new TextField ("1000");
        widthBox.getChildren().addAll(widthLabel, widthField);
        widthBox.setAlignment(Pos.TOP_CENTER);

        HBox heightBox = new HBox(10);
        Label heightLabel = new Label (  "Height:" );
        heightLabel.setMinWidth(100);
        TextField heightField = new TextField ("700");
        heightBox.getChildren().addAll(heightLabel, heightField);
        heightBox.setAlignment(Pos.TOP_CENTER);

        HBox moneyBox = new HBox(10);
        Label initMoneyLabel = new Label ("Money ($) :");
        initMoneyLabel.setMinWidth(100);
        TextField moneyField = new TextField ("8000");
        moneyBox.getChildren().addAll(initMoneyLabel, moneyField);
        moneyBox.setAlignment(Pos.TOP_CENTER);

        HBox fortBox = new HBox(10);
        Label fortLabel = new Label (    "Fort No :" );
        fortLabel.setMinWidth(100);
        TextField fortField = new TextField("5");
        fortBox.getChildren().addAll(fortLabel, fortField);
        fortBox.setAlignment(Pos.TOP_CENTER);

        HBox treasuryBox = new HBox(10);
        Label treasuryLabel = new Label ("Treasury No :" );
        treasuryLabel.setMinWidth(100);
        TextField treasuryField = new TextField("5");
        treasuryBox.getChildren().addAll(treasuryLabel, treasuryField);
        treasuryBox.setAlignment(Pos.TOP_CENTER);

        HBox shieldBox = new HBox(10);
        Label shieldLabel = new Label ("Shield No :" );
        shieldLabel.setMinWidth(100);
        TextField shieldField = new TextField("5");
        shieldBox.getChildren().addAll(shieldLabel, shieldField);
        shieldBox.setAlignment(Pos.TOP_CENTER);

        HBox btnBox = new HBox();
        Button btnStart = new Button ("Start!");
        btnBox.getChildren().add(btnStart);
        btnBox.setAlignment(Pos.CENTER);

        mainBox.getChildren().addAll(wlcmeBox, widthBox, heightBox, moneyBox, fortBox, treasuryBox, shieldBox, btnBox);
        mainBox.setMinWidth(300);
        mainBox.setMinHeight(300);
        Scene firstScene = new Scene (mainBox);

        Stage getInput = new Stage();
        getInput.setScene(firstScene);
        getInput.setTitle("PaperTank");
        getInput.setResizable(false);

        class bntStartHandlerClass implements EventHandler<ActionEvent> {
            @Override
            public void handle(ActionEvent e) {
                width = Integer.parseInt(widthField.getText());
                height = Integer.parseInt(heightField.getText());
                fortCnt = Integer.parseInt(fortField.getText());
                treasuryCnt = Integer.parseInt(treasuryField.getText());
                shieldCnt = Integer.parseInt(shieldField.getText());
                money = Integer.parseInt(moneyField.getText());
                getInput.close();
            }
        }

        btnStart.setOnAction(new bntStartHandlerClass());
        getInput.showAndWait();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        initiate ();

        // Building the Defense ...

        Defense.Build ( defense, width, height, treasuryCnt, fortCnt, shieldCnt );

        // Game GUI goes here ...

        Pane mainPane = new Pane();
        for ( Defense d : defense ) mainPane.getChildren().add(d.getPane());

        HBox menu = new HBox(5);
        ItemOfMenu grenadeItem = new ItemOfMenu(GRENADE, width);
        ItemOfMenu rpgItem = new ItemOfMenu(RPG, width);
        ItemOfMenu tankItem = new ItemOfMenu(TANK, width);
        ItemOfMenu cannonItem = new ItemOfMenu(CANNON, width);
        ItemOfMenu missileItem = new ItemOfMenu(MISSILE, width);
        ItemOfMenu bombItem = new ItemOfMenu(BOMB, width);
        menu.getChildren().addAll(grenadeItem, rpgItem, tankItem, cannonItem, missileItem, bombItem);
        menu.setLayoutX( (width-(6*width/15+25))/2 );
        menu.setLayoutY(0);
        mainPane.getChildren().add(menu);

        Line line = new Line ( width/2, width/12, width/2, height-width/12);
        mainPane.getChildren().add(line);

        Button btnFire = new Button ("Fire!" );
        btnFire.setLayoutX(width/2-25);
        btnFire.setLayoutY(height-width/12+5);
        mainPane.getChildren().add(btnFire);

        StackPane moneyPane = new StackPane();
        moneyPane.setStyle("-fx-background-color: #96fde9");
        Label moneyLabel = new Label(money+"$");
        moneyLabel.setFont(javafx.scene.text.Font.font(20));
        moneyPane.setMinWidth(100);
        moneyPane.setLayoutX(width/2-moneyPane.getMinWidth()/2);
        moneyPane.setLayoutY(height-width/12+40);
        moneyPane.getChildren().add(moneyLabel);
        mainPane.getChildren().add(moneyPane);

        Scene scene = new Scene (mainPane, width, height);
        primaryStage.setScene(scene);
        primaryStage.setTitle("PaperTank");
        primaryStage.setResizable(false);
        primaryStage.show();

        VBox winBox = new VBox();
        Label fin1 = new Label ("You Scored " + money + " !!");
        fin1.setStyle("-fx-text-fill: #20641f");
        fin1.setFont(Font.font(30));
        HBox fin1Box = new HBox(fin1);
        fin1Box.setAlignment(Pos.CENTER);
        ImageView winImage = new ImageView(new Image(new FileInputStream( "src/game/icons/Win/1.jpg")));
        winBox.getChildren().addAll(winImage, fin1Box);

        VBox loseBox = new VBox();
        Label fin2 = new Label ("You Scored " + money + " !!");
        fin2.setStyle("-fx-text-fill: #FF0000");
        fin2.setFont(Font.font(30));
        HBox fin2Box = new HBox(fin2);
        fin2Box.setAlignment(Pos.CENTER);
        ImageView loseImage = new ImageView(new Image(new FileInputStream( "src/game/icons/Lose/1.png")));
        loseBox.getChildren().addAll(loseImage, fin2Box);

        Stage finStage = new Stage();

        // Handler classes go here ...

        Timeline delay1 = new Timeline(new KeyFrame(Duration.seconds(3),
                event2 -> Defense.hideItems(defense)));
        delay1.play();

        class mainPaneHandlerClass implements EventHandler<MouseEvent> {
            @Override
            public void handle(MouseEvent e) {
                if ( GUI.itemClicked == null ) {
                    System.out.println("No item chosen");
                    return;
                }
                if ( e.getSceneX() < width/2 ) {
                    System.out.println("Bad coordinates");
                    return;
                }
                if ( e.getSceneY() < width/12 ) {
                    System.out.println("Bad coordinates");
                    return;
                }
                Attack tmp = null;
                try {
                    tmp = new Attack(0,0,itemClicked);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                assert tmp != null;
                if ( money - tmp.getCost() < 0 ) {
                    System.out.println("Insufficient money");
                    return;
                }
                int r = tmp.getR();
                int x = (int)e.getSceneX()-r-15;
                int y = (int)e.getSceneY()-r-15;
                try {
                    Attack ad = new Attack(x, y, itemClicked);
                    attack.add(ad);
                    mainPane.getChildren().add(ad.getPane());
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                money -= tmp.getCost();
                moneyLabel.setText(money+"$");
                grenadeItem.showMainImage(width);
                rpgItem.showMainImage(width);
                tankItem.showMainImage(width);
                cannonItem.showMainImage(width);
                missileItem.showMainImage(width);
                bombItem.showMainImage(width);
                GUI.itemClicked = null;
            }
        }


        class itemHandlerClass implements EventHandler<MouseEvent> {
            private Attack.AttackType type;
            private ItemOfMenu item;
            private itemHandlerClass(Attack.AttackType type, ItemOfMenu item) {
                this.type = type;
                this.item = item;
            }
            @Override
            public void handle(MouseEvent e) {
                GUI.itemClicked = type;
                item.showClickedImage(width);
            }
        }

        // Game logic goes here ...

        class btnFireHandlerClass implements EventHandler<ActionEvent> {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("Fire clicked");
                cnt++;
                Defense.showItems(defense);
                for ( Attack me : attack )
                    for ( Defense enemy : defense )
                        if ( me.ifKills(enemy, width) ) {
                            enemy.explode();
                        }
                // Show explosion for 1 second ...
                Timeline delay2 = new Timeline(new KeyFrame(Duration.seconds(2), event ->{
                    for ( Attack me : attack )
                        for ( int i=defense.size()-1; i>=0; i-- )
                            if ( me.ifKills(defense.get(i), width) ) {
                                defense.get(i).endExplosion();
                                // todo : removing forts seems not working ... or working? :-?
                                if ( defense.get(i).getType() == Defense.DefenseType.FORT ) {
                                    defense.get(i).setVal(defense.get(i).getVal()-me.getPower());
                                    if ( defense.get(i).getVal() <= 0 ) {
                                        mainPane.getChildren().remove(defense.get(i).getPane());
                                        defense.remove(i);
                                        fortCnt--;
                                        continue;
                                    }
                                    defense.get(i).changeLabel(defense.get(i).getVal()+"%");
                                }
                                else {
                                    money += defense.get(i).getVal();
                                    moneyLabel.setText(money+"$");
                                    mainPane.getChildren().remove(defense.get(i).getPane());
                                    defense.remove(i);
                                }
                            }
                    for ( Attack el : attack )
                        mainPane.getChildren().remove(el.getPane());
                    attack.clear();
                    if ( cnt%3 == 0 )
                        Defense.shuffle(defense, width, height);
                    if ( fortCnt == 0 || money-100 < 0 ) {
                        if ( fortCnt == 0 ) {
                            fin1.setText("You Scored " + money + " !!");
                            Scene finScene = new Scene(winBox);
                            finStage.setScene(finScene);
                        }
                        else {
                            fin2.setText("You Scored " + money + " !!");
                            Scene finScene = new Scene(loseBox);
                            finStage.setScene(finScene);
                        }
                        primaryStage.close();
                        finStage.show();
                    }
                    // show defense for 3 seconds ...
                    Timeline delay1 = new Timeline(new KeyFrame(Duration.seconds(3),
                            event2 -> Defense.hideItems(defense)));
                    delay1.play();
                } ));
                delay2.play();
            }
        }

        btnFire.setOnAction(new btnFireHandlerClass());
        grenadeItem.setOnMouseClicked(new itemHandlerClass(Attack.AttackType.GRENADE, grenadeItem));
        rpgItem.setOnMouseClicked(new itemHandlerClass(Attack.AttackType.RPG, rpgItem));
        tankItem.setOnMouseClicked(new itemHandlerClass(Attack.AttackType.TANK, tankItem));
        cannonItem.setOnMouseClicked(new itemHandlerClass(Attack.AttackType.CANNON, cannonItem));
        missileItem.setOnMouseClicked(new itemHandlerClass(Attack.AttackType.MISSILE, missileItem));
        bombItem.setOnMouseClicked(new itemHandlerClass(Attack.AttackType.BOMB, bombItem));
        mainPane.setOnMouseClicked(new mainPaneHandlerClass());

    }
    public static void main(String[] args) { launch(args); }
}
