package Main;
import Datas.Move;
import Datas.Player;
import Datas.Territory;
import Exceptions.InvalidAttackedTerritory;
import Exceptions.InvalidAttackingTerritory;
import Logic.Game;
import Datas.Map;

import java.util.ArrayList;

import java.util.Random;
import java.util.Scanner;

public class Main {


    /*************** Choice of the map  *******************/

    static Map mapChoice(Scanner input) {

        // Choice of the map
        System.out.println("Choose the map you want : ");
        System.out.println("1. From CSV file");
        System.out.println("2. Map in the game ");

        int choiceMap = input.nextInt();

        switch (choiceMap) {
            case 1:
                //constructor csv file
                System.out.println("opening a file ...");
                break;

            case 2:
                //Load a map from the program
                // Map map = new Map();
                // return map;

            default:
                System.out.println("Non-valid input");
                break;
        }

        return null;
    }

/*************************************************************/


    /************** Choice number of players *********************/

    static ArrayList<Player> creationOfPlayers(Scanner input, ArrayList<Player> players) {


        System.out.println("How many players ? [2 - 6]");
        int nbPlayers = input.nextInt();


        for (int i = 1; i <= nbPlayers; i++) {

            //creation of the player with an ID
            Player p = new Player(i);

            // Name settings
            System.out.print("Player " + (i + 1) + " ,enter your name : ");
            String name = input.next();
            p.setName(name);

            //add to the list of players
            players.add(p);

        }

        System.out.println("There are " + nbPlayers + " players");
        System.out.println("--------------------");
        for (int i = 0; i < players.size(); i++) {
            System.out.println("Player " + (i + 1) + " : " + players.get(i).getName());
        }
        System.out.println("--------------------");

        return players;
    }

    /*****************************************************************************/


    public static void main(String[] args) throws InvalidAttackingTerritory, InvalidAttackedTerritory {

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
        boolean firstDistribution = true;

        //Creation of a list of players
        ArrayList<Player> players = new ArrayList<Player>();

        //Creation of a list of all territories
        ArrayList<Territory> allTerritories = new ArrayList<Territory>();

        /**************** CONFIGURATION *****************/

        //Ask the number of players and create each players with their id and names
        players = creationOfPlayers(input, players);

        //The player choose if he want a csv file or a random map + creation of the map
        Map map = new Map(players.size());


        /**************** INITIALIZATION ****************/


        //Creation of the game. Parameters : players and map
        Game game = new Game(players, map);
        game.setAllTerritories(game.getMap().getListOfTerritories());

        //display of the map
        game.displayMap(game.getPlayers().size());


        //Distribution of territories between all players
        game.territoriesDistribution(random);


        //display of the map
        game.displayMap(game.getPlayers().size());


        System.out.println("Territoire de P1");
        for (Territory t : game.getPlayers().get(0).getTerritories())
            System.out.println(t.getId());

        System.out.println("Territoire de P2");
        for (Territory t : game.getPlayers().get(1).getTerritories())
            System.out.println(t.getId());

        //Distribution strength for territories (number of dices per territory)
        final int totalStrength = 16;
        for (Player p : game.getPlayers()) {
            game.distributionStrengthTerritory(totalStrength, p, random, firstDistribution);
        }


        //display of the map
        game.displayMap(game.getPlayers().size());

        for(Territory t : game.getAllTerritories()){
            System.out.println("dans main : "+ t.getStrength());
        }


        /******************** PLAYING *********************/

        //------------------------------------------------------------------------------------------

        // Choose the first player randomly
        int firstPlayer = random.nextInt(game.getPlayers().size());
        int indexPlayer = firstPlayer;

        // Boolean to stop the game when the ocndition is reach
        boolean endGame = false;

        //Boolean to check if the turn is over
        boolean endTurn = false;


        // Choose te action : attack or pass
        int choice;

        // Create a move to store the attack action (attacking territory, defend territory)
        Move move;


        // Display the information about the players : territories, strength, neighbour
        for (Player p : game.getPlayers()) {
            game.infoPlayer(p);
        }









          //------------------------------------------------------------------------------------------

          // while the condition is not reach, we continue to play
          while(!endGame){

              // A player who loose can't play, we pass its turn
              if(game.getPlayers().get(indexPlayer).isLost())
                  indexPlayer++;

              if(indexPlayer == game.getPlayers().size())
                  indexPlayer = 0;


             switch(game.askAction(input,indexPlayer)){

                 case 1: /***ATTACK***/
              // The current player attack, we save its move if it is valid
                     game.displayMap(game.getPlayers().size());
                    move = game.getPlayers().get(indexPlayer).attackTerritory(input);
                     if(move == null)
                         continue;

                     // The defender and attacker throw their dices, we compare and perform the change according to the result.
                     game.throwDices(move);//display map
                     game.displayMap(game.getPlayers().size());
                     break;

                 case 2: /**PASS**/
                // If the  player pass, we increase the index of the next player
                 indexPlayer++;
                     if(indexPlayer == players.size())
                        indexPlayer = 0;


                     if(game.isEndTurn(endTurn, firstPlayer, indexPlayer)) {
                         System.out.println("END TURN");

                         //redistribution des dés, on récupère les territoires contigus.
                         int nb;
                         for (Player p : game.getPlayers()) {
                             System.out.println("player : " + p.getName());
                             nb = game.nbOfcontiguousTerritory(p);

                             //distribution strength
                             firstDistribution = false;
                             game.distributionStrengthTerritory(nb, p, random,firstDistribution);
                             System.out.println("There are " + nb + " contiguous");
                             //game.infoPlayer(p);
                         }
                     }
                     break;

                 default :
                     System.out.println("Please, choose a valid option.");
                     break;

             }


              game.isPlayerLoose();      //check if there is a player who loose
              endGame = game.checkEnd(); // check if the game is over or not (only one player)

          }//end while

        System.out.println("End of the game the winner is : " + game.whoIsWinner().getName());


        }


    }

