package com.grupocumb.petroastur.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.grupocumb.petroastur.model.EstacionServicio;

@Database(entities = {EstacionServicio.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract EstacionServicioDao estacionServicioDao();
}
