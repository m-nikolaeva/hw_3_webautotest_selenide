package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.visible;

public class ProfilePage {
    @FindBy(xpath = "//h3/following-sibling::div" +
            "//div[contains(text(), 'Full name')]/following-sibling::div")
    private SelenideElement fullNameInAdditionalInfo;
    @FindBy(css = "div.mdc-card h2")
    private SelenideElement fullNameInAvatarSection;

    public String getFullNameFromAdditionalInfo() {
        return fullNameInAdditionalInfo.shouldBe(visible).text();
    }

    public String getFullNameFromAvatarSection() {
        return fullNameInAvatarSection.shouldBe(visible).text();
    }

}
