package pl.intelliseq.explorare.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import pl.intelliseq.explorare.utils.json.Views;

public class Results {
	
	@JsonView(Views.Rest.class)
	List <Object> results = new ArrayList <Object>();

	public List<Object> getResults() {
		return results;
	}

	public void addResult(Object result) {
		this.results.add(result);
	}
	
}
