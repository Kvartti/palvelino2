package palvelino.harjoitustyo.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource
public interface SeriesRepository extends CrudRepository<Series, Long>{
	
	List<Series> findBySeriesname(@Param(value="seriesname") String seriesname);

}