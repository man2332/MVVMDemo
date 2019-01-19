package com.example.man2332.mvvmdemo;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {
    private static NoteDatabase instance;

    public abstract NoteDao noteDao();

    public static synchronized NoteDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
//RoomDatabase class
//-@Database() - tells Room this is a database
//      -u provide the Entity class's you want Room to create entities = ...
//      -also provide version number     version = ...
//-the database is a singleton - meaning there can only be one instant of it
//-private static NoteDatabase instance;- this line ensures only one instance
//      of the database will ever be created
//-public abstract NoteDao noteDao(); - Room will define this method for u
//      -it returns NoteDao to later used to access the db by Respository
//-synchronized means the method can only be run on one thread at a time
//      -this ensures the ctor won't be called an extra by accident
//-Room.databaseBuilder()- creates a Database for you
//      -provide it a context, the db class, the db name
//      -.fallbackToDestructiveMigration()-this tells Room how to migirate
//       to a new version schema if db versions don't match
//      room will destroy the old db and recreate the new db