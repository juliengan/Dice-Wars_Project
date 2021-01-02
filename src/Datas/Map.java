package Datas;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Map {
   private Territory [][] map;

   private ArrayList<Territory> listOfTerritories;


    public Map(File gnagnagna){ //init map from CSV file

    }
    public Territory getTerritory(int x, int y){
        return this.map[x][y];

    }
    public Territory[][] getMap() {
        return map;
    }

    public ArrayList<Territory> getListOfTerritories() {
        return listOfTerritories;
    }

    public Map(){

        Territory o = new Territory(0,0);
       Territory t1 = new Territory(1,0);
       Territory t2 = new Territory(2,0);
       Territory t3 = new Territory(3,0);
       Territory t4 = new Territory(4,0);

       this.listOfTerritories = new ArrayList<Territory>();

       listOfTerritories.add(o);
       listOfTerritories.add(t1);
       listOfTerritories.add(t2);
       listOfTerritories.add(t3);
       listOfTerritories.add(t4);



        this.map = new Territory[][]{  {t1, t1, t1, o, o, o, o, t2, t2, t2},
                                  {t1, t1, t1, t1, t1, o, o, t2, t2, t2},
                                  {t1, t1, t1, t1, t1, o, o, t2, t2, t2},
                                  {t1, t1, t1, t2, o, o, o, t2, t2, t2},
                                  {t1, t1, t1, t1, t1, o, t2, t2, t2, t4},
                                  {t3, t3, t3, t3, t3, t1, t2, t2, t4, t4},
                                  {t3, t3, t3, t3, t3, t3, t3, t2, t4, t4},
                                  {t3, t3, t3, t3, t3, t3, t3, t4, t4, t4},
                                  {o, o, o,o, t4, t4, t4, t4, t4, t4},
                                  {t1, t1, t1, t4, t4, t4, t4, t4, t4, t4},


        };





    }

}
