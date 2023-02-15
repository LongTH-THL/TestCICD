package cucumber.tasks.common;

import cucumber.singleton.GVs;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static org.hamcrest.CoreMatchers.not;

public class CommonWaitUntil {
	public static Interaction isVisible(Target target) {
		 return WaitUntil.the(target, WebElementStateMatchers.isCurrentlyVisible()).forNoMoreThan(GVs.HTTP_TIMEOUT).seconds();
	}
	public static Interaction isClickable(Target target) {
		 return WaitUntil.the(target, WebElementStateMatchers.isClickable()).forNoMoreThan(GVs.HTTP_TIMEOUT).seconds();
	}
	public static Interaction isEnabled(Target target) {
		 return WaitUntil.the(target, WebElementStateMatchers.isCurrentlyEnabled()).forNoMoreThan(GVs.HTTP_TIMEOUT).seconds();
	}
	public static Interaction isPresent(Target target) {
		 return WaitUntil.the(target, WebElementStateMatchers.isPresent()).forNoMoreThan(GVs.HTTP_TIMEOUT).seconds();
	}
	public static Interaction isNotVisible(Target target) {//được sử dụng kiểm tra sự có mặt của phần tử trong cây DOM
		 return WaitUntil.the(target, not(WebElementStateMatchers.isCurrentlyVisible())).forNoMoreThan(GVs.HTTP_TIMEOUT).seconds();
	}
}
