package Datas;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Map {

    /*************** ATTRIBUTES ****************/
    private Territory[][] map;
    private ArrayList<Territory> listOfTerritories;
    /******************************************/


    /**************** METHODS *****************/
    // Map()
    // Map (File file)

    /*****************************************/
    public Territory[][] getMap() {
        return map;
    }

    public ArrayList<Territory> getListOfTerritories() {
        return listOfTerritories;
    }

    public Territory getTerritory(int x, int y) {
        return this.map[x][y];
    }

    public Map(File file) {
//init map from CSV file

        //Créer un territoire ajouter un id
        // l'ajouter au tableau map  : map.add(t)
    }

    public Map(int nbPlayers) {
        System.out.println("dans le constructeur map");
        Random rand = new Random();

        //alloue la liste des territoires
        this.listOfTerritories = new ArrayList<Territory>();




        for (int i = 0; i < nbPlayers * 2; i++) {
            Territory t = new Territory(i, 0);
            this.listOfTerritories.add(t);

        }

        //neighbours
        for (Territory t : this.listOfTerritories) {
            t.UpdateneighboringTer(t);
        }

        //Creation of four territory : o is the water
     /*  Territory o = new Territory(0,0);
       Territory t1 = new Territory(1,0);
       Territory t2 = new Territory(2,0);
       Territory t3 = new Territory(3,0);
       Territory t4 = new Territory(4,0);

        //Initialization of neighbours
        t1.UpdateneighboringTer(t3);

        t2.UpdateneighboringTer(t3);
        t2.UpdateneighboringTer(t4);

        t3.UpdateneighboringTer(t1);
        t3.UpdateneighboringTer(t2);
        t3.UpdateneighboringTer(t4);

        t4.UpdateneighboringTer(t2);
        t4.UpdateneighboringTer(t3);*/


        // Add all territories to the list of territories
      /* listOfTerritories.add(o);
       listOfTerritories.add(t1);
       listOfTerritories.add(t2);
       listOfTerritories.add(t3);
       listOfTerritories.add(t4);*/
        System.out.println("taille list etrritory : " + this.listOfTerritories.size());

        int index;
        ArrayList<Territory> temp = (ArrayList<Territory>) this.listOfTerritories.clone();

        this.map = new Territory[nbPlayers][nbPlayers];

        while (temp.size() != 0) {


            for (int i = 0; i < nbPlayers; i++) {
                for (int j = 0; j < nbPlayers; j++) {
                    if(temp.size() == 0)
                        break;
                    System.out.println("tmp size : "+temp.size());

                    //index au hasard dans la liste de tous les territoires
                    index = rand.nextInt(temp.size());

                    this.map[i][j] = temp.get(index);
                    System.out.println("j ajoute " + temp.get(index).getId());

                    temp.remove(temp.get(index));
                }
            }
        }



    }
}



