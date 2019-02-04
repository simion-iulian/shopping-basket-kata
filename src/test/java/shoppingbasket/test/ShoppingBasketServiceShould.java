package shoppingbasket.test;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import shoppingbasket.Basket;
import shoppingbasket.BasketRepository;
import shoppingbasket.BasketLine;
import shoppingbasket.Clock;
import shoppingbasket.Product;
import shoppingbasket.ProductID;
import shoppingbasket.ProductRepository;
import shoppingbasket.ShoppingBasketService;
import shoppingbasket.UserID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ShoppingBasketServiceShould {

  @Test
  void create_basket_when_first_item_is_added() {
    final UserID userId = new UserID("2982");
    final ProductID productId = new ProductID("20001");
    final Product gameOfThrones = new Product(productId, "Game of Thrones", 9);
    LocalDate date = LocalDate.now();

    Clock clock = mock(Clock.class);
    ProductRepository productRepository = mock(ProductRepository.class);
    BasketRepository basketRepository = mock(BasketRepository.class);
    ShoppingBasketService service = new ShoppingBasketService(clock,productRepository,basketRepository);

    when(clock.now()).thenReturn(date);
    when(productRepository.getById(productId)).thenReturn(gameOfThrones);


    service.addItem(userId, productId, 1);

    Basket basket = new Basket(userId, date);

    final BasketLine basketLine = new BasketLine(productId, "Game of Thrones", 9, 1);
    basket.add(basketLine);

    verify(basketRepository).save(basket);
  }
}