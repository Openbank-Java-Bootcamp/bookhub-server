package com.ironhack.bookhubserver.Service.Interface;

import com.ironhack.bookhubserver.DTO.BookDTOPages;

public interface BookUserServiceInterface {
      void editPagesBook(long id, BookDTOPages bookDT0Pages, String logEmail);
}
