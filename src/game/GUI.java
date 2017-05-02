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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import static game.ItemOfMenu.ItemOfMenuType.*;

/**
 * Created by kiarash on 4/29/17.
 */
public class GUI  extends Application {
    public static int money = 8000;
    public static int fortCnt = 5;
    public static int cnt = 0;
    public static int width = 1000;
    public static int height = 700;
    public static int treasuryCnt = 5;
    public static int shieldCnt = 5;
    public static Attack.AttackType itemClicked = null;
    public static ArrayList<Defense> defense = new ArrayList<Defense>();
    public static ArrayList<Attack> attack = new ArrayList<Attack>();
    @Override
    public void start(Stage primaryStage) throws Exception{

        // Building the Defense ...
        loop1: for ( int i=0; i<treasuryCnt; i++ ) {
            int x = ThreadLocalRandom.current().nextInt(0, width/2-60 + 1);
            int y = ThreadLocalRandom.current().nextInt(width/12, height-width/12 + 1);
            Defense d = new Defense(x, y, Defense.DefenseType.TREASURY);
            for ( Defense ex : defense )
                if (ex.ifIntersects(d)) {
                    i--;
                    continue loop1;
                }
            defense.add(d);
        }
        loop2: for ( int i=0; i<fortCnt; i++ ) {
            int x = ThreadLocalRandom.current().nextInt(0, width/2-60 + 1);
            int y = ThreadLocalRandom.current().nextInt(width/12, height-width/12 + 1);
            Defense d = new Defense(x, y, Defense.DefenseType.FORT);
            for ( Defense ex : defense )
                if (ex.ifIntersects(d)) {
                    i--;
                    continue loop2;
                }
            defense.add(d);
        }
        loop3: for ( int i=0; i<shieldCnt; i++ ) {
            int x = ThreadLocalRandom.current().nextInt(0, width/2-60 + 1);
            int y = ThreadLocalRandom.current().nextInt(width/12, height-width/12 + 1);
            Defense d = new Defense(x, y, Defense.DefenseType.SHIELD);
            for ( Defense ex : defense )
                if (ex.ifIntersects(d)) {
                    i--;
                    continue loop3;
                }
            defense.add(d);
        }


        // GUI goes here ...

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
        primaryStage.setTitle("PaperTank");
        //primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();


        // First page GUI goes here ...

        VBox mainBox = new VBox (10);
        HBox wlcmeBox = new HBox ();
        Label wlcme = new Label ( "Welcome!" );
        wlcmeBox.getChildren().add(wlcme);
        wlcmeBox.setAlignment(Pos.TOP_CENTER);

        HBox widthBox = new HBox(10);
        Label widthLabel = new Label (   "Width:        " );
        TextField widthField = new TextField ("1000");
        widthBox.getChildren().addAll(widthLabel, widthField);
        widthBox.setAlignment(Pos.TOP_CENTER);

        HBox heightBox = new HBox(10);
        Label heightLabel = new Label (  "Height:       " );
        TextField heightField = new TextField ("700");
        heightBox.getChildren().addAll(heightLabel, heightField);
        heightBox.setAlignment(Pos.TOP_CENTER);

        HBox moneyBox = new HBox(10);
        Label initMoneyLabel = new Label ("Money ($) :  ");
        TextField moneyField = new TextField ("8000");
        moneyBox.getChildren().addAll(initMoneyLabel, moneyField);
        moneyBox.setAlignment(Pos.TOP_CENTER);

        HBox fortBox = new HBox(10);
        Label fortLabel = new Label (    "Fort No :    " );
        TextField fortField = new TextField("5");
        fortBox.getChildren().addAll(fortLabel, fortField);
        fortBox.setAlignment(Pos.TOP_CENTER);

        HBox treasuryBox = new HBox(10);
        Label treasuryLabel = new Label ("Treasury No :" );
        TextField treasuryField = new TextField("5");
        treasuryBox.getChildren().addAll(treasuryLabel, treasuryField);
        treasuryBox.setAlignment(Pos.TOP_CENTER);

        HBox shieldBox = new HBox(10);
        Label shieldLabel = new Label ("Shield No :" );
        TextField shieldField = new TextField("5");
        shieldBox.getChildren().addAll(shieldLabel, shieldField);
        shieldBox.setAlignment(Pos.TOP_CENTER);

        HBox btnBox = new HBox();
        Button btnStart = new Button ("Start!");
        btnBox.getChildren().add(btnStart);
        btnBox.setAlignment(Pos.CENTER);

        class bntDoneHandlerClass implements EventHandler<ActionEvent> {
            @Override
            public void handle(ActionEvent e) {
                primaryStage.setScene(scene);
                /*width = Integer.parseInt(widthField.getText());
                height = Integer.parseInt(heightField.getText());
                fortCnt = Integer.parseInt(fortField.getText());
                treasuryCnt = Integer.parseInt(treasuryField.getText());
                shieldCnt = Integer.parseInt(shieldField.getText());
                money = Integer.parseInt(moneyField.getText());
                primaryStage.setWidth(width);
                primaryStage.setHeight(height);*/
                Defense.showItems(defense);
                Timeline delay3 = new Timeline(new KeyFrame(Duration.seconds(3),
                        event3 ->{ Defense.hideItems(defense); } ));
                delay3.play();
            }
        }

        btnStart.setOnAction(new bntDoneHandlerClass());

        mainBox.getChildren().addAll(wlcmeBox, widthBox, heightBox, moneyBox, fortBox, treasuryBox, shieldBox, btnBox);
        mainBox.setMinWidth(300);
        mainBox.setMinHeight(300);
        Scene firstScene = new Scene (mainBox);
        primaryStage.setScene(firstScene);

        // Handler classes go here ...

        Timeline delay1 = new Timeline(new KeyFrame(Duration.seconds(3),
                event2 ->{ Defense.hideItems(defense); } ));
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
            public itemHandlerClass ( Attack.AttackType type, ItemOfMenu item ) {
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
                if ( money-100 < 0 || fortCnt==0 ) {
                    // todo : end the game ...
                }
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
                                    if ( defense.get(i).getVal() < 0 ) {
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
                    // show defense for 3 seconds ...
                    Timeline delay1 = new Timeline(new KeyFrame(Duration.seconds(3),
                            event2 ->{ Defense.hideItems(defense); } ));
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
