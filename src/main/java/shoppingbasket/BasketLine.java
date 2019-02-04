package shoppingbasket;

import java.util.Objects;

public class BasketLine {
  private final ProductID productId;
  public final String name;
  public final int price;
  public final int quantity;

  public BasketLine(ProductID productId, String name, int price, int quantity) {
    this.productId = productId;
    this.name = name;
    this.price = price;
    this.quantity = quantity;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BasketLine that = (BasketLine) o;
    return price == that.price &&
      quantity == that.quantity &&
      Objects.equals(productId, that.productId) &&
      Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(productId, name, price, quantity);
  }

  @Override
  public String toString() {
    return "BasketLine{" +
      "productId=" + productId +
      ", name='" + name + '\'' +
      ", price=" + price +
      ", quantity=" + quantity +
      '}';
  }
}
