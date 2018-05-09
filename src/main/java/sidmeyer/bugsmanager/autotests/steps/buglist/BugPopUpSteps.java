package sidmeyer.bugsmanager.autotests.steps.buglist;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import sidmeyer.bugsmanager.autotests.pages.buglist.BugPopUp;
import sidmeyer.bugsmanager.autotests.steps.BugsManagerAppSteps;

/**
 * Steps for add/edit bug popup window.
 */
public class BugPopUpSteps extends BugsManagerAppSteps {

	public BugPopUpSteps(Pages pages) {
		super(pages);
	}

	private BugPopUp onBugPopUp() {
		return pages().currentPageAt(BugPopUp.class);
	}

	@Step
	public void save() {
		onBugPopUp().okButton.click();
		waitForLoadingEnds();
	}

	@Step
	public void cancel() {
		onBugPopUp().cancelButton.click();
	}

	@Step
	public void clickDoneCheckbox() {
		onBugPopUp().doneCheckbox.click();
	}

	@Step
	public void enterName(final String name) {
		onBugPopUp().nameField.type(name);
	}

	@Step
	public void enterNotes(final String notes) {
		onBugPopUp().notesField.type(notes);
	}

	@Step
	public void enterPriority(final String priority) {
		onBugPopUp().priorityField.type(priority);
	}

	@Step
	public void enterDueDate(final int year, final int month, final int day) {
		onBugPopUp().dueField.type(formatDate(year, month, day));
	}

	@Step
	public void setCreatedDateTime(final int year, final int month, final int day,
								   final int hours, final int minutes, final int seconds) {
		enterCreatedDate(year, month, day);
		enterCreatedTime(hours, minutes, seconds);
	}

	@Step
	public void enterCreatedDate(final int year, final int month, final int day) {
		onBugPopUp().createdAtDateField.type(formatDate(year, month, day));
	}

	@Step
	public void enterCreatedTime(final int hours, final int minutes, final int seconds) {
		onBugPopUp().createdAtTimeField.type(formatTime(hours, minutes, seconds));
	}

	@Step
	public void setUpdatedDateTime(final int year, final int month, final int day,
								   final int hours, final int minutes, final int seconds) {
		enterUpdatedDate(year, month, day);
		enterUpdatedTime(hours, minutes, seconds);
	}

	@Step
	public void enterUpdatedDate(final int year, final int month, final int day) {
		onBugPopUp().updatedAtDateField.type(formatDate(year, month, day));
	}

	@Step
	public void enterUpdatedTime(final int hours, final int minutes, final int seconds) {
		onBugPopUp().updatedAtTimeField.type(formatTime(hours, minutes, seconds));
	}

	private String formatDate(final int year, final int month, final int day) {
		return String.format("%02d-%02d-%02d", year, month, day);
	}

	private static String formatTime(final int hours, final int minutes, final int seconds) {
		return String.format("%02d:%02d:%02d", hours, minutes, seconds);
	}

}
