package Datas;

import java.util.ArrayList;

public class Territory {

    private final Integer id;
    private Integer playerId;
    private Integer strength;
    private final ArrayList<Territory> neighboringTer;

    public void addStrength(int n){
        this.strength += n;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

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


    public Territory(Integer id, Integer playerID){
        this.id = id;
        this.playerId = playerID;
        this.strength = 1; //we put at least a strength of 1 for each territory
        this.neighboringTer = new ArrayList<Territory>();
    }
    public void UpdateIDPlayer(){}

    public void UpdateneighboringTer(Territory t){
       neighboringTer.add(t);
    }

}
