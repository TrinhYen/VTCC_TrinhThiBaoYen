package com.anhtester.Exam.testcases;

import com.anhtester.Exam.pages.LoginPage;
import com.anhtester.Exam.pages.ProductListPage;
import com.anhtester.common.BaseTest;
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
