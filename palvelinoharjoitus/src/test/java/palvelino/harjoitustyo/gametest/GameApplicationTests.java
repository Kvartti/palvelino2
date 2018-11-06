package palvelino.harjoitustyo.gametest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import palvelino.harjoitustyo.web.GameController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameApplicationTests {

	@Autowired
	private GameController gcontroller;
	
	@Test
	public void contextLoads() throws Exception {
		assertThat(gcontroller).isNotNull();
	}

}
