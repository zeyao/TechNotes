public class LFUCache {
	class Node {
		int key;
		int val;
		int count;
		Node pre;
		Node next;
        Node(){};
		Node(int key, int val) {
			this.val = val;
			this.key = key;
			count = 1;
		}
	}

	class DlList {
		Node head;
		Node tail;
		int size;
		
		public DlList() {
			head = new Node();
			tail = new Node();
			head.pre = tail;
			tail.next = head;
			size = 0;
		}

    	public void removeNode(Node node) {
        	Node next = node.next;
        	Node pre = node.pre;
        
        	next.pre = pre;
        	pre.next = next;
        	size--;
    	}
    
    	private void addNode(Node node) {
        	// always add to the head;
        	Node pre = head.pre;
        	pre.next = node;
        	node.pre = pre;
        
        	node.next = head;
        	head.pre = node;
        	size++;
    	}

    	private Node getLast() {
    		return tail.next;
    	}
	}

	int capacity;
	int minFreq;
	Map<Integer, Node> map;
	Map<Integer, DlList> freqMap;

	public LFUCache(int capacity) {
    	this.capacity = capacity;
    	map = new HashMap<>();
    	freqMap = new HashMap<>();
    }
    
    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
        	return -1;
        }
        update(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        if (capacity == 0) return;
        if (map.containsKey(key)) {
        	Node node = map.get(key);
        	node.val = value;
        	update(node);
        }
        else { 
        //not contains we need to insert; and remove the min frequenct node based on LRU before insert;
        	Node node = new Node(key, value);
        	map.put(key, node);
        	if (map.size() > capacity) { //remove first;
        		DlList minList = freqMap.get(minFreq);
        		//remove the last;
        		Node last = minList.getLast();
        		if (last != null) {
        			minList.removeNode(last);
                    map.remove(last.key);
        		}
        	}
        	//add the new frequency;
        	minFreq = 1;
        	DlList newList = freqMap.get(1);
    		if (newList == null) {
    			newList = new DlList();
    		}
    		newList.addNode(node);
    		freqMap.put(1, newList);
        }
    }


    private void update(Node node) {
    	//remove the node in the freqMap, increate frequent and add to new map;
    	DlList list = freqMap.get(node.count);
    	list.removeNode(node);
    	if (node.count == minFreq && list.size == 0) {
    		minFreq++;
    	}
    	node.count++;
    	DlList newList = freqMap.get(node.count);
    	if (newList == null) {
    		newList = new DlList();
    	}
    	newList.addNode(node);
    	freqMap.put(node.count, newList);
    }

}









