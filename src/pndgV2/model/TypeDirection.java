package pndgV2.model;

public enum TypeDirection {
    DOWN(1),UP(-1);

    private int code;

    TypeDirection( int code ){
        this.code = code;
    }

    public int getCode(){
        return code;
    }
}
