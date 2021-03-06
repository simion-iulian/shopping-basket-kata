package shoppingbasket.basket;

import java.util.ArrayList;
import java.util.List;

import shoppingbasket.user.UserID;

public class BasketRepository {
  private List<Basket> baskets = new ArrayList<>();

  public void save(Basket basket) {
    if(!baskets.contains(basket))
      baskets.add(basket);
  }

  public Basket getByUserId(UserID userId) {
    return baskets.stream()
      .filter(b -> userId.equals(b.userId))
      .findFirst()
      .orElse(null);
  }
}
