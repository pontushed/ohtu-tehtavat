package ohtu;

public class Player implements Comparable<Player> {
  private int score;
  private String name;

  public Player(String name) {
    this.name = name;
    this.score = 0;
  }

  public String getName() {
    return name;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public void incrementScore() {
    this.score++;
  }

  @Override
  public int compareTo(Player other) {
    return this.score - other.getScore();
  }
}
