class LRUCache {
    class Node {
        int key;
        int val;
        Node pre;
        Node next;
    }
    
    Node head;
    Node tail;
    int capacity;
    
    Map<Integer, Node> map;
    
    public LRUCache(int capacity) {
        map = new HashMap<>();
        this.capacity = capacity;
        head = new Node();
        tail = new Node();
        head.pre = tail;
        tail.next = head;
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        moveToHead(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        if (!map.containsKey(key)) {
            Node node = new Node();
            node.val = value;
            node.key = key;
            addNode(node);
            map.put(key, node);
            if (map.size() > capacity) {
                Node last = tail.next;
                removeNode(last);
                map.remove(last.key);
            }
        }
        else {
            Node node = map.get(key);
            node.val = value;
            moveToHead(node);
        }
    }
    
    private void moveToHead(Node node) {
        removeNode(node);
        addNode(node);
    }
    
    private void removeNode(Node node) {
        Node next = node.next;
        Node pre = node.pre;
        
        next.pre = pre;
        pre.next = next;
    }
    
    private void addNode(Node node) {
        // always add to the head;
        Node pre = head.pre;
        pre.next = node;
        node.pre = pre;
        
        node.next = head;
        head.pre = node;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */