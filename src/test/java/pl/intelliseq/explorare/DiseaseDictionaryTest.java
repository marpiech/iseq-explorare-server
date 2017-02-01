package pl.intelliseq.explorare;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;

import pl.intelliseq.explorare.model.hpo.DiseaseGeneDictionary;
import pl.intelliseq.explorare.model.hpo.HpoOboParser;
import pl.intelliseq.explorare.model.hpo.HpoTerm;
import pl.intelliseq.explorare.model.hpo.HpoTree;
import pl.intelliseq.explorare.model.phenoMarks.PhenoMarksParser;

public class DiseaseDictionaryTest {

	@Test
	public void parserTest() throws IOException {
	
		DiseaseGeneDictionary dict = new DiseaseGeneDictionary();
		assertEquals(dict.getDiseaseById("OMIM:614652").getPrimaryName(), "COENZYME Q10 DEFICIENCY, PRIMARY, 3");
		
	}

	
	
}
