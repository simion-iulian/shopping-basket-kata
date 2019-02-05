package shoppingbasket.test;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import shoppingbasket.basket.Basket;
import shoppingbasket.basket.BasketLine;
import shoppingbasket.product.ProductID;
import shoppingbasket.user.UserID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class BasketShould {
  @Test
  public void
  return_date_contents_and_total_as_a_string() {
    final ProductID hobbitId = new ProductID("10002");
    final ProductID breakingBadId = new ProductID("20110");
    final LocalDate date = LocalDate.of(2018, 10, 21);
    final UserID userId = new UserID("3832");

    Basket basket = new Basket(userId, date);
    basket.add(new BasketLine(hobbitId, "The Hobbit", 5, 2));
    basket.add(new BasketLine(breakingBadId, "Breaking Bad", 7, 5));

    String contents = basket.printContents();

    assertThat(contents,
      is("21-10-2018\n" +
        "2 x The Hobbit\n" +
        "5 x Breaking Bad\n" +
        "Total: £45.00\n"));
  }
}