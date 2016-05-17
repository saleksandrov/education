package com.asv.edu.patterns.builder;

import java.time.LocalDateTime;
import java.time.Month;

/**
 * @author alexandrov
 * @since 17.05.2016
 */
public class Client {

    public static void main(String[] args) {

        // we will get Exception because start date is lower current date
        Appointment appointmentOne1 = Appointment.AppointmentBuilder.buildNew().buildStartDate(LocalDateTime.of(2014, 1, 1, 1, 1)).getAppointmentOne();

        // ok
        Appointment appointmentOne2 = Appointment.AppointmentBuilder.buildNew().buildStartDate(LocalDateTime.of(2018, Month.JUNE, 15, 11, 45)).getAppointmentOne();

        //ok
        Appointment appointmentTwo = Appointment.AppointmentBuilder.buildNew().
                buildStartDate(LocalDateTime.of(2017, Month.MAY, 18, 10, 10)).buildEndDate(LocalDateTime.of(2017, Month.MAY, 19, 11, 30)).buildDescription("Test").getAppointmentTwo();


    }

}
