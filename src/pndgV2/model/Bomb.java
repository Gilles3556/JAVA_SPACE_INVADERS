package pndgV2.model;

public class Bomb extends Sprite {
    private boolean destroyed;

    public Bomb(int x, int y) {
        super(x,y, TypeObjet.BOMB);
        initBomb();
    }
    private void initBomb() {
        setDestroyed(true);
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public boolean isDestroyed() {
        return destroyed;
    }
}
