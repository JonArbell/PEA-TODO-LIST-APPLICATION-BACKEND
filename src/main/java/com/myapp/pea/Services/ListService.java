package com.myapp.pea.Services;


import com.myapp.pea.DTO.Request.ListAddRequestDTO;
import com.myapp.pea.DTO.Response.ListResponseDTO;
import com.myapp.pea.Entities.List;
import com.myapp.pea.Repositories.ListRepo;
import com.myapp.pea.Repositories.TodoRepo;
import com.myapp.pea.Repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Service
public class ListService {

    private final ListRepo listRepo;
    private final TodoRepo todoRepo;
    private final UserRepo userRepo;

    public ListResponseDTO addList(ListAddRequestDTO request){

        var user = userRepo.findByGoogleId(3L)
                .orElseThrow(() -> new RuntimeException("User not found."));

        var list = List.builder()
                .listName(request.getListName())
                .user(user)
                .date(LocalDateTime.now())
                .build();

        var listSave = listRepo.save(list);

        return ListResponseDTO.fromEntity(listSave);
    }

    public java.util.List<ListResponseDTO> getAllList(){

        return listRepo.findAll().stream().map(ListResponseDTO::fromEntity).filter(list -> list.getUserId().equals(1L)).toList();
    }

}
