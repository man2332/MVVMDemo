package com.example.man2332.mvvmdemo;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;
public class NoteViewModel extends AndroidViewModel {
    private NoteRepository repository;
    private LiveData<List<Note>> allNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
        allNotes = repository.getAllNotes();
    }

    public void insert(Note note) {
        repository.insert(note);
    }

    public void update(Note note) {
        repository.update(note);
    }

    public void delete(Note note) {
        repository.delete(note);
    }

    public void deleteAllNotes() {
        repository.deleteAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
}
//ViewModel - calls the repository to operate on the db
//  -the ViewModel has CRUD methods-to get crud commands done, but
//  the VM shouldn't need to know the details of how it gets done,
//  thats the job of the repository.
//NoteViewModel CONSTRUCTOR -
//  gets passed the application context(by magic)
//  which the repository needs to use to create a db


//LiveData is a feature of Room
//  -it provides live updates to fields and all changes
//  to that data will be updated automatically by Room

//repository - responsible for CRUD/db functionality
//  and contains all the code/methods needed to make it work


//-line 15
