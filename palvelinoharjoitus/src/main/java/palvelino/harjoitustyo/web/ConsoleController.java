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
}
