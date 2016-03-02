package com.testingSystem.controller;

import com.testingSystem.entity.Test;
import com.testingSystem.entity.User;
import com.testingSystem.dao.UserDao;
import com.testingSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired private UserService userService;

	@RequestMapping("/list")
	public String list(ModelMap model) {
        List<User> users = userService.getAll("id");
        model.addAttribute("list", users);
		return "userList";
	}

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreate() {
        return "createUser";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(String firstName, String lastName, boolean admin) {
        User user = new User();

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAdmin(admin);
        userService.save(user);

        return "redirect:/user/list";
    }
}