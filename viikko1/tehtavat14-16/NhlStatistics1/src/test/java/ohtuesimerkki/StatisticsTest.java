package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Before;

public class StatisticsTest {

  Reader readerStub = new Reader() {

    public List<Player> getPlayers() {
      ArrayList<Player> players = new ArrayList<>();

      players.add(new Player("Semenko", "EDM", 4, 12));
      players.add(new Player("Lemieux", "PIT", 45, 54));
      players.add(new Player("Kurri", "EDM", 37, 53));
      players.add(new Player("Yzerman", "DET", 42, 56));
      players.add(new Player("Gretzky", "EDM", 35, 89));

      return players;
    }
  };

  Statistics stats;

  @Before
  public void setUp() {
    // luodaan Statistics-olio joka käyttää "stubia"
    stats = new Statistics(readerStub);
  }

  @Test
  public void teamListTest() {
    List<Player> players = stats.team("EDM");
    ArrayList<String> playerNames = new ArrayList<>();

    for (Player p : players) {

      playerNames.add(p.getName());

    }
    assertEquals(true, playerNames.contains("Kurri"));
  }

  @Test
  public void topScorersTest() {
    List<Player> players = stats.topScorers(4);
    int numberOfTopScorers = players.size();
    assertEquals(5, numberOfTopScorers);
  }

  @Test
  public void searchTest() {
    Player kurri = stats.search("Kurri");
    assertEquals("Kurri", kurri.getName());
  }

  @Test
  public void searchUnknownTest() {
    Player barkov = stats.search("Barkov");
    assertEquals(null, barkov);
  }
}