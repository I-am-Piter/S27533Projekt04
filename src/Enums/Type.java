package Enums;

public enum Type {
    HEAD(1),BODY(2),FOOD(3),SPACE(4);
    int id;
    Type(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
}
