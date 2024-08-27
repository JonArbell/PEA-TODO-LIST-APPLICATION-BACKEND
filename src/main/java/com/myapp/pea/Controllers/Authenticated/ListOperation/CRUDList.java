package com.myapp.pea.Controllers.Authenticated.ListOperation;

import com.myapp.pea.Controllers.CustomComponent.MyCustomModelMap;
import com.myapp.pea.Models.Lists;
import com.myapp.pea.Services.ListsService.ListsOperation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Objects;

@Controller
@AllArgsConstructor
public class CRUDList {

    private final ListsOperation listsOperation;
    private final MyCustomModelMap myCustomModelMap;

    @PostMapping("/list/add")
    public String createNewList(@Valid @ModelAttribute("list") Lists list,
                                BindingResult result,
                                RedirectAttributes reMap){

        if(result.hasErrors()){
            String errorMessage = Objects.requireNonNull(result.getFieldError()).getDefaultMessage();
            reMap.addFlashAttribute("createListMessage",errorMessage);

        }else{
            listsOperation.createNewList(list);
            reMap.addFlashAttribute("createListMessage","success");
        }

        return "redirect:"+myCustomModelMap.getCurrentUrl();
    }

    @PostMapping("/list/delete/{id}")
    public String deleteList(@PathVariable Long id,
                            boolean deleteTasks,
                             RedirectAttributes reMap){

        try{
            listsOperation.deleteList(id,deleteTasks);
            reMap.addFlashAttribute("deleteListMessage","success");
        }catch (Exception e){
            System.out.println("Error : "+e);
            reMap.addFlashAttribute("deleteListMessage",e.getMessage());
        }

        return "redirect:"+myCustomModelMap.getCurrentUrl();
    }

    @PostMapping("/list/edit")
    public String editList(@Valid @ModelAttribute Lists list,
                           BindingResult result,
                           RedirectAttributes reMap){

        try{

            if(result.hasErrors()){

                String errorMessage = Objects.requireNonNull(result.getFieldError()).getDefaultMessage();

                reMap.addFlashAttribute("editListMessage",errorMessage);
            }else{
                reMap.addFlashAttribute("editListMessage","success");
                listsOperation.updateListName(list);
            }
        }catch (Exception e){
            System.out.println("Error : "+e.getMessage());
            reMap.addFlashAttribute("editListMessage",e.getMessage());
        }

        return "redirect:"+myCustomModelMap.getCurrentUrl();
    }

}
