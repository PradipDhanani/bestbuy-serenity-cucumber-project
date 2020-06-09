package com.bestbuy.constants;

/**
 * Created by Pradip
 */
public class EndPoints {

    /**
     * Product EndPoints
     */

    public static final String GET_ALL_PRODUCT = "/products";
    public static final String CREATE_PRODUCT = "/products/";
    public static final String GET_PRODUCT_BY_ID = "/products/{id}";
    public static final String UPDATE_PRODUCT_BY_ID = "/products/{id}";
    public static final String DELETE_PRODUCT_BY_ID = "/products/{id}";
    public static final String INVALID_ENDPOINT_PRODUCT = "/products123";

    /**
     * Stores EndPoints
     */

    public static final String GET_ALL_STORES = "/stores";
    public static final String CREATE_STORE = "/stores";
    public static final String GET_STORE_BY_ID = "/stores/{id}";
    public static final String UPDATE_STORE_BY_ID = "/stores/{id}";
    public static final String DELETE_STORE_BY_ID = "/stores/{id}";
    public static final String INVALID_ENDPOINT_STORE = "/store123";

    /**
     * Category EndPoints
     */
    public static final String GET_ALL_CATEGORIES = "/categories";
    public static final String CREATE_CATEGORIES = "/categories";
    public static final String GET_CATEGORIES_BY_ID = "/categories/{id}";
    public static final String UPDATE_CATEGORIES_BY_ID = "/categories/{id}";
    public static final String DELETE_CATEGORY_BY_ID = "/categories/{id}";
    public static final String INVALID_ENDPOINT_CATEGORIES = "/categories123";


    /**
     * Service EndPoints
     */

    public static final String GET_ALL_SERVICES = "/services";
    public static final String CREATE_SERVICES = "/services";
    public static final String GET_SERVICES_BY_ID = "/services/{id}";
    public static final String UPDATE_SERVICES_BY_ID = "/services/{id}";
    public static final String DELETE_SERVICES_BY_ID = "/services/{id}";
    public static final String INVALID_ENDPOINT_SERVICES = "/services123";


}
