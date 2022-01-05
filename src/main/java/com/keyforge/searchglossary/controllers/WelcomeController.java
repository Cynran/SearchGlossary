package com.keyforge.searchglossary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.keyforge.searchglossary.autocomplete.Trie;
import com.keyforge.searchglossary.search.Search;

@Controller
public class WelcomeController {
	
	@Autowired
	private Search service;
	
	private Trie autocompleteTrie;
	
	@RequestMapping(value="/")
	public String welcomePage() {
		
		if(autocompleteTrie == null) {
			autocompleteTrie = service.initTrie();
		}
		
		return "search";
	}

}
