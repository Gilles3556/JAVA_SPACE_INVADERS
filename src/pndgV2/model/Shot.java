package pndgV2.model;

public class Shot extends Sprite {

    public Shot() {
    }

    public Shot(int x, int y) {
        super(x,y, TypeObjet.SHOT);
        initShot();
    }

    private void initShot() {

        int H_SPACE = 6;
        setX(x + H_SPACE);

        int V_SPACE = 1;
        setY(y - V_SPACE);
    }
}
