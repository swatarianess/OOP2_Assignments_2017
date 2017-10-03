package part1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Stephen Adu on 12/09/2017.
 */
public class Transaction<T extends Coin> {
    private final String fromKey;
    private final String toKey;
    private final Date timestamp;
    private final List<? extends Coin> coins;

    private List<Coin> fee;
    private List<? extends Coin> change;

    public Transaction(String fromKey, String toKey, List<? extends Coin> coins, IFee<? extends Coin> feeCalc) {
        super();
        this.fromKey = fromKey;
        this.toKey = toKey;
        this.coins = coins;
        this.timestamp = new Date();
        this.fee = new ArrayList<>();

        List<? extends Coin> feeList = feeCalc.calculateFee(coins);
        if (feeList != null) {
            this.fee.addAll(feeList);
        }
    }

    public String getFromKey() {
        return fromKey;
    }

    public String getToKey() {
        return toKey;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public List<? extends Coin> getCoins() {
        return coins;
    }

    public List<? extends Coin> getChange() {
        return change;
    }

    private String allcoinHashes() {
        StringBuilder result = new StringBuilder();
        for (Coin aCoin: coins) {
            result.append(aCoin.getHash());
        }
        return new String(result);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
        result = prime * result + ((allcoinHashes() == null) ? 0 : allcoinHashes().hashCode());
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
        @SuppressWarnings("unchecked")
        Transaction<T> other = (Transaction<T>) obj;
        if (timestamp == null) {
            if (other.timestamp != null)
                return false;
        } else if (!timestamp.equals(other.timestamp))
            return false;
        if (this.allcoinHashes() == null) {
            if (other.allcoinHashes() != null)
                return false;
        } else if (!allcoinHashes().equals(other.allcoinHashes()))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Transaction from " + fromKey + " to " + toKey + " on " + timestamp + " size " + coins.size() + " coins.";
    }
}