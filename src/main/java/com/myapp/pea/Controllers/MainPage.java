package com.myapp.pea.Controllers;

import com.myapp.pea.Models.Lists;
import com.myapp.pea.Models.User;
import com.myapp.pea.Services.TodoService.GetTasks;
import com.myapp.pea.Services.UserService;
import com.myapp.pea.Services.Lists.ListsOperation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@RequestMapping("/pea")
public class MainPage {

    private final ListsOperation listsOperation;
    private final UserService userService;
    private final GetTasks getTasks;




}
