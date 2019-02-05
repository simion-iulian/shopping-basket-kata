package shoppingbasket.test;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.List;

import shoppingbasket.Console;
import shoppingbasket.basket.Basket;
import shoppingbasket.basket.BasketRepository;
import shoppingbasket.utils.Clock;
import shoppingbasket.product.Product;
import shoppingbasket.product.ProductID;
import shoppingbasket.product.ProductRepository;
import shoppingbasket.ShoppingBasketService;
import shoppingbasket.user.UserID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AT_ShoppingBasketService {
  private final UserID userId = new UserID("1234");

  private final ProductID hobbitId = new ProductID("10002");
  private final ProductID breakingBadId = new ProductID("20110");
  private final ProductID lordOfTheRingsId = new ProductID("10001");

  private final List<Product> productSeed = List.of(
    new Product(hobbitId, "The Hobbit", 5),
    new Product(breakingBadId, "Breaking Bad", 7),
    new Product(lordOfTheRingsId, "Lord Of The Rings", 10)
  );

  private final ProductRepository productRepository = new ProductRepository(productSeed);
  private final BasketRepository basketRepository = new BasketRepository();
  private final Clock clock = mock(Clock.class);

  @Test
  public void
  display_contents_of_a_basket() {
    when(clock.now()).thenReturn(LocalDate.of(2018, 10, 21));

    ShoppingBasketService basketService = new ShoppingBasketService(new Console(), clock, productRepository, basketRepository);

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

  @Test
  public void
  show_basket_with_one_item_on_the_console() throws IOException {
    when(clock.now()).thenReturn(LocalDate.of(2018, 07, 12));
    final PrintStream originalOut = System.out;
    try (ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
         PrintStream printStream = new PrintStream(consoleOutput)){
      System.setOut(printStream);

      Console console = new Console();
      ShoppingBasketService basketService = new ShoppingBasketService(console, clock, productRepository, basketRepository);

      basketService.addItem(userId, lordOfTheRingsId, 2);

      String expectedOutput =
        "[BASKET CREATED]: Created [<2018-07-12>], User[]" + System.lineSeparator() +
        "[ITEM ADDED TO SHOPPING CART]: Added[<2018-07-12>], User[], Product[10001], Quantity[2], Price[<£10.00>]" + System.lineSeparator();

      assertThat(consoleOutput.toString(), is(expectedOutput));
    } finally {
      System.setOut(originalOut);
    }
  }
}