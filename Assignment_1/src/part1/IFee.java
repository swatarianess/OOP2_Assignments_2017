package part1;

import java.util.List;

public interface IFee<T extends Coin> {
    List<? extends Coin> calculateFee(List<? extends Coin> coins);
}
