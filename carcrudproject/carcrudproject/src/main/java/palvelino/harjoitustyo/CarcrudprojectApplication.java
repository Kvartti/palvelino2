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
public class CarcrudprojectApplication {
	
	private static final Logger log = LoggerFactory.getLogger(CarcrudprojectApplication.class);  // uusi loggeriattribuutti

	public static void main(String[] args) {
		SpringApplication.run(CarcrudprojectApplication.class, args);
	}
	
	
	//  testidatan luonti H2-testitietokantaan aina sovelluksen käynnistyessä
	@Bean
	public CommandLineRunner carDemo(GameRepository grepository) { 
		return (args) -> {
			log.info("save games");
			grepository.save(new Game("Overwatch", 2016, "Blizzard"));
			grepository.save(new Game("The Legend of Zelda: Breath of the Wild", 2017, "Nintendo"));
		
			log.info("fetch games");
			for (Game game : grepository.findAll()) {
				log.info(game.toString());
			}

		};
	}
}
