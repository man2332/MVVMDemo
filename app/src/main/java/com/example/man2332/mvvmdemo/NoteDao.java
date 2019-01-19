package com.example.man2332.mvvmdemo;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface NoteDao {
    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("DELETE FROM note_table")
    void deleteAllNotes();

    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    LiveData<List<Note>> getAllNotes();

}
//-@Dao - tells Room that this is a DAO(db access object)
//-all operationss we want to perform on Note Entity
//-room will generate code for us to perform SQL commands
//  -we just provide annotations @Insert,@Update, etc
//-abstract or interface only
//      -because Room needs to define the code,not us
//-Usually it's One dao per entity
//-@Query() - provide a SQL statement to perform on the db
//-LiveData<> - you can watch the data live- any changes will
//      be notify to the activity and changed