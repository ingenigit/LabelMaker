package com.genipos.labelmaker.roomdb;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ProViewModel extends AndroidViewModel {
    ProRepository proRepository;
    final LiveData<List<Product>> listLiveData;

    public ProViewModel( Application application) {
        super(application);
        this.proRepository = new ProRepository(application);
        this.listLiveData = proRepository.getAllProducts();
    }
    public LiveData<List<Product>> getAllProducts() {
        return listLiveData;
    }

    public Product getSingleProducts(int ID) {
        return proRepository.getSingleData(ID);
    }

    public void insertProduct(Product product) {
        proRepository.insertProduct(product);
    }
}
