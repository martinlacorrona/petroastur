package com.grupocumb.petroastur.service;

import com.grupocumb.petroastur.model.EstacionServicio;

import java.util.List;

public interface SQLService {
    List<EstacionServicio> getAll();

    List<EstacionServicio> getByIds(List<Integer> ids);

    EstacionServicio getById(int id);
}
