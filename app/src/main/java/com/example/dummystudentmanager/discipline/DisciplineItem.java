package com.example.dummystudentmanager.discipline;

public class DisciplineItem
{
    String name;
    String teacher;
    String description;
    String debt;

    DisciplineItem(String name, String teacher, String description, String debt)
    {
        this.name = name;
        this.teacher = teacher;
        this.description = description;
        this.debt = debt;
    }
}
