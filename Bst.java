public class Bst {
    Node root;
    private class Node { 
        int val;
        Node left;
        Node right;

        public Node(int val) { 
            this.val = val;
        }
    }

    public Bst() { 
    
    }

    public void add(int val) { 
        this.root = this.add(this.root, val);
    }

    public Node add(Node node, int val){
        if(node == null){
            node = new Node(val);
        }

        if(val > node.val){
            node.right = add(node.right, val);
        } else if(val < node.val){
            node.left = add(node.left, val);
        } 
        return node;
    }

    public void inOrder(){
        inOrder(this.root);
    }

    public void inOrder(Node node) {
        if(node == null)
            return;
        inOrder(node.left); 
        System.out.println(node.val);
        inOrder(node.right);
    }

    public int getHeight() { 
        return getHeight(this.root);
    }

    public int getHeight(Node node) { 
        if(node == null)
            return -1;
        int lDepth = getHeight(node.left);
        int rDepth = getHeight(node.right);
        if(lDepth > rDepth)
            return lDepth + 1;
        return rDepth + 1;
    }

    public void delete(Node node) {

    }

    public boolean contains(int val) { 
        return this.contains(this.root, val);
    }

    public boolean contains(Node node, int val){
        if(node == null)
            return false;
        if(node.val == val){
            return true;
        } else if(node.val < val) {
            return contains(node.right, val);
        } else {
            return contains(node.left, val);
        }
    }

    public void delete(int val) { 
        delete(this.root, val);
    }

    public Node delete(Node node, int val) { 
        if(node == null)
            return node;
        if(val < node.val){
            node.left = delete(node.left, val);
        } else if(val > node.val) {
            node.right = delete(node.right,val);
        } else { 
            if(node.left == null){ 
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else { 
                node.val = getMinValue(node.right);
                node.right = delete(node.right, node.val);
            }
        }
        return node;
    }

    public int getMinValue(Node node){ 
        if(node == null)
            return node.val;
        else {
            return getMinValue(node.left);
        }
    }

    public static void main(String [] args) { 
        Bst bst = new Bst();
        bst.add(100);
        bst.add(50);
        bst.add(40);
        bst.add(60);
        bst.add(200);
        bst.add(150);
        bst.delete(40);
        bst.inOrder();
        System.out.println("Altura: "+bst.getHeight());
        System.out.println(bst.contains(25));
        // 40 - 50 - 60 - 150 - 200 - 100
    }
}