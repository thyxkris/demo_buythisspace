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

    //profileCard__header__info W(100%)
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
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ei) {
                // Restore the interrupted status
                logger.info(ei.getMessage());
                Thread.currentThread().interrupt();
            }
            takeScreenShot();
            findButtonsForClick();
        }


    }

    public void clickToSeeMoreInfo() throws InterruptedException {

        try {
            while (driver.findElements(BTN_TO_SEE_MORE_INFO).size() < 2) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ei) {
                    // Restore the interrupted status
                    logger.info("clickToSeeMoreInfo() -- " + ei.getStackTrace());
                    Thread.currentThread().interrupt();
                }
                if (driver.findElements(By.xpath("//span[starts-with(text(),'Report')]")).size() > 0) {
                    logger.info("clickToSeeMoreInfo() finished");
                    return;
                }

            }
            logger.info("clickToSeeMoreInfo() clicking");
            driver.clickButton(driver.findElement(REC_BEFORE_MORE_INFO_SCREENSHOT).findElement(BTN_TO_SEE_MORE_INFO));//.c
            logger.info("clickToSeeMoreInfo() clicked");

            if (!driver.waitForElementVisible(By.xpath("//span[starts-with(text(),'Report')]"))) {
                //if the page doesn't change, click again
                clickToSeeMoreInfo();
            }
            logger.info("clickToSeeMoreInfo() finished");
            return;
        } catch (org.openqa.selenium.StaleElementReferenceException e) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ei) {
                // Restore the interrupted status
                logger.info(ei.getMessage());
                Thread.currentThread().interrupt();
            }
            logger.info("clickToSeeMoreInfo() -- " + e.getStackTrace());
            takeScreenShot();
            clickToSeeMoreInfo();
        } catch (java.lang.IndexOutOfBoundsException e) {
            //it means this is no data coming back
            logger.info(e.getMessage());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ei) {
                // Restore the interrupted status
                logger.info(ei.getMessage());
                Thread.currentThread().interrupt();
            }
            //takeScreenShot();
            clickToSeeMoreInfo();
        } catch (Exception e) {
            //it means this is no data coming back
            logger.info(e.getMessage());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ei) {
                // Restore the interrupted status
                logger.info(ei.getMessage());
                Thread.currentThread().interrupt();
            }
            //takeScreenShot();
            clickToSeeMoreInfo();
        }
    }

    public void clickButtonOf(String button) throws InterruptedException {
        try {
            findButtonsForClick();
            driver.clickButton(hashMapButtons.get(button));
        } catch (org.openqa.selenium.StaleElementReferenceException e) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ei) {
                // Restore the interrupted status
                logger.info(ei.getMessage());
                Thread.currentThread().interrupt();
            }
            takeScreenShot();
            findButtonsForClick();
            driver.clickButton(hashMapButtons.get(button));
        }
    }

    public void scrollWindowToBottom() throws InterruptedException {
        try {
            logger.info("scrollWindowToBottom() scrolling");
            driver.scrollViewToWebElement(By.xpath("//span[starts-with(text(),'Report')]"));
            logger.info("scrollWindowToBottom() scrolled");
        } catch (org.openqa.selenium.StaleElementReferenceException e) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ei) {
                // Restore the interrupted status
                logger.info(ei.getMessage());
                Thread.currentThread().interrupt();
            }
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
            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                // Restore the interrupted status
                Thread.currentThread().interrupt();
            }

        }
        //||driver.waitForElementInvisible(REC_MORE_INFO_SCREENSHOT)
    }

    public void clikeLikeButton() throws InterruptedException {


        waitTillPageLoad();
        //take screenshot

        driver.waitForElementVisible(REC_BEFORE_MORE_INFO_SCREENSHOT);
        while (this.hashMapButtons.size() < 5) {
            findButtonsForClick();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // Restore the interrupted status
                Thread.currentThread().interrupt();
            }
        }

        // logger.info(takeScreenShot(true, driver.findKElement(REC_BEFORE_MORE_INFO_SCREENSHOT),deviationX,deviationY, 0.6));

        //to see more info
        clickToSeeMoreInfo();
        String name_age = "";
        try {
            name_age = driver.findElement(By.cssSelector("div[class^='profileCard__header__info']")).getText().split("\\n")[0];
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
        } else {
            logger.info(takeScreenShot(true, false, null, 0, 0, 0.7, name_age));

        }
        Actions action = new Actions(driver);
        new Actions(driver).moveByOffset(100, 300).doubleClick().perform();

        //scroll to the bottom
        scrollWindowToBottom();

        //take screenshot
        driver.waitForElementVisible(REC_MORE_INFO_SCREENSHOT);
        String instagram_account = "";
        if (driver.findElements(By.cssSelector("div[class^='Pos(r)")).size() > 1) {
            //it has instagram
            //click the last button to see the link of instagram
            try {
                String instagram = "";
                int size = driver.findElements(By.cssSelector("div[class^='Pos(r)")).get(1).findElements(By.cssSelector("div[class^='bullet ']")).size();
                if (size == 0) {
                    // the link on the first page

                } else {
                    driver.clickButton(driver.findElements(By.cssSelector("div[class^='Pos(r)")).get(1).findElements(By.cssSelector("div[class^='bullet ']")).get(size - 1));
                }
                int size_a= driver.findElements(By.xpath("//a")).size();
                instagram = driver.findElements(By.xpath("//a")).get(size_a-1).getAttribute("href");
                instagram_account = instagram.replace("https://www.instagram.com/", "ins-");
                if(instagram_account.contains("inder"))
                {
                    for(KWebElement kWebElement: driver.findKElements(By.xpath("//a")))
                    {
                        logger.info("kWebElement.getAttribute(\"href\") "+kWebElement.getAttribute("href"));
                    }
                    logger.info("instagram_account : "+instagram_account);
                    instagram_account="";
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
        } else {
            logger.info(takeScreenShot(true, false, null, 0, 0, 0.7, name_age + instagram_account));

        }

        //like it
        clickButtonOf("like");

        return;
    }
}
