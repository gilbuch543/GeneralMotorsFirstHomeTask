import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HomePageTests extends BaseFrontEnd {
    public HomePage homePage;

    private String URL = "https://www.google.com/maps";//google maps in hebrew
    private String LOCATION = "Rome";


    @BeforeTest
    public void initialize() {
        super.initialize();
        homePage = PageFactory.initElements(driver, HomePage.class);
    }

    @AfterTest
//Test cleanup
    public void TeardownTest() {
        super.TeardownTest();
    }

    @Test
    public void TestVerifyButtonClickResult() {
        /**
         *  Create api on provider and verify it on PD
         *      Test steps:
         *          1. Open/Navigate to google maps
         *          2. Search for “Rome” and choose it
         *          3. Take a screenshot
         *          4. Zoom in
         *          5. Take a screenshot
         *          6. Zoom in
         *          7. Take a screenshot
         *          8. Zoom in
         *
         *
         */
        //  1. Open/Navigate to google maps
        homePage.navigate(URL);
        //  2. Search for “Rome” and choose it
        homePage.searchForLocation(LOCATION);
        homePage.clickOnSearchButton();
        //  3. Take a screenshot
        homePage.WaitForResults();
        TestUtils.takeScreenShot();
        //  4. Zoom in
        homePage.zoomIn();
        // 5. Take a screenshot
        homePage.WaitForResults();
        TestUtils.takeScreenShot();
        //  6. Zoom in
        homePage.zoomIn();
        // 7. Take a screenshot
        homePage.WaitForResults();
        TestUtils.takeScreenShot();
        //  8. Zoom in
        homePage.zoomIn();

    }


}
