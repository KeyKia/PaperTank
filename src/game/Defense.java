package game;

import com.sun.prism.paint.Color;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by kiarash on 4/28/17.
 */
public class Defense {

    public enum DefenseType {
        FORT, SHIELD, TREASURY
    }
    private int x;
    private int y;
    private int r;
    private int val; // val is health for fort and bonus for others
    private DefenseType type;
    private CirclePane pane;

    public Defense (int x, int y, DefenseType type) throws FileNotFoundException {
        this.x = x; this.y = y; this.type = type;
        switch ( type ) {
            case FORT:
                this.val = ThreadLocalRandom.current().nextInt(50, 100 + 1);
                this.r = ThreadLocalRandom.current().nextInt(18, 25 + 1);
                pane = new CirclePane(x, y, r, new Image(new FileInputStream( "src/game/icons/Fort/1.png")));
                changeLabel(val+"%");
                break;
            case SHIELD:
                this.val = -1*ThreadLocalRandom.current().nextInt(200, 500 + 1);
                this.r = ThreadLocalRandom.current().nextInt(22, 30 + 1);
                pane = new CirclePane(x, y, r, new Image (new FileInputStream( "src/game/icons/Shield/1.jpeg")));
                changeLabel(val+"$");
                pane.getLabel().setStyle("-fx-text-fill: #FF0000");
                break;
            case TREASURY:
                this.val = ThreadLocalRandom.current().nextInt(400, 700 + 1);
                this.r = ThreadLocalRandom.current().nextInt(12, 22 + 1);
                pane = new CirclePane(x, y, r, new Image (new FileInputStream( "src/game/icons/Treasury/1.jpg")));
                changeLabel(val+"$");
                pane.getLabel().setStyle("-fx-text-fill: #20641f");
                break;
        }
    }

    public static void shuffle ( ArrayList<Defense> defense, int paneWidth, int paneHeight ) {
        loop: for ( int i=0; i<defense.size(); i++ ) {
            int x1 = defense.get(i).getX();
            int y1 = defense.get(i).getY();
            defense.get(i).setX ( ThreadLocalRandom.current().nextInt(0, paneWidth/2-60 + 1) );
            defense.get(i).setY(ThreadLocalRandom.current().nextInt(paneWidth/12, paneHeight-paneWidth/12 + 1));
            for ( int j=0; j<i; j++ )
                if ( defense.get(i).ifIntersects(defense.get(j)) ) {
                    defense.get(i).setX(x1);
                    defense.get(i).setY(y1);
                    i--;
                    continue loop;
                }
        }
    }

    public boolean ifIntersects ( Defense d ) {
        int x1 = d.getX() + d.getR() + 15;
        int y1 = d.getY() + d.getR() + 15;
        int x2 = this.x + this.r + 15;
        int y2 = this.y + this.r + 15;
        return Math.sqrt( (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2) ) < d.getR() + this.r + 10;
    }

    public static void hideItems ( ArrayList<Defense> defense ) {
        for ( Defense item : defense )
            item.getPane().setVisible(false);
    }

    public static void showItems ( ArrayList<Defense> defense ) {
        for ( Defense item : defense )
            item.getPane().setVisible(true);
    }

    public void changeLabel ( String txt ) {
        pane.setLabelText (txt);
    }

    public void explode () {
        pane.showExplosion();
    }

    public void endExplosion () {
        pane.endExplosion();
    }

    public void setX(int x) {
        this.x = x;
        pane.setLayoutX(x);
    }

    public void setY(int y) {
        this.y = y;
        pane.setLayoutY(y);
    }

    public DefenseType getType() {
        return type;
    }

    public CirclePane getPane() {
        return pane;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getR() {
        return r;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }
}
