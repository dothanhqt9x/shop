package com.example.shop.controller.admin;

import com.example.shop.repository.ProductRepository;
import com.example.shop.service.IUserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/admin-user")
public class UserController {

    private final IUserService iUserService;

    public UserController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @GetMapping("list")
    public String viewListUser(Model model,
                               @RequestParam(name = "page", required = false, defaultValue = "0") Integer page){
        Sort sortable = null;
        sortable = Sort.by("id").ascending();
        Pageable pageable = PageRequest.of(page, 5, sortable);
        model.addAttribute("user", iUserService.getListUser(pageable));
        return "admin/user/listuser";
    }

    @GetMapping("add")
    public String viewAdd(){
        return "admin/user/adduser";
    }
    @PostMapping("add")
    public void addUser(@RequestBody Object userData, HttpServletResponse response) throws IOException {
        iUserService.addUser(userData);
        response.sendRedirect("list");
    }

    @GetMapping("update")
    public String viewEdit(Model model, @RequestParam Integer id){
        model.addAttribute("user", iUserService.getUserById(id));
        return "admin/user/edituser";
    }
    @PostMapping("update")
    public void editUser(@RequestParam Integer id, @RequestBody Object userDate, HttpServletResponse response) throws IOException {
        iUserService.updateUserById(id, userDate);
        response.sendRedirect("list");
    }

    @GetMapping("delete")
    public void deleteUser(@RequestParam Integer id, HttpServletResponse response) throws IOException {
        iUserService.deleteUserById(id);
        response.sendRedirect("list");
    }

}
