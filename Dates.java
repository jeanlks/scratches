import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class Dates { 
    public static void main(String []args){
        int d = Period.between(LocalDate.now(), LocalDate.parse("2020-03-05")).getMonths();
        System.out.println(d);
    }
}