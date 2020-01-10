package com.grupocumb.petroastur.controller.impl;

import android.content.Context;

import com.grupocumb.petroastur.controller.DataController;
import com.grupocumb.petroastur.model.EstacionServicio;
import com.grupocumb.petroastur.model.TransactionStatus;
import com.grupocumb.petroastur.service.APIRequestService;
import com.grupocumb.petroastur.service.SQLService;
import com.grupocumb.petroastur.service.UpdateService;
import com.grupocumb.petroastur.service.impl.APIRequestServiceImpl;
import com.grupocumb.petroastur.service.impl.SQLServiceImpl;
import com.grupocumb.petroastur.service.impl.UpdateServiceImpl;

import java.util.List;

public class DataControllerImpl implements DataController {

    private SQLService sqlService;
    private UpdateService updateService;
    private APIRequestService apiRequestService;

    /**
     * Contructor, le pasamos el contexto.
     *
     * @param context Le pasamos el contexto de la aplicacion, a poder ser el de MainActivity.
     */
    public DataControllerImpl(Context context) {
        this.sqlService = new SQLServiceImpl(context);
        this.apiRequestService = new APIRequestServiceImpl();
        this.updateService = new UpdateServiceImpl(sqlService, apiRequestService);
    }

    @Override
    public List<EstacionServicio> getAll() {
        return this.sqlService.getAll();
    }

    @Override
    public List<EstacionServicio> getByIds(int[] ids) {
        return this.sqlService.getByIds(ids);
    }

    @Override
    public EstacionServicio getById(int id) {
        return this.sqlService.getById(id);
    }

    @Override
    public void update() {
        this.updateService.update();
    }

    @Override
    public TransactionStatus isUpdated() {
        return this.updateService.getStatus();
    }
}
