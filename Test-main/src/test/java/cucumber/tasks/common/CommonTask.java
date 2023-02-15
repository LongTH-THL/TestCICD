package cucumber.tasks.common;

import cucumber.models.User;
import cucumber.models.api.SignIn;
import cucumber.singleton.GVs;
import cucumber.singleton.user.PodFood;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.targets.Target;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommonTask {
    /**
     * Upload file bằng robot
     * @param path
     * @throws Exception
     */
    public static void uploadFile(String path) throws Exception {
        String root = System.getProperty("user.dir");
        String otherFolder = root + path;
        Robot robot = new Robot();
        StringSelection stringSelection = new StringSelection(otherFolder);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
        robot.setAutoDelay(1000);
        //native key strokes for CTRL, V and ENTER keys
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);

        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);

        robot.setAutoDelay(1000);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    public static User getUser(String name) {
        User user = null;
        SignIn signIn =null;
        GVs.AccountType actor = GVs.AccountType.valueOf(name.toUpperCase());
        switch (actor) {
            case THUYEXAM:
                user = PodFood.thuyExam();
                break;
            case ADMIN:
                user = PodFood.admin();
                break;
        }
        return user;
    }

    public static String formatAmountDisplay(String amount) {
        BigDecimal amountBigDecimal = amount == null || amount.length() == 0
                ? BigDecimal.ZERO : new BigDecimal(amount);
        return formatAmountDisplay(amountBigDecimal);
    }

    public static String formatAmountDisplay(BigDecimal amount) {
        NumberFormat nf = new DecimalFormat("###,###,###,###,###");
        return nf.format(amount);
    }

    public static void get_current_time() {
        //2021-03-23 16:45:00
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime now = LocalDateTime.now();
        System.out.println("current Date and Time:: " + dtf.format(now));
        Serenity.setSessionVariable("Thời gian tạo giao dịch").to(dtf.format(now));
    }

    public static Task chooseItemInDropdown(Target parentTarget, Target childTarget) {
        return Task.where("Chọn giá trị trong dropdown",
                CommonWaitUntil.isClickable(parentTarget),
                Click.on(parentTarget),
                CommonWaitUntil.isVisible(childTarget),
                Scroll.to(childTarget),
                Click.on(childTarget)
        );
    }


    public static Task chooseItemInDropdownWithValueInput(Target parentTarget, String value, Target childTarget) {
        return Task.where("Chọn giá trị trong dropdown với giá trị nhập vào",
                Scroll.to(parentTarget),
                CommonWaitUntil.isEnabled(parentTarget),
                Click.on(parentTarget),
                Enter.theValue(value).into(parentTarget),
                CommonWaitUntil.isVisible(childTarget),
                Click.on(childTarget)
        );
    }

}