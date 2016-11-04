/*
 2 - Database & Consistent Hashing

 LRU Cache

 Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.


*/


/*public class LRUCache {
    
    public LRUCache(int capacity) {
        
    }
    
    public int get(int key) {
        
    }
    
    public void set(int key, int value) {
        
    }
}*/

public class Solution {
    HashMap<Integer, DoubleNode> map;
    DoubleNode head;
    DoubleNode tail;
    int capacity;
    // @param capacity, an integer
    public Solution(int capacity) {
        // write your code here
        map = new HashMap<Integer, DoubleNode>();
        head = null;
        tail = null;
        this.capacity = capacity;
    }

    // @return an integer
    public int get(int key) {
        // write your code here
        //if not exist, return -1;
        if(!map.containsKey(key)){
            return -1;
        }
        //if exists, move the cur to tail;
        DoubleNode cur = map.get(key);
        if(cur == tail || head == tail)
            return cur.val;
        else if(cur == head){
            head = head.next;
            head.pre = null;
        }else{
            cur.pre.next = cur.next;
            cur.next.pre = cur.pre; 
        }
        cur.pre = tail;
        tail.next = cur;
        tail = cur;
        
        return cur.val;
    }

    // @param key, an integer
    // @param value, an integer
    // @return nothing
    public void set(int key, int value) {
        // write your code here
        //if contains key, use get;
        if( get(key) != -1) {
            map.get(key).val = value;
            return;
        }
        //if map is full, remove the oldest node
        //then put the now node to tail
        if(map.size() == capacity){
            map.remove(head.key);
            head = head.next;
            if(head != null)
                head.pre = null;
        }
        
        DoubleNode cur = new DoubleNode(key, value);
        if(map.size() == 0){
            head = cur;
            tail = cur;
        }else{
            cur.pre = tail;
            tail.next = cur;
            tail = cur;
        }
        map.put(key, cur);

        return;  
    }
}

class DoubleNode{
    DoubleNode pre;
    DoubleNode next;
    int val;
    int key;
    public DoubleNode(int key, int val){
        this.key = key;
        this.val = val;
    }
}