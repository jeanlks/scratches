import java.util.*;
public class SuffixTrie {
      static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<Character, TrieNode>();
      }
      TrieNode root = new TrieNode();
      char endSymbol = '*';
  
    
  
      public SuffixTrie() {
        
      }
  
  
      public void insertIntoTrie(String str) {
              str = str.toLowerCase();
              TrieNode curr = root;
              for(int i = 0; i< str.length(); i++) { 
                  char letter = str.charAt(i);
                  if(!curr.children.containsKey(letter))
                      curr.children.put(letter, new TrieNode());
                  curr = curr.children.get(letter);
              }
              curr.children.put(endSymbol, null);
      }
          
      public boolean contains(String str) {
          str = str.toLowerCase();
              TrieNode curr = root;
        for(int i = 0; i< str.length(); i++) { 
                  char letter = str.charAt(i);
                  if(!curr.children.containsKey(letter))
                      return false;
                  curr = curr.children.get(letter);
              }
        return curr.children.containsKey(endSymbol) ? true: false ;
      }
}

  