package shoppingbasket;

import java.util.Objects;

public class Product {
  public final ProductID productId;
  public final String name;
  public final int price;

  public Product(ProductID productId, String name, int price) {
    this.productId = productId;
    this.name = name;
    this.price = price;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Product product = (Product) o;
    return price == product.price &&
      Objects.equals(productId, product.productId) &&
      Objects.equals(name, product.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(productId, name, price);
  }
}
