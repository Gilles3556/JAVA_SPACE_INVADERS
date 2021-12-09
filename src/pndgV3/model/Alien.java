package pndgV3.model;

public class Alien extends Sprite {

    private Bomb bomb;
    private boolean touche;

    public boolean isTouche() {
        return touche;
    }

    public Alien( int x, int y) {
        super(x,y, TypeObjet.ALIEN);
        bomb = new Bomb(x, y);
        touche = false;
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

    public void shooted(){
        touche = true;
    }
}
