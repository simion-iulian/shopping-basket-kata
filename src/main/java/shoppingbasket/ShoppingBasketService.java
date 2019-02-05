package shoppingbasket;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import shoppingbasket.basket.Basket;
import shoppingbasket.basket.BasketLine;
import shoppingbasket.basket.BasketRepository;
import shoppingbasket.product.Product;
import shoppingbasket.product.ProductID;
import shoppingbasket.product.ProductRepository;
import shoppingbasket.user.UserID;
import shoppingbasket.utils.Clock;

public class ShoppingBasketService {
  private final Console console;
  private final Clock clock;
  private final ProductRepository productRepository;
  private final BasketRepository basketRepository;

  public ShoppingBasketService(Console console, Clock clock, ProductRepository productRepository, BasketRepository basketRepository) {
    this.console = console;
    this.clock = clock;
    this.productRepository = productRepository;
    this.basketRepository = basketRepository;
  }

  public void addItem(UserID userId, ProductID productId, int quantity) {
    Product product = productRepository.getById(productId);
    Basket basket = getBasket(userId);
    BasketLine basketLine = new BasketLine(product.productId, product.name, product.price, quantity);

    basket.add(basketLine);
    basketRepository.save(basket);
    console.print(productToConsole(productId, quantity, product));
  }

  private Basket getBasket(UserID userId) {
    if (basketFor(userId) == null) {
      final LocalDate now = clock.now();
      String creationConsoleLine = getCreationConsoleLine(now);

      console.print(creationConsoleLine);
      return new Basket(userId, now);
    } else {
      return basketFor(userId);
    }
  }

  public Basket basketFor(UserID userId) {
    return basketRepository.getByUserId(userId);
  }

  private String getCreationConsoleLine(LocalDate now) {
    return String.format("[BASKET CREATED]: Created [<%s>], User[]", now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
  }

  private String productToConsole(ProductID productId, int quantity, Product product) {
    return String.format("[ITEM ADDED TO SHOPPING CART]: " +
      "Added[<2018-07-12>], User[], Product[%s], Quantity[%d], Price[<£%d.00>]", productId, quantity, product.price);
  }
}
