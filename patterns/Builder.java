public class Builder { 
    public static class Pizza { 
        private String flavor;

        public  Pizza(PizzaBuilder builder) { 
            this.flavor = builder.flavor;
        }


        public String getFlavor() {
            return flavor;
        }

        public void setFlavor(String flavor) {
            this.flavor = flavor;
        }   
    }

    public static class PizzaBuilder { 
        private String flavor;
        
        public PizzaBuilder setFlavor(String flavor) { 
            this.flavor = flavor;
            return this;
        }

        public Pizza build(){
            return new Pizza(this);
        }

        public PizzaBuilder() {
        }
    }

    public static void main(String [] args){
        PizzaBuilder builder = new PizzaBuilder();
        System.out.println(builder.setFlavor("pepperoni").build().getFlavor());
    }
}

