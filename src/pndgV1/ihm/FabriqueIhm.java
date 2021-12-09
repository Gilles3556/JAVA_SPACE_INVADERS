package pndgV1.ihm;

import pndgV1.model.JeuInvaders;
import pndgV1.model.TypeObjet;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class FabriqueIhm {

    public static FenetreSpaceInvaders creerFenetre( JeuInvaders leJeu){
        return new FenetreSpaceInvaders(leJeu);
    }

    public static Board creerBoard(JFrame jf,JeuInvaders leJeu){
        return new Board(jf,leJeu);
    }

    public static TAdapter creerAdapter(Board b, JeuInvaders leJeu){
        return new TAdapter(b,leJeu);
    }

    public static Image creerImage( TypeObjet leType ){
        ImageIcon img =null;
        switch (leType.ordinal()){
            case 0: //Alien
                img = new ImageIcon(Commons.IMG_ALIEN);
                break;
            case 1: //Bomb
                img = new ImageIcon(Commons.IMG_BOMB);
                break;
            case 2: //player
                img = new ImageIcon(Commons.IMG_PLAYER);
                break;
            case 3: //Shot
                img = new ImageIcon(Commons.IMG_SHOT);
                break;
            case  4: //Explosion
                img = new ImageIcon(Commons.PATH_IMG_EXPLOSION);
                break;
        }
        if (Objects.isNull(img)) {
            return null;
        }
        return img.getImage();
    }
}
