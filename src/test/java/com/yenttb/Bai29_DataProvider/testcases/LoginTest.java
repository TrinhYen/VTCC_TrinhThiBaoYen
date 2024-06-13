package com.yenttb.Bai29_DataProvider.testcases;

import com.yenttb.Bai29_DataProvider.pages.LoginPage;
import com.yenttb.common.BaseTest;
import com.yenttb.dataproviders.DataProviderFactory;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class LoginTest extends BaseTest {

    LoginPage loginPage;

    @Test(priority = 1, dataProvider = "data_provider_login", dataProviderClass = DataProviderFactory.class)
    public void testLoginSuccess(String email, String password){
        loginPage = new LoginPage();
        loginPage.loginCRM(email, password);
        loginPage.verifyLoginSuccess();
        loginPage.logout();
        loginPage.verifyRedirectToLoginPage();
    }

    @Test(priority = 2, dataProvider = "data_provider_login_excel", dataProviderClass = DataProviderFactory.class)
    public void testLoginSuccessFromExcel(String email, String password){
        loginPage = new LoginPage();
        loginPage.loginCRM(email, password);
        loginPage.verifyLoginSuccess();
        loginPage.logout();
        loginPage.verifyRedirectToLoginPage();
    }

    @Test(priority = 3, dataProvider = "data_provider_login_excel_multi_row", dataProviderClass = DataProviderFactory.class)
    public void testLoginSuccessFromExcelHashtable(Hashtable<String, String> data){
        loginPage = new LoginPage();
        loginPage.loginCRM(data.get("EMAIL"), data.get("PASSWORD"));
        loginPage.verifyLoginSuccess();
        loginPage.logout();
        loginPage.verifyRedirectToLoginPage();
    }
}
