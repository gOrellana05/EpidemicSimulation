package model;
import java.util.*;

import static model.Person.State.*;

public class Grid {
    private Person[][] people;
    int width;
    int heigth;
    public Grid(int max_x, int max_y) {
        this.width = max_x;
        this.heigth = max_y;


        people = new Person[max_x][max_y];

        for (int x = 0; x < max_x; x++) {
            for (int y = 0; y < max_y; y++) {
                people[x][y] = new Person(x, y);
                people[x][y].homeX = x;
                people[x][y].homeY = y;


            }
        }
    }

    public List<Person> getNeighbors(Person p) {
        List<Person> neighbors = new ArrayList<>();

        int x = p.getX();
        int y = p.getY();


        // arriba
        addIfValid(neighbors, x, y - 1);

        // abajo
        addIfValid(neighbors, x, y + 1);

        // izquierda
        addIfValid(neighbors, x - 1, y);

        // derecha
        addIfValid(neighbors, x + 1, y);

        return neighbors;
    }
    private void addIfValid(List<Person> list, int x, int y){
        Person p = getPerson(x,y);
        if(p!=null){
            list.add(p);
        }
    }
    public Person getPerson(int x, int y){
        if (x >= 0 && y >= 0 && x < width && y < heigth) {
        return people[x][y];
    }

        return null;
    }
    public Person[][] getPeople(){
        return people;
    }
    public int getWidth() {
        return width;
    }

    public int getHeigth() {
        return heigth;
    }

}
