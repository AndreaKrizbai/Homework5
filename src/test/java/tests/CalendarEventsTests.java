package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CalendarEventsPage;
import pages.LoginPage;
import utilities.BrowserUtils;
import utilities.Driver;

import java.util.List;

public class CalendarEventsTests extends AbstractTestBase {
    public WebDriver driver = Driver.getDriver();

    /*
    * Test Case #1
    * 1. Go to “https://qa1.vytrack.com/"
    * 2. Login as a store manager
    * 3. Navigate to “Activities -> Calendar Events”
    * 4. Hover on three dots “…” for “Testers meeting” calendar event
    * 5. Verify that “view”, “edit” and “delete” options are available
    */
    @Test
    public void test1(){
        test = report.createTest("Verify that “view”, “edit” and “delete” options are available");
        calendarEventsPage.hoverOverThreeDots();
        Assert.assertTrue(calendarEventsPage.view.isDisplayed());
        Assert.assertTrue(calendarEventsPage.edit.isDisplayed());
        Assert.assertTrue(calendarEventsPage.delete.isDisplayed());
        test.pass("View, edit and delete options are verified");
    }

    /*
     * Test Case #2
     * 1. Go to “https://qa1.vytrack.com/"
     * 2. Login as a store manager
     * 3. Navigate to “Activities -> Calendar Events”
     * 4. Click on “Grid Options” button
     * 5. Deselect all options except “Title”
     * 6. Verify that “Title” column still displayed
     */
    @Test
    public void test2(){
        test = report.createTest("Verify that “Title” column still displayed");
        calendarEventsPage.gridIcon.click();
        List<WebElement> gridOptions = driver.findElements(By.xpath("//*[@data-role='renderable']"));
        for (int i = 1; i < gridOptions.size(); i++) {
          gridOptions.get(i).click();
            BrowserUtils.wait(1);
        }
        Assert.assertTrue(calendarEventsPage.titleColumn.isDisplayed());
        test.pass("Title column is verified");
    }

    /**
     * Test Case #3
     * 1. Go to “https://qa1.vytrack.com/"
     * 2. Login as a store manager
     * 3. Navigate to “Activities -> Calendar Events”
     * 4. Click on “Create Calendar Event” button
     * 5. Expand “Save And Close” menu
     * 6. Verify that “Save And Close”, “Save And New” and “Save” options are available
     */
    @Test
    public void test3(){
        test = report.createTest("Verify that “Save And Close”, “Save And New” and “Save” options are available");
        calendarEventsPage.clickToCreateCalendarEvent();
        calendarEventsPage.saveAndCloseDropdown.click();
        Assert.assertTrue(calendarEventsPage.saveAndClose1.isDisplayed());
        Assert.assertTrue(calendarEventsPage.saveAndNew2.isDisplayed());
        Assert.assertTrue(calendarEventsPage.save3.isDisplayed());
        test.pass("“Save And Close”, “Save And New” and “Save” options are verified");
    }

    /*
     * Test Case #4
     * 1. Go to “https://qa1.vytrack.com/"
     * 2. Login as a store manager
     * 3. Navigate to “Activities -> Calendar Events”
     * 4. Click on “Create Calendar Event” button
     * 5. Then, click on “Cancel” button
     * 6. Verify that “All Calendar Events” page subtitle is displayed
     */
    @Test
    public void test4(){
        test = report.createTest("Verify that “All Calendar Events” page subtitle is displayed");
        calendarEventsPage.clickToCreateCalendarEvent();
        calendarEventsPage.cancelButton.click();
        Assert.assertTrue(calendarEventsPage.allCalendarEventsTitle.isDisplayed());
        test.pass("All Calendar Events verified");
    }

    /**
     * Test Case #5
     * 1. Go to “https://qa1.vytrack.com/"
     * 2. Login as a store manager
     * 3. Navigate to “Activities -> Calendar Events”
     * 4. Click on “Create Calendar Event” button
     * 5. Verify that difference between end and start time is exactly 1 hour
     */
    @Test
    public void test5(){
        test = report.createTest("Verify that difference between end and start time is exactly 1 hour");
        calendarEventsPage.clickToCreateCalendarEvent();
        Assert.assertTrue(calendarEventsPage.timeDiff()==1);
        test.pass("Difference between end and start time being exactly one hour is verified");
    }

    /*
     * Test Case #6
     * 1. Go to “https://qa1.vytrack.com/"
     * 2. Login as a store manager
     * 3. Navigate to “Activities -> Calendar Events”
     * 4. Click on “Create Calendar Event” button
     * 5. Select “9:00 PM” as a start time
     * 6. Verify that end time is equals to “10:00 PM”
     */
    @Test
    public void test6(){
        test = report.createTest("Verify that end time equals to “10:00 PM”");
        calendarEventsPage.clickToCreateCalendarEvent();
        calendarEventsPage.startTime.click();
        Actions actions = new Actions(driver);
        BrowserUtils.wait(3);
        actions.moveToElement(calendarEventsPage.time900Pm).click().perform();
        BrowserUtils.wait(2);
        Assert.assertEquals(calendarEventsPage.getEndTime(), "10:00 PM");
        test.pass("End time equaling to 10PM is verified");
    }

    /**
     * Test Case #7
     * 1. Go to “https://qa1.vytrack.com/"
     * 2. Login as a store manager
     * 3. Navigate to “Activities -> Calendar Events”
     * 4. Click on “Create Calendar Event” button
     * 5. Select “All-Day Event” checkbox
     * 6. Verify that “All-Day Event” checkbox is selected
     * 7. Verify that start and end time input boxes are not displayed
     * 8. Verify that start and end date input boxes are displayed
     */
    @Test
    public void test7(){
        test = report.createTest("Verify that “All-Day Event” checkbox is selected, start and end time input boxes are not displayed, start and end date input boxes are displayed”");
        calendarEventsPage.clickToCreateCalendarEvent();
        calendarEventsPage.allDayCheckbox.click();
        BrowserUtils.wait(3);
        Assert.assertTrue(calendarEventsPage.allDayCheckbox.isSelected());
        Assert.assertFalse(calendarEventsPage.startTime.isDisplayed());
        Assert.assertFalse(calendarEventsPage.endTime.isDisplayed());
        Assert.assertTrue(calendarEventsPage.startDate.isDisplayed());
        Assert.assertTrue(calendarEventsPage.endDate.isDisplayed());
        test.pass("All Day Event actions are verified");
    }

    /**
     * Test Case #8
     * 1. Go to “https://qa1.vytrack.com/"
     * 2. Login as a store manager
     * 3. Navigate to “Activities -> Calendar Events”
     * 4. Click on “Create Calendar Event” button
     * 5. Select “Repeat” checkbox
     * 6. Verify that “Repeat” checkbox is selected
     * 7. Verify that “Daily” is selected by default and following options are available in “Repeats” drop-down:
     */
    @Test
    public void test8(){
        test = report.createTest("Verify repeat checkbox is selected and options are displayed");
        calendarEventsPage.clickToCreateCalendarEvent();
        calendarEventsPage.repeatCheckbox.click();
        Assert.assertTrue(calendarEventsPage.repeatCheckbox.isSelected());
        BrowserUtils.wait(1);
        Select select = new Select(calendarEventsPage.repeatDropdown);
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Daily");
        List<WebElement>repeats = select.getOptions();
        Assert.assertEquals(repeats.get(0).getText(), "Daily");
        Assert.assertEquals(repeats.get(1).getText(), "Weekly");
        Assert.assertEquals(repeats.get(2).getText(), "Monthly");
        Assert.assertEquals(repeats.get(3).getText(), "Yearly");
        test.pass("Repeats verified");
    }

    /**
     * Test Case #9
     * 1. Go to “https://qa1.vytrack.com/"
     * 2. Login as a store manager
     * 3. Navigate to “Activities -> Calendar Events”
     * 4. Click on “Create Calendar Event” button
     * 5. Select “Repeat” checkbox
     * 6. Verify that “Repeat” checkbox is selected
     * 7. Verify that “Repeat Every” radio button is selected
     * 8. Verify that “Never” radio button is selected as an “Ends” option.
     * 9. Verify that following message as a summary is displayed: “Summary: Daily every 1 day”
     */
    @Test
    public void test9(){
        test = report.createTest("Verify repeat checkbox is selected, summary displayed, radio buttons selected");
        calendarEventsPage.clickToCreateCalendarEvent();
        calendarEventsPage.repeatCheckbox.click();
        Assert.assertTrue(calendarEventsPage.repeatCheckbox.isSelected());
        Assert.assertTrue(calendarEventsPage.repeatEveryRadioBtn.isSelected());
        Assert.assertTrue(calendarEventsPage.endsNeverRadioBtn.isSelected());
        Assert.assertEquals(calendarEventsPage.summaryPart1.getText()+" "+calendarEventsPage.summaryPart2.getText(), "Summary: Daily every 1 day");
        test.pass("Repeats summary and buttons verified");
    }

    /**
     * Test Case #10
     * 1. Go to “https://qa1.vytrack.com/"
     * 2. Login as a store manager
     * 3. Navigate to “Activities -> Calendar Events”
     * 4. Click on “Create Calendar Event” button
     * 5. Select “Repeat” checkbox
     * 6. Select “After 10 occurrences” as an “Ends” option.
     * 7. Verify that following message as a summary is displayed: “Summary: Daily every 1 day, end after 10 occurrences”
     */
    @Test
    public void test10(){
        test = report.createTest("erify that following message as a summary is displayed: “Summary: Daily every 1 day, end after 10 occurrences");
        calendarEventsPage.clickToCreateCalendarEvent();
        calendarEventsPage.repeatCheckbox.click();
        calendarEventsPage.afterRadioBtn.click();
        calendarEventsPage.occurrencesBox.sendKeys("10", Keys.ENTER);
        Assert.assertEquals(calendarEventsPage.summaryPart1.getText()+" "+calendarEventsPage.summaryPart2.getText() + calendarEventsPage.summaryPart3.getText(),
                "Summary: Daily every 1 day, end after 10 occurrences");
        test.pass("10 occurrences verified");
    }

    /**
     * Test Case #11
     * 1. Go to “https://qa1.vytrack.com/"
     * 2. Login as a store manager
     * 3. Navigate to “Activities -> Calendar Events”
     * 4. Click on “Create Calendar Event” button
     * 5. Select “Repeat” checkbox
     * 6. Select “By Nov 18, 2021” as an “Ends” option.
     * 7. Verify that following message as a summary is displayed: “Summary: Daily every 1 day, end by Nov 18, 2021”
     */
    @Test
    public void test11(){

    }

    /**
     * Test Case #12
     * 1. Go to “https://qa1.vytrack.com/"
     * 2. Login as a store manager
     * 3. Navigate to “Activities -> Calendar Events”
     * 4. Click on “Create Calendar Event” button
     * 5. Select “Repeat” checkbox
     * 6. Select “Weekly” options as a “Repeat” option
     * 7. Select “Monday and Friday” options as a “Repeat On” options
     * 8. Verify that “Monday and Friday” options are selected
     * 9. Verify that following message as a summary is displayed: “Summary: Weekly every 1 week on Monday, Friday”
     */
    @Test
    public void test12(){

    }


}
