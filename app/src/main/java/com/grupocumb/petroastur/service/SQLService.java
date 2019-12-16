package com.grupocumb.petroastur.service;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.grupocumb.petroastur.model.EstacionServicio;

import java.util.List;

public interface SQLService {

    List<EstacionServicio> getAll();

    List<EstacionServicio> getByIds(int[] ids);

    EstacionServicio getById(int id);

    void insertAll(EstacionServicio... list);

    void deleteAll();
}
