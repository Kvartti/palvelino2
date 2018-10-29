package palvelino.harjoitustyo.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Series {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long seriesid;
	private String seriesname;
	private String seriesdeveloper;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "series")
	private List <Game> games;
	
	public Series() {}

	public Series(String seriesname, String seriesdeveloper) {
		super();
		this.seriesname = seriesname;
		this.seriesdeveloper = seriesdeveloper;
	}

	public Long getSeriesid() {
		return seriesid;
	}

	public void setSeriesid(Long seriesid) {
		this.seriesid = seriesid;
	}

	public String getSeriesname() {
		return seriesname;
	}

	public void setSeriesname(String seriesname) {
		this.seriesname = seriesname;
	}

	public String getSeriesdeveloper() {
		return seriesdeveloper;
	}

	public void setSeriesdeveloper(String seriesdeveloper) {
		this.seriesdeveloper = seriesdeveloper;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	@Override
	public String toString() {
		return "Series [seriesid=" + seriesid + ", seriesname=" + seriesname + ", seriesdeveloper=" + seriesdeveloper
				+ "]";
	}

}
