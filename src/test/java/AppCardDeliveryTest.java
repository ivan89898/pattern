import com.codeborne.selenide.Condition;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Selenide.*;

public class AppCardDeliveryTest {
    private static Faker faker;

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    String planningDate = generateDate(4);

    @BeforeAll
    static void setUpAll() {
        faker = new Faker(new Locale("ru"));
    }


    {
        open("http://localhost:9999");
    }

    @Test
    void shouldTestV3() {
        $("[data-test-id=city] input").setValue("Екатеринбург");
        $("[data-test-id=date] input.input__control").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id=date] input.input__control").sendKeys(Keys.DELETE);
        $("[data-test-id=date] input.input__control").setValue(planningDate);
        $("[data-test-id=name] input").setValue(faker.name().fullName());
        $("[data-test-id=phone] input").setValue(faker.phoneNumber().phoneNumber());
        $("[data-test-id=agreement]").click();
        $(By.xpath("//*[text()='Запланировать']")).click();
        $("[data-test-id=success-notification]").shouldBe(Condition.visible, Duration.ofSeconds(15));
        $("[data-test-id=success-notification]").shouldHave(Condition.text("Успешно!\n" +
                        "Встреча успешно запланирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
        refresh();
        $("[data-test-id=city] input").setValue("Екатеринбург");
        $("[data-test-id=date] input.input__control").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id=date] input.input__control").sendKeys(Keys.DELETE);
        $("[data-test-id=date] input.input__control").setValue(planningDate);
        $("[data-test-id=name] input").setValue(faker.name().fullName());
        $("[data-test-id=phone] input").setValue(faker.phoneNumber().phoneNumber());
        $("[data-test-id=agreement]").click();
        $(By.xpath("//*[text()='Запланировать']")).click();
        $("[data-test-id=success-notification]").shouldBe(Condition.visible, Duration.ofSeconds(15));
        $("[data-test-id=success-notification]").shouldHave(Condition.text("Успешно!\n" +
                        "Встреча успешно запланирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);

    }

    @Test
    void shouldTestV4SameAll() {
        $("[data-test-id=city] input").setValue("Екатеринбург");
        $("[data-test-id=date] input.input__control").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id=date] input.input__control").sendKeys(Keys.DELETE);
        $("[data-test-id=date] input.input__control").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Иван Иванович");
        $("[data-test-id=phone] input").setValue("79999999667");
        $("[data-test-id=agreement]").click();
        $(By.xpath("//*[text()='Запланировать']")).click();
        $("[data-test-id=success-notification]").shouldBe(Condition.visible, Duration.ofSeconds(15));
        $("[data-test-id=success-notification]").shouldHave(Condition.text("Успешно!\n" +
                        "Встреча успешно запланирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
        refresh();
        $("[data-test-id=city] input").setValue("Екатеринбург");
        $("[data-test-id=date] input.input__control").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id=date] input.input__control").sendKeys(Keys.DELETE);
        $("[data-test-id=date] input.input__control").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Иван Иванович");
        $("[data-test-id=phone] input").setValue("79999999667");
        $("[data-test-id=agreement]").click();
        $(By.xpath("//*[text()='Запланировать']")).click();
        $("[data-test-id=success-notification]").shouldBe(Condition.visible, Duration.ofSeconds(15));
        $("[data-test-id=success-notification]").shouldHave(Condition.text("Успешно!\n" +
                        "Встреча успешно запланирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);

    }

    @Test
    void shouldTestV5SameAllNewDate() {
        $("[data-test-id=city] input").setValue("Екатеринбург");
        $("[data-test-id=date] input.input__control").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id=date] input.input__control").sendKeys(Keys.DELETE);
        $("[data-test-id=date] input.input__control").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Иван ван");
        $("[data-test-id=phone] input").setValue("79555555533");
        $("[data-test-id=agreement]").click();
        $(By.xpath("//*[text()='Запланировать']")).click();
        $("[data-test-id=success-notification]").shouldBe(Condition.visible, Duration.ofSeconds(15));
        $("[data-test-id=success-notification]").shouldHave(Condition.text("Успешно!\n" +
                        "Встреча успешно запланирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
        refresh();
        $("[data-test-id=city] input").setValue("Екатеринбург");
        $("[data-test-id=date] input.input__control").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id=date] input.input__control").sendKeys(Keys.DELETE);
        $("[data-test-id=date] input.input__control").setValue("25092022");
        $("[data-test-id=name] input").setValue("Иван ван");
        $("[data-test-id=phone] input").setValue("79555555533");
        $("[data-test-id=agreement]").click();
        $(By.xpath("//*[text()='Запланировать']")).click();
        $("[data-test-id=replan-notification]").shouldBe(Condition.visible, Duration.ofSeconds(15));
        $("[data-test-id=replan-notification]").shouldHave(Condition.text("Необходимо подтверждение\n" +
                        "У вас уже запланирована встреча на другую дату. Перепланировать?\n" +
                        "\n" +
                        "Перепланировать"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);

    }

}
