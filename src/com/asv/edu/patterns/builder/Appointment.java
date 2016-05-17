package com.asv.edu.patterns.builder;

import java.time.LocalDateTime;

/**
 * @author alexandrov
 * @since 17.05.2016
 */
public class Appointment {

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String description;

    /**
     *
     * Builder должен обеспечивать создание класса Appointment для двух вариантов использования.
     *
     * В первом варианте обязательна только дата начала
     * Во втором вариете обязательны дата начала, дата окончания и описание
     *
     */
    static class AppointmentBuilder {

        private Appointment appointment;

        public static AppointmentBuilder buildNew() {
            AppointmentBuilder builder = new AppointmentBuilder();
            builder.appointment = new Appointment();
            return builder;
        }

        public AppointmentBuilder buildStartDate(LocalDateTime sd) {

            if (sd == null || sd.isBefore(LocalDateTime.now())) {
                throw new IllegalArgumentException(String.format("Start date cannot be in past. Start date is %1$td.%1$tm.%1$tY %1$tH:%1$tM Now is %2$td.%2$tm.%2$tY %2$tH:%2$tM", sd, LocalDateTime.now()));
            }
            appointment.setStartDate(sd);
            return this;
        }

        public AppointmentBuilder buildEndDate(LocalDateTime ed) {
            if (ed == null || ed.isBefore(LocalDateTime.now())) {
                throw new IllegalArgumentException(String.format("End date cannot be in past. End date is %1$td.%1$tm.%1$tY ", ed));
            }
            appointment.setEndDate(ed);
            return this;
        }

        public AppointmentBuilder buildDescription(String desc) {
            if (desc == null || desc.length() == 0) {
                throw new IllegalArgumentException("Description cannot be null");
            }
            appointment.setDescription(desc);
            return this;
        }

        public Appointment getAppointmentOne() {
            if (appointment.getStartDate() == null) {
                throw new IllegalArgumentException("Start date cannot be null");
            }
            return appointment;
        }

        public Appointment getAppointmentTwo() {
            if (appointment.getStartDate() == null) {
                throw new IllegalArgumentException("Start date cannot be null");
            }
            if (appointment.getEndDate() == null) {
                throw new IllegalArgumentException("End date cannot be null");
            }
            if (appointment.getDescription() == null) {
                throw new IllegalArgumentException("Description cannot be null");
            }
            if (appointment.getStartDate().isAfter(appointment.getEndDate())) {
                throw new IllegalArgumentException("Start date cannot be bigger than end date");
            }
            return appointment;
        }


    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public String getDescription() {
        return description;
    }
}
