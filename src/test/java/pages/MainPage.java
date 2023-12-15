package pages;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import elements.GroupTableRow;
import elements.StudentsTableRow;

import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    private final SelenideElement usernameLinkInNavBar = $("nav li.mdc-menu-surface--anchor a");
    private final SelenideElement createGroupButton = $("#create-btn");
    private final SelenideElement groupNameField = $x("//form//span[contains(text(), 'Group name')]/following-sibling::input");
    private final SelenideElement submitButtonOnModalWindow = $("form div.submit button");
    private final SelenideElement closeCreateGroupIcon = $x("//span[text()='Creating Study Group']" +
            "//ancestor::div[contains(@class, 'form-modal-header')]//button");
    private final ElementsCollection rowsInGroupTable = $$x("//table[@aria-label='Tutors list']/tbody/tr");
    private final SelenideElement createStudentsForm = $("div#generateStudentsForm-content input");
    private final SelenideElement saveCreateStudentsForm = $("div#generateStudentsForm-content div.submit button");
    private final SelenideElement closeCreateStudentsForm = $x("//h2[@id='generateStudentsForm-title']/../button");
    private final ElementsCollection rowsInStudentsTable = $$x("//table[@aria-label='User list']/tbody/tr");
    private final SelenideElement profileLinkInNavBar = $x("//nav//li[contains(@class,'mdc-menu-surface--anchor')]//span[text()='Profile']");


    //Students Table
    public void clickAddStudentIconOnGroupWithTitle(String title){
        getRowByTitle(title).clickAddStudentIcon();
    }

    public void clickZoomIconOnGroupWithTitle(String title){
        getRowByTitle(title).clickZoomIcon();
    }

    public void typeAmountOfStudentsInForm(int amount){
        createStudentsForm.should(visible).setValue(String.valueOf(amount));
    }

    public void clickSaveBtnOnCreateStudentsForm(){
        saveCreateStudentsForm.should(visible).click();
    }

    public void closeCreateStudentsModalWindow(){
        closeCreateStudentsForm.click();
    }

    public void clickTrashIconOnStudentWithName(String name) {
        getStudentRowName(name).clickTrashIcon();
    }

    public void clickRestoreFromTrashIconOnStudentWithName(String name) {
        getStudentRowName(name).clickRestoreFromTrashIcon();
    }

    public String getStatusOfStudent(String name){
        return getStudentRowName(name).getStatus();
    }

    public String getNameByIndex(int index){
        return rowsInStudentsTable.should(CollectionCondition.sizeGreaterThan(0))
                .asDynamicIterable().stream()
                .map(StudentsTableRow::new)
                .toList().get(index).getName();
    }

    private StudentsTableRow getStudentRowName(String name) {
        return rowsInStudentsTable.should(CollectionCondition.sizeGreaterThan(0))
                .asDynamicIterable().stream()
                .map(StudentsTableRow::new)
                .filter(row -> row.getName().equals(name))
                .findFirst().orElseThrow();
    }

//group
    public SelenideElement waitGroupTitleByText(String title) {
        return $x(String.format("//table[@aria-label='Tutors list']/tbody//td[text()='%s']", title)).should(visible);
    }

    public void createGroup(String groupName) {
        createGroupButton.should(visible).click();
        groupNameField.should(visible).setValue(groupName);
        groupNameField.should(value(groupName));
        submitButtonOnModalWindow.should(visible).click();
        waitGroupTitleByText(groupName);
    }

    public void closeCreateGroupModalWindow() {
        closeCreateGroupIcon.should(visible).click();
        closeCreateGroupIcon.should(Condition.hidden);
    }

    public String getUsernameLabelText() {
        return usernameLinkInNavBar.should(visible).getText().replace("\n", " ");

    }

    public void clickUsernameLabel() {
        usernameLinkInNavBar.should(visible).click();
    }

    public void clickProfileLink() {
        profileLinkInNavBar.should(visible).click();
    }

    public void clickTrashIconOnGroupWithTitle(String title) {
        getRowByTitle(title).clickTrashIcon();
    }

    public void clickRestoreFromTrashIconOnGroupWithTitle(String title) {
        getRowByTitle(title).clickRestoreFromTrashIcon();
    }

    public String getStatusOfGroupWithTitle(String title) {
        return getRowByTitle(title).getStatus();
    }

    private GroupTableRow getRowByTitle(String title) {
        return rowsInGroupTable.should(CollectionCondition.sizeGreaterThan(0))
                .asDynamicIterable().stream()
                .map(GroupTableRow::new)
                .filter(row -> row.getTitle().equals(title))
                .findFirst().orElseThrow();
    }

    public void waitStudentCount(String groupTestName, int studentCount) {
        getRowByTitle(groupTestName).waitCountStudents(studentCount);
    }
}
