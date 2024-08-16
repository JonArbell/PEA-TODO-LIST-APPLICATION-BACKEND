package com.myapp.pea.Controllers.Authenticated.ListsPage;

import com.myapp.pea.Controllers.CustomComponent.MyCustomModelMap;
import com.myapp.pea.Exceptions.TodoListNotFoundException;
import com.myapp.pea.Models.Lists;
import com.myapp.pea.Models.Todo;
import com.myapp.pea.Services.Lists.GetLists;
import com.myapp.pea.Services.TodoService.GetTasks;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/pea")
public class ListsSortedByDateModified {

    private final GetLists getLists;
    private final GetTasks getTasks;
    private final MyCustomModelMap myCustomModelMap;

    private Lists getList(Long id){
        Lists list = getLists
                .allListsDateModified()
                .stream()
                .filter(get -> get.getId().equals(id))
                .findFirst()
                .orElse(null);

        if(list == null){
            throw new TodoListNotFoundException("Todo not found");
        }

        return list;
    }

    @GetMapping("/list/{id}")
    public String getList(@PathVariable Long id, ModelMap map, RedirectAttributes remap, HttpServletRequest request) throws TodoListNotFoundException{

        if(id == null){
            return "redirect:/pea";
        }

        try{

            Lists list = getList(id);

            List<Todo> allTodo = getTasks
                            .allTodoDateModified()
                            .stream()
                            .filter(todo -> todo.getLists() != null)
                            .filter(todo -> todo.getLists().getId().equals(id))
                            .toList();

            map.addAttribute("getListName",list.getListName());
            myCustomModelMap.modelMap(map,allTodo,"tasksOfList","totalTaskOfList",request.getRequestURI());

        }catch (TodoListNotFoundException e){
            System.out.println("Error : "+e.getMessage());
            remap.addFlashAttribute("getListError",e.getMessage());
            return "redirect:/pea";
        }catch (RuntimeException e){
            System.out.println("Runtime Error : "+e.getMessage());
            remap.addFlashAttribute("getListError",e.getMessage());
            return "redirect:/pea";
        }

        return "authenticated/lists/listPage";
    }

}
