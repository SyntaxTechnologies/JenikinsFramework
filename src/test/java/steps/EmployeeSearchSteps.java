package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.DashboardPage;
import pages.EmployeeInformationPage;
import utils.CommonMethods;

public class EmployeeSearchSteps extends CommonMethods {

    @When("user clicks on PIM option")
    public void user_clicks_on_pim_option() {
     //   DashboardPage dash = new DashboardPage();
        click(dash.pimOption);
    }

    @When("user clicks on employee list option")
    public void user_clicks_on_employee_list_option() {
    //    DashboardPage dash = new DashboardPage();
        click(dash.employeeListOption);
    }

    @When("user enters valid employee id")
    public void user_enters_valid_employee_id() {
     //   EmployeeInformationPage emp = new EmployeeInformationPage();
        //this line will search the employee whose locator is avaiable on empl info page
        //send text method we are calling from common methods
        sendText(emp.idEmployeeSearch, "30249233");
    }

    @When("user clicks on search button")
    public void user_clicks_on_search_button() {
     //   EmployeeInformationPage emp = new EmployeeInformationPage();
        //this line will click on search button whose locator is available on empl infor page
        click(emp.searchButton);
    }

    @Then("user is able to see the employee")
    public void user_is_able_to_see_the_employee() {
        //homework - verify the employee is present
        System.out.println("Employee is available");
    }

    @When("user enters name of the employee")
    public void user_enters_name_of_the_employee() {
       sendText(emp.nameEmployeeSearch,  "test");
    }

}
