package com.genipos.labelmaker.roomdb;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "print_ui")
public class Print {
    @PrimaryKey(autoGenerate = true)
    public int printid;
}
