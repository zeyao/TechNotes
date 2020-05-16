package trie;

import java.util.HashMap;
import java.util.Map;

/** Trie is good for prefix word search**/
public class Trie {

	Node root;

	public Trie() {
		root = new Node(' ');
	}

	/** Inserts a word into the trie. */
	public void insert(String word) {
		Node tmp = root;
		for (char c : word.toCharArray()) {
			if (!tmp.children.containsKey(c)) {
				Node child = new Node(c);
				tmp.children.put(c, child);
			}
			tmp = tmp.children.get(c);
		}
		tmp.isEnd = true;
	}

	/** Returns if the word is in the trie. */
	public boolean search(String word) {
		Node tmp = root;
		for (char c : word.toCharArray()) {
			if (!tmp.children.containsKey(c)) {
				return false;
			}
			tmp = tmp.children.get(c);
		}
		return tmp.isEnd;
	}

	/**
	 * Returns if there is any word in the trie that starts with the given
	 * prefix.
	 */
	public boolean startsWith(String prefix) {
		Node tmp = root;
		for (char c : prefix.toCharArray()) {
			if (!tmp.children.containsKey(c)) {
				return false;
			}
			tmp = tmp.children.get(c);
		}
		return true;
	}

	class Node {
		char val;
		Map<Character, Node> children = new HashMap<>();
		boolean isEnd;

		public Node(char val) {
			this.val = val;
		}
	}
}
