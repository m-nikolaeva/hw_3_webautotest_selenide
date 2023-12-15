package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final SelenideElement usernameField = $("form#login input[type='text']");
    private final SelenideElement passwordField = $("form#login input[type='password']");
    private final SelenideElement loginButton = $("form#login button");
    private final SelenideElement error = $("div.error-block");


    public void login(String username, String password) {
        typeUsernameInField(username);
        typePasswordInField(password);
        clickLoginButton();
    }

    public void typeUsernameInField(String username) {
        usernameField.should(Condition.visible).setValue(username);
    }

    public void typePasswordInField(String password) {
        passwordField.should(Condition.visible).setValue(password);
    }

    public void clickLoginButton() {
        loginButton.should(Condition.visible).click();
    }

    public String getErrorText() {
        return error.should(Condition.visible).getText();
    }
}
