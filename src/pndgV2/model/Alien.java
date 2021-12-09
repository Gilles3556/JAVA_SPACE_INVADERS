package pndgV2.model;

public class Alien extends Sprite {

    private Bomb bomb;

    public Alien(int x, int y) {
        super(x,y, TypeObjet.ALIEN);
        bomb = new Bomb(x, y);
    }

    public void dirigerHorizontalement(int direction) {
        this.x += direction;
    }

    public void dirigerVerticalement(int direction){
        this.y += direction;
    }

    public Bomb getBomb() {
        return bomb;
    }

}
