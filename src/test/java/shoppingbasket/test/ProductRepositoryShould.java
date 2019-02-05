package shoppingbasket.test;

import org.junit.jupiter.api.Test;

import java.util.List;

import shoppingbasket.product.Product;
import shoppingbasket.product.ProductID;
import shoppingbasket.product.ProductRepository;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class ProductRepositoryShould {

  @Test
  public void
  get_a_product_by_id() {
    final ProductID productId = new ProductID("2293");
    final Product expectedProduct = new Product(productId, "How To Run a Banana Republic", 200);
    final List<Product> productSeed = List.of(
      new Product(productId, "How To Run a Banana Republic", 200)
    );

    final ProductRepository productRepository = new ProductRepository(productSeed);

    final Product actualProduct = productRepository.getById(productId);

    assertThat(actualProduct, is(expectedProduct));
  }
}