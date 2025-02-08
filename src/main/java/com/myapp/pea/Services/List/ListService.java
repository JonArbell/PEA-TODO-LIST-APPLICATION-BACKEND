package com.myapp.pea.Services.List;

import com.myapp.pea.DTO.Request.List.ListAddRequestDTO;
import com.myapp.pea.DTO.Request.List.ListUpdateRequestDTO;
import com.myapp.pea.DTO.Response.ListResponseDTO;
import com.myapp.pea.Entities.List;
import com.myapp.pea.ExceptionErrorsHandler.CustomExceptionErrors.ListNotFoundException;
import com.myapp.pea.Repositories.ListRepo;
import com.myapp.pea.Services.User.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Service
public class ListService {

    private final ListRepo listRepo;
    private final UserService userService;

    public Long getCurrentUserId() {
        return userService.getCurrentUser().getId();
    }

    public ListResponseDTO addList(ListAddRequestDTO request){

        var list = List.builder()
                .listName(request.getListName())
                .user(userService.getCurrentUser())
                .date(LocalDateTime.now())
                .build();

        var listSave = listRepo.save(list);

        return ListResponseDTO.fromEntity(listSave);
    }

    @Transactional
    @CachePut(value = "lists", key = "#update.id + '-' + #root.target.getCurrentUserId()")
    public ListResponseDTO updateList(ListUpdateRequestDTO update){

        var currentList = listRepo.findByUser_IdAndId(getCurrentUserId(), update.getId())
                .orElseThrow(() -> new ListNotFoundException("List item not found."));

        currentList.setListName(update.getListName());
        currentList.setDate(LocalDateTime.now());

        var saveList = listRepo.save(currentList);

        return ListResponseDTO.fromEntity(saveList);

    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(value = "lists",key = "'allLists-' + #root.target.getCurrentUserId()"),
            @CacheEvict(value = "lists",key = "#id + '-' + #root.target.getCurrentUserId()")
    })
    public ListResponseDTO deleteListItem(Long id){

        var searchList = listRepo.findByUser_IdAndId(getCurrentUserId(),id)
                .map(list -> {

                    if(list.getListName().equals("Work") || list.getListName().equals("Personal"))
                        throw new RuntimeException("Cannot delete Personal and Work list");

                    return list;
                })
                .orElseThrow(()-> new RuntimeException("List item not found."));

        listRepo.delete(searchList);

        return ListResponseDTO.fromEntity(searchList);
    }

    @Transactional
    @CacheEvict(value = "lists", allEntries = true)
    public int deleteAllList(boolean isDelete){

        if(!isDelete)
            return 0;

        var getDeletableList = listRepo.findAllByUser_Id(getCurrentUserId())
                .stream().map(ListResponseDTO::fromEntity)
                .toList()
                .stream()
                .filter(list -> !list.getListName().equals("Personal") && !list.getListName().equals("Work"))
                .map(ListResponseDTO::getId)
                .toList();

        log.info("Deletable List : {}",getDeletableList);

        if(getDeletableList.isEmpty())
            return 0;

        listRepo.deleteAllById(getDeletableList);

        return getDeletableList.size();

    }

    @Transactional(readOnly = true)
    @Cacheable(value = "lists",key = "#id + '-'+#root.target.getCurrentUserId()")
    public ListResponseDTO getListItem(Long id){

        var searchList = listRepo.findByUser_IdAndId(getCurrentUserId(), id)
                .orElseThrow(() -> new ListNotFoundException("List item not found."));

        return ListResponseDTO.fromEntity(searchList);
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "lists",key = "'allLists-'+#root.target.getCurrentUserId()")
    public java.util.List<ListResponseDTO> getAllList(){

        return listRepo.findAllByUser_Id(getCurrentUserId())
                .stream().map(ListResponseDTO::fromEntity)
                .toList();
    }

}
