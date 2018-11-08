package palvelino.harjoitustyo.web;

import java.util.Arrays;
import java.util.Collections;
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
public class SeriesController {
	
	@Autowired
	GameRepository grepository;
	@Autowired
	ConsoleRepository crepository;
	@Autowired
	SeriesRepository srepository;
	
	//LIST
	@RequestMapping(value = "/serieslist", method = RequestMethod.GET)
	public String listSeries(Model model) {
        	model.addAttribute("serieses", srepository.findAll());
            model.addAttribute("games", grepository.findAll());
			return "serieslist";
	}
	
    //ALL GAMES
	@RequestMapping(value="/seriesgames/{id}", method = RequestMethod.GET)
    public String allGames(@PathVariable("id") Long seriesid, Model model) {
		Series series = srepository.findById(seriesid).get();
		model.addAttribute("series", series);
		
		List<Game> games = grepository.findBySeries(series);
		model.addAttribute("games", games);
		
        return "seriesgames";
    }
	
	
	/*@RequestMapping(value="/seriesgames/{seriesid}", method = RequestMethod.GET)
	    public String allGames(Model model) {
	    	model.addAttribute("serieses", srepository.findAll());
	        model.addAttribute("games", grepository.findAll());
	        return "seriesgames";
	    }*/
	 
  /*  @RequestMapping(value="/seriesgames/{seriesid}", method = RequestMethod.GET)
    public String allGames(@PathVariable("id") Long seriesid, Model model) {
        model.addAttribute("editseries", srepository.findById(seriesid));
    	model.addAttribute("serieses", srepository.findAll());
        model.addAttribute("games", grepository.findAll());
        return "seriesgames";
    } */
	
/*	@RequestMapping(value="/seriesgames/{seriesid}", method = RequestMethod.GET)
    public @ResponseBody List<Series> seriesById(@PathVariable("seriesid") Long seriesid)  {	
		
		Series series = srepository.findById(seriesid).get();
		
		return (List<Game>) grepository.findBySeries(series);
    } */
     
	/** näyttää kaikki kysymykset kysely Id:n perusteella **/
/*	@RequestMapping(value="/kysely/{kyselyId}", method = RequestMethod.GET)
    public @ResponseBody List<Kysymys> kysymysById(@PathVariable("kyselyId") Long kyselyId)  {	
		
		Kysely kysely = kyselyRepository.findById(kyselyId).get();
		
		return (List<Kysymys>) kysymysRepository.findByKysely(kysely );
    } */
    

	//NEW FORM
	@RequestMapping(value = "/newseries", method = RequestMethod.GET)
	public String getSeriesForm(Model model) {
		model.addAttribute("series", new Series());
		return "seriesform";
	}
    
	//SAVE
    @RequestMapping(value = "/saveseries", method = RequestMethod.POST)
    public String saveSeries(Series series){
        srepository.save(series);
        return "redirect:serieslist";
    }  
    
    
    //EDIT
    @RequestMapping(value= "/editseries/{id}", method = RequestMethod.GET)
    public String editSeries(@PathVariable("id") Long seriesid, Model model) {
        model.addAttribute("editseries", srepository.findById(seriesid));
    	model.addAttribute("serieses", srepository.findAll());
        return "seriesedit";
    }
    
    //DELETE
    @RequestMapping(value = "/deleteseries/{id}", method = RequestMethod.GET)
    public String deleteSeries(@PathVariable("id") Long seriesid, Model model) {
    	srepository.deleteById(seriesid);
        return "redirect:../serieslist";
    } 
    
    //REST, all series
    @RequestMapping(value="/serieses", method = RequestMethod.GET)
    public @ResponseBody List<Series> seriesListRest() {	
        return (List<Series>) srepository.findAll();
    }
    
    //REST, series by id
    @RequestMapping(value="/series/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Series> findSeriesRest(@PathVariable("id") Long seriesid) {	
    	return srepository.findById(seriesid);
    } 
}
