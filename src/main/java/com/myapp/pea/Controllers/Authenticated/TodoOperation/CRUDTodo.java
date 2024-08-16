package com.myapp.pea.Controllers.Authenticated.TodoOperation;

import com.myapp.pea.Controllers.CustomComponent.MyCustomModelMap;
import com.myapp.pea.Models.Todo;
import com.myapp.pea.Services.TodoService.TaskOperation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Objects;

@Controller
@AllArgsConstructor
@RequestMapping("/pea")
public class CRUDTodo {
    private final TaskOperation taskOperation;
    private final MyCustomModelMap myCustomModelMap;

    @PostMapping("/todo/{id}/mark-as-done")
    public String markAsComplete(@PathVariable Long id, RedirectAttributes reMap){
        System.out.println("Panis : "+id);
        if(id == null)
            reMap.addFlashAttribute("markAsCompleteMessage","No task ID was provided. Please provide a valid ID.");
        else {
            try {
                taskOperation.markAsComplete(id);
            }catch (Exception e){
                reMap.addFlashAttribute("markAsCompleteMessage",e.getMessage());
            }
        }
        System.out.println("Current URL : "+myCustomModelMap.getCurrentUrl());
        return "redirect:"+myCustomModelMap.getCurrentUrl();
    }

    @GetMapping("/todo/{id}/mark-as-done")
    public String getMarkAsComplete(@PathVariable Long id,RedirectAttributes map){
        map.addFlashAttribute("markAsCompleteMessage","The request to Mark As Complete the item could not be processed. Please use the appropriate method to perform this action or contact support for assistance.");

        return "redirect:"+myCustomModelMap.getCurrentUrl();
    }

    @PostMapping("/todo/delete/{id}")
    public String deleteTodo(@PathVariable Long id,
                             RedirectAttributes map){

        try{
            taskOperation.deleteTodo(id);
            map.addFlashAttribute("deleteTodoMessage","delete");
        }catch (Exception e){
            map.addFlashAttribute("deleteTodoMessage",e.getMessage());
        }

        return "redirect:"+myCustomModelMap.getCurrentUrl();
    }

    @GetMapping("/todo/delete/{id}")
    public String getDeleteTodo(@PathVariable Long id,
                                RedirectAttributes map){

        map.addFlashAttribute("deleteTodoMessage","The request to delete the item could not be processed. Please use the appropriate method to perform this action or contact support for assistance.");

        return "redirect:"+myCustomModelMap.getCurrentUrl();

    }

    @PostMapping("/todo/edit")
    public String editTodo(@Valid Todo todo,
                           BindingResult result,
                           RedirectAttributes map){
        System.out.println("Edit TODO : " + todo);
        try{

            if(result.hasErrors()){
                var errorMessage = Objects
                        .requireNonNull(result.getFieldError()).getDefaultMessage();
                map.addFlashAttribute("editTodoMessage",errorMessage);
            }else {

                taskOperation.updateTodo(todo);
                map.addFlashAttribute("editTodoMessage","success");
            }

        }catch (Exception e){
            System.out.println("Error : "+e.getMessage());
            map.addFlashAttribute("editTodoMessage",e.getMessage());
        }

        return "redirect:"+myCustomModelMap.getCurrentUrl();
    }


    @PostMapping("/todo/add")
    public String addNew(@Valid Todo todo,
                         BindingResult result,
                         RedirectAttributes reMap){
        System.out.println("New Task : "+todo);
        try{
            if(result.hasErrors()){

                var errorMessage = Objects
                        .requireNonNull(result.getFieldError())
                        .getDefaultMessage();

                if(errorMessage.contains("Failed to convert")){
                    reMap.addFlashAttribute("addTodoMessage","Invalid date input. Please enter valid date.");
                }else {
                    reMap.addFlashAttribute("addTodoMessage",errorMessage);
                }

            }else {
                reMap.addFlashAttribute("addTodoMessage","add");
                taskOperation.addNewTodo(todo);
            }

        }catch (Exception e){
            reMap.addFlashAttribute("addTodoMessage",e.getMessage());
            System.out.println("Error : "+e);
        }

        return "redirect:"+myCustomModelMap.getCurrentUrl();
    }


}
