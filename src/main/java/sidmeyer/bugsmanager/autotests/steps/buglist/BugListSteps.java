package sidmeyer.bugsmanager.autotests.steps.buglist;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.matchers.BeanMatcher;
import net.thucydides.core.matchers.BeanMatcherAsserts;
import net.thucydides.core.pages.Pages;
import org.junit.Assert;
import sidmeyer.bugsmanager.autotests.common.Settings;
import sidmeyer.bugsmanager.autotests.pages.buglist.BugsListPage;
import sidmeyer.bugsmanager.autotests.steps.BugsManagerAppSteps;

/**
 * Steps for bug list page.
 */
public class BugListSteps extends BugsManagerAppSteps {

	public BugListSteps(Pages pages) {
		super(pages);
	}

	private BugsListPage onBugsListPage() {
		return pages().currentPageAt(BugsListPage.class);
	}

	@Steps
	private BugPopUpSteps bugPopUpSteps;

	@Steps
	private SearchPopUpSteps searchPopUpSteps;

	@Step
	public void goToBugsListPage() {
		getDriver().navigate().to(Settings.BUG_LIST_URL);
	}

	@Step
	public void clickAddButton() {
		onBugsListPage().addButton.click();
	}

	@Step
	public void clickEditButton() {
		onBugsListPage().editButton.click();
	}

	@Step
	public void clickApplyButton() {
		onBugsListPage().applyButton.click();
		waitForLoadingEnds();
	}

	@Step
	public void clickAddInFormButton() {
		onBugsListPage().addInFormButton.click();
		waitForLoadingEnds();
	}

	@Step
	public void clickEditInFormButton() {
		onBugsListPage().editInFormButton.click();
		waitForLoadingEnds();
	}

	@Step
	public void clickDeleteButton() {
		onBugsListPage().deleteButton.click();
	}

	@Step
	public void clickSearchButton() {
		onBugsListPage().searchButton.click();
	}

	@Step
	public void refresh() {
		onBugsListPage().refreshButton.click();
		waitForLoadingEnds();
	}

	@Step
	public void shouldShowBugsWhere(final BeanMatcher... matchers) {
		BeanMatcherAsserts.shouldMatch(onBugsListPage().getBugsList(), matchers);
	}

	@Step
	public void bugShouldNotBePresentInList(final BeanMatcher... matchers) {
		Assert.assertTrue("The bug is present in the bugs list.", BeanMatcherAsserts.filterElements(onBugsListPage().getBugsList(), matchers).isEmpty());
	}

	@Step
	public void createBugWithName(final String name) {
		clickAddInFormButton();
		bugPopUpSteps.clickDoneCheckbox();
		bugPopUpSteps.enterName(name);
		bugPopUpSteps.enterNotes("note02");
		bugPopUpSteps.enterPriority("2");
		bugPopUpSteps.enterDueDate(2016, 6, 6);
		bugPopUpSteps.setCreatedDateTime(2012, 2, 2, 0, 0, 0);
		bugPopUpSteps.setUpdatedDateTime(2012, 12, 12, 0, 0, 0);
		bugPopUpSteps.save();
	}

	@Step
	public void createBugInListWithData(final String name,
										final String notes,
										final String priority,
										final String dueDate,
										final String createdDateTime,
										final String updatedDateTime) {
		clickAddButton();
		fillDataInLine(name, notes, priority, dueDate, createdDateTime, updatedDateTime);
		clickApplyButton();
	}

	@Step
	//TODO Improve filling method
	public void fillDataInLine(final String name,
							   final String notes,
							   final String priority,
							   final String dueDate,
							   final String createdDateTime,
							   final String updatedDateTime) {
		onBugsListPage().newBugInListCurrentActiveField.typeAndTab(name);
		onBugsListPage().newBugInListCurrentActiveField.typeAndTab(notes);
		onBugsListPage().newBugInListCurrentActiveField.typeAndTab(priority);
		onBugsListPage().newBugInListCurrentActiveField.typeAndTab(dueDate);
		onBugsListPage().newBugInListCurrentActiveField.type("\ue003\ue003\ue003\ue003\ue003\ue003\ue003\ue003\ue003\ue003" + createdDateTime.replace(" ", "\t\ue003\ue003\ue003\ue003\ue003\ue003\ue003\ue003") + "\t");
		onBugsListPage().newBugInListCurrentActiveField.type("\ue003\ue003\ue003\ue003\ue003\ue003\ue003\ue003\ue003\ue003" + updatedDateTime.replace(" ", "\t\ue003\ue003\ue003\ue003\ue003\ue003\ue003\ue003") + "\t");
	}

	@Step
	public void searchBugByName(final String name) {
		clickSearchButton();
		searchPopUpSteps.clearCriteria();
		searchPopUpSteps.addCriteriaByNameMatches(name);
		searchPopUpSteps.clickSearch();
	}

	@Step
	public void selectFirstBugInList() {
		onBugsListPage().getBugsListElements().get(0).click();
	}
}
