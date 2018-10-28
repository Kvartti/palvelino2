package palvelino.harjoitustyo.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Console {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long consoleid;
	private String consolename;
	private String consolepublisher;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "console")
	private List <Game> games;
	
	public Console() {}
	
	public Console(String consolename, String consolepublisher) {
		super();
		this.consolename = consolename;
		this.consolepublisher = consolepublisher;
	}

	public Long getConsoleid() {
		return consoleid;
	}

	public void setConsoleid(Long consoleid) {
		this.consoleid = consoleid;
	}

	public String getConsolename() {
		return consolename;
	}

	public void setConsolename(String consolename) {
		this.consolename = consolename;
	}
	
	public String getConsolepublisher() {
		return consolepublisher;
	}

	public void setConsolepublisher(String consolepublisher) {
		this.consolepublisher = consolepublisher;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	@Override
	public String toString() {
		return "Console [consoleid=" + consoleid + ", consolename=" + consolename + ", consolepublisher="
				+ consolepublisher + "]";
	}

}
