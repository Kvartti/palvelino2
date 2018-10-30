package palvelino.harjoitustyo.web;

import java.util.Arrays;
import java.util.List;

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
	
    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }	
	
	//LISTS
	@RequestMapping(value = "/gamelist", method = RequestMethod.GET)
	public String listGames(Model model) {
        model.addAttribute("games", grepository.findAll());
			return "gamelist";
	}
	
	@RequestMapping(value = "/consolelist", method = RequestMethod.GET)
	public String listConsoles(Model model) {
        	model.addAttribute("consoles", crepository.findAll());
            model.addAttribute("games", grepository.findAll());
			return "consolelist";
	}
	
	@RequestMapping(value = "/serieslist", method = RequestMethod.GET)
	public String listSeries(Model model) {
        	model.addAttribute("serieses", srepository.findAll());
            model.addAttribute("games", grepository.findAll());
			return "serieslist";
	}

	//NEW FORMS
	@RequestMapping(value = "/newgame", method = RequestMethod.GET)
	public String getGameForm(Model model) {
		model.addAttribute("game", new Game());
    	model.addAttribute("consoles", crepository.findAll());
    	model.addAttribute("serieses", srepository.findAll());
		return "gameform";
	}
	
	@RequestMapping(value = "/newconsole", method = RequestMethod.GET)
	public String getConsoleForm(Model model) {
		model.addAttribute("console", new Console());
    	model.addAttribute("games", grepository.findAll());
		return "consoleform";
	}
	
	@RequestMapping(value = "/newseries", method = RequestMethod.GET)
	public String getSeriesForm(Model model) {
		model.addAttribute("series", new Series());
		return "seriesform";
	}
    
	//SAVE
    @RequestMapping(value = "/savegame", method = RequestMethod.POST)
    public String saveGame(Game game){
        grepository.save(game);
        return "redirect:gamelist";
    }  
    
    @RequestMapping(value = "/saveconsole", method = RequestMethod.POST)
    public String saveConsole(Console console){
        crepository.save(console);
        return "redirect:consolelist";
    }  
    
    @RequestMapping(value = "/saveseries", method = RequestMethod.POST)
    public String saveSeries(Series series){
        srepository.save(series);
        return "redirect:serieslist";
    }  
    
    
    //EDIT
    @RequestMapping(value= "/editgame/{id}", method = RequestMethod.GET)
    public String editGame(@PathVariable("id") Long gameid, Model model) {
        model.addAttribute("editgame", grepository.findById(gameid));
    	model.addAttribute("consoles", crepository.findAll());
    	model.addAttribute("serieses", srepository.findAll());
        return "gameedit";
    }
    
    @RequestMapping(value= "/editconsole/{id}", method = RequestMethod.GET)
    public String editConsole(@PathVariable("id") Long consoleid, Model model) {
        model.addAttribute("editconsole", crepository.findById(consoleid));
    	model.addAttribute("consoles", crepository.findAll());
    	model.addAttribute("serieses", srepository.findAll());
        return "consoleedit";
    }
    
    //DELETE
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteGame(@PathVariable("id") Long gameid, Model model) {
    	grepository.deleteById(gameid);
        return "redirect:../gamelist";
    }  
    
    @RequestMapping(value = "/deleteconsole/{id}", method = RequestMethod.GET)
    public String deleteConsole(@PathVariable("id") Long consoleid, Model model) {
    	crepository.deleteById(consoleid);
        return "redirect:../consolelist";
    } 
}
