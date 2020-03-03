public class LinkedListCustom { 
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

    public void print(){
        Node node = this.root;
        while(node != null){
            System.out.println(node.data);
            node = node.next;
        }
    }

    public void reverseList(){
        this .root = reverseList(this.root);
    }

    public Node reverseList(Node head){
        Node reversed = null;
        Node p2 = head;
        while(p2 != null){
            Node temp = p2.next;
            p2.next = reversed;
            reversed = p2;
            p2 = temp;
        }
        return reversed;
    }

    public static void main(String [] args){
        LinkedListCustom ll = new LinkedListCustom();
        ll.insert(1);
        ll.insert(10);
        ll.insert(11);
        ll.insert(12);
        ll.print();
        System.out.println("======================Reverse==============");
        ll.reverseList();
        ll.print();
    }
}