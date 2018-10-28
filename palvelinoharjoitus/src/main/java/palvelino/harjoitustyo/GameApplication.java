package palvelino.harjoitustyo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import palvelino.harjoitustyo.domain.Console;
import palvelino.harjoitustyo.domain.ConsoleRepository;
import palvelino.harjoitustyo.domain.Game;
import palvelino.harjoitustyo.domain.GameRepository;
import palvelino.harjoitustyo.domain.Series;
import palvelino.harjoitustyo.domain.SeriesRepository;

@SpringBootApplication
public class GameApplication {
	
	private static final Logger log = LoggerFactory.getLogger(GameApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GameApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner gameDemo(GameRepository grepository, ConsoleRepository crepository, SeriesRepository srepository) { 
		return (args) -> {
			crepository.save(new Console("PS4", "Sony"));
			crepository.save(new Console("PC", null));
			crepository.save(new Console("Switch", "Nintendo"));

			srepository.save(new Series("-"));
			srepository.save(new Series("The Legend of Zelda"));
			srepository.save(new Series("Persona"));
			
			grepository.save(new Game("Overwatch", 2016, "Blizzard", crepository.findByConsolename("PC").get(0), srepository.findBySeriesname("-").get(0)));
			grepository.save(new Game("Breath of the Wild", 2017, "Nintendo", crepository.findByConsolename("Switch").get(0), srepository.findBySeriesname("The Legend of Zelda").get(0)));
				
			log.info("fetch games");
			for (Game game : grepository.findAll()) {
				log.info(game.toString());
			}

		};
	}
}
