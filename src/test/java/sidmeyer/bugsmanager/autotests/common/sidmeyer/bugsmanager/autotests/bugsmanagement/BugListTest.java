package sidmeyer.bugsmanager.autotests.common.sidmeyer.bugsmanager.autotests.bugsmanagement;

import net.thucydides.core.annotations.Story;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sidmeyer.bugsmanager.autotests.BugsManagerApp;
import sidmeyer.bugsmanager.autotests.common.BugsManagerAppTest;

import static net.thucydides.core.matchers.BeanMatchers.each;
import static net.thucydides.core.matchers.BeanMatchers.the;
import static net.thucydides.core.matchers.BeanMatchers.the_count;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.is;

/**
 * Tests for bug list.
 */
@Story(BugsManagerApp.BugsManagement.BugList.class)
public class BugListTest extends BugsManagerAppTest {

	private final Logger LOG = LoggerFactory.getLogger(BugListTest.class);

	@Test
	public void should_add_a_new_bug_with_popup() {
		final String bugName = "sm " + System.currentTimeMillis();
		LOG.info("bugName: {}", bugName);

		bugListSteps.goToBugsListPage();

		bugListSteps.clickAddInFormButton();
		bugPopUpSteps.clickDoneCheckbox();
		bugPopUpSteps.enterName(bugName);
		bugPopUpSteps.enterNotes("note01");
		bugPopUpSteps.enterPriority("10");
		bugPopUpSteps.enterDueDate(2017, 5, 14);
		bugPopUpSteps.setCreatedDateTime(2015, 10, 22, 23, 8, 0);
		bugPopUpSteps.setUpdatedDateTime(2016, 11, 23, 13, 5, 4);
		bugPopUpSteps.save();

		bugListSteps.searchBugByName(bugName);

		bugListSteps.shouldShowBugsWhere(the("Name", is(bugName)),
				the("Notes", is("note01")),
				the("Priority", is("10")));
	}

	@Test
	public void should_not_add_a_new_bug_with_an_existing_name_with_popup() {
		final String bugName = "sm " + System.currentTimeMillis();
		LOG.info("bugName: {}", bugName);

		bugListSteps.goToBugsListPage();

		//preparation
		bugListSteps.createBugWithName(bugName);

		bugListSteps.clickAddInFormButton();
		bugPopUpSteps.clickDoneCheckbox();
		bugPopUpSteps.enterName(bugName);
		bugPopUpSteps.enterNotes("note100");
		bugPopUpSteps.save();

		bugsManagerAppSteps.shouldShowInfoMessageWithText("Name has already been taken");

		bugPopUpSteps.cancel();
		bugListSteps.searchBugByName(bugName);

		bugListSteps.shouldShowBugsWhere(the("Name", is(bugName)),
				the_count(is(1)));
	}

	/*
	This test fails because of obvious bug:
	times in the bugs list are displayed in incorrect format (12 hours formst but without AM/PM mark).
	So datetime "2018-09-19 22:07:03" is displayed as "2018-09-19 10:07:03".
	It should be displayed as "2018-09-19 22:07:03" or "2018-09-19 10:07:03 PM".
	 */
	@Test
	public void should_edit_an_existing_bug_with_popup() {
		final String oldBugName = "sm " + System.currentTimeMillis();
		final String editedBugName = "edited " + oldBugName;
		LOG.info("oldBugName: {}", oldBugName);
		LOG.info("editedBugName: {}", editedBugName);

		bugListSteps.goToBugsListPage();

		//preparation
		bugListSteps.createBugWithName(oldBugName);

		bugListSteps.searchBugByName(oldBugName);
		bugListSteps.shouldShowBugsWhere(the("Name", is(oldBugName)));
		bugListSteps.selectFirstBugInList();
		bugListSteps.clickEditInFormButton();
		bugPopUpSteps.clickDoneCheckbox();
		bugPopUpSteps.enterName(editedBugName);
		bugPopUpSteps.enterNotes("edited note");
		bugPopUpSteps.enterPriority("5");
		bugPopUpSteps.enterDueDate(2018, 5, 14);
		bugPopUpSteps.setCreatedDateTime(2018, 9, 19, 22, 7, 3);
		bugPopUpSteps.setUpdatedDateTime(2018, 10, 20, 0, 3, 9);
		bugPopUpSteps.save();

		bugListSteps.searchBugByName(editedBugName);

		bugListSteps.shouldShowBugsWhere(the("Name", is(editedBugName)),
				the("Notes", is("edited note")),
				the("Priority", is("5")),
				the("Due", is("05/14/2018")),
				the("Created at", is("2018-09-19 22:07:03")),
				the("Updated at", is("2018-10-20 00:03:09")));
	}

	@Test
	public void should_delete_an_existing_bug() {
		final String bugName = "sm " + System.currentTimeMillis();
		LOG.info("bugName: {}", bugName);

		bugListSteps.goToBugsListPage();

		//preparation
		bugListSteps.createBugWithName(bugName);

		bugListSteps.searchBugByName(bugName);
		bugListSteps.selectFirstBugInList();
		bugListSteps.clickDeleteButton();
		bugsManagerAppSteps.clickYesConfirmButton();

		bugListSteps.searchBugByName(bugName);

		bugListSteps.bugShouldNotBePresentInList(the("Name", is(bugName)));
	}

	@Test
	public void should_search_existing_bugs() {
		final String timestamp = "" + System.currentTimeMillis();
		final String bug1Name = "sm 1 " + timestamp;
		final String bug2Name = "sm 2 " + timestamp;
		LOG.info("bug1Name: {}", bug1Name);
		LOG.info("bug2Name: {}", bug2Name);

		bugListSteps.goToBugsListPage();

		//preparation
		bugListSteps.createBugWithName(bug1Name);
		bugListSteps.createBugWithName(bug2Name);

		bugListSteps.clickSearchButton();
		searchPopUpSteps.addCriteriaByNameContains(timestamp);
		searchPopUpSteps.clickSearch();

		bugListSteps.shouldShowBugsWhere(the("Name", endsWith(timestamp)),
				each("Name").isDifferent(),
				the_count(is(2)));
	}

	@Test
	public void should_add_a_new_bug_in_list() {
		final String bugName = "sm " + System.currentTimeMillis();
		LOG.info("bugName: {}", bugName);

		bugListSteps.goToBugsListPage();

		bugListSteps.createBugInListWithData(bugName,
				"note04",
				"4",
				"05/18/2018",
				"2018-05-23 10:15:00",
				"2018-05-22 11:15:00");

		bugListSteps.searchBugByName(bugName);

		bugListSteps.shouldShowBugsWhere(the("Name", is(bugName)),
				the("Notes", is("note04")),
				the("Priority", is("4")),
				the("Due", is("05/18/2018")),
				the("Created at", is("2018-05-23 10:15:00")),
				the("Updated at", is("2018-05-22 11:15:00")));
	}

	@Test
	public void should_edit_an_existing_bug_in_list() {
		final String oldBugName = "sm " + System.currentTimeMillis();
		final String editedBugName = "edited " + oldBugName;
		LOG.info("oldBugName: {}", oldBugName);
		LOG.info("editedBugName: {}", editedBugName);

		bugListSteps.goToBugsListPage();

		//preparation
		bugListSteps.createBugWithName(oldBugName);

		bugListSteps.searchBugByName(oldBugName);
		bugListSteps.selectFirstBugInList();
		bugListSteps.clickEditButton();
		bugListSteps.fillDataInLine(editedBugName,
				"note04",
				"4",
				"05/18/2018",
				"2018-05-23 10:15:00",
				"2018-05-22 11:15:00");
		bugListSteps.clickApplyButton();

		bugListSteps.searchBugByName(editedBugName);

		bugListSteps.shouldShowBugsWhere(the("Name", is(editedBugName)),
				the("Notes", is("note04")),
				the("Priority", is("4")),
				the("Due", is("05/18/2018")),
				the("Created at", is("2018-05-23 10:15:00")),
				the("Updated at", is("2018-05-22 11:15:00")));
	}

}
