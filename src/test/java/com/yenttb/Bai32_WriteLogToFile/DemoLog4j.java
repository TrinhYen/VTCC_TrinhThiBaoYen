package com.yenttb.Bai32_WriteLogToFile;

import com.yenttb.Bai26_ParallelPageObject.pages.LoginPage;
import com.yenttb.common.BaseTest;
import org.testng.annotations.Test;

public class DemoLog4j extends BaseTest {

    LoginPage loginPage;

    @Test(priority = 1)
    public void testLoginSuccess(){
        loginPage = new LoginPage();
        loginPage.loginCRM("admin@example.com", "123456");
        loginPage.verifyLoginSuccess();
        loginPage.logout();
        loginPage.verifyRedirectToLoginPage();
    }

}
