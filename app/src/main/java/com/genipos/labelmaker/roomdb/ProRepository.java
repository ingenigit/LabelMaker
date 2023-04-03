package com.genipos.labelmaker.roomdb;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ProRepository {
    ProRoomDatabase proRoomDatabase;
    ProductDao productDao;
    LiveData<List<Product>> listProducts;

    public ProRepository(Application application) {
        proRoomDatabase = ProRoomDatabase.getDatabase(application);
        productDao = proRoomDatabase.productDao();
        listProducts = productDao.getAll();
    }

    public void insertProduct(Product product) {
        ProRoomDatabase.databaseWriteExecutor.execute(() -> productDao.insert(product));
    }

    public LiveData<List<Product>> getAllProducts() {
        return listProducts;
    }

    public Product getSingleData(int id){
        return productDao.getDataById(id);
    }
}
