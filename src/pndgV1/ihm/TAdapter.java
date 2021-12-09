package pndgV1.ihm;

import pndgV1.model.JeuInvaders;
import pndgV1.model.Shot;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TAdapter extends KeyAdapter {
    private JeuInvaders leJeu;
    private Board b;

    public TAdapter(Board b, JeuInvaders leJeu){
        this.b = b;
        this.leJeu = leJeu;
    }

    @Override
    public void keyReleased( KeyEvent e) {
        leJeu.getUnPlayer().keyReleased(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        leJeu.getUnPlayer().keyPressed(e);

        int x = leJeu.getUnPlayer().getX();
        int y = leJeu.getUnPlayer().getY();
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {

                if (!leJeu.getUnShot().isVisible()) {
                    leJeu.setUnShot(new Shot(x, y));
                }

        }
    }
}
