package shoppingbasket;

import java.util.Optional;

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
    Basket basket =
      Optional.ofNullable(basketRepository.getByUserId(userId))
      .orElse(new Basket(userId, clock.now()));
    basket.add(new BasketLine(product.productId, product.name, product.price, quantity));
    basketRepository.save(basket);
  }
  public Basket basketFor(UserID userId) {
    return basketRepository.getByUserId(userId);
  }
}
