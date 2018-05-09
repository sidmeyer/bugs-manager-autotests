package sidmeyer.bugsmanager.autotests.pages.buglist;

import net.thucydides.core.annotations.findby.FindBy;
import net.thucydides.core.pages.WebElementFacade;
import org.openqa.selenium.WebDriver;
import sidmeyer.bugsmanager.autotests.pages.BugsManagerAppPage;

/**
 * Search bug pop up window.
 */
public class SearchPopUp extends BugsManagerAppPage {

	public SearchPopUp(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[contains(@name,'_attr')]")
	public WebElementFacade attributeField;

	@FindBy(xpath = "//input[contains(@name,'_attr')]/following-sibling::div")
	public WebElementFacade expandAttributesListButton;

	@FindBy(xpath = "//input[contains(@name,'_operator')]")
	public WebElementFacade operatorsField;

	@FindBy(xpath = "//input[contains(@name,'_operator')]/following-sibling::div")
	public WebElementFacade expandOperatorsListButton;

	@FindBy(xpath = "//input[contains(@name,'_value')]")
	public WebElementFacade valueField;

	@FindBy(xpath = "//div[@id='bugs__search_form']//button//*[text()='Search']")
	public WebElementFacade searchButton;

	@FindBy(xpath = "//div[@id='bugs__search_form']//button//*[text()='Cancel']")
	public WebElementFacade cancelButton;

	@FindBy(xpath = "//div[@id='bugs__search_form']//button//*[text()='Clear']")
	public WebElementFacade clearButton;

}
