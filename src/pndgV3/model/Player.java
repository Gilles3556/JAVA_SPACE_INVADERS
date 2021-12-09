package pndgV3.model;

import pndgV3.ihm.Commons;

import java.awt.event.KeyEvent;

public class Player extends Sprite {

    private int width;
    private int nbVie;

    public Player() {
        super(135,280, TypeObjet.PLAYER);
        setDying(false);
        nbVie = Commons.DEBUT_NB_VIE;
    }

    public int getNbVie() {
        return nbVie;
    }

    public void gagnerUneVie(){
        nbVie++;
    }
    public void perdreUneVie(){
        nbVie--;
    }
    public boolean isDead(){
        return (nbVie==0);
    }
    public void act() {
        x += dx;
        if (x <= 2) {
            x = 2;
        }
        if (x >= Commons.BOARD_WIDTH - 2 * width) {
            x = Commons.BOARD_WIDTH - 2 * width;
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            dx = Commons.DEP_LEFT;
        }
        if (key == KeyEvent.VK_RIGHT) {
            dx = Commons.DEP_RIGHT;
        }

    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            dx = Commons.DEP_ZERO;
        }
        if (key == KeyEvent.VK_RIGHT) {
            dx = Commons.DEP_ZERO;
        }
    }
}
