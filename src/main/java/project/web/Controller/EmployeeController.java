package project.web.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.entity.Employee;
import project.service.EmployeeService;
import project.service.ValidationDeleteService;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    ValidationDeleteService validationDeleteService;

    @RequestMapping("/employees")
    public ModelAndView employees(Model model){
        ModelAndView mav = new ModelAndView("employees");
        if(((BindingAwareModelMap) model).size() !=0){
            String message = (String) model.asMap().get("msg");
            mav.addObject("messageAfterRemoved", message);
        }
        mav.addObject("employeeList", employeeService.showAllEmployees());
        return mav;
    }

    @RequestMapping(value = "/addEmployee")
    public ModelAndView addEmployee() {
        ModelAndView mav = new ModelAndView("addEmployee");
        mav.addObject(new Employee());
        mav.addObject("isUpdateOrCreate", "Create employee");
        return mav;
    }

    @RequestMapping(value = "/updateEmployee")
    public ModelAndView updateEmployee(@RequestParam("id") String employeeId) {
        Employee employee = employeeService.getEmployeeById(Integer.parseInt(employeeId));
        ModelAndView mav = new ModelAndView("addEmployee");
        mav.addObject(employee);
        mav.addObject("isUpdateOrCreate", "Update employee");
        return mav;
    }


    @RequestMapping(value = "/createEmployee", method = RequestMethod.POST)
    public String createEmployee(@ModelAttribute Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/employees";
    }

    @RequestMapping(value = "/deleteEmployee")
    public String deleteEmployee(@RequestParam("id") String employeeId, final RedirectAttributes redirectAttributes) {
        Integer result = validationDeleteService.removeItem(Integer.parseInt(employeeId), "Employee");
        switch(result) {
            case (-1):
                redirectAttributes.addFlashAttribute("msg", "The employee cannot be removed, because he has any tickets!");
                break;
            case (0):
                redirectAttributes.addFlashAttribute("msg", "Sometimes went wrong. Please try again later.");
                break;
        }
        return "redirect:/employees";
    }
}
