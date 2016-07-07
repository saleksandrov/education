package com.asv.edu.lambda;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

/**
 *
 * Created by work on 02.07.2016.
 */
public class ThreadSafeDateFormat {


    public static void main(String[] args) {

        Supplier<DateFormat> dfSupplier = DateFormatContainer::getDF;
        //Supplier<DateFormat> dfSupplier = NotSynchronizedDateFormatContainer::getDF;

        Runnable task = () -> {
            System.out.println(dfSupplier.get().format(new Date()));
        };

        Runnable parseTask = () -> {
            try {
                System.out.println(dfSupplier.get().parse("Jul 1, 2015"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        };


        ExecutorService es = Executors.newFixedThreadPool(50);

        int count = 50;
        while (count-- > 0) {
            es.submit(count % 2 == 0 ? task: parseTask);
        }

        es.shutdown();


    }

    static class DateFormatContainer {

        static ThreadLocal<DateFormat> dateFormatContainer = ThreadLocal.withInitial(() -> DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.ENGLISH));

        static DateFormat getDF() {
            return dateFormatContainer.get();
        }

    }

    static class NotSynchronizedDateFormatContainer {

        static DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.ENGLISH);

        static DateFormat getDF() {
            return df;
        }

    }

}
