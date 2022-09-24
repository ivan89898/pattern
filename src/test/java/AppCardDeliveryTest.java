import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class AppCardDeliveryTest {
    private final DataGenerator.UserInfo validUser = DataGenerator.Registration.generateUser("ru");
    private final int daysToAddForFirstMeeting = 4;
    private final String firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    void shouldTestV3() {
        int daysToAddForSecondMeeting = 3;
        String secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);
        $("[data-test-id=city] input").setValue(validUser.getCity());
        $("[data-test-id=date] input.input__control").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id=date] input.input__control").sendKeys(Keys.DELETE);
        $("[data-test-id=date] input.input__control").setValue(firstMeetingDate);
        $("[data-test-id=name] input").setValue(validUser.getName());
        $("[data-test-id=phone] input").setValue(validUser.getPhone());
        $("[data-test-id=agreement]").click();
        $(By.xpath("//*[text()='Запланировать']")).click();
        $("[data-test-id=success-notification] .notification__content").shouldBe(Condition.visible, Duration.ofSeconds(15));
        $("[data-test-id=success-notification] .notification__content").shouldHave(Condition.exactText("Встреча успешно запланирована на " + firstMeetingDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
        refresh();
        $("[data-test-id=city] input").setValue(validUser.getCity());
        $("[data-test-id=date] input.input__control").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id=date] input.input__control").sendKeys(Keys.DELETE);
        $("[data-test-id=date] input.input__control").setValue(secondMeetingDate);
        $("[data-test-id=name] input").setValue(validUser.getName());
        $("[data-test-id=phone] input").setValue(validUser.getPhone());
        $("[data-test-id=agreement]").click();
        $(By.xpath("//*[text()='Запланировать']")).click();
        //$("[data-test-id=replan-notification] .notification__content").shouldBe(Condition.visible, Duration.ofSeconds(15));
        $("[data-test-id=replan-notification] .notification__content").shouldHave(Condition.exactText("У вас уже запланирована встреча на другую дату. Перепланировать?\n" +
                        "\n" +
                        "Перепланировать"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
        $("[data-test-id=replan-notification] button").click();
        $("[data-test-id=success-notification] .notification__content").shouldHave(Condition.exactText("Встреча успешно запланирована на " + secondMeetingDate));

    }

    @Test
    void shouldTestSameDate() {
        int daysToAddForSecondMeeting = 4;
        String secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);
        $("[data-test-id=city] input").setValue(validUser.getCity());
        $("[data-test-id=date] input.input__control").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id=date] input.input__control").sendKeys(Keys.DELETE);
        $("[data-test-id=date] input.input__control").setValue(firstMeetingDate);
        $("[data-test-id=name] input").setValue(validUser.getName());
        $("[data-test-id=phone] input").setValue(validUser.getPhone());
        $("[data-test-id=agreement]").click();
        $(By.xpath("//*[text()='Запланировать']")).click();
        $("[data-test-id=success-notification] .notification__content").shouldBe(Condition.visible, Duration.ofSeconds(15));
        $("[data-test-id=success-notification] .notification__content").shouldHave(Condition.exactText("Встреча успешно запланирована на " + firstMeetingDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
        refresh();
        $("[data-test-id=city] input").setValue(validUser.getCity());
        $("[data-test-id=date] input.input__control").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id=date] input.input__control").sendKeys(Keys.DELETE);
        $("[data-test-id=date] input.input__control").setValue(secondMeetingDate);
        $("[data-test-id=name] input").setValue(validUser.getName());
        $("[data-test-id=phone] input").setValue(validUser.getPhone());
        $("[data-test-id=agreement]").click();
        $(By.xpath("//*[text()='Запланировать']")).click();
        $("[data-test-id=success-notification] .notification__content").shouldHave(Condition.exactText("Встреча успешно запланирована на " + secondMeetingDate));
    }
}
