public class LinkedList { 
    Node root = null;
    public static class Node {
        int data;
        Node next;

        public Node(int data){
            this.data = data;
        }
    }

    public void insert(int data){
        this.root = insert(this.root, data);
    }

    public Node insert(Node node, int data){
        if(node == null){
            node = new Node(data);
        } else {
            node.next = insert(node.next, data);
        }
        return node;
    }

    public  void print(){
        Node node = this.root;
        while(node != null){
            System.out.println(node.data);
            node = node.next;
        }
    }

    public static void main(String []args){
        LinkedList ll = new LinkedList();
        ll.insert(1);
        ll.insert(10);
        ll.insert(11);
        ll.insert(12);
        ll.print();
    }
}