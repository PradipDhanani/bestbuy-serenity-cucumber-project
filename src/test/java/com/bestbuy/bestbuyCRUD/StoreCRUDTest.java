package com.bestbuy.bestbuyCRUD;

import com.bestbuy.bestbuyinfosteps.StoreSteps;
import com.bestbuy.testbase.TestBase;
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
public class StoreCRUDTest extends TestBase {

    static String name = "Testing Store";
    static String type = "Testing Tools";
    static String address = "268 Alan Lodge";
    static String address2 = "Nether Street";
    static String city = "London";
    static String state = "North";
    static String zip = "525255";
    static double lat = 45.958785;
    static double lng = -90.445596;
    static String hours = "Mon: 9-6; Tue: 9-6; Wed: 9-6; Thurs: 9-6; Fri: 9-6; Sat: 9-6; Sun: 9-6";
    static long storeId;


    @Steps
    StoreSteps storesSteps;

    @Title("Creating a new Store")
    @Test
    public void test001() {
        storeId = storesSteps.createNewStore(name, type, address, address2, city, state, zip, lat, lng, hours).log().all()
                .extract().response().body().jsonPath().getLong("id");
        System.out.println("Store id is : " + storeId);

    }

    @Title("Getting a created Store")
    @Test
    public void test002() {
        storesSteps.getStoreById(storeId).statusCode(200);

    }

    @Title("This test will update the store information and verify the updated information")
    @Test

    public void test003() {

        name = "Testing Store";
        type = "Hardware Tools";
        address = "260 Alan Lodge";
        address2 = "Finchley Street";
        city = "London";
        state = "North";
        zip = "525852";
        lat = 40.854785;
        lng = -80.847596;
        hours = "Mon: 8-6; Tue: 8-6; Wed: 8-6; Thurs: 8-6; Fri: 8-6; Sat: 8-6; Sun: 8-6";

        storesSteps.updateStore(storeId,name, type, address, address2, city, state, zip, lat, lng, hours).statusCode(200);
        storesSteps.getStoreById(storeId).body("name", equalTo(name));

    }

    @Title("This test will delete the store and verify the store is deleted ")
    @Test
    public void test004() {
        storesSteps.deleteStore(storeId).statusCode(200);
        storesSteps.getStoreById(storeId).statusCode(404);
    }

}
