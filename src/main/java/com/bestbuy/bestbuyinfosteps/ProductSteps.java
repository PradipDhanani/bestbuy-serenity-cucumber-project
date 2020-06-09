package com.bestbuy.bestbuyinfosteps;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.ProductPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

/**
 * Created by Pradip
 */
public class ProductSteps {

    @Step("Getting the information of the all the products")
    public ValidatableResponse getAllProducts() {
        return SerenityRest.rest()
                .given()
                .when()
                .get(EndPoints.GET_ALL_PRODUCT)
                .then();
    }


    @Step("Creating a new product with name:{0}, type:{1}, upc:{2}, price:{3}, description{4}, model:{5}")
    public ValidatableResponse createNewProduct(String name, String type, String upc,
                                                int price, String description, String model) {


        ProductPojo productsPojo = new ProductPojo();
        productsPojo.setName(name);
        productsPojo.setType(type);
        productsPojo.setUpc(upc);
        productsPojo.setPrice(price);
        productsPojo.setDescription(description);
        productsPojo.setModel(model);

        return SerenityRest.rest().given()
                .contentType(ContentType.JSON)
                .log()
                .all()
                .when()
                .body(productsPojo)
                .post(EndPoints.CREATE_PRODUCT)
                .then();
    }



    @Step("Getting the information of the product created by Product id:{0}")
    public ValidatableResponse getProductById(long productId) {
        return SerenityRest.rest()
                .given()
                .pathParam("id",productId)
                .contentType(ContentType.JSON)
                .when()
                .get(EndPoints.GET_PRODUCT_BY_ID)
                .then();


    }

    @Step("Updating Product information with productId: {0} name: {1} , type : {2} , upc : {3} , price : {4} , description : {5} , model : {6} ")

    public ValidatableResponse updateProduct(long productId, String name, String type, String upc, int price, String description, String model) {

        ProductPojo productsPojo = new ProductPojo();
        productsPojo.setName(name);
        productsPojo.setType(type);
        productsPojo.setUpc(upc);
        productsPojo.setPrice(price);
        productsPojo.setDescription(description);
        productsPojo.setModel(model);

        return SerenityRest.rest().given()
                .contentType(ContentType.JSON)
                .pathParam("id",productId)
                .log()
                .all()
                .when()
                .body(productsPojo)
                .patch(EndPoints.UPDATE_PRODUCT_BY_ID)
                .then();


    }

    @Step("Deleting the Product information with productId : {0} ")

    public ValidatableResponse deleteProduct(long productId) {
        return SerenityRest.rest()
                .given()
                .pathParam("id",productId)
                .when()
                .delete(EndPoints.DELETE_PRODUCT_BY_ID)
                .then();


    }

}
