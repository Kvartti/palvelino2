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

	public Game() {
		super();
		this.gametitle = null;
		this.gameyear = 0;
		this.gamepublisher = null;
	}

	public Game(String gametitle, int gameyear, String gamepublisher) {
		super();
		this.gametitle = gametitle;
		this.gameyear = gameyear;
		this.gamepublisher = gamepublisher;
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

	@Override
	public String toString() {
		return "Game [gameid=" + gameid + ", gametitle=" + gametitle + ", gameyear=" + gameyear + ", gamepublisher="
				+ gamepublisher + "]";
	}
	
}
