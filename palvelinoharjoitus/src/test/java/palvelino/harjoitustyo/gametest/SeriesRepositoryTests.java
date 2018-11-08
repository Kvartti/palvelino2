package palvelino.harjoitustyo.gametest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import palvelino.harjoitustyo.domain.Series;
import palvelino.harjoitustyo.domain.SeriesRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SeriesRepositoryTests {

	@Autowired
	private SeriesRepository srepository;
	
	@Test
	public void createNewSeries() {
		Series series = new Series("Ace Attorney", "Capcom");
		srepository.save(series);
		assertThat(series.getSeriesid()).isNotNull();
	}
	
	@Test
	public void deleteSeries() {
		Series series = new Series("Ace Attorney", "Capcom");
		srepository.save(series);
		Long id = series.getSeriesid();

    	srepository.deleteById(id);
    	Optional<Series> newseries = srepository.findById(id);
    	
    	assertThat(newseries).isEmpty();
	}
	
	@Test
	public void findBySeriesname() {
	    List<Series> serieses = srepository.findBySeriesname("Dragon Age");
	        
	    assertThat(serieses).hasSize(1);
	    assertThat(serieses.get(0).getSeriesdeveloper()).isEqualTo("BioWare");
	}
}
