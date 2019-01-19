package com.example.man2332.mvvmdemo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    private String description;

    private int priority;

    public Note(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }
}
//-ENTITY
//-an entity class(in java) represents a table in a database
//-@Entity - Room will create a sqlite table in the db, using this entity class
//  -if no tableName is provided, by default, the class name will be used as table name
//-@PrimaryKey(autoGenerate = true) - sets the field as the pk, first entry will be 1,then 2, etc
//-@ColumnInfo(name = "") - set the column name for the sqlite database instead of using default
//      -by default the column name will be the field name
//-@Ignore - the field with this annotation will not be created in the sqlite db
//-CONSTRUCTOR - Room will need a ctor to help create the table in sqlite db
//      -do not pass in the id, because Room will autogenerate it for us
//      -if a field is not passed in ctor,room cannot set it ever,
//      therfor we need to provide a setter method for the id so we can set it in future if desired