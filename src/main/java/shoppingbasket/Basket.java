package shoppingbasket;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.lang.System.*;

public class Basket {
  public final UserID userId;
  private final LocalDate date;
  private final List<BasketLine> basketLines;

  public Basket(UserID userId, LocalDate date) {
    this.userId = userId;
    this.date = date;
    basketLines = new ArrayList<>();
  }

  public String printContents() {
    final String lineWithDate = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))+ lineSeparator();
    String productLines = basketLines.stream().map(b -> String.format("%d x %s",b.quantity,b.name)).collect(Collectors.joining(lineSeparator())) + lineSeparator();

    int total = basketLines.stream().mapToInt(b -> b.price*b.quantity).sum();
    String totalLine = String.format("Total: £%d.00",total)+ lineSeparator();
    return lineWithDate + productLines + totalLine;
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
