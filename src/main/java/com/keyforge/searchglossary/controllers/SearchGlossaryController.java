package com.keyforge.searchglossary.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.keyforge.searchglossary.data.FormatText;
import com.keyforge.searchglossary.data.GlossaryEntry;
import com.keyforge.searchglossary.search.Search;

@Controller
public class SearchGlossaryController {
	
	@Autowired
	private Search search;
	
	@Autowired
	private FormatText formatter;
	
	@RequestMapping(value = "/search")
	public String searchByName(@RequestParam String keyword, ModelMap model) {
		List<GlossaryEntry> results = search.searchDescription(keyword);
		if(results.isEmpty()) {
			model.put("error", keyword + " is not a keyword");
		}else {
			String description = formatter.addFormatting(results.get(0).getDescription());
			model.put("keyword", keyword);
			model.put("description", description);
		}
		return "search";
	}

}
