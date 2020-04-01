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
   
   
    public int getMinValue(Node node) { 
        if(node.left != null){
            return getMinValue(node.left);
        } else {
            return node.val;
        }

    }

    public void delete(int val) { 
        delete(this.root, val);
    }

    public Node delete(Node node, int val){
        if(node == null)
            return node;
        if(val > node.val){
            node.right = delete(node.right, val); 
        } else if(val <  node.val){
            node.left = delete(node.left, val);
        } else { 
            if(node.left == null){
                return node.right;
            } else if(node.right == null){
                return node.left;
            } else { 
                node.val = getMinValue(node.right);
                node.right = delete(node.right, node.val);
            }
        }
        
        return node;
    } 

    public void invertTree(){
        this.invertTree(this.root);
    }

    public void invertTree(Node node) { 
        if(node == null) return;
        swapNodes(node);
        invertTree(node.left);
        invertTree(node.right);
    }

    public void swapNodes(Node node){
        Node temp = node.left;
        node.left = node.right;
        node.right = temp;
    }
    
    public boolean validateBst() {
       return validateBst(this.root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public Boolean validateBst(Node node, int min, int max){
        if(node.val < min || node.val > max) return false;
        if(node.left != null && !validateBst(node.left, min, node.val)) return false;
        if(node.left != null && !validateBst(node.left, node.val, max)) return false;
        return true;
    }

    public int getMinValue(){
        return getMinValue(this.root);
    }

    public void inOrder(){
        this.inOrder(this.root);
    }

    public void inOrder(Node node){
        if(node == null) return;
        inOrder(node.left);
        System.out.println(node.val);
        inOrder(node.right);
    }

    public boolean hasPathSum(int sum){
        return hasPathSum(this.root, sum);
    }

    public boolean hasPathSum(Node node, int sum){
        if(node == null)
            return (sum == 0);
        int subSum = sum - node.val;
        return(hasPathSum(node.left, subSum) || hasPathSum(node.right, subSum));
    }

    public void add() { 

    }

     public int getHeight(){
         return this.getHeight(this.root);
     }

     public boolean contains(int val){ 
        return this.contains(this.root, val);
     }

     public boolean contains(Node node, int val){
        if(node == null) return false;
        if(val < node.val){
            return contains(node.left, val);
        } else if(val > node.val){
            return contains(node.right, val);
        } else { 
            return true;
        }
     }

    public int getHeight(Node node){
        if(node == null) return 1;
        int lHeight = getHeight(node.left);
        int rHeight = getHeight(node.right);
        return Math.max(lHeight, rHeight) + 1;
    }

     public void add(int val){
         this.root = this.add(this.root, val);
     }

     public Node add(Node node, int val) { 
        if(node == null)
            return new Node(val);
        if(val < node.val){
            node.left = add(node.left, val);
        } else {
            node.right = add(node.right, val);
            return node;
        }
        return node;
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
        System.out.println("Minimum value: "+bst.getMinValue());
        System.out.println("Height: "+bst.getHeight());
        System.out.println("Does it contains 50: "+ bst.contains(50));
        System.out.println("Is it a valid bst: " +bst.validateBst());
        System.out.println("has path sum to 210: "+bst.hasPathSum(210));
        System.out.println("Inverting tree");
        bst.invertTree();
        System.out.println("Should not be valid anymore: "+bst.validateBst());
        // 40 - 50 - 60 - 150 - 200 - 100 
    }
}