package com.ironhack.bookhubserver.Controllers.Interface;

import com.ironhack.bookhubserver.DTO.BookDTOPages;
import com.ironhack.bookhubserver.DTO.DTOUserBookStatus;
import com.ironhack.bookhubserver.Model.UserBook;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface BookUserControllerInterface {
    void editPages(int id, BookDTOPages bookDT0Pages);

    UserBook getUserBook( long isbn);

    UserBook editStatus(long id, DTOUserBookStatus dtoUserBookStatus);
}
