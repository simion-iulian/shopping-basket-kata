package shoppingbasket.test;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import shoppingbasket.Basket;
import shoppingbasket.BasketRepository;
import shoppingbasket.Clock;
import shoppingbasket.UserID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BasketRepositoryShould {
  @Test
  public void
  store_a_basket_for_a_user() {
    LocalDate date = LocalDate.now();
    final UserID userId = new UserID("5474");
    Basket expectedBasket = new Basket(userId, date);
    Basket basket = new Basket(userId, date);
    BasketRepository basketRepository = new BasketRepository();

    basketRepository.save(basket);
    Basket actualBasket = basketRepository.getByUserId(userId);

    assertThat(actualBasket, is(expectedBasket));
  }
}