package palvelino.harjoitustyo.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import palvelino.harjoitustyo.domain.ConsoleRepository;
import palvelino.harjoitustyo.domain.Game;
import palvelino.harjoitustyo.domain.GameRepository;
import palvelino.harjoitustyo.domain.SeriesRepository;


@Controller
public class ValidationController implements WebMvcConfigurer {

	@Autowired
	GameRepository grepository;
	@Autowired
	ConsoleRepository crepository;
	@Autowired
	SeriesRepository srepository;
	
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/gamelist").setViewName("gamelist");
    }

    //NEW GAME
    @GetMapping("/savegame")
    public String showForm(Game game) {
        return "gameform";
    }

    @PostMapping("/savegame")
    public String checkNewGame(@Valid Game game, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
        	model.addAttribute("consoles", crepository.findAll());
        	model.addAttribute("serieses", srepository.findAll());
            return "gameform";
        }
        
        else {
        	grepository.save(game);
        }

        return "redirect:/gamelist";
    }
    
    
    //EDIT GAME - palauttaa virhetilanteessa sivun, muttei näytä errorviestiä
	@RequestMapping(value="/saveeditgame", method = RequestMethod.POST)
    public String saveEditgame(@Valid Game game, BindingResult bindingResult, Model model) {
   
        if (bindingResult.hasErrors()) {
        	Long gameid = game.getGameid();
            model.addAttribute("editgame", grepository.findById(gameid));
        	model.addAttribute("consoles", crepository.findAll());
        	model.addAttribute("serieses", srepository.findAll());
        	return "gameedit";
        	//return "redirect:/editgame/" + game.getGameid();
        }
        
        else {
        	grepository.save(game);
            return "redirect:/gamelist";
        }

    }
}