package com.bestbuy.bestbuyCRUD;

import com.bestbuy.bestbuyinfosteps.ServicesSteps;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static org.hamcrest.Matchers.equalTo;


/**
 * Created by Pradip
 */
@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ServiceCRUDTest extends TestBase {

    static String name = "Laptop Refurbished" + TestUtils.getRandomValue();
    static long serviceId;

    @Steps
    ServicesSteps servicesSteps;


    @Title("This test will create a new services and verify its generated")
    @Test
    public void test001() {
        ValidatableResponse response = servicesSteps.createNewServices(name).statusCode(201).log().all();
        serviceId = response.extract()
                .body().jsonPath().getLong("id");
        System.out.println("services ID is" + serviceId);
    }

    @Title("This test will get the services information by ID")
    @Test

    public void test002() {
        servicesSteps.getServiceById(serviceId).log().all().statusCode(200);

    }

    @Title("This test will update the services information and verify the updated information")
    @Test

    public void test003() {

        name = name + "_Changed";

        servicesSteps.updateServices(serviceId, name).statusCode(200);
        servicesSteps.getServiceById(serviceId).body("name", equalTo(name));

    }

    @Title("This test will delete the categories information and verify the categories is deleted ")
    @Test

    public void test004() {
        servicesSteps.deleteServiceById(serviceId).statusCode(200);
        servicesSteps.getServiceById(serviceId).statusCode(404);
    }


}
