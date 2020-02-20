import java.time.LocalDate;
import java.util.*;
public class Parking { 

    static abstract class Vehicle { 
        String plate;
        String color; 
        Size size;

        
        public String getPlate() {
            return plate;
        }

        public void setPlate(String plate) {
            this.plate = plate;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public Size getSize() {
            return size;
        }

        public void setSize(Size size) {
            this.size = size;
        }

        public Vehicle(String plate, String color, Size size) {
            this.plate = plate;
            this.color = color;
            this.size = size;
        }

        public Vehicle(){

        }

        @Override
        public String toString(){
            return " { \"Vehicle:\"" + plate+"}";
        }
    }

    static class Floor {
        String name; 

        Map<Size, List<Spot>> spots = new HashMap<>();

        
        public Map<Size, List<Spot>> getSpots() {
            return spots;
        }

        public void setSpots(Map<Size, List<Spot>> spots) {
            this.spots = spots;
        }

        public void addSpots(Size size, int qt) {
            List<Spot> spotsTemp = new ArrayList<>();
            for(int i = 0; i< qt; i++){
                spotsTemp.add(new Spot(name+size+i));
            }
            spots.put(size,spotsTemp);
        }

        public Spot parkCar(Vehicle vehicle) throws ParkingException {
            if(!spots.containsKey(vehicle.getSize())){
                throw new ParkingException("Floor does not have spots for this size");
            } else {
                return spots.get(vehicle.getSize())
                     .stream().filter(s -> s.free)
                     .findFirst().map(s -> {
                         s.free = false;
                         s.vehicle = vehicle;
                         return s;
                     }).orElseThrow(() -> new ParkingException("Spots are full for floor!"));
            }
        }

        public Floor(Map<Size, List<Spot>> spots) {
            this.spots = spots;
        }

        public Floor(String name){
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
        
    }
    static class ParkingException extends Exception {

        public ParkingException(String message) {
            super(message);
        }
        
    }

    static class Spot {
        String name;
        Vehicle vehicle;
        Boolean free;

        public Spot(String name) {
            this.name = name;
            this.free = true;
        }

        @Override
        public String toString(){
            return "{ \"Spot:\"" +name +"\""+"}";
        }
        
    }

    enum Size { 
        S,M,L,XL
    }

    static class Bike extends Vehicle {

        public Bike(String plate, String color) {
            super(plate, color, Size.S);
        }
        
    }

    static class Corolla extends Vehicle {

        public Corolla(String plate, String color) {
            super(plate, color, Size.M);
        }

        
    }

    static class Truck extends Vehicle {

        public Truck(String plate, String color, Size size) {
            super(plate, color, Size.L);
        }

        public Truck() {
        }
        
    }

    static class Ticket { 
        Vehicle vehicle;
        LocalDate dateStart;
        LocalDate dateEnd;
        Spot spot;

        public Vehicle getVehicle() {
            return vehicle;
        }

        public void setVehicle(Vehicle vehicle) {
            this.vehicle = vehicle;
        }

        public LocalDate getDateStart() {
            return dateStart;
        }

        public void setDateStart(LocalDate dateStart) {
            this.dateStart = dateStart;
        }

        public LocalDate getDateEnd() {
            return dateEnd;
        }

        public void setDateEnd(LocalDate dateEnd) {
            this.dateEnd = dateEnd;
        }

        public Spot getSpot() {
            return spot;
        }

        public void setSpot(Spot spot) {
            this.spot = spot;
        }

        public Ticket(Vehicle vehicle, LocalDate dateStart, LocalDate dateEnd, Spot spot) {
            this.vehicle = vehicle;
            this.dateStart = dateStart;
            this.dateEnd = dateEnd;
            this.spot = spot;
        }

        public Ticket(){

        }
        
        @Override
        public String toString(){
            return "{ \"Ticket\": "+dateStart+ "," +this.spot.toString()+","+ this.vehicle.toString()+"}";
        }
        
    }

    static class ParkingLot {
        Map<String, Floor> floors = new HashMap<>();
        
        public void addFloor(Floor floor) {
            floors.put(floor.getName(), floor);
        }

        public Map<String, Floor> getFloors() {
            return floors;
        }

        public Ticket parkCar(Vehicle vehicle, Floor floor) throws ParkingException {
            Ticket ticket = new Ticket();
            ticket.setVehicle(vehicle);
            ticket.setDateStart(LocalDate.now());
            ticket.setSpot(floor.parkCar(vehicle));
            return ticket;
        }

        public void setFloors(Map<String, Floor> floors) {
            this.floors = floors;
        }

        public ParkingLot(Map<String, Floor> floors) {
            this.floors = floors;
        }

        public ParkingLot(){

        }

        public int getTotalFreeSpots(){
            int total = 0;
            for(Map.Entry<String, Floor> floor : floors.entrySet()) { 
                for(Map.Entry<Size, List<Spot>> spots : floor.getValue().getSpots().entrySet()) {
                    total += spots.getValue().stream().filter(s-> s.free).count();
                }
            }
            return total;
        }
        
    }

    public static void main(String [] args) throws ParkingException {
        Floor firstFloor = new Floor("1st");
        Floor secondFloor = new Floor("2nd");
        firstFloor.addSpots(Size.M, 2);
        firstFloor.addSpots(Size.S, 10);
        secondFloor.addSpots(Size.S, 1);
        Vehicle bike1 = new Bike("LN2040", "BLUE");
        Vehicle corolla1 = new Corolla("AB2310", "BLACK");
        Vehicle corolla2 = new Corolla("CA232", "WHITE");

        ParkingLot parking = new ParkingLot();
        parking.addFloor(firstFloor);
        parking.addFloor(secondFloor);
        System.out.println("Parking spots free: "+parking.getTotalFreeSpots());
        System.out.println(parking.parkCar(bike1, secondFloor).toString());
        System.out.println(parking.parkCar(corolla1, firstFloor).toString());
        System.out.println("Parking spots free: "+parking.getTotalFreeSpots());
        System.out.println(parking.parkCar(corolla2, firstFloor));
    }
}