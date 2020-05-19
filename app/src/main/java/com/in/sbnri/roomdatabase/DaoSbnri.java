package com.in.sbnri.roomdatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.in.sbnri.entities.MainEntity;
import java.util.List;

@androidx.room.Dao
public interface DaoSbnri {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertData(List<MainEntity> data);
    @Query("SELECT * FROM `main_table`")
    LiveData<List<MainEntity>> getAllData();


}
