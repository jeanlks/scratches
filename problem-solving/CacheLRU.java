import java.util.*;

public class CacheLRU {
    private Map<String, DoublyLinkedListNode> map = new HashMap<>();
    private DoublyLinkedList leastRecentlyUsedList = new DoublyLinkedList();

    private int maxCapacity = 0;
    private int currentSize = 0;

    public CacheLRU(int capacity) {
        this.maxCapacity = capacity;
    }

    public void insertKeyValue(String key, int value) {
        DoublyLinkedListNode node = new DoublyLinkedListNode(key, value);
        if (!this.map.containsKey(key)) {
            if (maxCapacity == currentSize) {
                evict();
            } else {
                currentSize++;
            }
            leastRecentlyUsedList.setHeadTo(node);
            this.map.put(key, node);
        } else {
            this.map.put(key, node);
        }
    }

    public void evict() {
        map.remove(leastRecentlyUsedList.tail.key);
        leastRecentlyUsedList.removeTail();
    }

    public int getFromKey(String key) {
        updateRecentlyUsed(key);
        return map.get(key).value;
    }

    public void updateRecentlyUsed(String key){
        DoublyLinkedListNode recentlyUsed = map.get(key);
        leastRecentlyUsedList.setHeadTo(recentlyUsed);
    }

    public String getMostRecentKey() {
        return leastRecentlyUsedList.head.key;
    }

    public class  DoublyLinkedList {
        DoublyLinkedListNode head = null;
        DoublyLinkedListNode tail = null;

        public void setHeadTo(DoublyLinkedListNode node) { 
            if(head == node){
                return;
            } else if(head ==null){
                head = node;
                tail = node;
            } else {
                if(tail == node){
                    removeTail();
                }
                node.removeBindings();
                head.prev = node;
                node.next = head;
                head = node;
            }
        }

        public void removeTail(){
            if(tail == null)
                return;
            if(tail == head){
                head = null;
                tail = null;
                return;
            }
            tail = tail.prev;
            tail.next = null;
        }
    }

    public class DoublyLinkedListNode { 
        int value;
        String key;
        DoublyLinkedListNode prev = null;
        DoublyLinkedListNode next = null;

        public DoublyLinkedListNode(String key, int value){
            this.value = value;
            this.key = key;
        }
        
        public void removeBindings(){
            if (prev!=null){
                prev.next = next;
            }
            if(next!= null){
                next.prev = prev;
            }
            next = null;
            prev = null;
        }
    }


    public static void main(String[] args){
        CacheLRU cache = new CacheLRU(3);
        cache.insertKeyValue("a", 5);
        cache.insertKeyValue("b", 10);
        cache.insertKeyValue("c", 14);
        cache.insertKeyValue("d", 20);
        try {
            System.out.println(cache.getFromKey("a"));
        } catch (NullPointerException e) {
            System.out.println("Key not found");
        }
        System.out.println("Key most used should be D, because it was inserted last: key:"+cache.getMostRecentKey());
        cache.getFromKey("b");
        System.out.println("Now should be B: key: "+cache.getMostRecentKey());
    }
}