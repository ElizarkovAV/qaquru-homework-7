package tests;

import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

public class RegistrationFormTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();

    String name = "User";
    String lastName = "Testov";
    String email = "test@email.com";
    String mobile = "7999999999";
    String subject = "Computer";
    String address = "Default city 2 Pushkin's Street";
    String state = "Haryana";
    String city = "Karnal";
    String gender = "Female";
    String hobby = "Reading";
    String date = "26 April,1992";
    String picName = "pic.jpg";

    @Test
    void fillRegistrationFormTest() {
        //act
        registrationPage.openPage()
                .setFirstName(name)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setUserNumber(mobile)
                .setDateOfBirth("26", "April", "1992")
                .setSubject(subject)
                .setHobby(hobby)
                .uploadImage("images/pic.jpg")
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
                .checkResult("Date of Birth", date)
                .checkResult("Subjects", subject)
                .checkResult("Hobbies", hobby)
                .checkResult("Picture", picName)
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
}