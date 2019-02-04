package shoppingbasket;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Basket {
  private final UserID userId;
  private final LocalDate date;
  private final List<BasketLine> basketLines;

  public Basket(UserID userId, LocalDate date) {
    this.userId = userId;
    this.date = date;
    basketLines = new ArrayList<>();
  }

  public String printContents() {
      throw new UnsupportedOperationException();
  }

  public void add(BasketLine basketLine) {
    basketLines.add(basketLine);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Basket basket = (Basket) o;
    return userId.equals(basket.userId) &&
      date.equals(basket.date) &&
      basketLines.equals(basket.basketLines);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, date, basketLines);
  }

  @Override
  public String toString() {
    return "Basket{" +
      "userId=" + userId +
      ", date=" + date +
      ", basketLines=" + basketLines +
      '}';
  }
}
