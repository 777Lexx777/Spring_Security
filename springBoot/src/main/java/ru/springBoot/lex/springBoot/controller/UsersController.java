package ru.springBoot.lex.springBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.springBoot.lex.springBoot.model.User;
import ru.springBoot.lex.springBoot.service.UserServiceImp;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/start")
public class UsersController {

    @Autowired
    private UserServiceImp userServiceImp;

    @GetMapping("/login")
    public String getLoginPage() {System.out.println("login"); return "login";}

    @GetMapping("/logout")
    public String getLogOutPage() {System.out.println("logout"); return "logout";}

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('developers:write')")
    public String printUsers(@RequestParam(value = "count", required = false) String count, Model model){
        if (count == null) { count = "0"; }
        model.addAttribute("messages", userServiceImp.getCountUser(count));
        return "allUsers";
    }

    @GetMapping("/user/{id}")
    @PreAuthorize("hasAuthority('developers:read')")
    public String printUsersId(@PathVariable("id") long id, Model model){
        model.addAttribute("messagesID", userServiceImp.getUserId(id));
        return "userId";
    }

    @GetMapping("/info")
    @PreAuthorize("hasAuthority('developers:read')")
    public String printUsersId(Model model, Principal principal){
        String username = principal.getName();
        model.addAttribute("userInfo", userServiceImp.getUserByName(username));
        return "userInfo";
    }


    @GetMapping("/new")
    @PreAuthorize("hasAuthority('developers:write')")
    public String newUsers( @ModelAttribute("newUser") User user){
        return "new";
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('developers:write')")
    public String createNewUsers(@ModelAttribute("newUser") @Valid User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {return "new";}
        userServiceImp.saveUser(user);
        return "redirect:/start/admin";
    }

    @GetMapping("admin/{id}/edit")
    @PreAuthorize("hasAuthority('developers:write')")
    public String editUser(Model model, @PathVariable("id") long id){
        model.addAttribute("editUsers", userServiceImp.getUserId(id));
        return "/editUser";
    }

    @PostMapping("/update/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public String updateUsers(@ModelAttribute("editUsers") @Valid User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {return "editUser";}
        userServiceImp.updateUser(user);
        return "redirect:/start/admin";
    }

    @PostMapping("/deleted/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public String deleteUsers( @ModelAttribute("messagesID") User user){
        userServiceImp.deleteUser(user);
        return "redirect:/start/admin";
    }
}
