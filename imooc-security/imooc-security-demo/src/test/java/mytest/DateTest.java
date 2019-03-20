package mytest;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description
 * @auther 断弯刀
 * @create 2019-03-17 21:28
 */
public class DateTest {

    @Test
    public void dateTest01(){
        Date date = new Date();
        System.out.println(date.getTime());
        date.setTime(100);
        System.out.println(date);
    }

    @Test
    public void dateTest02() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = simpleDateFormat.parse("2018-08-10 08:08:123");
        Date date2=simpleDateFormat.parse("2018-08-10 08:08:123");
        System.out.println(date1);
        System.out.println("date1.equals(date2): " + date1.equals(date2));

    }
}
