package com.keyforge.searchglossary.search;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keyforge.searchglossary.autocomplete.Trie;
import com.keyforge.searchglossary.data.GlossaryEntry;
import com.keyforge.searchglossary.data.GlossarySpringDataRepository;

@Service
public class Search {
	
	@Autowired
	private GlossarySpringDataRepository data;
	
	private Trie autocompleteTrie;
	
	public Trie getAutocompleteTrie() {
		return autocompleteTrie;
	}

	/*
	 * Use this if you want to setup the Trie with the keywords in the database
	 */
	public Trie initTrie() {
		autocompleteTrie = new Trie(getKeywords());
		return autocompleteTrie;
	}
	
	/*
	 * Use this if you want to setup the Trie without a database and with specific words
	 */
	public Trie initTrie(List<String> words) {
		autocompleteTrie = new Trie(words);
		return autocompleteTrie;
	}
	
	
	/*
	 * Returns a list of descriptions from the database based on the keyword provided
	 * The correct behavior is to return a list with only one element, but it is not guaranteed
	 */
	public List<GlossaryEntry> searchDescription(String keyword) {
		return data.findByKeyword(keyword);
	}
	
	/*
	 * Returns all the keywords from the database
	 */
	public List<String> getKeywords(){
		List<GlossaryEntry> allEntries = data.findAll();
		List<String> keywords = new ArrayList<>();
		
		for (GlossaryEntry entry : allEntries) {
			keywords.add(entry.getKeyword());
		}
		
		return keywords;
	}
}
