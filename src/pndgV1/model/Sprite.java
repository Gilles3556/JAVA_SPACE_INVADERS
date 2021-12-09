package pndgV1.model;

public class Sprite {
    private TypeObjet leType;
    private boolean visible;
    private boolean dying;

    int x;
    int y;
    int dx;

    public Sprite() {
        visible = true;
    }
    public Sprite(int x,int y,TypeObjet t)
    {
        setX(x);
        setY(y);
        leType=t;
        visible = true;
    }

    public void die() {
        visible = false;
    }

    public boolean isVisible() {
        return visible;
    }

    protected void setVisible(boolean visible) {
        this.visible = visible;
    }


    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void setDying(boolean dying) {
        this.dying = dying;
        if(dying) {
            leType = TypeObjet.EXPLOSION;
        }
    }

    public boolean isDying() {
        return this.dying;
    }

    public String toString(){
        return " SPRITE en Y="+y+": X="+x;
    }
}
