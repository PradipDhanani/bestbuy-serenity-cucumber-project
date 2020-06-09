package com.bestbuy.cucumber.stepsdefs;

import com.bestbuy.bestbuyinfosteps.CategorySteps;
import com.bestbuy.utils.TestUtils;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

import static org.hamcrest.Matchers.equalTo;

/**
 * Created by Pradip
 */
public class CategoriesStepdefs {

    static String name = "APPLE Mobile" + TestUtils.getRandomValue();
    static String id = "XYZ" + TestUtils.getRandomValue();
    static String categoriesId;

    @Steps
    CategorySteps categorySteps;



    @When("^User sends a GET request to the categories endpoint , they must get back a valid status code 200$")
    public void userSendsAGETRequestToTheCategoriesEndpointTheyMustGetBackAValidStatusCode() {
        categorySteps.getAllCategories().statusCode(200);
    }

    @When("^I create a new categories by providing the information name & id$")
    public void iCreateANewCategoriesByProvidingTheInformationNameId() {
        categoriesId = categorySteps.createNewCategories(name, id).log().all().statusCode(201).extract().response().body().jsonPath().getString("id");
    }

    @Then("^I verify the categories is created$")
    public void iVerifyTheCategoriesIsCreated() {
        categorySteps.getCategoriesById(categoriesId).log().all().statusCode(200);
        System.out.println("categories id is : " + categoriesId);

    }

    @When("^I send GET request to categories endpoint with Id \"([^\"]*)\" , I should received the categories information$")
    public void iSendGETRequestToCategoriesEndpointWithIdIShouldReceivedTheCategoriesInformation(String _categoriesId)  {
        categorySteps.getCategoriesById(categoriesId).log().all().statusCode(200);
    }

    @When("^I update a created categories providing the new name$")
    public void iUpdateACreatedCategoriesProvidingTheNewName() {
        name = name + "_changed";
        categorySteps.updateCategories(categoriesId,name).statusCode(200);
    }

    @Then("^I verify the categories is updated$")
    public void iVerifyTheCategoriesIsUpdated() {
        categorySteps.getCategoriesById(categoriesId).body("name",equalTo(name));
    }

    @When("^I delete a created categories ,they must get back a valid status code  is 200$")
    public void iDeleteACreatedCategoriesTheyMustGetBackAValidStatusCodeIs() {
        categorySteps.deleteCategoriesById(categoriesId).statusCode(200);
    }

    @Then("^I verify the categories is deleted$")
    public void iVerifyTheCategoriesIsDeleted() {
        categorySteps.getCategoriesById(categoriesId).statusCode(404);
    }
}
