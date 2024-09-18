package com.myapp.pea.Views.Authenticated.ListsPage;

import com.myapp.pea.Services.AccountService.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class ListsSortedByDateModified {

    private final UserService userService;

    @GetMapping("/list/{id}")
    public String getList(){

        if(!userService.isUserAuthenticated()){
            return "redirect:/";
        }

        return "authenticated/lists/listPage";
    }

//    @GetMapping("/list/{id}")
//    public String getList(@PathVariable Long id, ModelMap map, RedirectAttributes remap, HttpServletRequest request) throws TodoListNotFoundException{
//
//        if(id == null){
//            return "redirect:/";
//        }
//
//        try{
//
//            Lists list = getList(id);
//
//            List<Todo> allTodo = getTasks
//                            .allTodoDateModified()
//                            .stream()
//                            .filter(todo -> todo.getLists() != null)
//                            .filter(todo -> todo.getLists().getId().equals(id))
//                            .toList();
//
//            map.addAttribute("getListName",list.getListName());
//            myCustomModelMap.modelMap(map,allTodo,"tasksOfList","totalTaskOfList",request.getRequestURI());
//
//        }catch (TodoListNotFoundException e){
//            System.out.println("Error : "+e.getMessage());
//            remap.addFlashAttribute("getListError",e.getMessage());
//            return "redirect:/home";
//        }catch (RuntimeException e){
//            System.out.println("Runtime Error : "+e.getMessage());
//            remap.addFlashAttribute("getListError",e.getMessage());
//            return "redirect:/home";
//        }
//
//        return "authenticated/lists/listPage";
//    }

}
