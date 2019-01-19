package com.example.man2332.mvvmdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

//def lifecycle_version = "2.0.0"
//    //View Model and Live Data + Annotation
//    implementation "androidx.lifecycle:lifecycle-extensions:$lifecycle_version"
//    annotationProcessor "androidx.lifecycle:lifecycle-compiler:$lifecycle_version"
//
//    def room_version = "1.1.1"
//    //Room
//    implementation "android.arch.persistence.room:runtime:$room_version"
//    annotationProcessor "android.arch.persistence.room:compiler:$room_version"

//https://codinginflow.com/tutorials/android/room-viewmodel-livedata-recyclerview-mvvm/part-2-entity
