package com.example.man2332.mvvmdemo;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {
    private static NoteDatabase instance;

    public abstract NoteDao noteDao();

    public static synchronized NoteDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDao noteDao;

        private PopulateDbAsyncTask(NoteDatabase db) {
            noteDao = db.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //populate initial dummy db data for us to work with
            noteDao.insert(new Note("Title 1", "Description 1", 1));
            noteDao.insert(new Note("Title 2", "Description 2", 2));
            noteDao.insert(new Note("Title 3", "Description 3", 3));
            return null;
        }
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
//      since we use Room.build() to create this class/object
//      -it returns NoteDao to later used to access the db by Respository
//-synchronized means the method can only be run on one thread at a time
//      -this ensures the ctor won't be called an extra by accident
//-Room.databaseBuilder()- creates a Database for you
//      -provide it a context, the db class, the db name
//      -.fallbackToDestructiveMigration()-this tells Room how to migirate
//       to a new version schema if db versions don't match
//      room will destroy the old db and recreate the new db
//-RoomDatabase.Callback is used to callback when the db first creates
//      or when db opens up everytime(onCreate() and onOpen())
//      -onCreate() is only called once when db is first created
//      -onOpen() is called whenever the db opens
//      -it's static because our getInstance() method is also static