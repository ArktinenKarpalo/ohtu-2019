package ohtuesimerkki;

import ohtuesimerkki.Player;
import ohtuesimerkki.Reader;
import ohtuesimerkki.Statistics;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class StatisticsTest {

	Reader readerStub = new Reader() {

		public List<Player> getPlayers() {
			ArrayList<Player> players = new ArrayList<>();

			players.add(new Player("Semenko", "EDM", 4, 12));
			players.add(new Player("Lemieux", "PIT", 45, 54));
			players.add(new Player("Kurri",   "EDM", 37, 53));
			players.add(new Player("Yzerman", "DET", 42, 56));
			players.add(new Player("Gretzky", "EDM", 35, 89));

			return players;
		}
	};

	Statistics stats;

	@Before
	public void setUp(){
		// luodaan Statistics-olio joka käyttää "stubia"
		stats = new Statistics(readerStub);
	}

	@Test
	public void findPlayerExists() {
		Assert.assertEquals(new Player("Semenko", "EDM", 4, 12), stats.search("Semenko"));
	}

	@Test
	public void findPlayerNotExists() {
		Assert.assertEquals(null, stats.search("Matti Nykänen"));
	}

	@Test
	public void findTeamPlayers() {
		ArrayList<Player> edmPlayers = new ArrayList<>();
		edmPlayers.add(new Player("Semenko", "EDM", 4, 12));
		edmPlayers.add(new Player("Kurri",   "EDM", 37, 53));
		edmPlayers.add(new Player("Gretzky", "EDM", 35, 89));
		Assert.assertEquals(true, stats.team("EDM").containsAll(edmPlayers));
	}

	@Test
	public void topScorers() {
		Assert.assertEquals(new Player("Gretzky", "EDM", 35, 89), stats.topScorers(1).get(0));
	}
}