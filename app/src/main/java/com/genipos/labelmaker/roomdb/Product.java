package com.genipos.labelmaker.roomdb;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "product_table")
public class Product {
    @PrimaryKey(autoGenerate = true)
    public int pid;
//    @ColumnInfo(name = "company_name")
    public String cName;
//    @ColumnInfo(name = "product_name")
    public String pName;
//    @ColumnInfo(name = "product_desc")
    public String pDesc;
//    @ColumnInfo(name = "product_mfg")
    public String pMfg;
//    @ColumnInfo(name = "product_exp")
    public String pExp;
//    @ColumnInfo(name = "product_weight")
    public String pWeight;
//    @ColumnInfo(name = "product_unit")
    public String pUnit;
//    @ColumnInfo(name = "product_price")
    public String pPrice;
//    @ColumnInfo(name = "product_batch")
    public String pBatch;
//    @ColumnInfo(name = "product_mfg_at")
    public String pMfgAt;
//    @ColumnInfo(name = "product_lic")
    public String pLic;

    public Product(int pid, String cName, String pName, String pDesc, String pMfg, String pExp, String pWeight, String pUnit, String pPrice, String pBatch, String pMfgAt, String pLic) {
        this.pid = pid;
        this.cName = cName;
        this.pName = pName;
        this.pDesc = pDesc;
        this.pMfg = pMfg;
        this.pExp = pExp;
        this.pWeight = pWeight;
        this.pUnit = pUnit;
        this.pPrice = pPrice;
        this.pBatch = pBatch;
        this.pMfgAt = pMfgAt;
        this.pLic = pLic;
    }

    public int getPid() {
        return pid;
    }

    public String getcName() {
        return cName;
    }

    public String getpName() {
        return pName;
    }

    public String getpDesc() {
        return pDesc;
    }

    public String getpMfg() {
        return pMfg;
    }

    public String getpExp() {
        return pExp;
    }

    public String getpWeight() {
        return pWeight;
    }

    public String getpUnit() {
        return pUnit;
    }

    public String getpPrice() {
        return pPrice;
    }

    public String getpBatch() {
        return pBatch;
    }

    public String getpMfgAt() {
        return pMfgAt;
    }

    public String getpLic() {
        return pLic;
    }
}
