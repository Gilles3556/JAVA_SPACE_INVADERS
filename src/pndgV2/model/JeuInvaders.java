package pndgV2.model;

import pndgV2.ihm.Commons;
import pndgV2.ihm.OutilsIhm;
import pndgV2.model.exceptions.JeuInvadersFinException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JeuInvaders {
    private int level;
    private int totalAlien;
    private List<Alien> aliens;
    private Player unPlayer;
    private Shot unShot;
    private TypeDirection direction = TypeDirection.DOWN;

    public void setUnShot( Shot unShot ) {
        this.unShot = unShot;
    }
    public List<Alien> getAliens() {
        return aliens;
    }
    public Player getUnPlayer() {
        return unPlayer;
    }
    public Shot getUnShot() {
        return unShot;
    }

    public int getLevel() {
        return level;
    }

    public JeuInvaders(){
        level =1;
        totalAlien=0;
    }
    public void init() {
        //CR des aliens
        aliens = new ArrayList<>();

        int maxLigne = Commons.MAX_ALIEN_LIGNE+level;
        int maxNb = Commons.MAX_ALIEN_NB+level;
        totalAlien = maxLigne*maxNb;

        for (int i = 0; i < maxLigne; i++) {
            for (int j = 0; j < maxNb; j++) {

                Alien unAlien = FabriqueMetier.creerUnAlien(Commons.ALIEN_INIT_X + 18 * j,
                        Commons.ALIEN_INIT_Y + 18 * i);
                aliens.add(unAlien);
            }
        }

        //CR PLAYER
        unPlayer = FabriqueMetier.creerUnPlayer();

        //CR SHOT
        unShot = FabriqueMetier.creerUnShot();
    }

    //-----------------------SHOT
    public void gererUnTir()  {
        //System.out.println("\t - Board.gererUnTir():"+ MesOutils.recuperTemps());
        if (this.getUnShot().isVisible()) {
            int shotX = this.getUnShot().getX();
            int shotY = this.getUnShot().getY();

            for (Alien alien : this.getAliens()) {

                if (alien.isVisible() && this.getUnShot().isVisible()) {
                    if (shotX >= (alien.getX())
                            && shotX <= (alien.getX() + Commons.ALIEN_WIDTH)
                            && shotY >= (alien.getY())
                            && shotY <= (alien.getY() + Commons.ALIEN_HEIGHT)) {
                        alien.setDying(true);
                        //deaths++;
                        this.getUnShot().die();
                    }
                }
            }

            int y = this.getUnShot().getY();
            y -= 5;

            if (y < Commons.LIGNE_LIMITE_SHOT) {
                this.getUnShot().die();
            } else {
                this.getUnShot().setY(y);
            }
        }
    }
    //----------------------BOMBS
    public void gererBombs () throws JeuInvadersFinException {

        Random generator = new Random();
        for (Alien alien1 : this.getAliens()) {
            int shot = generator.nextInt(15);
            Bomb bomb = alien1.getBomb();
            if (shot == Commons.CHANCE && alien1.isVisible() && bomb.isDestroyed()) {
                bomb.setDestroyed(false);
                bomb.setX(alien1.getX());
                bomb.setY(alien1.getY());
            }

            int bombX = bomb.getX();
            int bombY = bomb.getY();
            int playerX = this.getUnPlayer().getX();
            int playerY = this.getUnPlayer().getY();

            if (this.getUnPlayer().isVisible() && !bomb.isDestroyed()) {
                if (bombX >= (playerX)
                        && bombX <= (playerX + Commons.PLAYER_WIDTH)
                        && bombY >= (playerY)
                        && bombY <= (playerY + Commons.PLAYER_HEIGHT)) {

                    //TODO
                    // leJeu.getUnPlayer().setImage(FabriqueIhm.creerImage(TypeObjet.EXPLOSION));
                    this.getUnPlayer().perdreUneVie();

                    if (this.getUnPlayer().isDead()) {
                        this.getUnPlayer().setDying(true);
                    }
                    bomb.setDestroyed(true);
                }
            }

            if (!bomb.isDestroyed()) {
                bomb.setY(bomb.getY() + 1);
                if (bomb.getY() >= Commons.GROUND - Commons.BOMB_HEIGHT) {
                    bomb.setDestroyed(true);
                }
            }
        }
    }
    //----------------------ALIENS
    public void gererLesAliens() throws JeuInvadersFinException {

        for (Alien alien : this.getAliens()) {

            //if (isSortiDroite(alien.getX()) && direction != TypeDirection.DOWN) {
            if (OutilsIhm.isSortiDroite(alien.getX()) ) {
                direction = TypeDirection.DOWN;

            }
           // if (isSortiGauche(alien.getX()) && direction != TypeDirection.UP) {
            if (OutilsIhm.isSortiGauche(alien.getX()) ) {
                direction = TypeDirection.UP;
            }
            alien.dirigerVerticalement(direction.getCode());

            //Visible ou pas
            if (alien.isVisible()) {
                if (OutilsIhm.isSortiEnBas( alien.getY())) {
                   throw new JeuInvadersFinException(Commons.MSG_GAME_INVASION);
                }
                if(OutilsIhm.isSortiEnHaut(alien.getY())){
                    direction = TypeDirection.DOWN;
                }
                alien.dirigerHorizontalement(direction.getCode()*-1);
            }
        }
    }

    //--------------------------TOUS LES ALIENS ABATTUS
    public  boolean etreFini(){
        boolean fini = (compterAlienMort() >= totalAlien);
        if (fini) {
            System.out.println(">>ALL ALIENS KILLED");
        }
        return fini;
    }
    private int compterAlienMort(){
        int ctr=0;
        for (Alien unAlien: aliens ) {
            if (!unAlien.isVisible()){
                ctr++;
            }
        }
        return ctr;
    }


    public void nextLevel() throws JeuInvadersFinException {
        level++;
        init();
        if (level>Commons.MAX_LEVEL){
            throw new JeuInvadersFinException(Commons.MSG_GAME_WON);
        }
    }
}
