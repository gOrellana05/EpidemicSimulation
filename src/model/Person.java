package model;

public class Person {
    public enum State{
        EMPTY,
        SUSCEPTIBLE,
        INFECTED,
        RECOVERED
    }


    private int x, y;
    public State state;
    public int homeX;
    public int homeY;

    public int infectionTime = 0;
    public Person(int x, int y){
        this.x=x;
        this.y=y;
        this.state = State.SUSCEPTIBLE;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void setX(int x){
        this.x=x;
    }
    public void setY(int y){
        this.y=y;
    }
}
