package indi.zzl.trank;

import java.io.Serializable;

public class Hero extends Tank implements Serializable {
    public Hero(int x, int y) {
        super(x, y);
    }

    public Hero(int x, int y, int direct) {
        this(x, y);
        this.setDirect(direct);
    }
}
