package palvelino.harjoitustyo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import palvelino.harjoitustyo.domain.Game;
import palvelino.harjoitustyo.domain.GameRepository;

@SpringBootApplication
public class GameApplication {
	
	private static final Logger log = LoggerFactory.getLogger(GameApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GameApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner gameDemo(GameRepository grepository) { 
		return (args) -> {
			grepository.save(new Game("Overwatch", 2016, "Blizzard"));
			grepository.save(new Game("Breath of the Wild", 2017, "Nintendo"));
				
			log.info("fetch games");
			for (Game game : grepository.findAll()) {
				log.info(game.toString());
			}

		};
	}
}
