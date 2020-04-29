package pages;

import org.openqa.selenium.interactions.Actions;
import utilities.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CalendarEventsPage extends AbstractPageBase {

    @FindBy (css = "[title='Create Calendar event']")
    private WebElement createCalendarEvent;

    @FindBy(className = "select2-chosen")
    private WebElement owner;

    @FindBy(css = "[id^='date_selector_oro_calendar_event_form_start']")
    public WebElement startDate;

    @FindBy(css = "[id^='time_selector_oro_calendar_event_form_start']")
    public WebElement startTime;

    @FindBy(css = "[id^='time_selector_oro_calendar_event_form_end']")
    public WebElement endTime;

    @FindBy(css = "[id^='date_selector_oro_calendar_event_form_end']")
    public WebElement endDate;

    @FindBy(className = "grid-header-cell__label")
    private List<WebElement> columnNames;

    @FindBy(css = "iframe[id^='oro_calendar_event_form_description-uid']")
    private WebElement descriptionFrame;

    @FindBy(css = "[id^='oro_calendar_event_form_title-uid']")
    private WebElement title;

    @FindBy(id = "tinymce")
    private WebElement descriptionTextArea;

    @FindBy(css = "[class='btn-group pull-right'] > button")
    private WebElement saveAndClose;

    @FindBy(xpath = "(//div[@class='control-label'])[1]")
    private WebElement generalInfoTitle;

    @FindBy(xpath = "//label[text()='Description']/following-sibling::div//div")
    private WebElement generalInfoDescription;

    public void enterCalendarEventTitle(String titleValue){
        BrowserUtils.waitForPageToLoad(25);
        wait.until(ExpectedConditions.visibilityOf(title)).sendKeys(titleValue);
        BrowserUtils.wait(2);
    }

    public void enterCalendarEventDescription(String description){
        BrowserUtils.waitForPageToLoad(25);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(descriptionFrame));
        descriptionTextArea.sendKeys(description);
        driver.switchTo().defaultContent();//exit from the frame
        BrowserUtils.wait(2);
    }

    public void clickOnSaveAndClose(){
        BrowserUtils.waitForPageToLoad(25);
        wait.until(ExpectedConditions.elementToBeClickable(saveAndClose)).click();
    }

    public String getGeneralInfoTitleText(){
        BrowserUtils.waitForPageToLoad(25);
        return generalInfoTitle.getText();
    }

    public String getGeneralInfoDescriptionText(){
        BrowserUtils.waitForPageToLoad(25);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[text()='Description']/following-sibling::div//div")));
        return generalInfoDescription.getText();
    }
//####################################################################

    public List<String>getColumnNames(){
        BrowserUtils.waitForPageToLoad(25);
        return BrowserUtils.getTextFromWebElements(columnNames);
    }

    public String getOwnerName(){
        BrowserUtils.waitForPageToLoad(25);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("select2-chosen")));
        wait.until(ExpectedConditions.visibilityOf(owner));
        return owner.getText().trim();
    }

    public void clickToCreateCalendarEvent(){
        BrowserUtils.waitForPageToLoad(25);
        wait.until(ExpectedConditions.elementToBeClickable(createCalendarEvent)).click();
        BrowserUtils.waitForPageToLoad(25);
        BrowserUtils.wait(2);
    }

    public String getStartDate(){
        BrowserUtils.waitForPageToLoad(25);
        wait.until(ExpectedConditions.visibilityOf(startDate));
        //BrowserUtils.scrollTo(startDate);
        return startDate.getAttribute("value");
    }

    public String getStartTime(){
        BrowserUtils.waitForPageToLoad(25);
        wait.until(ExpectedConditions.visibilityOf(startTime));
        return startTime.getAttribute(("value"));
    }

    public String getEndTime(){
        BrowserUtils.waitForPageToLoad(25);
        wait.until(ExpectedConditions.visibilityOf(endTime));
        return endTime.getAttribute(("value"));
    }
//###############################################################################################################
    @FindBy(xpath = "//td[text()='Testers Meeting']/following-sibling::td//a[text()='...']")
    private WebElement threeDots;

    @FindBy(xpath ="//*[@href='/calendar/event/view/1846']")
    public WebElement view;
    @FindBy(xpath = "//*[@href='/calendar/event/update/1846']")
    public WebElement edit;
    @FindBy(xpath = "//*[@href='/calendar/event/update/1846']/../following-sibling::*//a")
    public WebElement delete;

    @FindBy(xpath = "//*[@title='Grid Settings']")
    public WebElement gridIcon;

    @FindBy(xpath = "//span[text()='Title']")
    public WebElement titleColumn;

    @FindBy(xpath = "(//*[@data-toggle='dropdown'])[4]")
    public WebElement saveAndCloseDropdown;

    @FindBy(xpath = "//ul//li//button[contains(text(),'Save and Close')]")
    public WebElement saveAndClose1;
    @FindBy(xpath = "//ul//li//button[contains(text(),'Save and New')]")
    public WebElement saveAndNew2;
    @FindBy(xpath = "(//ul//li//button[contains(text(),'Save')])[3]")
    public WebElement save3;

    @FindBy(xpath = "//a[@title='Cancel']")
    public WebElement cancelButton;
    @FindBy(className = "oro-subtitle")
    public WebElement allCalendarEventsTitle;

    @FindBy(xpath = "//li[text()='9:00 PM']")
    public WebElement time900Pm;

    @FindBy(css = "[id^='oro_calendar_event_form_allDay-uid']")
    public WebElement allDayCheckbox;

    @FindBy(css = "input[id^='recurrence-repeat']")
    public WebElement repeatCheckbox;

    @FindBy(css = "select[id^='recurrence-repeat']")
    public WebElement repeatDropdown;

    @FindBy(xpath = "//input[@checked='checked']")
    public WebElement repeatEveryRadioBtn;

    @FindBy(xpath = "(//input[@type='radio'])[3]")
    public WebElement endsNeverRadioBtn;

    @FindBy(xpath = "//label[text()='Summary:']")
    public WebElement summaryPart1;

    @FindBy(xpath = "//span[text()='Daily every 1 day']")
    public WebElement summaryPart2;

    @FindBy(xpath = "(//input[@type='radio'])[4]")
    public WebElement afterRadioBtn;

    @FindBy(xpath = "//*[@data-related-field='occurrences']")
    public WebElement occurrencesBox;

    @FindBy(xpath = "//span[text()=', end after 10 occurrences']")
    public WebElement summaryPart3;


    public void hoverOverThreeDots(){
      Actions actions = new Actions(driver);
        BrowserUtils.waitForPageToLoad(25);
      actions.moveToElement(threeDots).pause(2000).perform();
    }

    public Integer timeDiff(){
        int startHour = Integer.parseInt(getStartTime().split(":")[0]);
        int endHour = Integer.parseInt(getEndTime().split(":")[0]);
        if(startHour==12){
            startHour-=12;
        }
        return endHour-startHour;
    }

}
