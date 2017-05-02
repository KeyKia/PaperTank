package game;

import javafx.scene.image.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by kiarash on 4/28/17.
 */
public class Attack {
    public enum AttackType {
        GRENADE, RPG, TANK, CANNON, MISSILE, BOMB
    }
    private int x;
    private int y;
    private int r;
    private int power;
    private int cost;
    private AttackType type;
    private CirclePane pane;

    public Attack ( int x, int y, AttackType type ) throws FileNotFoundException {
        this.x = x; this.y = y; this.type = type;
        switch ( type ) {
            case GRENADE:
                this.r = 12;
                this.power = 10;
                this.cost = 100;
                pane = new CirclePane(x, y, r, new Image(new FileInputStream( "src/game/icons/Grenade/1.png")));
                break;
            case RPG:
                this.r = 18;
                this.power = 20;
                this.cost = 200;
                pane = new CirclePane(x, y, r, new Image(new FileInputStream( "src/game/icons/RPG/1.png")));
                break;
            case TANK:
                this.r = 22;
                this.power = 40;
                this.cost = 400;
                pane = new CirclePane(x, y, r, new Image(new FileInputStream( "src/game/icons/Tank/1.png")));
                break;
            case CANNON:
                this.r = 28;
                this.power = 60;
                this.cost = 600;
                pane = new CirclePane(x, y, r, new Image(new FileInputStream( "src/game/icons/Cannon/1.png")));
                break;
            case MISSILE:
                this.r = 36;
                this.power = 90;
                this.cost = 900;
                pane = new CirclePane(x, y, r, new Image(new FileInputStream( "src/game/icons/Missile/1.png")));
                break;
            case BOMB:
                this.r = 46;
                this.power = 120;
                this.cost = 1200;
                pane = new CirclePane(x, y, r, new Image(new FileInputStream( "src/game/icons/Bomb/1.png")));
                break;
        }
    }

    public boolean ifKills ( Defense enemy, int paneWidth ) {
        int x1 = enemy.getX() + enemy.getR() + 15;
        int y1 = enemy.getY() + enemy.getR() + 15;
        int x2 = paneWidth - (this.x + this.r + 15);
        int y2 = this.y + this.r + 15;
        return Math.sqrt( (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2) ) < enemy.getR() + this.r;
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

    public int getPower() {
        return power;
    }

    public int getCost() {
        return cost;
    }

    public AttackType getType() {
        return type;
    }

    public CirclePane getPane() {
        return pane;
    }
}
