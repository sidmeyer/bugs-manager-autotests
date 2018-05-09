package sidmeyer.bugsmanager.autotests.pages;

import net.thucydides.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import net.thucydides.core.pages.WebElementFacade;
import org.openqa.selenium.WebDriver;
import sidmeyer.bugsmanager.autotests.common.Settings;

/**
 * Base page object for all pages.
 */
public class BugsManagerAppPage extends PageObject {

	public BugsManagerAppPage(WebDriver driver) {
		super(driver, Settings.AJAX_TIMEOUT);
	}

	@FindBy(className = "x-mask-loading")
	public WebElementFacade loadingImage;

	@FindBy(xpath = "//div[contains(@id,'messagebox')]//button//*[text()='Yes']")
	public WebElementFacade yesConfirmButton;

	@FindBy(xpath = "//div[contains(@id,'messagebox')]//button//*[text()='No']")
	public WebElementFacade noConfirmButton;

	@FindBy(css = "#msg-div")
	public WebElementFacade infoMessage;

	public String getInfoMessageText() {
		return infoMessage.getText();
	}
}
