package com.grupocumb.petroastur.service.impl;

import com.grupocumb.petroastur.model.TransactionStatus;
import com.grupocumb.petroastur.service.APIRequestService;
import com.grupocumb.petroastur.service.SQLService;
import com.grupocumb.petroastur.service.UpdateService;

public class UpdateServiceImpl implements UpdateService {

    private final SQLService sqlService;
    private final APIRequestService apiRequestService;

    public UpdateServiceImpl(SQLService sqlService, APIRequestService apiRequestService) {
        this.sqlService = sqlService;
        this.apiRequestService = apiRequestService;
    }

    @Override
    public void update() {
        apiRequestService.update(sqlService);
    }

    @Override
    public TransactionStatus getStatus() {
        return apiRequestService.getTransactionStatus();
    }

}
