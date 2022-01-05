package com.keyforge.searchglossary.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.keyforge.searchglossary.autocomplete.Trie;
import com.keyforge.searchglossary.search.Search;

@Controller
public class AutocompleteController {
	
	@Autowired
	private Search service;

	@RequestMapping(value="/autocomplete")
	@ResponseBody
	public List<String> suggest(@RequestParam String keyword){
		Trie autocomplete = service.getAutocompleteTrie();
		if(autocomplete == null) {
			autocomplete = service.initTrie();
		}
		return autocomplete.suggest(keyword);
	}
}
