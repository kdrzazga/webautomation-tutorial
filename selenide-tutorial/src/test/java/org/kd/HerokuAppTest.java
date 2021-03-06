package org.kd;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.junit.Test;

import static com.codeborne.selenide.Condition.selected;
import static com.codeborne.selenide.Selenide.*;

public class HerokuAppTest {

    public static final String HEROKUAPP_URL = "https://the-internet.herokuapp.com";

    public static void main(String[] args) {
        HerokuAppTest helloSelenide = new HerokuAppTest();
        helloSelenide.simpleCheckboxTest();
        helloSelenide.simpleDropdownTestOption1();
        helloSelenide.simpleDropdownTestDisabledOption();
    }

    @Severity(SeverityLevel.NORMAL)
    @Story("{empty}")
    @Description("")
    @Test
    public void simpleCheckboxTest() {
        Selenide.open(HEROKUAPP_URL + "/checkboxes");
        ElementsCollection checkboxes = $("form[id='checkboxes']")
                .findAll(By.tagName("input"));
        checkboxes.iterator().forEachRemaining(WebElement::click);

        checkboxes.get(0).shouldBe(selected);
        checkboxes.get(1).shouldNotBe(selected);

        tearDown();
    }

    @Severity(SeverityLevel.NORMAL)
    @Story("{empty}")
    @Description("")
    @Test
    public void simpleDropdownTestOption1() {
        open(HEROKUAPP_URL + "/dropdown");
        ElementsCollection options = $("select[id='dropdown']")
                .findAll(By.tagName("option"));
        options.get(1).click();

        options.get(0).shouldNotBe(selected);
        options.get(1).shouldBe(selected);
        options.get(2).shouldNotBe(selected);

        tearDown();
    }

    @Severity(SeverityLevel.NORMAL)
    @Story("{empty}")
    @Description("Test, if disabled option ca be clicked.")
    @Test
    public void simpleDropdownTestDisabledOption() {
        open(HEROKUAPP_URL + "/dropdown");
        ElementsCollection options = $("select[id='dropdown']")
                .findAll(By.tagName("option"));
        options.shouldHaveSize(3);

        options.get(1).click();
        options.get(0).click();

        options.get(0).shouldNotBe(selected);
        options.get(1).shouldBe(selected);
        options.get(2).shouldNotBe(selected);

        tearDown();
    }

    private void tearDown() {
        closeWebDriver();
    }

}
