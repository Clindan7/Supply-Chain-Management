package com.supplychain.supplychain.controller;
import com.supplychain.supplychain.entity.User;
import com.supplychain.supplychain.form.ChangePasswordForm;
import com.supplychain.supplychain.form.UserForm;
import com.supplychain.supplychain.service.UserService;
import com.supplychain.supplychain.view.UserView;
import java.util.Collection;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;


    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    public UserView add(@Valid @RequestBody UserForm form) {
        return userService.add(form);
    }

    @GetMapping
    public Collection<User> list() {
        return userService.list();
    }


    @GetMapping("/supplier/users")
    public Collection<User> listSupplier() {
        return userService.listSupplier();
    }

    @GetMapping("/purchaser/users")
    public Collection<User> listPurchaser() {
        return userService.listPurchaser();
    }

    @GetMapping("/currentUser")
    public User currentUser() {
        return userService.getCurrentUser();
    }

    // @GetMapping("/currentpurchaser")
    // public UserView currentPurchaser() {
    //     return userService.currentPurchaser();
    // }




    @GetMapping("/{userId}")
    public UserView get(
            @PathVariable("userId") Integer userId

    ) {
        return userService.getUser(userId);
    }

    @PutMapping("/{userId}")
    public UserView update(
            @PathVariable("userId") Integer userId,
            @Valid @RequestBody UserForm form
    ) {
        return userService.update(userId, form);
    }
   
    @PutMapping("/changepassword")
    public UserView updatepassword(
            @Valid @RequestBody ChangePasswordForm form
    ) {
        return userService.updatepassword(form);
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable("userId") Integer userId) {
        userService.delete(userId);
    }

    @PutMapping
    public UserView updateLoggedUser(
            @Valid @RequestBody UserForm form
    ) {
        return userService.updateLoggedUser(form);
    }
}

