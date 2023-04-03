package com.genipos.labelmaker.roomdb;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProductDao {
    @Insert
    void insert(Product product);

    @Query("Select * from product_table")
    LiveData<List<Product>> getAll();

    @Query("Select * from product_table where pid = :id ")
    Product getDataById(int id);
}
