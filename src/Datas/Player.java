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
        Move move;
        int territoryIdAttack;
        int territoryIdDefend;
        Integer d1, d2;
        Integer attack;
        Integer defend;

        System.out.print("Enter your territory : ");
        attack = input.nextInt();
        System.out.print("Enter the territory you want to attack : ");
        defend = input.nextInt();




        move = new Move(attack, defend);

        System.out.println("Your move is : ("+ move.getIdAttacker()+ " "+move.getIdDefender()+")");






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

    public void infoPlayer()
    {
        System.out.println("===============");
        System.out.println("Name : " + this.name);
        System.out.println("Territories : ");
        for(Territory t : territories) {
            System.out.println(t.getId() + " -> neighbour : ");
            for (Territory v : t.getNeighboringTer()) {
                System.out.println(v.getId() +", ");
            }
        }
        System.out.println("===============");





    }
}
