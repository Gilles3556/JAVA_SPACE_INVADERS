package pndgV3.presenter;

import pndgV3.ihm.FabriqueIhm;
import pndgV3.ihm.FenetreSpaceInvaders;
import pndgV3.model.FabriqueMetier;
import pndgV3.model.JeuInvaders;

import java.awt.*;

public class Presenter {

    private JeuInvaders jeu;
    private FenetreSpaceInvaders fen;

    public Presenter()  {
        // Metier
        jeu = FabriqueMetier.creerJeu();
        jeu.init();
        // IHM
        fen = FabriqueIhm.creerFenetre(jeu);
    }

    public void exec()
    {
        EventQueue.invokeLater(() -> {
            fen.afficher();
        });
    }
}
