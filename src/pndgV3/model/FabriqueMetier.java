package pndgV3.model;

public class FabriqueMetier {

    public static JeuInvaders creerJeu(){
        return new JeuInvaders();
    }
    public static Alien creerUnAlien( int x, int y){
        return new Alien(x,y);

    }

    public static Player creerUnPlayer() {
        return new Player();
    }

    public static Shot creerUnShot() {
        return new Shot();
    }
}
