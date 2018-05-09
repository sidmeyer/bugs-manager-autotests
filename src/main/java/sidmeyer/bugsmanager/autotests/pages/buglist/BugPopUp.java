package sidmeyer.bugsmanager.autotests.pages.buglist;

import net.thucydides.core.annotations.At;
import net.thucydides.core.annotations.findby.FindBy;
import net.thucydides.core.pages.WebElementFacade;
import org.openqa.selenium.WebDriver;
import sidmeyer.bugsmanager.autotests.common.Settings;
import sidmeyer.bugsmanager.autotests.pages.BugsManagerAppPage;

/**
 * Add/edit bug pop up window.
 */
@At(Settings.BUG_LIST_URL)
public class BugPopUp extends BugsManagerAppPage {

	public BugPopUp(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//label[text()='Done:']/following-sibling::div/input")
	public WebElementFacade doneCheckbox;

	@FindBy(xpath = "//label[text()='Name:']/following-sibling::div/input")
	public WebElementFacade nameField;

	@FindBy(xpath = "//label[text()='Notes:']/following-sibling::div/textarea")
	public WebElementFacade notesField;

	@FindBy(xpath = "//label[text()='Priority:']/following-sibling::div/input")
	public WebElementFacade priorityField;

	@FindBy(xpath = "//label[text()='Due:']/following-sibling::div/input")
	public WebElementFacade dueField;

	@FindBy(xpath = "//label[text()='Created at:']/following-sibling::*//div[contains(@id,'datefield')]//input")
	public WebElementFacade createdAtDateField;

	@FindBy(xpath = "//label[text()='Created at:']/following-sibling::*//div[contains(@id,'timefield')]//input")
	public WebElementFacade createdAtTimeField;

	@FindBy(xpath = "//label[text()='Updated at:']/following-sibling::*//div[contains(@id,'datefield')]//input")
	public WebElementFacade updatedAtDateField;

	@FindBy(xpath = "//label[text()='Updated at:']/following-sibling::*//div[contains(@id,'timefield')]//input")
	public WebElementFacade updatedAtTimeField;

	@FindBy(xpath = "//button//*[text()='OK']")
	public WebElementFacade okButton;

	@FindBy(xpath = "//button//*[text()='Cancel']")
	public WebElementFacade cancelButton;
}
