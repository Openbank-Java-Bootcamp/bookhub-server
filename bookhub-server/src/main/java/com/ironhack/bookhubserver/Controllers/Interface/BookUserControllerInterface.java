package com.ironhack.bookhubserver.Controllers.Interface;

import com.ironhack.bookhubserver.DTO.BookDTOPages;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface BookUserControllerInterface {
    void editPages(int id, BookDTOPages bookDT0Pages);
}
