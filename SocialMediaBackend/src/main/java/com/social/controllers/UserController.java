package com.social.controllers;

import com.social.dto.request.UserRegisterDTO;
import com.social.enums.UserStatus;
import com.social.pojo.User;
import com.social.services.UserService;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author LENOVO
 */
@Lazy
@Controller
@ControllerAdvice
@PropertySource("classpath:application.properties")
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    private Environment env;
    @Autowired
    private UserService userService;

    @ModelAttribute
    public void commonAttributesUser(Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("roles", this.userService.getAllRoles());
        model.addAttribute("status", UserStatus.values());
        redirectAttributes.addFlashAttribute("status", UserStatus.values());
    }

    @GetMapping
    public String get(Model model, @RequestParam Map<String, String> params) {
        Integer size = env.getProperty("PAGINATION", Integer.class);
        model.addAttribute("users", userService.getUsers(params));
        Long count = this.userService.count();
        model.addAttribute("pages", Math.ceil((double) count / size));
        return "user";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("user", new UserRegisterDTO());
        return "create-user";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute(value = "user") @Valid UserRegisterDTO user,
            BindingResult rs, Model model) {
        if (rs.hasErrors()) {
            model.addAttribute("user", user);
            return "create-user";
        }
        user.setStatus(UserStatus.valueOf(user.getStatus().toString()));
        this.userService.save(user);
        return "redirect:/admin/user";
    }

    @GetMapping("/active")
    public String alumni(Model model, @RequestParam Map<String, String> params) {
        params.put("roleId", "2");
        params.put("isDeactive", "1");
        model.addAttribute("users", this.userService.getUsers(params));
        return "user";
    }

    @GetMapping("/{id}")
    public String viewUser(@PathVariable(value = "id") int id,
            Model model) {
        model.addAttribute("user", this.userService.getUserById(id));
        return "account";
    }

    @PutMapping("/{id}/{status}")
    @ResponseStatus(HttpStatus.OK)
    public void change(@PathVariable(value = "id") int id,
            @PathVariable(value = "status") String status,
            RedirectAttributes redirectAttributes) {
        User user = this.userService.getUserById(id);
        user.setStatus(UserStatus.valueOf(status));
        this.userService.updateStatus(user);
        System.out.println(status);
//        redirectAttributes.addFlashAttribute("status", UserStatus.values());
//        return "redirect:/admin/user/{id}";
    }

    @PostMapping("/{id}")
    public String changeInfo(@PathVariable(value = "id") int id,
            @ModelAttribute(value = "user") @Valid User user, BindingResult rs,
            Model model, Principal principal) {
        System.out.println("OK");
        if (rs.hasErrors()) {
            model.addAttribute("user", user);
            return "account";
        }
        user.setModifiedDate(LocalDateTime.now());
        this.userService.updateInfo(user);
        return "redirect:/admin/user/" + id;
    }
    
    @DeleteMapping("/{id}/delete/")
    public ResponseEntity delete(@PathVariable("id") int id) {
        this.userService.deleteUser(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
