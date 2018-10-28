package palvelino.harjoitustyo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	//LISTS
	@RequestMapping(value = "/gamelist", method = RequestMethod.GET)
	public String listGames(Model model) {
        model.addAttribute("games", grepository.findAll());
			return "gamelist";
	}
	
	@RequestMapping(value = "/consolelist", method = RequestMethod.GET)
	public String listConsoles(Model model) {
        	model.addAttribute("consoles", crepository.findAll());
			return "consolelist";
	}
	
/*	@RequestMapping(value = "/consolelist", method = RequestMethod.GET)
	public String listConsoles(Model model) {
		List<Console> consoles =  (List<Console>) crepository.findAll();
		model.addAttribute("consoles", consoles);
		return "consolelist";
} 

    @RequestMapping(value="/booklist")
    public String bookList(Model model) {	
        model.addAttribute("books", repository.findAll());
        return "booklist";
    }

	@RequestMapping(value = "/gamelist", method = RequestMethod.GET)
	public String listGames(Model model) {
			List<Game> games =  (List<Game>) grepository.findAll();
			model.addAttribute("games", games);
			return "gamelist";
	}


*/


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
		return "consoleform";
	}
	
	@RequestMapping(value = "/newseries", method = RequestMethod.GET)
	public String getSeriesForm(Model model) {
		model.addAttribute("series", new Series());
		return "seriesform";
	}
    
	//SAVE NEW
    @RequestMapping(value = "/savegame", method = RequestMethod.POST)
    public String saveGame(Game game){
        grepository.save(game);
        return "redirect:gamelist";
    }  
    
    @RequestMapping(value = "/saveconsole", method = RequestMethod.POST)
    public String saveConsole(Console console){
        crepository.save(console);
        return "redirect:gamelist";
    }  
    
    @RequestMapping(value = "/saveseries", method = RequestMethod.POST)
    public String saveSeries(Series series){
        srepository.save(series);
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
    
    @RequestMapping(value = "/deleteconsole/{id}", method = RequestMethod.GET)
    public String deleteConsole(@PathVariable("id") Long consoleid, Model model) {
    	crepository.deleteById(consoleid);
        return "redirect:../consolelist";
    } 
    
  /*  @RequestMapping(value = "/deleteconsole", method = RequestMethod.GET)
    public String deleteConsole(Long consoleid, Console console) {
    	crepository.deleteById(consoleid);
        return "redirect:../gamelist";
    } */

/*	// autolomakkeella syötettyjen tietojen vastaanotto ja tallennus
	@RequestMapping(value = "/newgame", method = RequestMethod.POST)
	public String saveGame(@ModelAttribute Game game) {
		// talletetaan yhden auton tiedot tietokantaan
		grepository.save(game);
		return "redirect:/gamelist";
	}

	// auton poisto
	@RequestMapping(value = "/deletecar/{id}", method = RequestMethod.GET)
	public String deleteCar(@PathVariable("id") Long carId) {
		carRepository.deleteById(carId);
		return "redirect:../cars";
	}
	
	// TODO edit car
	*/

}
