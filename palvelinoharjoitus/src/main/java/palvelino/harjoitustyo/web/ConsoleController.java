package palvelino.harjoitustyo.web;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
public class ConsoleController {
	
	@Autowired
	GameRepository grepository;
	@Autowired
	ConsoleRepository crepository;
	@Autowired
	SeriesRepository srepository;
	
	//LIST
	@RequestMapping(value = "/consolelist", method = RequestMethod.GET)
	public String listConsoles(Model model) {
        	model.addAttribute("consoles", crepository.findAll());
            model.addAttribute("games", grepository.findAll());
			return "consolelist";
	}
	
    //ALL GAMES
	@RequestMapping(value="/consolegames/{id}", method = RequestMethod.GET)
    public String allGamesConsole(@PathVariable("id") Long consoleid, Model model) {
		Console console = crepository.findById(consoleid).get();
		model.addAttribute("console", console);
		
		List<Game> games = grepository.findByConsole(console);
		model.addAttribute("games", games);
		
        return "consolegames";
    }
	
	//NEW FORM
	@RequestMapping(value = "/newconsole", method = RequestMethod.GET)
	public String getConsoleForm(Model model) {
		model.addAttribute("console", new Console());
    	model.addAttribute("games", grepository.findAll());
		return "consoleform";
	}
    
	//SAVE
    @RequestMapping(value = "/saveconsole", method = RequestMethod.POST)
    public String saveConsole(Console console){
        crepository.save(console);
        return "redirect:consolelist";
    }  
    
    
    //EDIT
    @RequestMapping(value= "/editconsole/{id}", method = RequestMethod.GET)
    public String editConsole(@PathVariable("id") Long consoleid, Model model) {
        model.addAttribute("editconsole", crepository.findById(consoleid));
    	model.addAttribute("consoles", crepository.findAll());
    	model.addAttribute("serieses", srepository.findAll());
        return "consoleedit";
    }
    
    //DELETE
    @RequestMapping(value = "/deleteconsole/{id}", method = RequestMethod.GET)
    public String deleteConsole(@PathVariable("id") Long consoleid, Model model) {
    	crepository.deleteById(consoleid);
        return "redirect:../consolelist";
    } 
    
    //REST, all consoles
    @RequestMapping(value="/consoles", method = RequestMethod.GET)
    public @ResponseBody List<Console> consoleListRest() {	
        return (List<Console>) crepository.findAll();
    }
    
    //REST, console by id
    @RequestMapping(value="/console/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Console> findConsoleRest(@PathVariable("id") Long consoleid) {	
    	return crepository.findById(consoleid);
    } 
}
