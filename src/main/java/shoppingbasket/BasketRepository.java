package shoppingbasket;

import java.util.ArrayList;
import java.util.List;

public class BasketRepository {
  List<Basket> baskets = new ArrayList<>();

  public void save(Basket basket) {
    baskets.add(basket);
  }

  public Basket getByUserId(UserID userId) {
    return baskets.stream()
      .filter(b -> userId.equals(b.userId))
      .findFirst()
      .orElse(null);
  }
}
