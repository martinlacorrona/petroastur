package com.grupocumb.petroastur;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.grupocumb.petroastur.controller.impl.AppControllerImpl;
import com.grupocumb.petroastur.model.EstacionServicio;
import com.grupocumb.petroastur.model.TransactionStatus;
import com.grupocumb.petroastur.service.APIRequestService;
import com.grupocumb.petroastur.service.SQLService;
import com.grupocumb.petroastur.service.UpdateService;
import com.grupocumb.petroastur.service.impl.APIRequestServiceImpl;
import com.grupocumb.petroastur.service.impl.SQLServiceImpl;
import com.grupocumb.petroastur.service.impl.UpdateServiceImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static junit.framework.TestCase.assertTrue;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class TestUpdateService {
    UpdateService updateService;
    SQLService sqlService;
    APIRequestService apiRequestService;
    Context mockContext;
    AppControllerImpl appController;

    @Before
    public void load() {
        mockContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        ;
        sqlService = new SQLServiceImpl(mockContext);
        apiRequestService = new APIRequestServiceImpl();

        updateService = new UpdateServiceImpl(sqlService, apiRequestService);

        appController = new AppControllerImpl(mockContext);

        //Reset database
        mockContext.deleteDatabase("estacionesservicio-db");
    }

    @Test
    public void testUpdateService() throws InterruptedException {

        assertTrue(sqlService.getAll().size() == 0);

        //1. update the database
        updateService.update();

        //2. waiting
        while(updateService.getStatus() == TransactionStatus.WAITING) {
            //WAITING...
        }

        //3.chqeuamos que en la base tenemos mas de 1
        assertTrue(sqlService.getAll().size() > 0);

        //4. lo imrpimimos para chequear
        System.out.println(sqlService.getAll());
        System.out.println("Tamaño: " + sqlService.getAll().size());

        List<EstacionServicio> lista = appController.getAllEESSOrdered();
        System.out.println("Lista:" + lista);
    }
}
