package palvelino.harjoitustyo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import palvelino.harjoitustyo.domain.Game;
import palvelino.harjoitustyo.domain.GameRepository;

@Controller
public class GameController {
	
	@Autowired
	GameRepository grepository;
	
	// autolistaus
	@RequestMapping(value = "/gamelist", method = RequestMethod.GET)
	public String listGames(Model model) {
			List<Game> games =  (List<Game>) grepository.findAll(); // haeta tietokannasta autot
			model.addAttribute("games", games); // välitetään autolista templatelle model-olion avulla
			return "gamelist"; // DispatherServlet saa tämän template-nimen ja kutsuu seuraavaksi carlist.html-templatea,
								// joka prosessoidaan palvelimella
	}

	@RequestMapping(value = "/newgame", method = RequestMethod.GET)
	public String getGameForm(Model model) {
		model.addAttribute("game", new Game()); // "tyhjä" auto-olio
		return "gameform";
	}
	
    @RequestMapping(value = "/gameform")
    public String addGame(Model model){
    	model.addAttribute("game", new Game());
    	//model.addAttribute("categories", crepository.findAll());
        return "gameform";
    }     
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Game game){
        grepository.save(game);
        return "redirect:gamelist";
    }  
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteGame(@PathVariable("id") Long gameid, Model model) {
    	grepository.deleteById(gameid);
        return "redirect:../gamelist";
    }   

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
