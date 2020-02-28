public class Decorator {
    static interface Employee { 
        public void join();
    }

    static class BasicEmployee implements Employee { 
        @Override
        public void join(){
            System.out.print("Basic employee. ");
        }
    }

   

    static class EmployeeDecorator implements Employee { 
        protected Employee employee;
        public EmployeeDecorator(Employee employee) {
            this.employee = employee;
        }
        @Override
        public void join(){
            this.employee.join();
        }
    }

    static class Developer extends EmployeeDecorator { 
        public Developer(Employee e){
            super(e);
        }
        @Override
        public void join(){
            super.join();
            System.out.print("Developer. ");
        }
    }

    static class TeamLead extends EmployeeDecorator {
        public TeamLead(Employee e){
            super(e);
        }
        @Override
        public void join(){
            super.join();
            System.out.print("Team lead. ");
        }
    }


    public static void main(String args[]){
        Employee employee = new Developer(new BasicEmployee());
        employee.join();
        System.out.println("");
        Employee employee2 = new Developer(new TeamLead(new BasicEmployee()));
        employee2.join();
    }
}