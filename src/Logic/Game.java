package Logic;

import Datas.Map;
import Datas.Move;
import Datas.Player;
import Datas.Territory;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
    /******************************************/
    private  Map map;
    private ArrayList <Territory> allTerritories;
    private final ArrayList<Player> players;
    /******************************************/

    /*************** CONSTRUCTOR **************/
    public Game(ArrayList<Player> players, Map newMap){

        map = new Map();
        allTerritories = new ArrayList<Territory>();
        this.players = players;
        this.map = newMap;
    }







    /************* DISPLAY MAP *******************/

    public void displayMap(){

        for(int y = 0; y <10; y++) {
            System.out.print("   ");
            for (int x = 0; x < 10; x++){
                System.out.print(this.map.getMap()[y][x].getId() + "  ");

            }
            System.out.println();
        }
    }


    /*********** TERRITORIES DISTRIBUTION *********/

    public void territoriesDistribution(ArrayList<Territory> allTerritories, Random random){

        int playerIndex = 0;

        // Create a random territory
        Territory randomTerritory;

        // Create a deep copy of allTerritories to avoid changes
        ArrayList<Territory> temp = (ArrayList<Territory>) allTerritories.clone();

        //Remove the first territory which is neutral (water)
        temp.remove(0);

        // While there are territories to distribute in th list
        while(temp.size() != 0){

                //choose a random index among the size of the list
                int randomIndex = random.nextInt(temp.size());

                //pick the territory according to the index
                 randomTerritory = temp.get(randomIndex);

                //give the territory to the current player
                 players.get(playerIndex).getTerritories().add(randomTerritory);

                 //set the player id for the territory
                 randomTerritory.setPlayerId( players.get(playerIndex).getId());

                 //delete the territory of the list
                 temp.remove(randomIndex);

            // increase the playerindex to deal with the next one
            playerIndex ++;

            // If we reach the end of the list of player we put the index to 0 and continue
            if(playerIndex == players.size() )
                playerIndex = 0;
        }


    }

    /*********** STRENGTH DISTRIBUTION *********/

    public void distributionStrengthTerritory(int totalStrength, Player p, Random r){
        System.out.println("Player "+ p.getId()+" : "+ p.getName().toUpperCase());

        int randomStrength;
        final int MAX_STRENGTH = totalStrength-players.size();
        int indexTerritory = 0;

        while((totalStrength-2 )> 0){
            randomStrength = r.nextInt(totalStrength);

            if(p.getTerritories().get(indexTerritory).getStrength() + randomStrength >= 8)
                continue;
            if(randomStrength == 0)
                continue;
            else{
                p.getTerritories().get(indexTerritory).addStrength(randomStrength);
            }

         totalStrength = totalStrength-randomStrength;
            indexTerritory++;
            if(indexTerritory == p.getTerritories().size())
                indexTerritory =0;

        }


          for(int j = 0; j < p.getTerritories().size(); j++){
              System.out.println("Territory "+p.getTerritories().get(j).getId()+ " : "+ p.getTerritories().get(j).getStrength() + "(strength)");
          }

    }

    //This function return a territory for a given ID
    public Territory getTerritoryById(int id){
        for(Territory t : this.allTerritories ){
            if(t.getId() == id){
                return  t;
            }
        }
        System.out.println("null");
        return null;
    }

    public void infoPlayer(Player p)
    {
        System.out.println("===============");
        System.out.println("Name : " + p.getName());
        System.out.println("Territories : ");
        for(Territory t : p.getTerritories()) {
            System.out.println(t.getId() +"("+t.getStrength()+ ")  -> neighbour : ");
            for (Territory v : t.getNeighboringTer()) {
                System.out.println(v.getId() +", ");
            }
        }
        System.out.println("===============");



    }

    public void throwDices(Move move) {
        Random random = new Random();

        Territory attackerTerritory = getTerritoryById(move.getIdAttacker());
        Territory defenderTerritory = getTerritoryById(move.getIdDefender());

        Player attackerPlayer = getPlayerfromTerritory(attackerTerritory);
        Player defendPlayer = getPlayerfromTerritory(defenderTerritory);


        int sumDiceAttacker = 0;
        int sumDiceDefender = 0;

        for (int i = 0; i < attackerTerritory.getStrength(); i++) {

            sumDiceAttacker += random.nextInt(6);

        }
        System.out.println("Attacker result : " + sumDiceAttacker);


        for (int i = 0; i < defenderTerritory.getStrength() ; i++) {

            sumDiceDefender += random.nextInt(6);
        }
        System.out.printf("Defender result : " + sumDiceDefender);


        if (sumDiceAttacker > sumDiceDefender) {

            System.out.println("Attacker wins");

            //the attackerTerritory take the territory, we add it to his list
            attackerPlayer.getTerritories().add(defenderTerritory);


            //change the player of the taken territory
            defenderTerritory.setPlayerId(attackerPlayer.getId());


            System.out.println( getPlayerfromTerritory(attackerTerritory).getName()+ "prend le territoire du joueur"+ defenderTerritory.getId());
            System.out.println("territoire conquis id player  : " + defenderTerritory.getPlayerId());

            //Remove the territory for the loser
           defendPlayer.getTerritories().remove(defenderTerritory);

            //he moves all his dice on the new territory exept 1
            defenderTerritory.setStrength(attackerTerritory.getStrength()-1);

            attackerTerritory.setStrength(1);

            for(Player p: this.players){
                infoPlayer(p);
            }



        }
        else {
            attackerTerritory.setStrength(1);
            System.out.println("Defender wins");
        }



    }



    /*********** GETTERS *********/

public Player getPlayerfromTerritory(Territory t){
        for(Player p :this.players){
            if(p.getTerritories().contains(t))
                return p;
        }

        return null;

}



    public Map getMap() {
        return map;
    }

    public ArrayList<Territory> getAllTerritories() {
        return allTerritories;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    /*********** SETTERS *********/
    public void setAllTerritories(ArrayList<Territory> allTerritories) {
        this.allTerritories = allTerritories;
    }
}
