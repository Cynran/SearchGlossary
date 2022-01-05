package com.keyforge.searchglossary.autocomplete;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.springframework.stereotype.Service;

/*
 * This Trie data structure is used to give autocomplete suggestions quickly and easily.
 */
@Service
public class Trie {
	
	public class TrieNode {
		private Map<Character,TrieNode> children;
		private char character;
		private boolean isWord;
		
		private TrieNode(char c){
			children = new HashMap<>();
			this.character = c;
		}
		
		private TrieNode(){
			children = new HashMap<>();
		}
		
		
		private void insert(String word){
			if(word == null || word.isEmpty()){
				return;
			}

			char firstChar = word.charAt(0);
			TrieNode child = null;

			if(children.keySet().contains(firstChar)){
				child = children.get(firstChar);
			}else{
				child = new TrieNode(firstChar);
				children.put(firstChar,child);
			}

			if(word.length() > 1){
				child.insert(word.substring(1));
			}else{
				child.isWord = true;
			}
		}
	}
	
	private TrieNode root;
	
	public Trie() {}

	public Trie(List<String> words){
		root = new TrieNode();

		for(String word : words){
			root.insert(word);
		}
	}
	
	/*
	 * Returns max 5 words in alphabetical order, each of them starts with the prefix (not case-sensitive).
	 * 
	 * prefix => the characters the user typed so far in the input box
	 * 
	 * Returns empty list if:
	 *                      => prefix's length less than 1 character
	 *                      => there aren't any words starting with prefix
	 */
	public List<String> suggest(String prefix){
		List<String> suggestions = new ArrayList<>();
		if(prefix.length() < 1) {
			return suggestions;
		}
		
		prefix = prefix.toLowerCase();
		TrieNode lastNode = root;

		StringBuilder curr = new StringBuilder();

		for(char c : prefix.toCharArray()){
			lastNode = lastNode.children.get(c);

			if(lastNode == null){
				return suggestions;
			}else{
				curr.append(c);
			}
		}
		
		suggestHelper(lastNode,suggestions,curr);
		
		Collections.sort(suggestions);
		List<String> suggestionsExact = new ArrayList<>();
		for(int i = 0; i < 5 && i < suggestions.size(); i++) {
			suggestionsExact.add(suggestions.get(i));
		}
		return suggestionsExact;

	}

	private void suggestHelper(TrieNode root, List<String> suggestions, StringBuilder curr){
		if(root.isWord){
			suggestions.add(curr.toString());
		}

		if(root.children.isEmpty() || root.children == null){
			return;
		}

		for(TrieNode child : root.children.values()){
			suggestHelper(child,suggestions,curr.append(child.character));
			curr.setLength(curr.length()-1);
		}
	}
	
	/*
	 * This returns all the characters present in the Trie. 
	 * Useful for debugging and testing.
	 */
	public List<Character> returnAllCharacters(){
		List<Character> characters = new ArrayList<>();
		
		Queue<TrieNode> nodes = new LinkedList<>();
		nodes.add(root);
		while(!nodes.isEmpty()) {
			TrieNode curr = nodes.remove();
			characters.add(curr.character);
			
			if(curr.children != null && !curr.children.isEmpty()) {
				for (TrieNode trieNode : curr.children.values()) {
					nodes.add(trieNode);
				}
			}
		}
		return characters;
	}
}
