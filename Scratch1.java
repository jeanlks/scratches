import java.util.*;



public class Scratch1 { 
    public static class Vehicle { 
        private String color;
        private String name;
        private Boolean parked;
        private String size;
    
        public String getColor() {
            return color;
        }
    
        public void setColor(String color) {
            this.color = color;
        }
    
        public String getName() {
            return name;
        }
    
        public void setName(String name) {
            this.name = name;
        }
    
        public Boolean getParked() {
            return parked;
        }
    
        public void setParked(Boolean parked) {
            this.parked = parked;
        }
    
        public String getSize() {
            return size;
        }
    
        public void setSize(String size) {
            this.size = size;
        }
    }
    
    public static class Motorcycle extends Vehicle{ 
        public Motorcycle() {
            super();
            super.setName("Harley");
            super.setSize("small");
        }
    }
    
    public static class Ferrari extends Vehicle { 
        public Ferrari() {
            super();
            super.setName("Ferrari");
            super.setSize("sport");
        }
    }
    
    public static class ParkingLot { 
        private List<Vehicle> spots;
    
        public ParkingLot() { 
            this.spots = new ArrayList<>();
        }

        public List<Vehicle> getSpots() {
            return spots;
        }

        public void setSpots(List<Vehicle> spots) {
            this.spots = spots;
        }
    
        
    }

    public static class ParkingManager { 
        private ParkingLot parkingLot;
        public ParkingManager(ParkingLot parkingLot) { 
            this.parkingLot = parkingLot;
        }

        public void addVehicleToPark(Vehicle vehicle){ 
            this.parkingLot.getSpots().add(vehicle);
        }
        
        public List<Vehicle> getParkedCars() { 
            return this.parkingLot.getSpots();
        }

    }
    public static void main(String [] args) { 
        ParkingLot park = new ParkingLot();
        ParkingManager manager = new ParkingManager(park);
        Vehicle vehicle = new Motorcycle();
        manager.addVehicleToPark(vehicle);
        vehicle = new Ferrari();
       manager.addVehicleToPark(vehicle);

       manager.getParkedCars().forEach(v ->{ 
            System.out.println(v.getName());
        });
    
    }
}