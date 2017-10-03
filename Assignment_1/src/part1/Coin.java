package part1;

import java.awt.*;

/**
 * Created by Stephen Adu on 12/09/2017.
 */

public abstract class Coin {
    protected final String name;
    protected final Image symbol;
    protected final String hash;

    public Coin(String name, Image symbol, String hash) {
        super();
        this.name = name;
        this.symbol = symbol;
        this.hash = hash;
    }

    public String getName() {
        return name;
    }

    public Image getSymbol() {
        return symbol;
    }

    public String getHash() {
        return hash;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((hash == null) ? 0 : hash.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Coin other = (Coin) obj;
        if (hash == null) {
            if (other.hash != null)
                return false;
        } else if (!hash.equals(other.hash))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return name + "(" + hash + " )";
    }
}