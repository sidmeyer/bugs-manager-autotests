package sidmeyer.bugsmanager.autotests.steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.steps.ScenarioSteps;
import org.junit.Assert;
import sidmeyer.bugsmanager.autotests.pages.BugsManagerAppPage;

/**
 * Base steps for all steps.
 */
public class BugsManagerAppSteps extends ScenarioSteps {

	public BugsManagerAppSteps(Pages pages) {
		super(pages);
	}

	private BugsManagerAppPage onBugsManagerAppPage() {
		return pages().get(BugsManagerAppPage.class);
	}

	@Step
	public void clickYesConfirmButton() {
		onBugsManagerAppPage().yesConfirmButton.click();
		waitForLoadingEnds();
	}

	@Step
	public void clickNoConfirmButton() {
		onBugsManagerAppPage().noConfirmButton.click();
	}

	@Step
	public void shouldShowInfoMessageWithText(final String text) {
		String infoMessageActualText = onBugsManagerAppPage().getInfoMessageText();
		Assert.assertTrue(infoMessageActualText.contains(text));
	}

	protected void waitForLoadingEnds() {
		onBugsManagerAppPage().loadingImage.waitUntilNotVisible();
	}

}
