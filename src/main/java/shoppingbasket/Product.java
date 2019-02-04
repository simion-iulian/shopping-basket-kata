package shoppingbasket;

public class Product {
  public final ProductID productId;
  public final String name;
  public final int price;

  public Product(ProductID productId, String name, int price) {
    this.productId = productId;
    this.name = name;
    this.price = price;
  }
}
