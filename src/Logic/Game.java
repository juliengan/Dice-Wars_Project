package Logic;

import Datas.Map;
import Datas.Player;
import Datas.Territory;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {

    private  Map map;
    private ArrayList <Territory> allTerritories;
    private final ArrayList<Player> players;


    public Game(ArrayList<Player> players, Map newMap){
        map = new Map();
        allTerritories = new ArrayList<Territory>();
        this.players = players;
        this.map = newMap;
    }
    public void OrderGame(/*array of players*/){ //order of the players
        System.out.println(/*name of the player,*/"Your turn :)\n");
    }
    public void DisplayMap()
    {

    }


    /************* Display *******************/

    public void displayMap(){



        for(int y = 0; y <10; y++) {
            System.out.print("   ");
            for (int x = 0; x < 10; x++){
                System.out.print(this.map.getMap()[y][x].getId() + "  ");

            }
            System.out.println();
        }
    }
    /*********** Game configuration **********/

    /********** Territories distribution *******/

    public void territoriesDistribution(ArrayList<Territory> allTerritories, Random random){

        int playerIndex = 0;
     //on enlève le territoire neutre
        Territory randomTerritory;

        allTerritories.remove(0);
        while(allTerritories.size() != 0){

                //choose a random index among territories lisrt
                int randomIndex = random.nextInt(allTerritories.size());
                //pick the territory according to the index
                 randomTerritory = allTerritories.get(randomIndex);
                 //delete the territory of the list
                 allTerritories.remove(randomIndex);
                //give the territory to the current player
                 players.get(playerIndex).getTerritories().add(randomTerritory);



            playerIndex ++;
            if(playerIndex == players.size() )
                playerIndex = 0;
        }


   for(int j = 0; j < players.size(); j ++){
       System.out.println("Player :" + players.get(j).getName());
       for(int i = 0; i < players.get(j).getTerritories().size(); i ++ ){
           System.out.println(players.get(j).getTerritories().get(i).getId());
       }
   }

    }



    public void distributionStrengthTerritory(int totalStrength, Player p, Random r){
        System.out.println("Player : " + p.getName());

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


    public void infoPlayer(Player p)
    {
        System.out.println("===============");
        System.out.println("Name : " + p.getName());
        System.out.println("Territories : ");
        for(Territory t : p.getTerritories()) {
            System.out.println(t.getId() + " -> neighbour : ");
            for (Territory v : t.getNeighboringTer()) {
                System.out.println(v.getId() +", ");
            }
        }
        System.out.println("===============");



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
}
