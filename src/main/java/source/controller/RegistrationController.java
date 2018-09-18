package source.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import source.entity.User;
import source.crm.UserCRM;
import source.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/showRegistrationForm")
    public String showMyLoginPage(Model theModel) {
        theModel.addAttribute("crmUser", new UserCRM());
        return "register";
    }

    @PostMapping("/processRegistrationForm")
    public String processRegistrationForm(
            @Valid @ModelAttribute("crmUser") UserCRM theCrmUser,
            BindingResult theBindingResult,
            Model theModel) {

        // form validation
        if (theBindingResult.hasErrors()) {
            return "register";
        }
        // check the database if user already exists
        User existing = userService.findByUserName(theCrmUser.getUserName());
        if(existing!=null) {
            theModel.addAttribute("crmUser", new UserCRM());
            theModel.addAttribute("registrationError", "User name already exists.");
            return "register";
        }
        // save user in the database
        userService.save(theCrmUser);
        return "registration-confirmation";
    }
}
