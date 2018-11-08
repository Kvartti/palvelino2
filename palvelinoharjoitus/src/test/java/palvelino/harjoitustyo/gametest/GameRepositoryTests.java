package palvelino.harjoitustyo.gametest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import palvelino.harjoitustyo.domain.Console;
import palvelino.harjoitustyo.domain.Game;
import palvelino.harjoitustyo.domain.GameRepository;
import palvelino.harjoitustyo.domain.Series;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GameRepositoryTests {

	@Autowired
	private GameRepository grepository;
	
	@Test
	public void createNewGame() {
		Game game = new Game("A Link to the Past", 1991, "Nintendo", new Console("SNES", "Nintendo"), new Series ("The Legend of Zelda", "Nintendo"));
		grepository.save(game);
		assertThat(game.getGameid()).isNotNull();
	}
	
	@Test
	public void deleteGame() {
		Game game = new Game("A Link to the Past", 1991, "Nintendo", new Console("SNES", "Nintendo"), new Series ("The Legend of Zelda", "Nintendo"));
		grepository.save(game);
		Long id = game.getGameid();

    	grepository.deleteById(id);
    	Optional<Game> newgame = grepository.findById(id);
    	
    	assertThat(newgame).isEmpty();
	}
	
	@Test
	public void findByGametitle() {
	    List<Game> games = grepository.findByGametitle("Overwatch");
	        
	    assertThat(games).hasSize(1);
	    assertThat(games.get(0).getGameyear()).isEqualTo(2016);
	}
}
