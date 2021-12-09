package pndgV2.ihm;

import pndgV2.model.JeuInvaders;

import javax.swing.*;

public class FenetreSpaceInvaders extends JFrame  {

    public FenetreSpaceInvaders( JeuInvaders leJeu) {
        initUI(leJeu);
    }

    private void initUI(JeuInvaders leJeu) {
        setTitle(Commons.TITRE_JEU+", niv:1");

        //Commons.BOARD_WIDTH
        setSize(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);
        add(new Board(this,leJeu));
    }

    public void afficher(){

        this.setVisible(true);
    }
}
