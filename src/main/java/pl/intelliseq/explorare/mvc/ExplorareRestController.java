package pl.intelliseq.explorare.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import pl.intelliseq.explorare.model.Results;
import pl.intelliseq.explorare.model.hpo.HpoTree;
import pl.intelliseq.explorare.model.phenoMarks.PhenoMarks;
import pl.intelliseq.explorare.model.phenoMarks.PhenoMarksParser;
import pl.intelliseq.explorare.mvc.response.HelloRestResponse;
import pl.intelliseq.explorare.utils.json.Views;

@RestController
public class ExplorareRestController {

    @Autowired
    PhenoMarksParser phenoMarksParser;
    
    @Autowired
    HpoTree hpoTree;
    
    @CrossOrigin()
    @RequestMapping(path = "/hello", method = RequestMethod.GET, produces = "application/json")
    public HelloRestResponse greeting() { return new HelloRestResponse("explorare service is alive"); }
    
    @CrossOrigin()
    @JsonView(Views.Rest.class)
    @RequestMapping(path = "/parse-text", method = RequestMethod.POST)
    public PhenoMarks postGreeting(@RequestBody Query query) {
    	
        PhenoMarks phenoMarks = phenoMarksParser.tagInput(query.getQuery());
        return phenoMarks;
                            
    }
    
    @CrossOrigin()
    @JsonView(Views.Rest.class)
    @RequestMapping(path = "/disease-autocomplete", method = RequestMethod.GET)
    public Results diseasesAutocomplete(
    		@RequestParam String firstLetters,
    		@RequestParam String resultsCount) {
    	
    	
    	
    	Results results = new Results();
    	results.addResult("Juzef");
        //PhenoMarks phenoMarks = phenoMarksParser.tagInput(query.getQuery());
        return results;//phenoMarks;
                            
    }
}