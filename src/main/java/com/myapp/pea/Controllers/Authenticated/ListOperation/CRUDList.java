package com.myapp.pea.Controllers.Authenticated.ListOperation;

import com.myapp.pea.Models.Lists;
import com.myapp.pea.Services.Lists.ListsOperation;
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
public class CRUDList {

    private final ListsOperation listsOperation;

    @PostMapping("/list/add")
    public String createNewList(@Valid @ModelAttribute("list") Lists list,
                                BindingResult result,
                                RedirectAttributes reMap){

        if(result.hasErrors()){
            String errorMessage = Objects.requireNonNull(result.getFieldError()).getDefaultMessage();
            reMap.addFlashAttribute("createListMessage",errorMessage);
            System.out.println("Error : "+errorMessage);
        }else
            listsOperation.createNewList(list);

        return "redirect:/pea/home";
    }

    @PostMapping("/list/delete/{id}")
    public String deleteList(Long id,
                             RedirectAttributes reMap){
        try{
            listsOperation.deleteList(id);
            reMap.addFlashAttribute("deleteListMessage","success");
        }catch (Exception e){
            System.out.println("Error : "+e);
            reMap.addFlashAttribute("deleteListMessage",e.getMessage());
        }

        return "redirect:/pea/home";
    }

    @PostMapping("/list/edit")
    public String editList(@Valid Lists editList,
                           BindingResult result,
                           RedirectAttributes reMap){

        try{

            if(result.hasErrors()){

                String errorMessage = Objects.requireNonNull(result.getFieldError()).getDefaultMessage();

                reMap.addFlashAttribute("editListMessage",errorMessage);
            }else{
                listsOperation.updateList(editList);
                reMap.addFlashAttribute("editListMessage","");
            }
        }catch (Exception e){
            System.out.println("Error : "+e.getMessage());
            reMap.addFlashAttribute("editListMessage",e.getMessage());
        }

        return "redirect:/pea/home";
    }

}
