package com.keyforge.searchglossary;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.keyforge.searchglossary.autocomplete.Trie;
import com.keyforge.searchglossary.controllers.SearchGlossaryController;
import com.keyforge.searchglossary.data.GlossaryEntry;
import com.keyforge.searchglossary.search.Search;

@AutoConfigureMockMvc
@SpringBootTest
class SearchGlossaryApplicationTests {
	
	@Autowired
	private Search search;

	@Autowired
	private MockMvc mockMvc;
	
	@InjectMocks
	private static SearchGlossaryController controller = new SearchGlossaryController();
	
	/*
	 * Specify test data below
	 */
	private static String KEYWORD = "end of turn";
	private static String DESCRIPTION = "End of turn effects are resolved when a player&apos;s turn is over after step 5, the &quot;Draw Cards&quot; step.";
	private static String INVALID_KEYWORD = "activehouse";
	private static String[] KEYWORDS = {"action", "active house", "active player"};
	
	/*
	 * Here you can specify the user input and the suggestions the autocomplete service should give back
	 * You have to add the first maximum 5 suggestions in alphabetical order
	 */
	private static String AUTOCOMPLETE_PREFIX = "act";
	private static String[] AUTOCOMPLETE_SUGGESTIONS = {"action", "active house", "active player"};

	
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void dbTestSearchByKeyword() {
		for (GlossaryEntry entry : search.searchDescription(KEYWORD)) {
				String description = entry.getDescription();
				assertTrue(description != null,
							"description for keyword1 is null");
		}
	}
	
	@Test
	public void dbTestSearchWithWrongKeyword() {
		for (GlossaryEntry entry : search.searchDescription(INVALID_KEYWORD)) {
				String description = entry.getDescription();
				assertTrue(description == null,
								"shouldn't return description for invalid name");
		}
	}
	
	@Test
	public void testRetrieveDescription() throws Exception{
		String url= "/search";
		mockMvc.perform(get(url)
			.param("keyword", KEYWORD))
			.andExpect(MockMvcResultMatchers.model().attribute("description", DESCRIPTION))
			.andExpect(status().isOk());
	}
	
	@Test
	public void testTrieCreation() {
		List<String> words = new ArrayList<>();
		words.add(KEYWORDS[0]);
		words.add(KEYWORDS[1]);
		words.add(KEYWORDS[2]);

		Trie autocomplete = search.initTrie(words);
		List<Character> characters = autocomplete.returnAllCharacters();
		
		int combinations = countUniqueCombinations()+1;
		assertTrue(characters.size() == combinations,
						"instead " + combinations + " there were " + characters.size() + " number of characters");
		
	}
	
	@Test
	public void testSuggestions() {
		Trie autocomplete = search.initTrie();
		List<String> suggestions = autocomplete.suggest(AUTOCOMPLETE_PREFIX);
		assertTrue(suggestions.size() == AUTOCOMPLETE_SUGGESTIONS.length,
						"there were " + suggestions.size() + " number of suggestions instead " + AUTOCOMPLETE_SUGGESTIONS.length);
		
		for(int i = 0; i < AUTOCOMPLETE_SUGGESTIONS.length; i++) {
			assertTrue(suggestions.contains(AUTOCOMPLETE_SUGGESTIONS[i]),
						"suggestion didn't have AUTOCOMPLETE_SUGGESTIONS[" + i + "]");
		}
		
	}
	
	@Test
	public void testRetrieveSuggestions() throws Exception{
		String url = "/autocomplete";
		mockMvc.perform(get(url)
				.param("keyword", AUTOCOMPLETE_PREFIX))
				.andExpect(MockMvcResultMatchers.content().string(suggestionsToString()))
				.andExpect(status().isOk());
				 	
	}
	
	private int countUniqueCombinations() {
		List<String> combinations = new ArrayList<String>();
		int count = 0;
		for(int i = 0; i < KEYWORDS.length; i++) {
			for(int k = 1; k <= KEYWORDS[i].length(); k++) {
				String currentCombination;
				if(k != KEYWORDS[i].length()) {
					currentCombination = KEYWORDS[i].substring(0, k).toLowerCase();
				}else {
					currentCombination = KEYWORDS[i].substring(0).toLowerCase();
				}
				
				if(! combinations.contains(currentCombination)) {
					combinations.add(currentCombination);
					count++;
				}
			}
				
		}
		return count;	
	}
	
	private String suggestionsToString() {
		StringBuilder suggestionsText = new StringBuilder();
		suggestionsText.append("[\"");
		for(int i = 0; i < AUTOCOMPLETE_SUGGESTIONS.length-1; i++) {
			suggestionsText.append(AUTOCOMPLETE_SUGGESTIONS[i]);
			suggestionsText.append("\",\"");
		}
		suggestionsText.append(AUTOCOMPLETE_SUGGESTIONS[AUTOCOMPLETE_SUGGESTIONS.length-1]);
		suggestionsText.append("\"]");	
		
		return suggestionsText.toString();
	}
	

}
