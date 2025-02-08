package com.myapp.pea.Services;

import com.myapp.pea.DTO.Request.List.ListAddRequestDTO;
import com.myapp.pea.DTO.Request.List.ListUpdateRequestDTO;
import com.myapp.pea.DTO.Response.ListResponseDTO;
import com.myapp.pea.Entities.List;
import com.myapp.pea.Entities.User;
import com.myapp.pea.ExceptionErrorsHandler.CustomExceptionErrors.ListNotFoundException;
import com.myapp.pea.ExceptionErrorsHandler.CustomExceptionErrors.UserNotFoundException;
import com.myapp.pea.Repositories.ListRepo;
import com.myapp.pea.Repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Service
public class ListService {

    private final ListRepo listRepo;
    private final UserRepo userRepo;

    private User getCurrentUser() {
        return userRepo.findByGoogleId(3L)
                .orElseThrow(() -> new UserNotFoundException("User not found."));
    }

    public ListResponseDTO addList(ListAddRequestDTO request){

        var list = List.builder()
                .listName(request.getListName())
                .user(getCurrentUser())
                .date(LocalDateTime.now())
                .build();

        var listSave = listRepo.save(list);

        return ListResponseDTO.fromEntity(listSave);
    }

    @Transactional
    public ListResponseDTO updateList(ListUpdateRequestDTO update){

        var currentList = listRepo.findByUser_IdAndId(getCurrentUser().getId(), update.getId())
                .orElseThrow(() -> new ListNotFoundException("List item not found."));

        currentList.setListName(update.getListName());
        currentList.setDate(LocalDateTime.now());

        var saveList = listRepo.save(currentList);

        return ListResponseDTO.fromEntity(saveList);

    }

    @Transactional
    public ListResponseDTO deleteListById(Long id){

        var searchList = getListById(id);

        listRepo.deleteById(searchList.getId());

        return searchList;
    }

    @Transactional
    public int deleteAllList(boolean isDelete){

        if(!isDelete)
            return 0;

        var getDeletableList = listRepo.findAllByUser_Id(getCurrentUser().getId())
                .stream().filter(list -> !list.getListName().equals("Personal") && !list.getListName().equals("Work"))
                .toList();

        log.info("Deletable List : {}",getDeletableList);

        if(getDeletableList.isEmpty())
            return 0;

        listRepo.deleteAll(getDeletableList);

        return getDeletableList.size();

    }

    @Transactional(readOnly = true)
    public ListResponseDTO getListById(Long id){

        var searchList = listRepo.findByUser_IdAndId(getCurrentUser().getId(), id)
                .orElseThrow(() -> new ListNotFoundException("List item not found."));

        return ListResponseDTO.fromEntity(searchList);
    }

}
