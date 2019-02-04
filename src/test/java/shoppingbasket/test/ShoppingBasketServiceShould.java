package shoppingbasket.test;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import shoppingbasket.Basket;
import shoppingbasket.BasketLine;
import shoppingbasket.BasketRepository;
import shoppingbasket.Clock;
import shoppingbasket.Product;
import shoppingbasket.ProductID;
import shoppingbasket.ProductRepository;
import shoppingbasket.ShoppingBasketService;
import shoppingbasket.UserID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ShoppingBasketServiceShould {
   private final UserID userId = new UserID("2982");
   private final ProductID productId = new ProductID("20001");
   private final Product gameOfThrones = new Product(productId, "Game of Thrones", 9);
   private LocalDate date = LocalDate.now();
  private final Clock clock = mock(Clock.class);
  private final ProductRepository productRepository = mock(ProductRepository.class);
  private final BasketRepository basketRepository = mock(BasketRepository.class);
  private final ShoppingBasketService service = new ShoppingBasketService(clock, productRepository, basketRepository);


  @Test
  void create_basket_when_first_item_is_added() {
    when(clock.now()).thenReturn(date);
    when(productRepository.getById(productId)).thenReturn(gameOfThrones);

    service.addItem(userId, productId, 1);

    Basket basket = new Basket(userId, date);
    basket.add(
      new BasketLine(
        productId,
        "Game of Thrones",
        9,
        1));

    verify(basketRepository).save(basket);
  }

  @Test
  public void
  get_basket_with_one_item_added() {
    when(basketRepository.getByUserId(userId)).thenReturn(getRandomBasket(userId));

    Basket actualBasket = service.basketFor(userId);

    Basket expectedBasket = getRandomBasket(userId);

    assertThat(actualBasket, is(expectedBasket));
  }

  private Basket getRandomBasket(UserID userId) {
    return new Basket(userId, LocalDate.now());
  }

}