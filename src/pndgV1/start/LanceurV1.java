package pndgV1.start;

import pndgV1.presenter.Presenter;

public class LanceurV1 {
    public static void main( String[] args ) {
        Presenter pres = new Presenter();
        pres.exec();
    }
}
