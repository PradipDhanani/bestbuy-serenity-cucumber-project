package com.bestbuy.bestbuyCRUD;

import com.bestbuy.bestbuyinfosteps.CategorySteps;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
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

public class CategoryCRUDTest extends TestBase {

    static String name = "APPLE MOBILE" + TestUtils.getRandomValue();
    static String id = "XYZ"+TestUtils.getRandomValue();
    static String categoriesId;

    @Steps
    CategorySteps categorySteps;

    @Title("This test will create a new categories and verify it is generated")
    @Test
    public void test001(){
        categoriesId = categorySteps.createNewCategories(name,id).log().all()
                .statusCode(201).extract().response().body().jsonPath().getString("id");
        System.out.println("Categories ID is " + categoriesId);
    }
    @Title("This test will get the categories information by ID")
    @Test
    public void test002(){
        categorySteps.getCategoriesById(categoriesId).log().all().statusCode(200);
       // categorySteps.getCategoriesById(categoriesId).log().all().statusCode(200);
    }

    @Title("This test will update the categories information and verify the updated information")
    @Test
    public void test003(){
        name = name + "_changed";

        categorySteps.updateCategories(categoriesId,name).statusCode(200);
        categorySteps.getCategoriesById(categoriesId).body("name",equalTo(name));

    }
    @Title("This test will delete the categories information and verify the categories is deleted")
    @Test
    public void test004(){
        categorySteps.deleteCategoriesById(categoriesId).statusCode(200);
        categorySteps.getCategoriesById(categoriesId).statusCode(404);
    }




}
