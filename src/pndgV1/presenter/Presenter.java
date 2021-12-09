package pndgV1.presenter;

import pndgV1.ihm.FabriqueIhm;
import pndgV1.ihm.FenetreSpaceInvaders;
import pndgV1.model.FabriqueMetier;
import pndgV1.model.JeuInvaders;

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
