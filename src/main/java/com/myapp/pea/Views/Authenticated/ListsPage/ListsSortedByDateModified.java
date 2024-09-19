package com.myapp.pea.Views.Authenticated.ListsPage;

import com.myapp.pea.Services.AccountService.UserService;
import com.myapp.pea.Services.ListsService.GetLists;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor
public class ListsSortedByDateModified {

    private final UserService userService;
    private final GetLists getLists;

    @GetMapping("/list/{id}")
    public String getList(@PathVariable Long id){

        if(!userService.isUserAuthenticated()){
            return "redirect:/";
        }

        var getList = getLists
                .allListsDateModified()
                .stream()
                .filter(list -> list != null && list.getId().equals(id))
                .findFirst()
                .orElse(null);

        if(getList == null){
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
