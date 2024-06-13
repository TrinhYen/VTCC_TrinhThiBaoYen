package com.yenttb.Exam.testcases;

import com.yenttb.Exam.pages.LoginPage;
import com.yenttb.Exam.pages.ProductListPage;
import com.yenttb.common.BaseTest;
import org.testng.annotations.Test;

public class AddNewProductTest extends BaseTest {

    LoginPage loginPage;
    ProductListPage productPage;

    @Test
    public void testAddNewProduct() {
        String PRODUCT_NAME = "yenttb";


        loginPage = new LoginPage();
        productPage = new ProductListPage();

        //Fluent design pattern
        loginPage.loginCRM("admin@example.com", "123456")
                .openAddNewProductPage()
                .verifyRedirectToProductPage()
                .addNewProduct(PRODUCT_NAME);
        productPage.verifyAddProduct(PRODUCT_NAME);
    }

}
