public class Bst {
    Node root;
    
    public class Node { 
        public int val;
        public Node left;
        public Node right;

        public Node(int val){
            this.val = val;
        }
    }
   
    public Bst(){
    }

    public void add(int val) { 
       this.root =  this.add(this.root, val);
    }

    public Node add(Node node, int val){
        if(node == null){
            node = new Node(val);
        }
        if(val < node.val){
            node.left =  add(node.left, val);
        } else if (val > node.val){
            node.right =  add(node.right, val);
        }
        return node;
    }

    public int getHeight(){
        return getHeight(this.root);
    }

    public int getHeight(Node node){
        if(node == null)
            return 0;
        int lHeight = getHeight(node.left);
        int rHeight = getHeight(node.right);
        return Math.max(lHeight, rHeight) + 1;
    }

    public boolean contains(int val){
        return this.contains(this.root, val);
    }

    public boolean contains(Node node, int val){
        if(node == null)
            return false;
        if(val < node.val){
            return contains(node.left, val);
        } else if(val > node.val){
            return contains(node.right, val);
        } else {
            return true;
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

    public boolean validateBst(){
       return validateBst(this.root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static boolean validateBst(Node node, int minValue, int maxValue){
        if(node.val < minValue || node.val >= maxValue) return false;
        if(node.left != null && !validateBst(node.left, minValue, node.val));
        if(node.right != null && !validateBst(node.right, node.val, maxValue));
        return true;
    }

    public int getMinValue(Node node){
        Node curr = node;
        while(curr.left != null){
            curr = node.left;
        }
        return curr.val;
    }

    public int getMinValue(){
        return getMinValue(this.root);
    }

    public void inOrder(){
        this.inOrder(this.root);
    }

    public void inOrder(Node node){
        if(node == null)
            return;
        inOrder(node.left);
        System.out.println(node.val);
        inOrder(node.right);
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
      //  bst.inOrder();
        System.out.println("Minimum value: "+bst.getMinValue());
        System.out.println("Height: "+bst.getHeight());
        System.out.println("Does it contains 50: "+ bst.contains(50));
        System.out.println("Is it a valid bst: " +bst.validateBst());
        // 40 - 50 - 60 - 150 - 200 - 100
    }
}