package pndgV3.ihm;

import pndgV3.model.Alien;
import pndgV3.model.Bomb;
import pndgV3.model.JeuInvaders;
import pndgV3.model.TypeObjet;
import pndgV3.model.exceptions.JeuInvadersFinException;

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

        GuiDrawer.majInfos( g,
                leJeu.getLevel(),
                leJeu.getNombreVie(),
                this);

        GuiDrawer.drawLigne(g,0,Commons.GROUND,Commons.BOARD_WIDTH, Commons.GROUND);

            try {
                GuiDrawer.drawAliens(g,leJeu.getAliens(),this);
                GuiDrawer.drawPlayer(g, leJeu.getUnPlayer(),this);
                GuiDrawer.drawShot(g,leJeu.getUnShot(),this);
                GuiDrawer.drawBombing(g, leJeu.getAliens(),this);
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

        GuiDrawer.drawSmallTexte(g,
                msgFin,
                (Commons.BOARD_WIDTH - msgFin.length())/3,
                (Commons.BOARD_WIDTH / 2),this);

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
