package Datas;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Map {

    /*************** ATTRIBUTES ****************/
    private Territory[][] map;
    private ArrayList<Territory> listOfTerritories;
    public int x;
    public int y;
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

    public Map(int nbPlayerCSV, int nbTerritoriesbyPlayerCSV, Player[] listPlayers) {

        //init map from CSV file
        //Créer un territoire ajouter un id
        // l'ajouter au tableau map  : map.add(t)

        //open a csv file and print it 

        /*String path = "C:\\Users\\chens\\Desktop\\projetJAVA\\openCSV\\CSV_test_Java.csv";
        String column = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while((column = br.readLine()) != null){
                String[] values = column.split(";");
                System.out.println(values[0]+ " " +  values[1] + " " + values[2] + " " + values[3] + " " + values[4] + " " + values[5]);
                //System.out.println(column);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        /*Initialise la map a partir du csv
        int i=0, j=0;
        File file = new File("test.csv");

        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(file));	//read the csv file
            String line;

            //continue to read the next line
            while ((line = reader.readLine()) != null)	//file reading
            {
                String[] row = line.split(";");  //every row is separated by a ";"
                for (String index : row)   //will go through each line of the file
                {
                    int ID = Integer.parseInt(index);

                    for (int g=0; g<=nbPlayerCSV -1; g++){
                        if (ID == listPlayers[g].getID()){
                            Territory[][] = new Territory(listPlayers[g]);
                            listPlayers[g].setListConqueredTerritories(Territory[i][j]);
                        }
                    }

                    j++;//increment the columns index
                }
                i++;//increment the line index
                j=0;//reset the columns index
            }


            reader.close();

        }catch( IOException ioException ) { }*/


        
        /*fonction pour calculer le nombre de ligne et de colonne dans le csv
        private static Integer[] getsizemapFromCVS() {
            int x = 0, y = 0, z=0;
            File file = new File("test.csv");

            try{
                BufferedReader reader = new BufferedReader(new FileReader(file));    //read the csv file
                String line;

                //continue to read the next line
                while ((line = reader.readLine()) != null)	//file reading
                {
                    String[] row = line.split(";");  //every row is separated by a ";"
                    for (String index : row)   //will go through each line of the file
                    {
                        y=y+1; //increment the columns index
                    }
                    x=x+1; //increment the line index
                    z = y;
                    y=0;//reset the columns index
                }
                Integer[] size = new Integer[2];
                size[0]=x;
                size[1]=z;

                reader.close();
                return size;

            }

            catch( Exception e ) {
                e.printStackTrace();
            }
            return null;
        }*/

    }

    public Map(int nbPlayers) {

        ;

        Random rand = new Random();

        //alloue la liste des territoires
        this.listOfTerritories = new ArrayList<Territory>();


        for (int i = 0; i < nbPlayers * 4; i++) {
            Territory t = new Territory(i + 1, 0);
            this.listOfTerritories.add(t);

        }


        int index;


        ArrayList<Territory> temp = new ArrayList<Territory>();
        Iterator<Territory> iterator = this.listOfTerritories.iterator();

        while (iterator.hasNext()) {
            //Add the object clones
            temp.add((Territory) iterator.next().clone());
        }

        if (nbPlayers == 2) {
            x = 2;
            y = 4;
            this.map = new Territory[x][y];
        }
        if (nbPlayers == 3) {
            x = 4;
            y = 3;
            this.map = new Territory[x][y];
        }
        if (nbPlayers == 4) {
            x = 4;
            y = 4;
            this.map = new Territory[x][y];
        }

        if (nbPlayers == 5) {
            x = 4;
            y = 5;
            this.map = new Territory[x][y];
        }


        if (nbPlayers == 6) {
            x = 4;
            y = 6;
            this.map = new Territory[x][y];
        }


        while (temp.size() != 0) {


            for (int i = 0; i < x; i++) {
                for (int j = 0; j < y; j++) {
                    if (temp.size() == 0)
                        break;


                    //index au hasard dans la liste de tous les territoires
                    index = rand.nextInt(temp.size());

                    this.map[i][j] = temp.get(index);


                    temp.remove(temp.get(index));
                }
            }
        }


        //neighbour


        for (int x = 0; x < this.x; x++) {

            for (int y = 0; y < this.y; y++) {

                //north west
                if (x == 0 && y == 0) {

                    System.out.println("je suis dans north west");
                    map[x][y].addNeighbour(map[x + 1][y]);
                    map[x][y].addNeighbour(map[x][y + 1]);

                } else if (x == 0 && y == this.y - 1) {
                    System.out.println("je suis dans south west");
                    map[x][y].addNeighbour(map[x][y - 1]);
                    map[x][y].addNeighbour(map[x + 1][y]);

                } else if (x == 0 && (y != 0 || y != this.y - 1)) {
                   // map[x][y].addNeighbour(map[x][y + 1]);
                    map[x][y].addNeighbour(map[x][y - 1]);
                    map[x][y].addNeighbour(map[x + 1][y]);
                }

                //north east

                else if (x == this.x - 1 && y == 0) {
                    map[x][y].addNeighbour(map[x - 1][y]);
                    map[x][y].addNeighbour(map[x][y + 1]);
                }
                //sud east
                else if (x == this.x - 1 && y == this.y - 1) {
                    map[x][y].addNeighbour(map[x - 1][y]);
                    map[x][y].addNeighbour(map[x][y - 1]);

                } else if (y == this.y - 1 && (x != 0 || x != this.x - 1)) {
                 //   map[x][y].addNeighbour(map[x][y + 1]);
                    map[x][y].addNeighbour(map[x - 1][y]);
                    map[x][y].addNeighbour(map[x + 1][y]);


                } else {
                    map[x][y].addNeighbour(map[x][y + 1]);
                  //  map[x][y].addNeighbour(map[x][y-1]);
                    map[x][y].addNeighbour(map[x - 1][y]);
                   // map[x][y].addNeighbour(map[x + 1][y]);

                }
            }
        }

    }
}
                //south west
                //if( x == 0 && y == this.y){


                //North east
               /* if(x == this.x && y == 0){
                    map[x][y].addNeighbour(map[x-1][y]);
                    map[x][y].addNeighbour(map[x][y+1]);
                }*/







              /*  if( x == this.x){
                    map[x][y].addNeighbour(map[x][y+1]);
                    map[x][y].addNeighbour(map[x+1][y]);

                }

                if(y == this.y){
                    map[x][y].addNeighbour(map[x][y-1]);
                    map[x][y].addNeighbour(map[x+1][y]);
                }
            }

            }

              /*  //check les 4 points cardinaux
                if (x-1 >0 || y-1>0){
                    map[x][y].addNeighbour(map[y - 1][x]);
                    t.addNeighbour(map[y][x - 1]);
                }



                if(x+1 <= XMAX || y+1 <= YMAX) {
                    t.addNeighbour(map[y][x+1]);
                    t.addNeighbour(map[y+1][x]);
                }*/










