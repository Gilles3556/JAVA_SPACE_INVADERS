package pndgV3.ihm;

import pndgV3.model.*;
import pndgV3.model.exceptions.JeuInvadersFinException;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public final class GuiDrawer {
    private GuiDrawer(){}

    public static  void drawAliens( Graphics g , List<Alien> lesAliens, JPanel jpan) {

        for (Alien alien : lesAliens) {
            if (alien.isVisible()) {
                g.drawImage(FabriqueIhm.creerImage(TypeObjet.ALIEN), alien.getX(), alien.getY(), jpan);
            }
            if (!alien.isDying() && alien.isTouche()) {
                   alien.setLeType(TypeObjet.EXPLOSION);
                   g.drawImage(FabriqueIhm.creerImage(TypeObjet.EXPLOSION), alien.getX(), alien.getY(), jpan);

                alien.die();
            }
        }
    }
    public static void drawPlayer( Graphics g , Player lePlayer, JPanel jpan) throws JeuInvadersFinException {
        // System.out.println(" drawPlayer():"+ MesOutils.recuperTemps());
        if (lePlayer.isVisible()) {
            g.drawImage(FabriqueIhm.creerImage(TypeObjet.PLAYER), lePlayer.getX(), lePlayer.getY(), jpan);
        }

        if (lePlayer.isDying()) {
            g.drawImage(FabriqueIhm.creerImage(TypeObjet.EXPLOSION), lePlayer.getX(), lePlayer.getY(), jpan);
            lePlayer.die();
            throw new JeuInvadersFinException(Commons.MSG_GAME_PLAYER_DIED);
        }
    }
    public static  void drawShot( Graphics g, Shot unShot , JPanel jpan) {
        if (unShot.isVisible()) {
            g.drawImage(FabriqueIhm.creerImage(TypeObjet.SHOT), unShot.getX(), unShot.getY(), jpan);
        }
    }

    public static void drawBombing( Graphics g ,List<Alien> lesAliens, JPanel jpan) {
        for (Alien a : lesAliens) {
            Bomb b = a.getBomb();
            if (!b.isDestroyed()) {
                g.drawImage(FabriqueIhm.creerImage(TypeObjet.BOMB), b.getX(), b.getY(), jpan);
            }
        }
    }
    public static  void majInfos(Graphics g,int nivo,int nbvie,JPanel jpan){
        drawBigTexte(g,"NIVEAU = "+nivo,380,50,jpan);
        drawSmallTexte(g,"VIE = "+nbvie,380,120,jpan);
    }
    public static void drawSmallTexte(Graphics g, String txt, int x, int y,JPanel jpan){
        var small = new Font("Helvetica", Font.BOLD, 14);
        var fontMetrics2 = jpan.getFontMetrics(small);
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(txt,x,y);
    }
    public static void drawBigTexte(Graphics g, String txt, int x, int y,JPanel jpan){
        var big = new Font("Helvetica", Font.BOLD, 20);
        var fontMetrics = jpan.getFontMetrics(big);
        g.setColor(Color.white);
        g.setFont(big);
        g.drawString(txt,x,y);
    }
    public static void drawLigne(Graphics g,int xdebut, int ydebut,int xfin, int yfin){
        g.setColor(Color.black);
        g.fillRect(0, 0, Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
        g.setColor(Color.green);

        g.drawLine(0, Commons.GROUND,
                Commons.BOARD_WIDTH, Commons.GROUND);
    }
}
