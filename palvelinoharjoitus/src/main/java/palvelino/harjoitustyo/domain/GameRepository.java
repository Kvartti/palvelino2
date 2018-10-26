package palvelino.harjoitustyo.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource
public interface GameRepository extends CrudRepository<Game, Long>{
	
	//List<Game> findByGametitle(String gametitle);

	//List<Game> findByTitle(@Param(value="gametitle") String gametitle);

}