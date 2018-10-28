package palvelino.harjoitustyo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Game {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long gameid;
	private String gametitle;
	private int gameyear;
	private String gamepublisher;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "consoleid")
	private Console console;

	public Game() {
		super();
		this.gametitle = null;
		this.gameyear = 0;
		this.gamepublisher = null;
	}

	public Game(String gametitle, int gameyear, String gamepublisher, Console console) {
		super();
		this.gametitle = gametitle;
		this.gameyear = gameyear;
		this.gamepublisher = gamepublisher;
		this.console = console;
	}

	public long getGameid() {
		return gameid;
	}

	public void setGameid(long gameid) {
		this.gameid = gameid;
	}

	public String getGametitle() {
		return gametitle;
	}
	public void setGametitle(String gametitle) {
		this.gametitle = gametitle;
	}
	public int getGameyear() {
		return gameyear;
	}
	public void setGameyear(int gameyear) {
		this.gameyear = gameyear;
	}
	public String getGamepublisher() {
		return gamepublisher;
	}
	public void setGamepublisher(String gamepublisher) {
		this.gamepublisher = gamepublisher;
	}
	
	public Console getConsole() {
		return console;
	}

	public void setConsole(Console console) {
		this.console = console;
	}


	@Override
	public String toString() {
		if (this.console != null)
			return "Game [gameid=" + gameid + ", gametitle=" + gametitle + ", gameyear=" + gameyear + ", gamepublisher="
			+ gamepublisher + ", console=" + this.getConsole() + "]";
		else
			return "Game [gameid=" + gameid + ", gametitle=" + gametitle + ", gameyear=" + gameyear + ", gamepublisher="
				+ gamepublisher + "]";
	}
	
}
