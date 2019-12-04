package com.grupocumb.petroastur.controller;

import com.grupocumb.petroastur.model.EstacionServicio;

import java.util.List;

public interface DataController {
    List<EstacionServicio> getAll();
    List<EstacionServicio> getByIds(List<Integer> ids);
    List<EstacionServicio> getById(int id);
}
