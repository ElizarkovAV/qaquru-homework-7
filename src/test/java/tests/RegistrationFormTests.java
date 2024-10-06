package tests;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import utils.RandomDataUtil;

public class RegistrationFormTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();
    RandomDataUtil random = new RandomDataUtil();

    String name = random.getRandomFirstName(),
            lastName = random.getRandomLastName(),
            email = random.getRandomEmail(),
            mobile = random.getRandomMobile(),
            subject = random.getRandomSubject(),
            address = random.getRandomAddress(),
            state = random.getRandomState(),
            city = random.getRandomCity(state),
            gender = random.getRandomGender(),
            hobby = random.getRandomHobby(),
            day = random.getRandomDay(),
            month = random.getRandomMonth(),
            year = random.getRandomYear(),
            picture = random.getRandomPicture();

    @Test
    void fillRegistrationFormTest() {
        //act
        registrationPage.openPage()
                .setFirstName(name)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setUserNumber(mobile)
                .setDateOfBirth(day, month, year)
                .setSubject(subject)
                .setHobby(hobby)
                .uploadImage(picture)
                .setAddress(address)
                .setState(state)
                .setCity(city)
                .submit();

        //asserts
        registrationPage
                .checkResult("Student Name", name + " " + lastName)
                .checkResult("Student Email", email)
                .checkResult("Gender", gender)
                .checkResult("Mobile", mobile)
                .checkResult("Date of Birth", day + " " + month + "," + year)
                .checkResult("Subjects", subject)
                .checkResult("Hobbies", hobby)
                .checkResult("Picture", picture)
                .checkResult("Address", address)
                .checkResult("State and City", state + " " + city);
    }

    //Проверка минимального количества введнных данных
    @Test
    public void minimalNumbersOfValuesTest() {
        //act
        registrationPage.openPage()
                .setFirstName(name)
                .setLastName(lastName)
                .setGender(gender)
                .setUserNumber(mobile)
                .submit();
        //assert
        registrationPage
                .checkResult("Student Name", name + " " + lastName)
                .checkResult("Gender", gender)
                .checkResult("Mobile", mobile);
    }

    //Попытка регистрации без заполнения фамилии
    @Test
    public void registrationWithoutLastname() {
        //act
        registrationPage.openPage()
                .setFirstName(name)
                .setGender(gender)
                .setUserNumber(mobile)
                .submit();

        //assert
        registrationPage.checkUnsuccessfulValidation();
    }

    @AfterEach
    void afterEach() {
        Selenide.closeWebDriver();
    }
}