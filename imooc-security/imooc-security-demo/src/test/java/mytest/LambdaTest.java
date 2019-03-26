package mytest;

import org.junit.Test;

import java.util.function.Consumer;

/**
 * @Description
 * @auther 断弯刀
 * @create 2019-03-26 9:58
 */
public class LambdaTest {

    /**
     * 无参数，无返回值
     */
    @Test
    public void test01(){
        Runnable runnable = () -> System.out.println("Thread start...");
        new Thread(runnable).start();
    }

    /**
     * 有一个参数，并且无返回值
     * Consumer接口是Java8内置的函数式接口
     */
    @Test
    public void test02(){
        Consumer<Integer> consumer = x -> System.out.println(x);
        consumer.accept(5);
    }

    /**
     * 方法引用
     */
    @Test
    public void test03(){
        Consumer<String> consumer = System.out::println;
        consumer.accept("This is Major Tom");
    }
}
