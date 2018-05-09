package sidmeyer.bugsmanager.autotests.steps.buglist;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.findby.By;
import net.thucydides.core.pages.Pages;
import sidmeyer.bugsmanager.autotests.pages.buglist.SearchPopUp;
import sidmeyer.bugsmanager.autotests.steps.BugsManagerAppSteps;

/**
 * Steps for search bug popup window.
 */
public class SearchPopUpSteps extends BugsManagerAppSteps {

	public SearchPopUpSteps(Pages pages) {
		super(pages);
	}

	private SearchPopUp onSearchPopUp() {
		return pages().currentPageAt(SearchPopUp.class);
	}

	@Step
	public void clearCriteria() {
		onSearchPopUp().clearButton.click();
	}

	@Step
	public void addCriteriaByNameMatches(final String name) {
		onSearchPopUp().expandAttributesListButton.click();
		onSearchPopUp().find(By.xpath("//li[text()='Name'][last()]")).click();
		onSearchPopUp().expandOperatorsListButton.click();
		onSearchPopUp().find(By.xpath("//li[text()='Matches'][last()]")).click();
		onSearchPopUp().valueField.waitUntilEnabled().type(name);
	}

	@Step
	public void addCriteriaByNameContains(final String namePart) {
		onSearchPopUp().expandAttributesListButton.click();
		onSearchPopUp().find(By.xpath("//li[text()='Name'][last()]")).click();
		onSearchPopUp().expandOperatorsListButton.click();
		onSearchPopUp().find(By.xpath("//li[text()='Contains'][last()]")).click();
		onSearchPopUp().valueField.waitUntilEnabled().type(namePart);
	}

	@Step
	public void clickSearch() {
		onSearchPopUp().searchButton.click();
		waitForLoadingEnds();
	}

}
