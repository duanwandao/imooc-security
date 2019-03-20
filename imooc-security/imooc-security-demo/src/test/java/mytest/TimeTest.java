package mytest;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * @Description
 * @auther 断弯刀
 * @create 2019-03-20 13:17
 */
public class TimeTest {

    @Test
    public void localDateTest(){
        System.out.println(LocalDate.now());  //2019-03-20
        LocalDate localDate1 = LocalDate.of(2001, 02, 02);
        System.out.println(localDate1);   //2001-02-02

        LocalDate localDate2=localDate1.plus(-1, ChronoUnit.MONTHS);
        System.out.println(localDate2);   //2001-01-02

        LocalDate localDate3=localDate1.plusDays(1);
        System.out.println(localDate3);   //2001-02-03

    }


    @Test
    public void localDateTimeTest(){
        LocalDateTime localDateTime1=LocalDateTime.now();
        System.out.println(localDateTime1);   //2019-03-20T13:28:08.330
    }
}
