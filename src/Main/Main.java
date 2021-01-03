package Main;
import Datas.Player;
import Datas.Territory;
import Logic.Game;
import Datas.Map;

import java.util.ArrayList;

import java.util.Random;
import java.util.Scanner;

public class Main {

    /*************** Choice of the map  *******************/

    static Map mapChoice(Scanner input){

        // Choice of the map
        System.out.println("Choose the map you want : ");
        System.out.println("1. From CSV file");
        System.out.println("2. Random map ");

        int choiceMap = input.nextInt();

        switch(choiceMap){
            case 1:
                //constructor csv file
               break;

            case 2 :
                Map map = new Map();
                return map;

            default :
                System.out.println("Non-valid input");
                break;
        }

        return null;
    }

/*************************************************************/


/************** Choice number of players *********************/

static ArrayList<Player> creationOfPlayers(Scanner input, ArrayList <Player> players){


    System.out.println("How many players ? [2 - 6]");
    int nbPlayers= input.nextInt();


    for(int i = 0; i < nbPlayers; i++){
        //creation of the player with an ID
        Player p = new Player(i+1);

        // Name settings
        System.out.print("Player " + (i+1) +" ,enter your name : ");
        String name = input.next();
        p.setName(name);
        //add to the list of players
        players.add(p);

    }

    System.out.println("There are " + nbPlayers + " players");
    System.out.println("--------------------");
    for(int i = 0; i < players.size(); i ++){
        System.out.println("Player "+ (i+1) + " : " + players.get(i).getName());
    }
    System.out.println("--------------------");

  return  players;
}

/*****************************************************************************/



    public static void main(String[] args) {

        /**************** Game configuration *****************/

        //1- The player choose if he wants to load a map from csv file or a random map.
        //2- Choose the number of player (2-6)
        //3- Initialize the name of players

        /***************************************************/

        /************ Initialization of the game ***********/

        //1- Create an array of players
        //2- Create an array of all territories
        //3- Create a map
        //4- Create a a Game (players, map)
        //5- Distribute strengh

        /***********************************************/

        /******************  Playing ******************/

        //1- Choose the first player
        //2- Two possible action : end turn and attack.
           // if(action == attack) -> do the action
           // else -> next player




        Scanner input = new Scanner(System.in);
        Random random = new Random();

        /******************* DATAS **********************/

        //Creation of a list of players
        ArrayList<Player> players = new ArrayList<Player>();
        ArrayList<Territory> allTerritories = new ArrayList<Territory>();

        /**************** CONFIGURATION *****************/

        //The player choose if he want a csv file or a random map + creation of the map
        Map map =  mapChoice(input);

        //Ask the number of players and create each players with their id and names
        players = creationOfPlayers(input,players);


        /**************** INITIALIZATION ****************/


        //Creation of the game. Parameters : players and map
        Game game = new Game(players, map);

        //display of the map
        game.displayMap();

       //Distribution of territories
       allTerritories = game.getMap().getListOfTerritories();
       game.territoriesDistribution(allTerritories, random);



       //Distribution strength for territories (number of dices per territory)
        int totalStrength = 8;
        for(Player p : game.getPlayers()) {
            game.distributionStrengthTerritory(totalStrength, p, random);
        }



          /******************** PLAYING *********************/





          int firstPlayer = random.nextInt(game.getPlayers().size());
          int indexPlayer = firstPlayer;
          boolean endGame = false;
          int choice;


          for(Player p : game.getPlayers()){
              p.infoPlayer();
          }


          while(!endGame){
              System.out.println("Player "+ game.getPlayers().get(indexPlayer).getName()+", it's your turn.");
              System.out.println("1. Attack");
              System.out.println("2.Pass");
              choice = input.nextInt();

              if(choice == 1){

                  //The current player attack
                  game.getPlayers().get(indexPlayer).attackTerritory(input);

                  //Info players update
                  for(Player p : game.getPlayers()){
                      p.infoPlayer();
                  }

              }


              else{
                 indexPlayer +=1;
	              if(indexPlayer== game.getPlayers().size())
		                indexPlayer = 0;
	               if( indexPlayer== firstPlayer)
                       System.out.println("fin du tour");
              }


          }



        }









    }
