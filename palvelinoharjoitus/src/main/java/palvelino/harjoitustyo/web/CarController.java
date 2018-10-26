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
public class CarController {
	
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

/*	// tyhjän autolomakkeen muodostaminen
	@RequestMapping(value = "/newcar", method = RequestMethod.GET)
	public String getNewCarForm(Model model) {
		model.addAttribute("car", new Game()); // "tyhjä" auto-olio
		return "carform";
	}

	// autolomakkeella syötettyjen tietojen vastaanotto ja tallennus
	@RequestMapping(value = "/newcar", method = RequestMethod.POST)
	public String saveCar(@ModelAttribute Game car) {
		// talletetaan yhden auton tiedot tietokantaan
		carRepository.save(car);
		return "redirect:/cars";
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
