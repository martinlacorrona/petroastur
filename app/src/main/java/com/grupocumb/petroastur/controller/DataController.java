package com.grupocumb.petroastur.controller;

import com.grupocumb.petroastur.model.EstacionServicio;
import com.grupocumb.petroastur.model.TransactionStatus;

import java.util.List;

public interface DataController {
    List<EstacionServicio> getAll();

    List<EstacionServicio> getByIds(int[] ids);

    EstacionServicio getById(int id);

    void update();

    TransactionStatus isUpdated();
}
