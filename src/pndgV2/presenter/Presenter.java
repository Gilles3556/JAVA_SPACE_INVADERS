package pndgV2.presenter;

import pndgV2.ihm.FabriqueIhm;
import pndgV2.ihm.FenetreSpaceInvaders;
import pndgV2.model.FabriqueMetier;
import pndgV2.model.JeuInvaders;

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
