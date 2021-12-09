package pndgV2.ihm;

import pndgV2.model.Alien;
import pndgV2.model.Bomb;
import pndgV2.model.JeuInvaders;
import pndgV2.model.TypeObjet;
import pndgV2.model.exceptions.JeuInvadersFinException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board extends JPanel  implements ActionListener{
    private JFrame parent;
    private JeuInvaders leJeu;
    private Timer timer;

    public Board( JFrame fen,JeuInvaders leJeu ) {
        this.parent = fen;
        this.leJeu = leJeu;
        initBoard();

    }
    private void majTitreFen(){
        String titre = Commons.TITRE_JEU+", niveau ="+leJeu.getLevel();
        titre+=" vie="+leJeu.getUnPlayer().getNbVie();
        parent.setTitle(titre);
    }
    private void initBoard() {
        majTitreFen();
        setFocusable(true);
        setBackground(Color.black);

        addKeyListener(FabriqueIhm.creerAdapter(this, leJeu));

        timer = new Timer(Commons.DELAY, this);
        timer.start();
    }

    private void drawAliens( Graphics g ) {
        for (Alien alien : leJeu.getAliens()) {
            if (alien.isVisible()) {
              g.drawImage(FabriqueIhm.creerImage(TypeObjet.ALIEN), alien.getX(), alien.getY(), this);
            }
            if (alien.isDying()) {
                alien.die();
            }
        }
    }

    private void drawPlayer( Graphics g ) throws JeuInvadersFinException {
       // System.out.println(" drawPlayer():"+ MesOutils.recuperTemps());
        if (leJeu.getUnPlayer().isVisible()) {
            g.drawImage(FabriqueIhm.creerImage(TypeObjet.PLAYER), leJeu.getUnPlayer().getX(), leJeu.getUnPlayer().getY(), this);
        }

        if (leJeu.getUnPlayer().isDying()) {
           // g.drawImage(FabriqueIhm.creerImage(TypeObjet.EXPLOSION), leJeu.getUnPlayer().getX(), leJeu.getUnPlayer().getY(), this);
            leJeu.getUnPlayer().die();
            throw new JeuInvadersFinException(Commons.MSG_GAME_PLAYER_DIED);
        }
    }

    private void drawShot( Graphics g ) {

        if (leJeu.getUnShot().isVisible()) {
            g.drawImage(FabriqueIhm.creerImage(TypeObjet.SHOT), leJeu.getUnShot().getX(), leJeu.getUnShot().getY(), this);
        }
    }

    private void drawBombing( Graphics g ) {

        for (Alien a : leJeu.getAliens()) {
            Bomb b = a.getBomb();
            if (!b.isDestroyed()) {
                g.drawImage(FabriqueIhm.creerImage(TypeObjet.BOMB), b.getX(), b.getY(), this);
            }
        }
    }

    @Override
    public void paintComponent( Graphics g ) {
        super.paintComponent(g);

        try {
            doDrawing(g );
        } catch (JeuInvadersFinException e) {
           gameOver(e.getMessage(),g);
        }
    }

    private void doDrawing( Graphics g ) throws JeuInvadersFinException {
        majTitreFen();
        g.setColor(Color.black);
        g.fillRect(0, 0, Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
        g.setColor(Color.green);

            g.drawLine(0, Commons.GROUND,
                    Commons.BOARD_WIDTH, Commons.GROUND);
            try {
                drawAliens(g);
                drawPlayer(g);
                drawShot(g);
                drawBombing(g);
            }
            catch (Exception ex){
                timer.stop();
                gameOver(ex.getMessage(),g);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    public void gameOver(String msgFin,Graphics g) {
        timer.stop();

        if (g==null){
             g= this.getGraphics();
        }

        g.setColor(Color.black);
        g.fillRect(0, 0, Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
        g.setColor(new Color(0, 32, 48));
        g.fillRect(50, Commons.BOARD_WIDTH / 2 - 30, Commons.BOARD_WIDTH - 100, 50);
        g.setColor(Color.white);
        g.drawRect(50, Commons.BOARD_WIDTH / 2 - 30, Commons.BOARD_WIDTH - 100, 50);

        var small = new Font("Helvetica", Font.BOLD, 14);
        var fontMetrics = this.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msgFin,
                (Commons.BOARD_WIDTH - fontMetrics.stringWidth(msgFin)) / 2,
                Commons.BOARD_WIDTH / 2);

    }

    protected void update() throws JeuInvadersFinException{
        if(leJeu.etreFini()) {
            timer.stop();

            if( leJeu.getLevel()<= Commons.MAX_LEVEL){
                leJeu.nextLevel();
                leJeu.getUnPlayer().gagnerUneVie();
                majTitreFen();
                initBoard();

                update();
                repaint();
            }else {
                throw new JeuInvadersFinException(Commons.MSG_GAME_WON);
            }
        }else {
            // player
            leJeu.getUnPlayer().act();

            // shot
            leJeu.gererUnTir();

            // aliens
            leJeu.gererLesAliens();

            // bombs
            leJeu.gererBombs();
        }
    }

    @Override
    public void actionPerformed( ActionEvent e) {

        try {
            update();
            repaint();
        } catch (JeuInvadersFinException ex) {
            gameOver(ex.getMessage(),null);
        }
    }

}
