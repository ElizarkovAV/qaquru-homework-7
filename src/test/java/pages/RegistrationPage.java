package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.ResultsTable;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {
    private SelenideElement firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            emailInput = $("#userEmail"),
            genderWrapper = $("#genterWrapper"),
            userNumberInput = $("#userNumber"),
            calendarInput = $("#dateOfBirthInput"),
            subjectInput = $("#subjectsInput"),
            hobbyRadioBtn = $("#hobbiesWrapper"),
            uploadImageInput = $("#uploadPicture"),
            addressInput = $("#currentAddress"),
            stateTabs = $("#state"),
            stateInput = $("#stateCity-wrapper"),
            cityTabs = $("#city"),
            cityInput = $("#stateCity-wrapper"),
            submitBtn = $("#submit"),
            userForm = $("form#userForm");

    CalendarComponent calendarComponent = new CalendarComponent();

    public RegistrationPage openPage() {
        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }
    public RegistrationPage setFirstName (String value) {
        firstNameInput.setValue(value);
        return this;
    }
    public RegistrationPage setLastName (String value) {
        lastNameInput.setValue(value);
        return this;
    }

    public RegistrationPage setEmail(String value) {
        emailInput.setValue(value);
        return this;
    }
    public RegistrationPage setGender (String value) {
        genderWrapper.$(byText(value)).click();
        return this;
    }
    public RegistrationPage setUserNumber(String value) {
        userNumberInput.setValue(value);
        return this;
    }
    public RegistrationPage setDateOfBirth (String day, String month, String year) {
        calendarInput.click();
        calendarComponent.setDate(day, month, year);
        return this;
    }
    public RegistrationPage checkResult(String key, String value) {
        new ResultsTable().checkResult(key, value);
        return this;
    }

    public RegistrationPage setSubject(String value) {
        subjectInput.setValue(value).pressEnter();
        return this;
    }

    public RegistrationPage setHobby(String hobby) {
        hobbyRadioBtn.$(byText(hobby)).click();
        return this;
    }

    public RegistrationPage uploadImage(String value) {
        uploadImageInput.uploadFromClasspath("images/" + value);
        return this;
    }

    public RegistrationPage setAddress(String value) {
        addressInput.setValue(value);
        return this;
    }

    public RegistrationPage setState (String value) {
        stateTabs.scrollTo();
        stateTabs.click();
        stateInput.$(byText(value)).click();
        return this;
    }

    public RegistrationPage setCity (String value) {
        cityTabs.scrollTo();
        cityTabs.click();
        cityInput.$(byText(value)).click();
        return this;
    }

    public RegistrationPage submit() {
        submitBtn.click();
        return this;
    }

    public RegistrationPage checkUnsuccessfulValidation() {
        userForm.shouldHave(attribute("class", "was-validated"));
        return this;
    }

}