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
import palvelino.harjoitustyo.domain.ConsoleRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ConsoleRepositoryTests {

	@Autowired
	private ConsoleRepository crepository;
	
	@Test
	public void createNewConsole() {
		Console console = new Console("PS2", "Sony");
		crepository.save(console);
		assertThat(console.getConsoleid()).isNotNull();
	}
	
	@Test
	public void deleteConsole() {
		Console console = new Console("PS2", "Sony");
		crepository.save(console);
		Long id = console.getConsoleid();

    	crepository.deleteById(id);
    	Optional<Console> newconsole = crepository.findById(id);
    	
    	assertThat(newconsole).isEmpty();
	}
	
	@Test
	public void findByConsolename() {
	    List<Console> consoles = crepository.findByConsolename("Switch");
	        
	    assertThat(consoles).hasSize(1);
	    assertThat(consoles.get(0).getConsolepublisher()).isEqualTo("Nintendo");
	}
}
