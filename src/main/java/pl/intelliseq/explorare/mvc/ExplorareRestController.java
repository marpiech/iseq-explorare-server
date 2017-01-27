package pl.intelliseq.explorare.mvc;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import pl.intelliseq.explorare.model.Results;
import pl.intelliseq.explorare.model.hpo.HpoTerm;
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
    @RequestMapping(path = "/get-phenotypes-by-disease", method = RequestMethod.GET)
    public Results getPhenotypesByDisease(@RequestParam String disease) {
    	
    	System.out.println(disease);
    	
    	Results results = new Results();
    	
    	results.addResult(
    			this.makeSetSpecific(
    					this.hpoTree.getPhenotypesByDisease(disease)
    			));
    	
        return results;
                            
    }
    
    private Set<HpoTerm> makeSetSpecific(Set<HpoTerm> hpoTerms) {
    	Set<HpoTerm> out = new HashSet<HpoTerm>();
    	for(HpoTerm term : hpoTerms) {
    		boolean hasChildren = false;
    		for(HpoTerm childTerm : hpoTerms) {
    			if(childTerm.isChildOf(term))
    				hasChildren = true;
    		}
    		if(!hasChildren)
    			out.add(term);
    	}
    	return out;
    }
    
    @CrossOrigin()
    @JsonView(Views.Rest.class)
    @RequestMapping(path = "/disease-autocomplete", method = RequestMethod.GET)
    public Results diseasesAutocomplete(
    		@RequestParam String firstLetters,
    		@RequestParam Integer resultsCount) {
    	
    	Results results = new Results();
    	
    	for(String disease: this.hpoTree.getDiseases()) {
    		if(disease.toLowerCase().startsWith(firstLetters.toLowerCase())) {
    			results.addResult(disease);
    			if(results.sizeGreaterOrEqualTo(resultsCount))
    				return results;
    		}
    	}
    	
    	for(String disease: this.hpoTree.getDiseases()) {
    		if(disease.toLowerCase().contains(firstLetters.toLowerCase())) {
    			results.addResult(disease);
    			if(results.sizeGreaterOrEqualTo(resultsCount))
    				return results;
    		}
    	}
    	
        return results;//phenoMarks;
                            
    }
    
    @CrossOrigin()
    @JsonView(Views.Rest.class)
    @RequestMapping(path = "/phenotype-autocomplete", method = RequestMethod.GET)
    public Results phenotypeAutocomplete(
    		@RequestParam String firstLetters,
    		@RequestParam Integer resultsCount) {
    	
    	Results results = new Results();
    	
    	for(HpoTerm hpoTerm : this.hpoTree.getTerms()) {
    		if(hpoTerm.getName().toLowerCase().startsWith(firstLetters.toLowerCase())) {
    			results.addResult(hpoTerm);
    			if(results.sizeGreaterOrEqualTo(resultsCount))
    				return results;
    		}
    	}
    	
    	for(HpoTerm hpoTerm : this.hpoTree.getTerms()) {
    		if(hpoTerm.getName().toLowerCase().contains(firstLetters.toLowerCase())) {
    			results.addResult(hpoTerm);
    			if(results.sizeGreaterOrEqualTo(resultsCount))
    				return results;
    		}
    	}
    	
        return results;
                            
    }
    
}