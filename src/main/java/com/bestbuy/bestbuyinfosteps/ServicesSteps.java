package com.bestbuy.bestbuyinfosteps;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.ServicePojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

/**
 * Created by Pradip
 */
public class ServicesSteps {


    @Step("Getting all services Information ")
    public ValidatableResponse getAllServices() {
        return SerenityRest.rest()
                .given()
                .when()
                .get(EndPoints.GET_ALL_SERVICES)
                .then();
    }

    @Step("Creating services with id : {0} ")

    public ValidatableResponse createNewServices(String name) {
        ServicePojo servicePojo = new ServicePojo();
        servicePojo.setName(name);

        return SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON)
                .log()
                .all()
                .when()
                .body(servicePojo)
                .post(EndPoints.CREATE_SERVICES)
                .then();

    }

    @Step("Get the services information by service Id : {0}")
    public ValidatableResponse getServiceById(long serviceId) {
        return SerenityRest.rest()
                .given()
                .pathParam("id", serviceId)
                .contentType(ContentType.JSON)
                .when()
                .get(EndPoints.GET_SERVICES_BY_ID)
                .then();
    }


    @Step("Updating services details with Service Id : {0} name : {1} ")
    public ValidatableResponse updateServices(long serviceId, String name) {
        ServicePojo servicePojo = new ServicePojo();
        servicePojo.setName(name);

        return SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON)
                .pathParam("id", serviceId)
                .log()
                .all()
                .when()
                .body(servicePojo)
                .patch(EndPoints.UPDATE_SERVICES_BY_ID)
                .then();
    }

    @Step("Deleting services details with service Id : {0} ")
    public ValidatableResponse deleteServiceById(long serviceId) {
        return SerenityRest.rest()
                .given()
                .pathParam("id", serviceId)
                .when()
                .delete(EndPoints.DELETE_SERVICES_BY_ID)
                .then();
    }


}
