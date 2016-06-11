package com.asv.edu.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 *
 * Created by work on 11.06.2016.
 */
public class Base {

    public static void main(String[] args) {
        List<String> data = new ArrayList<>();
        data.add("1");
        data.add("12");
        data.add("134");
        data.add("15643");

        Consumer<String> printlnConsumer = System.out::println;

        printlnConsumer.accept(modify1(data).toString());

        startAdminThread();
        printlnConsumer.accept(String.valueOf(generateParallel()));
        //printlnConsumer.accept(String.valueOf(generate()));
    }

    public static List<Integer> modify1(List<String> data) {
        return data.stream().map(String::length).collect(toList());
    }

    public static int generateParallel() {
        // -Xmx14G
        IntStream intStream = IntStream.range(0, 2_000_000_000).parallel().filter(x -> x % 2 == 0);
        return intStream.toArray().length;
    }

    public static int generate() {
        // потребляет гораздо меньше ОЗУ и процессорного времение чем параллельная версия
        IntStream intStream = IntStream.range(0, 2_000_000_000).filter(x -> x % 2 == 0);
        return intStream.toArray().length;
    }

    public static void startAdminThread() {
        Thread adminThread = new Thread(() -> {
            int count = 3;
            try {
                while (--count >= 0) {
                    int ac = Thread.activeCount();
                    System.out.println("activeCount = " + ac);
                    Thread.sleep(2000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        adminThread.start();
    }


}