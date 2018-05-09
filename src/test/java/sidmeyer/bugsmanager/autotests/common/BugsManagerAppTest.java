package sidmeyer.bugsmanager.autotests.common;

import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.pages.Pages;
import net.thucydides.junit.annotations.Concurrent;
import net.thucydides.junit.runners.ThucydidesRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import sidmeyer.bugsmanager.autotests.steps.BugsManagerAppSteps;
import sidmeyer.bugsmanager.autotests.steps.buglist.BugListSteps;
import sidmeyer.bugsmanager.autotests.steps.buglist.BugPopUpSteps;
import sidmeyer.bugsmanager.autotests.steps.buglist.SearchPopUpSteps;

/**
 * Base class for all Bugs Manager app test
 * @author Sid Meyer
 */
@RunWith(ThucydidesRunner.class)
@Concurrent(threads = "1")
public abstract class BugsManagerAppTest {

	@Managed(uniqueSession = true)
	private WebDriver driver;

	@ManagedPages(defaultUrl = Settings.BUG_LIST_URL)
	public Pages pages;

	@Steps
	protected BugsManagerAppSteps bugsManagerAppSteps;

	@Steps
	protected BugListSteps bugListSteps;

	@Steps
	protected BugPopUpSteps bugPopUpSteps;

	@Steps
	protected SearchPopUpSteps searchPopUpSteps;

	@Before
	public void beforeTest() {
		driver.manage().window().maximize();
	}

	@After
	public void afterTest() {
		driver.close();
	}
}
