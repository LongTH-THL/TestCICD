package cucumber.tasks.common;

import cucumber.questions.PageTitleQuestions;
import cucumber.singleton.GVs;
import cucumber.user_interface.admin.HomePageForm;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.conditions.Check;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.WebElementQuestion;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.Wait;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isCurrentlyEnabled;
import static net.serenitybdd.screenplay.questions.WebElementQuestion.stateOf;
import static org.hamcrest.Matchers.equalTo;

public class NavigateToSideBar {

    public static Task navigate(String pageTitle, Target parentMenu, Target targetNenu) {
        return Task.where("Truy cập vào trang " + pageTitle + " qua sidebar",
                Check.whether(WebElementQuestion.the(parentMenu), WebElementStateMatchers.isVisible())
                        .otherwise(Scroll.to(parentMenu)),
                Click.on(parentMenu),
                Scroll.to(targetNenu),
                WaitUntil.the(targetNenu, WebElementStateMatchers.isVisible()).forNoMoreThan(GVs.HTTP_TIMEOUT).seconds(),
                Check.whether(stateOf(targetNenu), isCurrentlyEnabled()),
                Scroll.to(targetNenu),
                Click.on(targetNenu),
                WaitUntil.the(HomePageForm.LOADING_ICON, WebElementStateMatchers.isNotVisible()).forNoMoreThan(150).seconds()

        );
    }
}
