package tqs.java.salary;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import tqs.salary.Employee;
import tqs.salary.SalaryManager;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;


public class SalarySteps {
    SalaryManager manager;



    @Given("the salary management system is initialized with the following data")
    public void the_salary_management_system_is_initialized_with_the_following_data(DataTable table) throws Throwable {
        // Convert DataTable to List<Employee> where employee = {id, name, salary}
        List<List<String>> rows = table.asLists(String.class);
        List<Employee> employees = new ArrayList<>();
        for (List<String> columns : rows) {
            // skip header
            if (columns.get(0).equals("id")) {
                continue;
            }
            employees.add(new Employee(Integer.parseInt(columns.get(0)), columns.get(1), Float.parseFloat(columns.get(2))));
        }
        manager = new SalaryManager(employees);
    }

    @When("the boss increases the salary for the employee with id {int} by {int}%")
    public void the_boss_increases_the_salary_for_the_employee_with_id_by(final int id, final int increaseInPercent) throws Throwable {
        manager.increaseSalary(id, increaseInPercent);
    }

    @Then("the payroll for the employee with id {int} should display a salary of {float}")
    public void the_payroll_for_the_employee_with_id_should_display_a_salary_of(final int id, final float salary) throws Throwable {
        Employee nominee = manager.getPayroll(id);
        assertEquals(nominee.getSalary(), salary);
    }
}
