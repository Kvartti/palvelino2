package palvelino.harjoitustyo.web;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import palvelino.harjoitustyo.domain.Console;
import palvelino.harjoitustyo.domain.ConsoleRepository;
import palvelino.harjoitustyo.domain.Game;
import palvelino.harjoitustyo.domain.GameRepository;
import palvelino.harjoitustyo.domain.Series;
import palvelino.harjoitustyo.domain.SeriesRepository;

@Controller
public class GameController {
	
	@Autowired
	GameRepository grepository;
	@Autowired
	ConsoleRepository crepository;
	@Autowired
	SeriesRepository srepository;
	
	//Yleiset
    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }	
	
    
    
	//LIST
	@RequestMapping(value = "/gamelist", method = RequestMethod.GET)
	public String listGames(Model model) {
        model.addAttribute("games", grepository.findAll());
			return "gamelist";
	}
	
	//NEW FORM
	@RequestMapping(value = "/newgame", method = RequestMethod.GET)
	public String getGameForm(Model model) {
		model.addAttribute("game", new Game());
    	model.addAttribute("consoles", crepository.findAll());
    	model.addAttribute("serieses", srepository.findAll());
		return "gameform";
	}
	
	//SAVE
    @RequestMapping(value = "/savegame", method = RequestMethod.POST)
    public String saveGame(Game game){
        grepository.save(game);
        return "redirect:gamelist";
    }  

    //EDIT
    @RequestMapping(value= "/editgame/{id}", method = RequestMethod.GET)
    public String editGame(@PathVariable("id") Long gameid, Model model) {
        model.addAttribute("editgame", grepository.findById(gameid));
    	model.addAttribute("consoles", crepository.findAll());
    	model.addAttribute("serieses", srepository.findAll());
        return "gameedit";
    }
    
    //DELETE
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteGame(@PathVariable("id") Long gameid, Model model) {
    	grepository.deleteById(gameid);
        return "redirect:../gamelist";
    }  
    
    //REST, all games
    @RequestMapping(value="/games", method = RequestMethod.GET)
    public @ResponseBody List<Game> gameListRest() {	
        return (List<Game>) grepository.findAll();
    }
    
    //REST, game by id
    @RequestMapping(value="/game/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Game> findGameRest(@PathVariable("id") Long gameid) {	
    	return grepository.findById(gameid);
    }    

}
