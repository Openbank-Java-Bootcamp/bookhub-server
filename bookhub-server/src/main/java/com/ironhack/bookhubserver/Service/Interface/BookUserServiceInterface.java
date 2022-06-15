package com.ironhack.bookhubserver.Service.Interface;

import com.ironhack.bookhubserver.DTO.BookDTOPages;
import com.ironhack.bookhubserver.Model.UserBook;

public interface BookUserServiceInterface {
      void editPagesBook(long id, BookDTOPages bookDT0Pages, String logEmail);

      UserBook getUserBook(long id, String logEmail);

      UserBook getEditStatus(long id, String logEmail, String status);
}
