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
import palvelino.harjoitustyo.domain.User;
import palvelino.harjoitustyo.domain.UserRepository;

@SpringBootApplication
public class GameApplication {
	
	private static final Logger log = LoggerFactory.getLogger(GameApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GameApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner gameDemo(GameRepository grepository, ConsoleRepository crepository, SeriesRepository srepository, UserRepository urepository) { 
		return (args) -> {
			crepository.save(new Console("PS4", "Sony"));
			crepository.save(new Console("PC", "-"));
			crepository.save(new Console("Switch", "Nintendo"));
			crepository.save(new Console("SNES", "Nintendo"));

			srepository.save(new Series("-", "-"));
			srepository.save(new Series("The Legend of Zelda", "Nintendo"));
			srepository.save(new Series("Persona", "Atlus"));
			srepository.save(new Series("Dragon Age", "BioWare"));
			srepository.save(new Series("Taiko no Tatsujin", "Nintendo"));
			srepository.save(new Series("The Sims", "Maxis"));
			
			grepository.save(new Game("Overwatch", 2016, "Blizzard", crepository.findByConsolename("PC").get(0), srepository.findBySeriesname("-").get(0)));
			grepository.save(new Game("The Legend of Zelda: Breath of the Wild", 2017, "Nintendo", crepository.findByConsolename("Switch").get(0), srepository.findBySeriesname("The Legend of Zelda").get(0)));
			grepository.save(new Game("Dragon Age II", 2011, "BioWare", crepository.findByConsolename("PC").get(0), srepository.findBySeriesname("Dragon Age").get(0)));
			grepository.save(new Game("Taiko no Tatsujin: Drum 'n' Fun!", 2018, "Nintendo", crepository.findByConsolename("Switch").get(0), srepository.findBySeriesname("Taiko no Tatsujin").get(0)));
			grepository.save(new Game("The Sims 4", 2014, "EA", crepository.findByConsolename("PC").get(0), srepository.findBySeriesname("The Sims").get(0)));
			grepository.save(new Game("Dragon Age: Inquisition", 2014, "BioWare", crepository.findByConsolename("PS4").get(0), srepository.findBySeriesname("Dragon Age").get(0)));
				
			User user1 = new User("admin", "$2a$10$evWPF7JaP0XaumZg4y0zMeZI1zUx6zLbZyFvM6IYAiRE6tBAz022u", "ADMIN");
			User user2 = new User("Carita", "$2a$10$KIkJSlRCI4R6oB.Z9MC/TOr16SN0jonHavz2tlxOR9I4prF51fS/C", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			
			log.info("fetch games");
			for (Game game : grepository.findAll()) {
				log.info(game.toString());
			}

		};
	}
}
