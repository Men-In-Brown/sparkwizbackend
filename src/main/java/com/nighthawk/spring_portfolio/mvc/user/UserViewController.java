package com.nighthawk.spring_portfolio.mvc.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/mvc/user")
public class UserViewController {

@Autowired
    private UserDetailsService repository;

    @GetMapping("/read")
    public String user(Model model) {
        List<user> list = repository.listAll();
        model.addAttribute("list", list);
        return "user/read";
    }

    /*  The HTML template Forms and userForm attributes are bound
        @return - template for user form
        @param - user Class
    */
    @GetMapping("/create")
    public String userAdd(user user) {
        return "user/create";
    }

    /* Gathers the attributes filled out in the form, tests for and retrieves validation error
    @param - user object with @Valid
    @param - BindingResult object
     */
    @PostMapping("/create")
    public String userSave(@Valid user user, BindingResult bindingResult) {
        // Validation of Decorated userForm attributes
        if (bindingResult.hasErrors()) {
            return "user/create";
        }
        repository.save(user);
        repository.addRoleTouser(user.getEmail(), "ROLE_STUDENT");
        // Redirect to next step
        return "redirect:/mvc/user/read";
    }

    @GetMapping("/update/{id}")
    public String userUpdate(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", repository.get(id));
        return "user/update";
    }

    @PostMapping("/update")
    public String userUpdateSave(@Valid user user, BindingResult bindingResult) {
        // Validation of Decorated userForm attributes
        if (bindingResult.hasErrors()) {
            return "user/update";
        }
        repository.save(user);
        repository.addRoleTouser(user.getEmail(), "ROLE_STUDENT");

        // Redirect to next step
        return "redirect:/mvc/user/read";
    }

    @GetMapping("/delete/{id}")
    public String userDelete(@PathVariable("id") long id) {
        repository.delete(id);
        return "redirect:/mvc/user/read";
    }

    @GetMapping("/search")
    public String user() {
        return "user/search";
    }

}