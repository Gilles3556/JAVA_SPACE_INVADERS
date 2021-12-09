package pndgV3.ihm;

public final class OutilsIhm {

    private OutilsIhm(){}

    /**
     * Méthode chargée de controler que y sort par le haut.
     * @param y: int, le y à vérifier
     * @return boolean
     */
    public static  boolean isSortiEnHaut(int y){
        boolean sorti =(y <0);
      /*  if (sorti) {
            System.out.println("est sorti en Y=" + y);
        }*/
        return sorti;
    }

    /**
     * Méthode chargée de controler que y sort par le bas.
     * @param y: int, le y à vérifier
     * @return boolean
     */
    public static  boolean isSortiEnBas(int y){
        boolean sorti =(y > Commons.GROUND - Commons.ALIEN_HEIGHT);
       /* if (sorti) {
            System.out.println("est sorti en Y=" + y);
        }*/
        return sorti;
    }

    /**
     * Méthode chargée de controler que x sort par la DROITE.
     * @param x: int, le x à vérifier
     * @return boolean
     */
    public static boolean isSortiDroite(int x){
        boolean sorti=(x>= Commons.BOARD_WIDTH - Commons.BORDER_RIGHT);
      /*  if(sorti){
            System.out.println("est sorti D en X=" + x);
        }*/
        return sorti;
    }

    /**
     * Méthode chargée de controler que x sort par la GAUCHE.
     * @param x: int, le x à vérifier
     * @return boolean
     */
    public  static  boolean isSortiGauche( int x ){
        boolean sorti=(x<= Commons.BORDER_LEFT);
     /*   if(sorti){
            System.out.println("est sorti G en X=" + x);
        }*/
        return sorti;
    }
}
