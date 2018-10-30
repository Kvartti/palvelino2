package palvelino.harjoitustyo.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ConsoleRepository extends CrudRepository<Console, Long> {

	List<Console> findByConsolename(@Param(value="console") String console);
	
	//List<Console> findByConsoleid(@Param(value="consoleid") String consoleid);

}