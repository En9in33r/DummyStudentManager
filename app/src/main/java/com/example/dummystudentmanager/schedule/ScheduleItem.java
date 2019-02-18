package com.example.dummystudentmanager.schedule;

public class ScheduleItem {
    int disc_id;
    int day;
    String chet;
    int time;
    String auditory;

    ScheduleItem(int disc_id, int day, String chet, int time, String auditory)
    {
        this.disc_id = disc_id;
        this.day = day;
        this.chet = chet;
        this.time = time;
        this.auditory = auditory;
    }
}
