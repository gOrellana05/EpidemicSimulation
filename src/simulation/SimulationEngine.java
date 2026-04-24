package simulation;

import model.*;
import java.util.*;

public class SimulationEngine {
    private Grid grid;

    private double infectionRate = 0.3;
    private int recoveryTime = 10;
    private Random random = new Random();

    private List<Integer> susceptibleHistory = new ArrayList<>();
    private List<Integer> infectedHistory = new ArrayList<>();
    private List<Integer> recoveredHistory = new ArrayList<>();

    public SimulationEngine(Grid grid){
        this.grid = grid;
    }

    public void step(){
        int infectedCount = 0;

        for (Person[] row : grid.getPeople()) {
            for (Person p : row) {
                if (p.state == Person.State.INFECTED) {
                    infectedCount++;
                }
            }
        }

        if (infectedCount == 0) return;
        movePeople();

        List<Person> newlyInfected = new ArrayList<>();

        for(Person[] row: grid.getPeople()){
            for(Person p: row){
                if (p == null) continue;
                if(p.state == Person.State.INFECTED){
                    for (Person n : grid.getNeighbors(p)) {

                        if (n.state == Person.State.SUSCEPTIBLE) {

                            if (Math.random() < infectionRate * 0.5 + random.nextDouble()*0.5) {
                                newlyInfected.add(n);
                            }
                        }
                    }
                    p.infectionTime++;

                    if(p.infectionTime >= recoveryTime){
                        p.state = Person.State.RECOVERED;
                    }
                }
            }
        }
        for(Person p: newlyInfected){
            p.state = Person.State.INFECTED;
            p.infectionTime = 0;
        }
        updateStats();
    }
    private void movePeople() {

        Person[][] gridData = grid.getPeople();

        for (int x = 0; x < grid.getWidth(); x++) {
            for (int y = 0; y < grid.getHeigth(); y++) {

                Person p = gridData[x][y];

                if (p == null) continue;

                int dx = random.nextInt(3) - 1;
                int dy = random.nextInt(3) - 1;

                int nx = x + dx;
                int ny = y + dy;

                if (x < p.homeX) nx++;
                if (x > p.homeX) nx--;
                if (y < p.homeY) ny++;
                if (y > p.homeY) ny--;


                if (nx < 0 || ny < 0 || nx >= grid.getWidth() || ny >= grid.getHeigth()) {
                    continue;
                }
                if (gridData[nx][ny] == null) {

                    gridData[nx][ny] = p;
                    gridData[x][y] = null;

                    p.setX(nx);
                    p.setY(ny);
                }
            }
        }
    }    private void updateStats(){
        int s = 0;
        int i = 0;
        int r = 0;

        for(Person[] row : grid.getPeople()){
            for(Person p: row){
                switch(p.state){
                    case SUSCEPTIBLE -> s++;
                    case INFECTED -> i++;
                    case RECOVERED -> r++;
                }
            }
        }
        susceptibleHistory.add(s);
        infectedHistory.add(i);
        recoveredHistory.add(r);
    }
    public void reset(){
        susceptibleHistory.clear();
        infectedHistory.clear();
        recoveredHistory.clear();
        updateStats();

        for (Person[] row : grid.getPeople()) {
            for (Person p : row) {

                p.state = Person.State.SUSCEPTIBLE;
                p.infectionTime = 0;
            }
        }

        Person p = grid.getPerson(
                grid.getWidth() / 2,
                grid.getHeigth() / 2
        );

        if (p != null) {
            p.state = Person.State.INFECTED;
        }


    }

    public List<Integer> getInfectedHistory() {
        return infectedHistory;
    }

    public List<Integer> getRecoveredHistory() {
        return recoveredHistory;
    }

    public List<Integer> getSusceptibleHistory() {
        return susceptibleHistory;
    }
}
