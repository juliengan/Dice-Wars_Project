package Datas;

import java.util.ArrayList;
import java.util.Random;
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

    public Player(Integer id) {
        territories = new ArrayList<>();
        id = id;
    }

    public Move attackTerritory(Scanner input) {
        Move move = null;
        int territoryIdAttack;
        int territoryIdDefend;
        Integer d1, d2;
        Integer attack;
        Integer defend;

        while (move == null) {

            System.out.print("Enter your territory : ");
            attack = input.nextInt();

            if (!this.territories.contains(getTerritoryById(attack))) {
                System.out.println("This is not your territory, you can't attack");
                continue;
            } else if (getTerritoryById(attack).getStrength() <= 1) {
                System.out.println("This territory doesn't have enough strength to attack");
                continue;
            }


            System.out.print("Enter the territory you want to attack : ");
            defend = input.nextInt();


            if (getTerritoryById(attack).getNeighboringTer().contains(getTerritoryById(defend))) {
                System.out.println("The territory you want to attack is not your neighbour ");
                continue;
            } else if (this.territories.contains(getTerritoryById(defend))) {
                System.out.println("You can't attack your own territory ! ");
                continue;
            }


            move = new Move(attack, defend);
            System.out.println("Your move is : (" + move.getIdAttacker() + " " + move.getIdDefender() + ")");

        }
return move;







    }


    //This function return a territory for a given ID
    public Territory getTerritoryById(int id){

        for(Territory t : this.territories){
            if(t.getId() == id){
                return  t;
            }
        }
        return null;
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
