package part1;

import java.util.List;

/**
 * Created by Stephen Adu on 12/09/2017.
 */
public class FlatFee<T extends Coin> implements IFee<T> {

    @Override
    public List<? extends Coin> calculateFee(List<? extends Coin> coins) {
        return null;
    }

}
