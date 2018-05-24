package pages;

import infrastructure.KWebElement;
import libraries.infrastructure.ScenarioContext;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;

import java.util.HashMap;
import java.util.List;

/**
 * Created by makri on 4/09/2017.
 */
public class TinderPageModel extends BasePageModel {
    public TinderPageModel(ScenarioContext scenarioContext) throws Exception {
        super(scenarioContext, "Tinder");

        this.hashMapButtons = new HashMap();

        driver.waitForElementVisible(By.cssSelector("button[class^='button']"));
        List<KWebElement> btnList = scenarioContext.getWebDriver().findKElements(By.cssSelector("button[class^='button']"));
        //rewind, Nope, Like, Super Like, promote
        for (KWebElement webElement : btnList) {
            if (webElement.getAttribute("aria-label") != null) {
                hashMapButtons.put(webElement.getAttribute("aria-label").toLowerCase(), webElement);
            } else {
                hashMapButtons.put("promote", webElement);
            }
        }

    }

    private HashMap<String, KWebElement> hashMapButtons;
    private final By NAVIBAR = By.cssSelector("div[class^='desktopNavbar Pos']");
    private final By BTN_TO_SEE_MORE_INFO = By.xpath("//div[starts-with(@class,'recCard__openProfile')]");
    private final By REC_BEFORE_MORE_INFO_SCREENSHOT = By.cssSelector("div[class^='recsCardboard W']");
    private final By REC_MORE_INFO_SCREENSHOT = By.cssSelector("div[class^='profileCard__card']");

    private void findButtonsForClick() {
        if (this.hashMapButtons != null) {
            hashMapButtons.clear();
        }
        this.hashMapButtons = null;
        this.hashMapButtons = new HashMap();
        List<KWebElement> btnList = scenarioContext.getWebDriver().findKElements(By.cssSelector("button[class^='button']"));
        //rewind, Nope, Like, Super Like, promote
        try {
            for (KWebElement webElement : btnList) {
                if (webElement.getAttribute("aria-label") != null) {
                    hashMapButtons.put(webElement.getAttribute("aria-label").toLowerCase(), webElement);
                } else {
                    hashMapButtons.put("promote", webElement);
                }
            }
            driver.waitForElementClickable(hashMapButtons.get("like"));
        } catch (org.openqa.selenium.StaleElementReferenceException e) {
            sleep(1000);
            takeScreenShot();
            findButtonsForClick();
        }


    }

    public void clickToSeeMoreInfo() throws InterruptedException {

        try {
            while (driver.findElements(BTN_TO_SEE_MORE_INFO).size() < 2) {
                sleep(1000);
                if (driver.findElements(By.xpath("//span[starts-with(text(),'Report')]")).size() > 0) {
                    logger.info("clickToSeeMoreInfo() finished");
                    return;
                }
            }
    //        logger.info("clickToSeeMoreInfo() clicking");
            driver.clickButton(driver.findElement(REC_BEFORE_MORE_INFO_SCREENSHOT).findElement(BTN_TO_SEE_MORE_INFO));//.c
  //          logger.info("clickToSeeMoreInfo() clicked");

            if (!driver.waitForElementVisible(By.xpath("//span[starts-with(text(),'Report')]"))) {
                //if the page doesn't change, click again
                clickToSeeMoreInfo();
            }
            logger.info("clickToSeeMoreInfo() finished");
            return;
        } catch (org.openqa.selenium.StaleElementReferenceException e) {
            sleep(1000);
            logger.info(e.getMessage() + ' ' + e.getStackTrace());
            takeScreenShot();
            clickToSeeMoreInfo();
        } catch (java.lang.IndexOutOfBoundsException e) {
            //it means this is no data coming back
            logger.info(e.getMessage() + ' ' + e.getStackTrace());
            sleep(1000);
            //takeScreenShot();
            clickToSeeMoreInfo();
        } catch (Exception e) {
            //it means this is no data coming back
            logger.info(e.getMessage() + ' ' + e.getStackTrace());
            sleep(1000);
            //takeScreenShot();
            clickToSeeMoreInfo();
        }
    }

    public void clickButtonOf(String button) throws InterruptedException {
        try {
            findButtonsForClick();
            driver.clickButton(hashMapButtons.get(button));
        } catch (org.openqa.selenium.StaleElementReferenceException e) {
            sleep(1000);
            takeScreenShot();
            findButtonsForClick();
            driver.clickButton(hashMapButtons.get(button));
        }
    }

    public void scrollWindowToBottom() throws InterruptedException {
        try {
//            logger.info("scrollWindowToBottom() scrolling");
            driver.scrollViewToWebElement(By.xpath("//span[starts-with(text(),'Report')]"));
//            logger.info("scrollWindowToBottom() scrolled");
        } catch (org.openqa.selenium.StaleElementReferenceException e) {
            sleep(1000);
            takeScreenShot();
            driver.scrollViewToWebElement(By.xpath("//span[starts-with(text(),'Report')]"));
        } catch (NoSuchElementException nsee) {
            if (nsee.getMessage().contains("span[starts-with(text(),'Report')]")) {
                //potentially reason is the more info button is not successfully clicked
                logger.info("clickToSeeMoreInfo() should be clicked again");
                this.clickToSeeMoreInfo();
            } else {
                throw nsee;
            }
        }

    }


    private void waitTillPageLoad() throws InterruptedException {
        //wait till the page loaded completely
        logger.info("it seems no data yet, wait ....");
        while (driver.findElements(BTN_TO_SEE_MORE_INFO).size() < 2) {
            sleep(1000);
        }
    }

    public void clikeLikeButton() throws InterruptedException {

        waitTillPageLoad();
        driver.waitForElementVisible(REC_BEFORE_MORE_INFO_SCREENSHOT);
        while (this.hashMapButtons.size() < 5) {
            findButtonsForClick();
            sleep(10);
        }

        //to see more info
        clickToSeeMoreInfo();
        String name_age = "";
        try {
            String info = driver.findElement(By.cssSelector("div[class^='profileCard__header__info']")).getText();
            name_age = info.split("\\n")[0];
            logger.info("name age info " + info);
        } catch (Exception e) {
            logger.info("failed in getting her name and age");
            logger.info(e.getMessage() + e.getStackTrace());
        }
        if (driver.manage().window().getSize().getWidth() > 782) {
            int deviationX = driver.findKElement(NAVIBAR).getSize().getWidth();
            int deviationY = driver.findKElement(NAVIBAR).getSize().getHeight();
            try {


                //click the button and take fotos
                int size = driver.findElements(By.cssSelector("div[class^='Pos(r)")).get(0).findElements(By.cssSelector("div[class^='bullet ']")).size();
                if (size == 0) {
                    logger.info(takeScreenShot(true, false, driver.findKElement(REC_MORE_INFO_SCREENSHOT), deviationX, deviationY, 0.7, name_age));
                } else {
                    for (int i = 0; i < size; i++) {
                        driver.findElements(By.cssSelector("div[class^='bullet ']")).get(i).click();
                        logger.info(takeScreenShot(true, false, driver.findKElement(REC_MORE_INFO_SCREENSHOT), deviationX, deviationY, 0.7, name_age));
                    }
                }
            } catch (Exception e) {
                logger.info(takeScreenShot(true, false, driver.findKElement(REC_MORE_INFO_SCREENSHOT), deviationX, deviationY, 0.7, name_age));
                logger.info("failed in swiping fotos");
                logger.info(e.getMessage() + e.getStackTrace());
            }
        } else {//getWidth() <= 782

            logger.info(takeScreenShot(true, false, null, 0, 0, 0.7, name_age));
            int size = driver.findElements(By.cssSelector("div[class^='Pos(r)")).get(0).findElements(By.cssSelector("div[class^='bullet ']")).size();
            if (size > 1) {
                Actions action = new Actions(driver);
                for (int i = 1; i < size-1; i++) {
                    try {
                        action.moveToElement(driver.findElements(By.cssSelector("div[class^='Pos(r)")).get(0).findElements(By.cssSelector("div[class^='bullet ']")).get(size - 1)).click().click();
                        action.build().perform();
                        logger.info(takeScreenShot(true, false, null, 0, 0, 0.7, name_age));
                    }catch(java.lang.IndexOutOfBoundsException e)
                    {
                        logger.info("size " + driver.findElements(By.cssSelector("div[class^='Pos(r)")).get(0).findElements(By.cssSelector("div[class^='bullet ']")).size());
                        logger.info(" index "+ String.valueOf(size -1));
                    }
                }
            }

        }
        //scroll to the bottom
       // logger.info(name_age + ' ' + driver.findKElement(By.cssSelector("div[class^='Px")).getText());
        scrollWindowToBottom();

        //take screenshot
        driver.waitForElementVisible(REC_MORE_INFO_SCREENSHOT);
        String instagram_account = "";


        if (driver.findKElements(By.cssSelector("div[class^='Px")).size() > 0) {

            //it has more info
            //click the last button to see the link of instagram
            boolean hasInstagram = false;
            try {
                String instagram = "";

                //int size_medium= driver.findElements(By.cssSelector("div[class^='Fw")).size();
                for (KWebElement kWebElement : driver.findKElements(By.cssSelector("div[class^='Px"))) {
                    logger.info(name_age + ' ' + kWebElement.getText());
                    if (kWebElement.getText().contains("Instagram Photos")) {
                        hasInstagram=true;
                        int size = kWebElement.findKElements(By.cssSelector("div[class^='bullet ']")).size();
                        if (size == 0) {
                            // the link on the first page

                        } else {
                            driver.clickButton(kWebElement.findKElements(By.cssSelector("div[class^='bullet ']")).get(size - 1));
                        }
                    }
                }
                // int size = driver.findElements(By.cssSelector("div[class^='Pos(r)")).get(1).findElements(By.cssSelector("div[class^='bullet ']")).size();
                if(hasInstagram){
                int size_a = driver.findElements(By.xpath("//a")).size();
                instagram = driver.findElements(By.xpath("//a")).get(size_a - 1).getAttribute("href");
                instagram_account = instagram.replace("https://www.instagram.com/", "ins-");
                logger.info("instagram: " + instagram);
                if (instagram_account.contains("https://tinder.com/app/recs")) {
                    for (KWebElement kWebElement : driver.findKElements(By.xpath("//a"))) {
                        logger.info("kWebElement.getAttribute(\"href\") " + kWebElement.getAttribute("href"));
                    }
                    logger.info("instagram_account : " + instagram_account);
                    instagram_account = "";
                }
                }
            } catch (Exception e) {
                logger.info(name_age + " fails in getting instagram info though it seems she has one");
                logger.info(e.getMessage() + e.getStackTrace());
            }
        }
        if (driver.manage().window().getSize().getWidth() > 782) {
            int deviationX = driver.findKElement(NAVIBAR).getSize().getWidth();
            int deviationY = driver.findKElement(NAVIBAR).getSize().getHeight();
            logger.info(takeScreenShot(true, false, driver.findKElement(REC_MORE_INFO_SCREENSHOT), deviationX, deviationY, 0.7, name_age + instagram_account));
        } else {////getWidth() <= 782
            logger.info(takeScreenShot(true, false, null, 0, 0, 0.7, name_age + instagram_account));

        }

        //like it
        clickButtonOf("like");

        return;
    }
}
