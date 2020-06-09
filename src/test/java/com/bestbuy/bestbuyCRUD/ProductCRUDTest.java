package com.bestbuy.bestbuyCRUD;

import com.bestbuy.bestbuyinfosteps.ProductSteps;
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
public class ProductCRUDTest extends TestBase {

    static String name = "Nokia New Mobile" + TestUtils.getRandomValue();
    static String type = "Nokia" + TestUtils.getRandomValue();
    static String upc = "8755"+ TestUtils.getRandomValue();
    static double price = 20.99;
    static String description = "This is the latest high-tech design in mobile phones";
    static String model = "NKA13243UK" + TestUtils.getRandomValue();
    static long productId;

    @Steps
    ProductSteps productSteps;



    @Title("This test is creating a new products in list")
    @Test
    public void test001() {
        productId = productSteps.createNewProduct(name, type, upc, (int) price, description, model)
                .log()
                .all()
                .statusCode(201)
                .extract()
                .response()
                .body()
                .jsonPath()
                .getLong("id");
        System.out.println("Product id is " + productId);

    }

    @Title("This test is getting a created product by ID")
    @Test
    public void test002() {
        productSteps.getProductById(productId).statusCode(200);

    }

    @Title("This test will update the product information and verify the updated information")
    @Test
    public void test003() {
        name = name + "_Changed";
        price = 29.99;
        upc = upc + "_updated";
        productSteps.updateProduct(productId, name, type, upc, (int) price, description, model).statusCode(200);
        productSteps.getProductById(productId)
                .body("name",equalTo(name));

    }

    @Title("This test will delete the product information and verify the product is deleted ")
    @Test
    public void test004() {
        productSteps.deleteProduct(productId).statusCode(200);
        productSteps.getProductById(productId).statusCode(404);
    }

}