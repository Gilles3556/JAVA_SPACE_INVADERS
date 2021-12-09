package pndgV2.ihm;

public interface Commons {

    int BOARD_WIDTH = 358;
    int BOARD_HEIGHT = 350;
    int BORDER_RIGHT = 30;
    int BORDER_LEFT = 5;

    int GROUND = 290;
    int BOMB_HEIGHT = 5;

    int ALIEN_HEIGHT = 12;
    int ALIEN_WIDTH = 12;
    int ALIEN_INIT_X = 150;
    int ALIEN_INIT_Y = 5;

    int GO_DOWN = 15;

    int CHANCE = 5;
    int DELAY = 17;
    int PLAYER_WIDTH = 15;
    int PLAYER_HEIGHT = 10;

    String MSG_GAME_OVER = "Game Over";
    String MSG_GAME_WON="Game won!";
    String MSG_GAME_INVASION = "INVASION !!!";

    String PATH_IMG_EXPLOSION = "src/images/explosion.png";
    String IMG_ALIEN = "src/images/alien.png";
    String IMG_BOMB = "src/images/bomb.png";;
    String IMG_PLAYER = "src/images/player.png";
    String IMG_SHOT = "src/images/shot.png";

    String MSG_GAME_PLAYER_DIED = "Vous êtes mort !!!" ;

    int DEP_LEFT = -2 ;
    int DEP_RIGHT= 2;
    int DEP_ZERO = 0;

    int MAX_ALIEN_LIGNE = 2;
    int MAX_ALIEN_NB = 5;

    int NUMBER_OF_ALIENS_TO_DESTROY = MAX_ALIEN_LIGNE * MAX_ALIEN_NB;
    int LIGNE_LIMITE_SHOT = 6;

    String TITRE_JEU = "Space Invaders";
    int MAX_LEVEL=5 ;
    int DEBUT_NB_VIE = 3;
}
