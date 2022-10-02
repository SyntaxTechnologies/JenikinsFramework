package steps;

import com.sun.management.DiagnosticCommandMBean;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.remote.Command;
import utils.CommonMethods;

public class AnotherStepsPage extends CommonMethods {

    @When("user enters invalid username and password")
    public void user_enters_invalid_username_and_password() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
