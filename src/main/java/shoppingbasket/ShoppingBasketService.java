package shoppingbasket;

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
  private final Clock clock;
  private final ProductRepository productRepository;
  private final BasketRepository basketRepository;

  public ShoppingBasketService(Clock clock, ProductRepository productRepository, BasketRepository basketRepository) {
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
  }

  private Basket getBasket(UserID userId) {
    return Optional.ofNullable(basketFor(userId))
    .orElse(new Basket(userId, clock.now()));
  }

  public Basket basketFor(UserID userId) {
    return basketRepository.getByUserId(userId);
  }
}
