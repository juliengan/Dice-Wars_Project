package Datas;

import java.util.ArrayList;

public class Territory {


    /*************** ATTRIBUTES ****************/
    private final Integer id;
    private Integer playerId;
    private Integer strength;
    private final ArrayList<Territory> neighboringTer;
    /******************************************/


    /**************** METHODS *****************/
    // Territory(Integer id, Integer playerID)
    // void UpdateneighboringTer(Territory t)

    /*****************************************/

    public Territory(Integer id, Integer playerID) {
        this.id = id;
        this.playerId = playerID;
        this.strength = 1; //we put at least a strength of 1 for each territory
        this.neighboringTer = new ArrayList<Territory>();
    }


    public void UpdateneighboringTer(Territory t) {
        neighboringTer.add(t);
    }


    /*********** SETTERS *********/


    public void addStrength(int n) {
        this.strength += n;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    /*********** GETTERS *********/

    public Integer getId() {
        return id;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public Integer getStrength() {
        return strength;
    }

    public ArrayList<Territory> getNeighboringTer() {
        return neighboringTer;
    }
}