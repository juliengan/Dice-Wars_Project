package Main;
import Datas.Move;
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
                System.out.println("opening a file ...");
               break;

            case 2 :
                //Load a map from the program
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






        /******************* DATAS **********************/

        Scanner input = new Scanner(System.in);
        Random random = new Random();

        //Creation of a list of players
        ArrayList<Player> players = new ArrayList<Player>();

        //Creation of a list of all territories
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

        allTerritories = game.getMap().getListOfTerritories();
        game.setAllTerritories(allTerritories);

        //Distribution of territories between all players
        game.territoriesDistribution(allTerritories, random);


       //Distribution strength for territories (number of dices per territory)
       //Probleme : pourquoi le premier joueur en a un de plus ? A corriger !
        int totalStrength = 8;
        for(Player p : game.getPlayers()) {
            game.distributionStrengthTerritory(totalStrength, p, random);
        }



          /******************** PLAYING *********************/

          // Choose the first player randomly
          int firstPlayer = random.nextInt(game.getPlayers().size());
          int indexPlayer = firstPlayer;

          // Boolean to stop the game when the ocndition is reach
          boolean endGame = false;

          // Choose te action : attack or pass
          int choice;

          // Create a move to store the attack action (attacking territory, defend territory)
          Move move;


          // Display the information about the players : territories, strength, neighbour
          for(Player p : game.getPlayers()){
             game.infoPlayer(p);
          }

          // while the condition is not reach, we continue to play
          while(!endGame){


              if(game.getPlayers().get(indexPlayer).isLost() == true)
                  indexPlayer++;

              if(indexPlayer== game.getPlayers().size())
                  indexPlayer = 0;



              System.out.println("Player "+ game.getPlayers().get(indexPlayer).getId() +" : "+ game.getPlayers().get(indexPlayer).getName()+", it's your turn.");
              System.out.println("1. Attack");
              System.out.println("2.Pass");
              choice = input.nextInt();

              if(choice == 1){

                  // The current player attack, we save its move if it is valid
                  move = game.getPlayers().get(indexPlayer).attackTerritory(input);

                  // The defender and attacker throw their dices, we compare and perform the change according to the result.
                  // REMAINDER : if the attacker wins he takes the territory he attcked, and put all his dice on it exept 1 he let on his initial territory
                  //            if defender wins the attacker can inly keep one dice on his territory
                  game.throwDices(move);
              }


              else {
                  // If the  player pass, we increase the index of the next player
                  indexPlayer +=1;

                  // If the index of the player reach the end, we put the index to 0 (the first player play)
	              if(indexPlayer== game.getPlayers().size())
		                indexPlayer = 0;

	              // If the first player play again, its the end of the turn
	               if( indexPlayer == firstPlayer)
                       System.out.println("END TURN");
	               //redistribution des dés
              }

              for(Player p : game.getPlayers()){
                  if(p.getTerritories().size() == 0){
                      System.out.println("Player "+ p.getName()+" doesn't have any territory, he lost !");
                      p.setLost(true);

                  }

              }
              endGame = game.checkEnd();

          }
        System.out.println("End of the game the winner is : " + game.whoIsWinner().getName());


        }


    }
