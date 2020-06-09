package com.bestbuy.bestbuyinfosteps;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.CategoryPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

/**
 * Created by Pradip
 */
public class CategorySteps {


    @Step("Getting all categories details")
    public ValidatableResponse getAllCategories(){
        return SerenityRest.rest()
                .given()
                .when()
                .get(EndPoints.GET_ALL_CATEGORIES)
                .then();
    }

    @Step("Creating Categories with name : {0} , id : {1} ")
    public ValidatableResponse createNewCategories(String name, String id){

        CategoryPojo categoryPojo = new CategoryPojo();
        categoryPojo.setName(name);
        categoryPojo.setId(id);


        return SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON)
                .log()
                .all()
                .when()
                .body(categoryPojo)
                .post(EndPoints.CREATE_CATEGORIES)
                .then();

    }@Step("Getting the categories details with categoriesId : {0}")
    public ValidatableResponse getCategoriesById(String categoriesId){
        return SerenityRest.rest()
                .given()
                .pathParam("id",categoriesId)
                .contentType(ContentType.JSON)
                .when()
                .get(EndPoints.GET_CATEGORIES_BY_ID)
                .then();

    }@Step("Updating categories details with categoriesId : {0} name : {1} ")
    public ValidatableResponse updateCategories(String categoriesId, String name){

        CategoryPojo categoryPojo = new CategoryPojo();
        categoryPojo.setName(name);
        categoryPojo.setId(categoriesId);

        return SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON)
                .pathParam("id",categoriesId)
                .log()
                .all()
                .when()
                .body(categoryPojo)
                .patch(EndPoints.UPDATE_CATEGORIES_BY_ID)
                .then();
    }
    @Step("Deleting the categories details categoriesId : {0} ")
    public ValidatableResponse deleteCategoriesById(String categoriesId){
        return SerenityRest.rest()
                .given()
                .pathParam("id",categoriesId)
                .when()
                .delete(EndPoints.DELETE_CATEGORY_BY_ID)
                .then();

    }



}
