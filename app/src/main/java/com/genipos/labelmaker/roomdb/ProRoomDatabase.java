package com.genipos.labelmaker.roomdb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Product.class}, version = 1, exportSchema = false)
public abstract class ProRoomDatabase extends RoomDatabase {
    public abstract ProductDao productDao();
    private static volatile ProRoomDatabase proRoomDatabase;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    static ProRoomDatabase getDatabase(final Context context) {
        if (proRoomDatabase == null) {
            synchronized (ProRoomDatabase.class) {
                if (proRoomDatabase == null) {
                    proRoomDatabase = Room.databaseBuilder(context.getApplicationContext(),
                                    ProRoomDatabase.class, "product_table")
                            .build();
                }
            }
        }
        return proRoomDatabase;
    }
}
