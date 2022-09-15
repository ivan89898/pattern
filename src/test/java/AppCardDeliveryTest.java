import com.codeborne.selenide.Condition;
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

    //  @Test
    //  void shouldTestV4SameAll() {
    //   $("[data-test-id=city] input").setValue("Екатеринбург");
    //    $("[data-test-id=date] input.input__control").sendKeys(Keys.CONTROL + "a");
    //   $("[data-test-id=date] input.input__control").sendKeys(Keys.DELETE);
    //   $("[data-test-id=date] input.input__control").setValue(planningDate);
    //   $("[data-test-id=name] input").setValue("Иван Иванович");
    //   $("[data-test-id=phone] input").setValue("79999999667");
    //   $("[data-test-id=agreement]").click();
    //   $(By.xpath("//*[text()='Запланировать']")).click();
    //   $("[data-test-id=success-notification]").shouldBe(Condition.visible, Duration.ofSeconds(15));
    //  $("[data-test-id=success-notification]").shouldHave(Condition.text("Успешно!\n" +
    //                 "Встреча успешно запланирована на " + planningDate), Duration.ofSeconds(15))
    //       .shouldBe(Condition.visible);
    // refresh();
    //  $("[data-test-id=city] input").setValue("Екатеринбург");
    //    $("[data-test-id=date] input.input__control").sendKeys(Keys.CONTROL + "a");
    //$("[data-test-id=date] input.input__control").sendKeys(Keys.DELETE);
    //  $("[data-test-id=date] input.input__control").setValue(planningDate);
    //  $("[data-test-id=name] input").setValue("Иван Иванович");
    //  $("[data-test-id=phone] input").setValue("79999999667");
    //   $("[data-test-id=agreement]").click();
    //  $(By.xpath("//*[text()='Запланировать']")).click();
    //   $("[data-test-id=success-notification]").shouldBe(Condition.visible, Duration.ofSeconds(15));
    //  $("[data-test-id=success-notification]").shouldHave(Condition.text("Успешно!\n" +
    //              "Встреча успешно запланирована на " + planningDate), Duration.ofSeconds(15))
    //     .shouldBe(Condition.visible);

    // }

    //@Test
    // void shouldTestV5SameAllNewDate() {
    //   $("[data-test-id=city] input").setValue("Екатеринбург");
    //    $("[data-test-id=date] input.input__control").sendKeys(Keys.CONTROL + "a");
    //     $("[data-test-id=date] input.input__control").sendKeys(Keys.DELETE);
    //   $("[data-test-id=date] input.input__control").setValue(planningDate);
    //   $("[data-test-id=name] input").setValue("Иван ван");
    //   $("[data-test-id=phone] input").setValue("79555555533");
    //  $("[data-test-id=agreement]").click();
    //  $(By.xpath("//*[text()='Запланировать']")).click();
    //  $("[data-test-id=success-notification]").shouldBe(Condition.visible, Duration.ofSeconds(15));
    //  $("[data-test-id=success-notification]").shouldHave(Condition.text("Успешно!\n" +
    //                 "Встреча успешно запланирована на " + planningDate), Duration.ofSeconds(15))
    //      .shouldBe(Condition.visible);
    // refresh();
    //  $("[data-test-id=city] input").setValue("Екатеринбург");
    // $("[data-test-id=date] input.input__control").sendKeys(Keys.CONTROL + "a");
    // $("[data-test-id=date] input.input__control").sendKeys(Keys.DELETE);
    // $("[data-test-id=date] input.input__control").setValue("25092022");
    // $("[data-test-id=name] input").setValue("Иван ван");
    //  $("[data-test-id=phone] input").setValue("79555555533");
    // $("[data-test-id=agreement]").click();
    //$(By.xpath("//*[text()='Запланировать']")).click();
    // $("[data-test-id=replan-notification]").shouldBe(Condition.visible, Duration.ofSeconds(15));
    //  $("[data-test-id=replan-notification]").shouldHave(Condition.text("Необходимо подтверждение\n" +
    //                "У вас уже запланирована встреча на другую дату. Перепланировать?\n" +
    //             "\n" +
    //            "Перепланировать"), Duration.ofSeconds(15))
    //    .shouldBe(Condition.visible);

    //}

}
