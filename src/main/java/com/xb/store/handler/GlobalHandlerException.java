package com.xb.store.handler;

import com.xb.store.exception.*;
import com.xb.store.utils.JsonResult;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalHandlerException{
    @ExceptionHandler({ServiceException.class,FileUploadException.class})
    public JsonResult<Void> UserNameException(Throwable e){
        JsonResult<Void> jsonResult=new JsonResult<>();
        jsonResult.setMessage(e.getMessage());
        if(e instanceof UserNameDuplicatedException){
            jsonResult.setState(4000);
        }else if(e instanceof InsertException){
            jsonResult.setState(5000);
        }else if(e instanceof UserNameNotFountException){
            jsonResult.setState(4500);
        }else if(e instanceof PassWordNotMatchException){
            jsonResult.setState(4000);
        }else if(e instanceof UpdateException){
            jsonResult.setState(4511);
        }else if(e instanceof FileEmptyException){
            jsonResult.setState(4111);
        }else if(e instanceof FileSizeException){
            jsonResult.setState(4222);
        }else if(e instanceof  FileStateException){
            jsonResult.setState(4333);
        }else if(e instanceof FileTypeException){
            jsonResult.setState(4444);
        }else if(e instanceof  FileUploadIoException){
            jsonResult.setState(4555);
        }else if(e instanceof  AddressCountLimitException){
            jsonResult.setState(4666);
        }else if(e instanceof  AddressNotFoundException){
            jsonResult.setState(4777);
        }else if(e instanceof AccessDeniedException){
            jsonResult.setState(4888);
        }else if(e instanceof DeleteException){
            jsonResult.setState(4889);
        }else if(e instanceof ProductNotFoundException){
            jsonResult.setState(4999);
        }
        return jsonResult;
    }
}
