package shoppingbasket;

import java.util.Objects;

public class UserID {
  private final String id;

  public UserID(String id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserID userID = (UserID) o;
    return Objects.equals(id, userID.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
