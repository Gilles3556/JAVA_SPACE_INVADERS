package pndgV1.ihm;

import pndgV1.model.JeuInvaders;

import javax.swing.*;

public class FenetreSpaceInvaders extends JFrame  {

    public FenetreSpaceInvaders( JeuInvaders leJeu) {
        initUI(leJeu);
    }

    private void initUI(JeuInvaders leJeu) {
        add(new Board(this,leJeu));

        setTitle(Commons.TITRE_JEU);
        setSize(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public void afficher(){
        this.setVisible(true);
    }
}
