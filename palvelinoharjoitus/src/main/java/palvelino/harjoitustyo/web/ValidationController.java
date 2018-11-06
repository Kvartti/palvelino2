package palvelino.harjoitustyo.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    
    
    //EDIT GAME
    @GetMapping("/saveeditgame")
    public String showEditForm(Game game, @PathVariable("id") Long gameid, Model model) {
        model.addAttribute("editgame", grepository.findById(gameid));
    	model.addAttribute("consoles", crepository.findAll());
    	model.addAttribute("serieses", srepository.findAll());
        return "gameedit";
    }
    
    @PostMapping("/saveeditgame")
    public String checkNewEdit(@Valid Game game, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
        	
        	return "redirect:/editgame/{id}";
        }
        
        else {
        	grepository.save(game);
        }

        return "redirect:/gamelist";
    }
}