package com.grupocumb.petroastur.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.grupocumb.petroastur.model.EstacionServicio;

import java.util.List;

@Dao
public interface EstacionServicioDao {

    @Query("SELECT * FROM estacionservicio")
    List<EstacionServicio> getAll();

    @Query("SELECT * FROM estacionservicio WHERE iDEESS IN (:ids)")
    List<EstacionServicio> getByIds(int[] ids);

    @Query("SELECT * FROM estacionservicio WHERE iDEESS == (:id)")
    EstacionServicio getById(int id);

    @Insert
    void insertAll(EstacionServicio... list);

    @Query("DELETE FROM estacionservicio")
    void deleteAll();
}
