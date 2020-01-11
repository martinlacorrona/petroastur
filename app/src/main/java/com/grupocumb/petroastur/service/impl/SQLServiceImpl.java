package com.grupocumb.petroastur.service.impl;

import android.content.Context;

import androidx.room.Room;

import com.grupocumb.petroastur.db.AppDatabase;
import com.grupocumb.petroastur.db.EstacionServicioDao;
import com.grupocumb.petroastur.model.EstacionServicio;
import com.grupocumb.petroastur.service.SQLService;

import java.util.List;

public class SQLServiceImpl implements SQLService {

    private EstacionServicioDao estacionServicioDao;

    public SQLServiceImpl(Context context) {
        AppDatabase db = Room.databaseBuilder(context, AppDatabase.class,
                "estacionesservicio-db")
                .allowMainThreadQueries()
                .build();

        estacionServicioDao = db.estacionServicioDao();
    }

    @Override
    public List<EstacionServicio> getAll() {
        return estacionServicioDao.getAll();
    }

    @Override
    public List<EstacionServicio> getByIds(int[] ids) {
        return estacionServicioDao.getByIds(ids);
    }

    @Override
    public EstacionServicio getById(int id) {
        return estacionServicioDao.getById(id);
    }

    @Override
    public void insertAll(List<EstacionServicio> list) {
        estacionServicioDao.insertAll(list);
    }

    @Override
    public void deleteAll() {
        estacionServicioDao.deleteAll();
    }
}
