package Datas;

public class Move {
    private int idAttacker;
    private int idDefender;

    public Move(int idAttacker, int idDefender) {
        this.idAttacker = idAttacker;
        this.idDefender = idDefender;
    }

    public int getIdAttacker() {
        return idAttacker;
    }

    public int getIdDefender() {
        return idDefender;
    }

    public void setIdAttacker(int idAttacker) {
        this.idAttacker = idAttacker;
    }

    public void setIdDefender(int idDefender) {
        this.idDefender = idDefender;
    }
}
