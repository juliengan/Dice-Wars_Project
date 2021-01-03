package Datas;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Player {

    /*******************************************/
    private final int id ;
    private ArrayList<Territory> territories;
    private String name;
    /*******************************************/


    /*************** CONSTRUCTOR **************/
    public Player(Integer id) {
        territories = new ArrayList<>();
        this.id = id;
    }


    /*********** ATTACK TERRITORY ************/
    public Move attackTerritory(Scanner input) {

        Move move = null;
        Integer attack;
        Integer defend;

        // While the move is not valid, we continue to ask to the player
        while (move == null) {

            System.out.print("Enter your territory : ");
            attack = input.nextInt();

            // If the territory doesn't belong to the player
            if (!this.territories.contains(getTerritoryById(attack))) {
                System.out.println("This is not your territory, you can't attack");
                continue;
            }
            // If the territory doesn't have enough strength to attack
            else if (getTerritoryById(attack).getStrength() <= 1) {
                System.out.println("This territory doesn't have enough strength to attack");
                continue;
            }

            //---------------------------------------------------------------------------------


            System.out.print("Enter the territory you want to attack : ");
            defend = input.nextInt();

            // If the territory is not a neighbour
            if (getTerritoryById(attack).getNeighboringTer().contains(getTerritoryById(defend))) {
                System.out.println("The territory you want to attack is not your neighbour ");
                continue;
            }

            //If the territory belongs to the player
            else if (this.territories.contains(getTerritoryById(defend))) {
                System.out.println("You can't attack your own territory ! ");
                continue;
            }

            // Creation of the move we can leave the loop and return this.
            move = new Move(attack, defend);
            System.out.println("Your move is : (" + move.getIdAttacker() + " " + move.getIdDefender() + ")");

        }
return move;

    }


    /*********** GET TERRITORY BY ID ************/
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
    /*********** SETTERS ************/

    public void setName(String name) {
        this.name = name;
    }


    /*********** GETTERS ***********/

    public String getName() {
        return name;
    }


    public int getId() {
        return id;
    }

    public ArrayList<Territory> getTerritories() {
        return territories;
    }



}
