package steps.vendor;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.models.web.SearchVendor;
import cucumber.questions.CommonQuestions;
import cucumber.tasks.vendor.CheckFilter;
import cucumber.user_interface.admin.VendorPageForm;

import java.util.List;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.equalTo;

public class VendorsStepDefinitions {
    @And("Search the vendor by info then system show result")
    public void search_the_vendor_by_full_name_field(List<SearchVendor> infos) {
        for(SearchVendor info : infos) {
            theActorInTheSpotlight().attemptsTo(
                    CheckFilter.resetFilter(),
                    CheckFilter.check(info)
            );

            theActorInTheSpotlight().should(
                    seeThat(CommonQuestions.isControlDisplay(VendorPageForm.FULL_NAME_RESULT(info.getFullName())), equalTo(true))
            );
        }

    }
}
