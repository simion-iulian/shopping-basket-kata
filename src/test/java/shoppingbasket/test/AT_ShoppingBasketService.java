package shoppingbasket.test;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import shoppingbasket.Basket;
import shoppingbasket.BasketRepository;
import shoppingbasket.Clock;
import shoppingbasket.ProductID;
import shoppingbasket.ProductRepository;
import shoppingbasket.ShoppingBasketService;
import shoppingbasket.UserID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class AT_ShoppingBasketService {



  @Test
  public void
  display_contents_of_a_basket() {
    ShoppingBasketService basketService = new ShoppingBasketService(new Clock(), new ProductRepository(Collections.emptyList()), new BasketRepository());

    final UserID userId = new UserID("1234");
    final ProductID hobbitId = new ProductID("10002");
    final ProductID breakingBadId = new ProductID("20110");

    basketService.addItem(userId, hobbitId, 2);
    basketService.addItem(userId, breakingBadId, 5);

    Basket basket = basketService.basketFor(userId);

    String contents = basket.printContents();

    assertThat(contents,
      is("21-10-2018\n" +
        "2 x The Hobbit\n" +
        "5 x Breaking Bad\n" +
        "Total: £45.00\n"));
  }
}