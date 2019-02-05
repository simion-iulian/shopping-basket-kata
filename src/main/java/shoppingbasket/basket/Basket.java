package shoppingbasket.basket;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import shoppingbasket.user.UserID;

import static java.lang.System.*;

public class Basket {
  final UserID userId;
  private final LocalDate date;
  private final List<BasketLine> lines;

  public Basket(UserID userId, LocalDate date) {
    this.userId = userId;
    this.date = date;
    this.lines = new ArrayList<>();
  }

  public String printContents() {
    return creationDate()
         + productLines()
         + lineWithTotal(basketTotal());
  }

  private String creationDate() {
    String pattern = "dd-MM-yyyy";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
    return date.format(formatter) + lineSeparator();
  }

  private String lineWithTotal(int total) {
    return String.format("Total: £%d.00", total)
      + lineSeparator();
  }

  private int basketTotal() {
    return lines.stream()
      .mapToInt(b -> b.price * b.quantity)
      .sum();
  }

  private String productLines() {
    return lines.stream()
      .map(b -> String.format("%d x %s", b.quantity, b.name))
      .collect(Collectors.joining(lineSeparator()))
      + lineSeparator();
  }

  public void add(BasketLine basketLine) {
    lines.add(basketLine);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Basket basket = (Basket) o;
    return userId.equals(basket.userId) &&
      date.equals(basket.date) &&
      lines.equals(basket.lines);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, date, lines);
  }

  @Override
  public String toString() {
    return "Basket{" +
      "userId=" + userId +
      ", date=" + date +
      ", lines=" + lines +
      '}';
  }
}
