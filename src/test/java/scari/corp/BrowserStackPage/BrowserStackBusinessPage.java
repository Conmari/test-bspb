package scari.corp.BrowserStackPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class BrowserStackBusinessPage {
    WebDriver driver;

    @FindBy(xpath = "//div[@class='css-1yfsqhs']//input[contains(@class, 'chakra-input ym-disable-keys')]")
    WebElement nameInput;

    @FindBy(xpath = "//div[@class='css-jq1kgv']//input[contains(@class, 'ym-disable-keys')]")
    WebElement phoneInput;

    @FindBy(xpath = "//div[@class='css-1mvvnnl']//input[contains(@class, 'chakra-input ym-disable-keys ')]")
    WebElement innInput;


    @FindBy(xpath = "//button[contains(@class, 'chakra-button css-g2axcz')]")
    WebElement submitButton;


    public BrowserStackBusinessPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setNameInput(String name) {
        nameInput.sendKeys(name);
    }

    public String getNameInput() {
        return nameInput.getAttribute("value");
    }

    public void setPhoneInput(String phoneNumber) {
        phoneInput.sendKeys(phoneNumber);
    }

    public String getPhoneInput() {
        return phoneInput.getAttribute("value");
    }


    public void setInnInput(String innNumber) {
        innInput.sendKeys(innNumber);
    }

    public String getInnInput() {
        return innInput.getAttribute("value");
    }

    public boolean verifySubmitButtonIiEnabled() {
        return submitButton.isEnabled();
    }


}
