package cucumber.questions;

import cucumber.singleton.GVs;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.Wait;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.CoreMatchers.equalTo;

public class CommonQuestions {
    /**
     * Hàm thực hiện validate
     *
     * @param patternStr  Pattern để thực hiện validate
     * @param target      Element lấy giá trị để thực hiện validate
     * @param errorTarget Element hiển thị lỗi của Element
     * @return Chính xác hoặc không
     */
    public static Question<Boolean> TextFieldValidation(String patternStr, Target target, Target errorTarget) {

        return new Question<Boolean>() {

            @Override
            public Boolean answeredBy(Actor actor) {
                Pattern pattern = Pattern.compile(patternStr);

                String input = target.resolveFor(actor).getTextValue();

                Matcher matcher = pattern.matcher(input);

                if (matcher.matches()) {
                    // Valid. errorTarget không nên hiện ra
                    if (errorTarget.resolveFor(actor).isVisible()) {
                        return false;
                    }

                    return true;
                } else {
                    // Invalid. errorTarget nên hiện ra
                    if (errorTarget.resolveFor(actor).isVisible()) {
                        return true;
                    }

                    return false;
                }
            }
        };
    }

    /**
     * Chuyển từ Question về Performable. Để có thể drop nếu xảy ra lỗi ở bước này
     * trong hàm attempto
     */
    public static Performable AskForTextFieldValidation(String patternStr, Target target, Target errorTarget) {
        return new Wait.WaitBuilder(CommonQuestions.TextFieldValidation(patternStr, target, errorTarget), equalTo(true))
                .forNoLongerThan(GVs.HTTP_TIMEOUT).seconds();
    }

    /**
     * Kiểm tra xem autocomplete đã chọn bản ghi nào chưa. Autocomplete bắt buộc
     * phải chọn giá trị
     *
     * @param target
     * @param removeButtonTarget
     * @param errorTarget
     * @return
     */
    public static Question<Boolean> AutocompleteValidation(Target target, Target removeButtonTarget,
                                                           Target errorTarget) {
        return new Question<Boolean>() {

            @Override
            public Boolean answeredBy(Actor actor) {
                if (removeButtonTarget.resolveFor(actor).isVisible()) {
                    // Đã chọn giá trị. Error Target nên ẩn đi
                    if (errorTarget.resolveFor(actor).isVisible()) {
                        return false;
                    }
                    return true;
                } else {
                    // Chưa chọn giá trị. Error Target nên hiện lên
                    if (errorTarget.resolveFor(actor).isVisible()) {
                        return true;
                    }
                    return false;
                }
            }
        };
    }

    /**
     * Chuyển từ Question về Performable để cho thể check trong AttempTo
     */
    public static Performable AskForAutocompleteValidation(Target target, Target removeButtonTarget,
                                                           Target errorTarget) {
        return new Wait.WaitBuilder(CommonQuestions.AutocompleteValidation(target, removeButtonTarget, errorTarget),
                equalTo(true)).forNoLongerThan(GVs.HTTP_TIMEOUT).seconds();
    }

    /**
     * Hàm kiểm tra đã chọn giá trị trong selector hay chưa
     *
     * @param target      Selector chính
     * @param errorTarget Element hiển thị lỗi của selector
     * @return
     */
    public static Question<Boolean> SelectorValidation(Target target, Target errorTarget) {
        return new Question<Boolean>() {

            @Override
            public Boolean answeredBy(Actor actor) {
                if (target.resolveFor(actor).containsElements("//span[contains(@class,'mat-select-value-text')]")) {
                    // Đã chọn giá trị. Error Target nên ẩn đi
                    if (errorTarget.resolveFor(actor).isVisible()) {
                        return false;
                    }
                    return true;
                } else {
                    // Chưa chọn giá trị. Error Target nên hiện lên
                    if (errorTarget.resolveFor(actor).isVisible()) {
                        return true;
                    }
                    return false;
                }
            }
        };
    }

    /**
     * Chuyển từ Question về Perfomable để có thể check trong AttempTo
     */
    public static Performable AskForSelectorValidation(Target target, Target errorTarget) {
        return new Wait.WaitBuilder(CommonQuestions.SelectorValidation(target, errorTarget), equalTo(true))
                .forNoLongerThan(GVs.HTTP_TIMEOUT).seconds();
    }

    /**
     * Kiểm tra xem object có null hay không
     *
     * @param value
     * @return if null return true: false
     */
    public static Question<Boolean> isNull(Object value) {
        return new Question<Boolean>() {

            @Subject("Giá trị của bảng")
            public Boolean answeredBy(Actor actor) {
                if (value == null)
                    return true;
                else
                    return false;
            }
        };
    }

    public static Question<Boolean> isControlDisplay(Target target) {
        return Question.about("'Kiểm tra hiển thị element " + target.getName() + "'").answeredBy(new Question<Boolean>() {
            @Override
            public Boolean answeredBy(Actor actor) {
                if (target.resolveFor(theActorInTheSpotlight()).isDisplayed())
                    return true;
                else
                    return false;
            }
        });
    }

    /**
     * `     * Chuyển từ Question về Perfomable để có thể check trong AttempTo`
     */
    public static Performable AskForElementIsDisplay(Target target, boolean value) {
        return new Wait.WaitBuilder(CommonQuestions.isControlDisplay(target), equalTo(value))
                .forNoLongerThan(GVs.HTTP_TIMEOUT).seconds();
    }

    /**
     * `     * Chuyển từ Question về Perfomable để có thể check trong AttempTo`
     */
    public static Performable AskForInputValueEquals(Target target, String value) {
        return new Wait.WaitBuilder(CommonQuestions.targetText(target), equalTo(value))
                .forNoLongerThan(GVs.HTTP_TIMEOUT).seconds();
    }


    /**
     * Lấy giá trị text
     *
     * @param target Đối tượng muốn truy vấn giá trị
     * @return text của element
     */
    public static Question<String> targetText(Target target) {
        return Question.about("'Text của Element " + target.getName() + "'").answeredBy(new Question<String>() {

            @Override
            public String answeredBy(Actor actor) {
                if (target.resolveFor(actor).isEnabled()) {
                    String text = target.resolveFor(actor).getTextContent().trim();
                    System.out.println("expected**: " + text);
                    return text;
                } else {
                    String text = target.resolveFor(actor).getAttribute("value").trim();
                    System.out.println("expected**: " + text);
                    return text;
                }
            }
        });
    }

    /**
     * Chuyển từ Question về Perfomable để có thể check trong AttempTo
     */
    public static Performable AskForTextEquals(Target target, String value) {
        return new Wait.WaitBuilder(CommonQuestions.textEquals(target, value), equalTo(true))
                .forNoLongerThan(GVs.HTTP_TIMEOUT).seconds();
    }

    /**
     * Kiểm tra xem giá trị của đối tượng có bằng giá trị truyền vào không ?
     *
     * @param target
     * @param value
     * @return
     */
    public static Question<Boolean> textEquals(Target target, String value) {
        return new Question<Boolean>() {

            @Subject("Thông báo phê duyệt thành công")
            public Boolean answeredBy(Actor actor) {

                String input = Text.of(target).viewedBy(actor).asString();

                return input.equals(value);
            }
        };
    }

    /**
     * Chuyển từ Question về Perfomable để có thể check trong AttempTo
     */
    public static Performable AskForTextValidation(Target target, String value) {
        return new Wait.WaitBuilder(CommonQuestions.textValidation(target, value), equalTo(true))
                .forNoLongerThan(GVs.HTTP_TIMEOUT).seconds();
    }

    /**
     * Kiểm tra xem giá trị của đối tượng có match với pattern không ?
     *
     * @param target
     * @param validation
     * @return
     */
    public static Question<Boolean> textValidation(Target target, String validation) {
        return new Question<Boolean>() {

            @Subject("Số tham chiếu phê duyệt thành công")
            public Boolean answeredBy(Actor actor) {
                Pattern pattern = Pattern.compile(validation);

                String input = Text.of(target).viewedBy(actor).asString();

                Matcher matcher = pattern.matcher(input);

                return matcher.matches();
            }
        };
    }

    /**
     * Lấy title của page
     *
     * @return
     */
    public static Question<String> pageTitle() {
        return new Question<String>() {
            @Override
            public String answeredBy(Actor actor) {
                return BrowseTheWeb.as(actor).getTitle();
            }
        };
    }

    public static Performable AskForPageTitle(String title) {
        return new Wait.WaitBuilder(CommonQuestions.pageTitle(), equalTo(title))
                .forNoLongerThan(GVs.HTTP_TIMEOUT).seconds();
    }

    /**
     * Lấy giá trị attribute bất kỳ
     *
     * @param target    Đối tượng muốn truy vấn giá trị
     * @param attribute Attribute muốn truy vấn giá trị
     * @return text của element
     */
    public static Question<String> attributeText(Target target, String attribute) {
        return Question.about("Giá trị attribute '" + attribute + "' của '" + target.getName() + "'").answeredBy(new Question<String>() {

            @Override
            public String answeredBy(Actor actor) {
                String text = target.resolveFor(actor).getAttribute(attribute).trim();
                return text;
            }
        });
    }

    /**
     * Chuyển từ Question về Perfomable để có thể check giá trị attribute
     */
    public static Performable AskForAttributeText(Target target, String attribute, String value) {
        return new Wait.WaitBuilder(CommonQuestions.attributeText(target, attribute), equalTo(value))
                .forNoLongerThan(GVs.HTTP_TIMEOUT).seconds();
    }

    /**
     * Lấy giá trị text
     *
     * @param elementFacade Đối tượng muốn truy vấn giá trị
     * @return text của element
     */
    public static Question<String> webelementFacadeText(WebElementFacade elementFacade) {
        return Question.about("'Text của Element ").answeredBy(new Question<String>() {

            @Override
            public String answeredBy(Actor actor) {
                if (elementFacade.isEnabled()) {
                    return elementFacade.getTextContent().trim();
                } else {
                    return elementFacade.getAttribute("value").trim();
                }
            }
        });
    }

    /*Lấy trá trị text của API*/
    public static Question<String> getText(String values) {
        if (values == null) {
            values = "";
        }
        String finalValues = values;
        return Question.about(values).answeredBy(new Question<String>() {
            @Override
            public String answeredBy(Actor actor) {
                return finalValues;
            }
        });
    }

    /**
     * Lấy giá trị value
     *
     * @param target Đối tượng muốn truy vấn giá trị
     * @return text của element
     */
    public static Question<String> targetValue(Target target) {
        return Question.about("'Value của Element " + target.getName() + "'").answeredBy(new Question<String>() {

            @Override
            public String answeredBy(Actor actor) {
                if (target.resolveFor(actor).isEnabled()) {
                    String text = target.resolveFor(actor).getValue().trim();
                    System.out.println("expected**: " + text);
                    return text;
                } else {
                    String text = target.resolveFor(actor).getAttribute("value").trim();
                    System.out.println("expected**: " + text);
                    return text;
                }
            }
        });
    }

    /**
     * Chuyển từ Question về Perfomable để có thể check trong AttempTo
     */
    public static Performable AskForValueEquals(Target target, String value) {
        return new Wait.WaitBuilder(CommonQuestions.targetValue(target), equalTo(value))
                .forNoLongerThan(GVs.HTTP_TIMEOUT).seconds();
    }
}
