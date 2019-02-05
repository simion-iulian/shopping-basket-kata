package shoppingbasket.product;

import java.util.List;

public class ProductRepository {
  private final List<Product> expectedProduct;

  public ProductRepository(List<Product> expectedProduct) {
    this.expectedProduct = expectedProduct;
  }

  public Product getById(ProductID productId) {
      return expectedProduct.stream()
        .filter(x -> x.productId == productId)
        .findFirst()
        .orElse(null);
  }
}
