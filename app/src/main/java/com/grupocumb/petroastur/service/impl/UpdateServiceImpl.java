package com.grupocumb.petroastur.service.impl;

import com.grupocumb.petroastur.service.APIRequestService;
import com.grupocumb.petroastur.service.SQLService;
import com.grupocumb.petroastur.service.UpdateService;

public class UpdateServiceImpl implements UpdateService {

    @Override
    public void update(SQLService sqlService, APIRequestService apiRequestService) {
        apiRequestService.update(sqlService);
    }

}
