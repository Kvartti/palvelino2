package palvelino.harjoitustyo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import palvelino.harjoitustyo.domain.ConsoleRepository;
import palvelino.harjoitustyo.domain.GameRepository;
import palvelino.harjoitustyo.domain.SeriesRepository;

@Controller
public class LocaleController {
	
	@Autowired
	GameRepository grepository;
	@Autowired
	ConsoleRepository crepository;
	@Autowired
	SeriesRepository srepository;
 
   @RequestMapping(value = "/locale", method = RequestMethod.GET)
   public String getLocalePage(Model model) {
       model.addAttribute("games", grepository.findAll());
       return "gamelist";
   }
}