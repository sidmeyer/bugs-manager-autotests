package sidmeyer.bugsmanager.autotests.pages.buglist;

import net.thucydides.core.annotations.At;
import net.thucydides.core.annotations.findby.By;
import net.thucydides.core.annotations.findby.FindBy;
import net.thucydides.core.pages.WebElementFacade;
import net.thucydides.core.pages.components.HtmlTable;
import org.openqa.selenium.WebDriver;
import sidmeyer.bugsmanager.autotests.common.Settings;
import sidmeyer.bugsmanager.autotests.pages.BugsManagerAppPage;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Bugs List page.
 */
@At(Settings.BUG_LIST_URL)
public class BugsListPage extends BugsManagerAppPage {

	public BugsListPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = ".x-grid-table")
	public WebElementFacade bugsTable;

	@FindBy(xpath = "//button[@data-qtip='First Page']")
	public WebElementFacade firstPageButton;

	@FindBy(xpath = "//button[@data-qtip='Previous Page']")
	public WebElementFacade previousPageButton;

	@FindBy(css = ".x-tbar-page-number input")
	public WebElementFacade pageNumberField;

	@FindBy(xpath = "//button[@data-qtip='Next Page']")
	public WebElementFacade nextPageButton;

	@FindBy(xpath = "//button[@data-qtip='Last Page']")
	public WebElementFacade lastPageButton;

	@FindBy(xpath = "//button[@data-qtip='Refresh']")
	public WebElementFacade refreshButton;

	@FindBy(xpath = "//button[@data-qtip='Add']")
	public WebElementFacade addButton;

	@FindBy(xpath = "//button[@data-qtip='Edit']")
	public WebElementFacade editButton;

	@FindBy(xpath = "//button[@data-qtip='Apply']")
	public WebElementFacade applyButton;

	@FindBy(xpath = "//button[@data-qtip='Delete']")
	public WebElementFacade deleteButton;

	@FindBy(xpath = "//button[@data-qtip='Add in form']")
	public WebElementFacade addInFormButton;

	@FindBy(xpath = "//button[@data-qtip='Edit in form']")
	public WebElementFacade editInFormButton;

	@FindBy(xpath = "//button[@data-qtip='Search']")
	public WebElementFacade searchButton;

	@FindBy(css = ".x-tool-refresh")
	public WebElementFacade topRefreshButton;

	@FindBy(css = ".x-mask-msg")
	public WebElementFacade loadingImage;

	@FindBy(xpath = "//div[contains(@class,'x-grid-editor') and not(contains(@style,'display: none'))]//*[self::textarea|self::input]")
	public WebElementFacade newBugInListCurrentActiveField;

	public List<Map<Object, String>> getBugsList() {
		if (!bugsTable.isPresent()) {
			return Collections.emptyList();
		}
		return HtmlTable
				.withColumns("ID", "Done", "Name", "Notes", "Priority", "Due", "Created at", "Updated at")
				.readRowsFrom(bugsTable);
	}

	public List<WebElementFacade> getBugsListElements() {
		if (!bugsTable.isPresent()) {
			return Collections.emptyList();
		}
		return findAll(By.cssSelector(".x-grid-table tr.x-grid-row"));
	}

}
