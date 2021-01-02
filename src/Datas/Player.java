package Datas;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    private final int id = 0;
    private ArrayList<Territory> territories;
    private String name;

    public int getId() {
        return id;
    }

    public ArrayList<Territory> getTerritories() {
        return territories;
    }

    public Player(Integer id)
    {
        territories = new ArrayList<>();
        id = id;
    }
    public void attackTerritory(Scanner input)
    {
        int territoryIdAttack;
        int territoryIdDefend;
        Integer d1, d2;
        String s;

        System.out.println("Enter your territory and the territory you want to attack (1 2) : ");
        s = input.next( "d1//.//d2");
        System.out.println(s);




    }
    public void endTurn()
    {

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
