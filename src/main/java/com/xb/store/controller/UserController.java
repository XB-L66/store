package com.xb.store.controller;

import com.xb.store.entity.User;
import com.xb.store.exception.*;
import com.xb.store.service.UserService;
import com.xb.store.utils.JsonResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;
    @RequestMapping("/reg")
    public JsonResult<Void> reg(User user){
        JsonResult<Void> jsonResult=new JsonResult<>();
            userService.reg(user);
            jsonResult.setState(200);
            jsonResult.setMessage("注册成功");
        return jsonResult;
    }
    @RequestMapping("/login")
    public JsonResult<User> login(String username, String password, HttpSession session){
        JsonResult<User> jsonResult=new JsonResult<>();
        User loginUser = userService.login(username, password);
        session.setAttribute("uid",loginUser.getUid());
        session.setAttribute("username",loginUser.getUsername());
        System.out.println(getUidFromSession(session));
        System.out.println(getUnameFromSession(session));
        jsonResult.setState(200);
        jsonResult.setMessage("登录成功");
        jsonResult.setData(loginUser);
        return jsonResult;
    }
    @RequestMapping("/change_password")
    public JsonResult<Void> editPassword(String oldPassword, String newPassword, HttpSession session){
        JsonResult<Void> jsonResult=new JsonResult<>();
        userService.editPassword(getUidFromSession(session),getUnameFromSession(session),oldPassword,newPassword);
        jsonResult.setState(200);
        jsonResult.setMessage("修改密码成功");
        session.invalidate();
        return jsonResult;
    }
    @RequestMapping("/get_by_uid")
    public JsonResult<User> getInfoById(HttpSession session){
        JsonResult<User> jsonResult=new JsonResult<>();
        Integer uid=getUidFromSession(session);
        User user = userService.getById(uid);
        jsonResult.setState(200);
        jsonResult.setData(user);
        return jsonResult;
    }
    @RequestMapping("/change_info")
    public JsonResult<Void> editInfo(User user,HttpSession session){
        JsonResult<Void> jsonResult=new JsonResult<>();
        Integer uid=getUidFromSession(session);
        String username=getUnameFromSession(session);
        userService.editInfo(uid,username,user);
        jsonResult.setState(200);
        jsonResult.setMessage("修改成功！");
        return jsonResult;
    }
    private static final Integer AVATAR_SIZE=10*1024*1024;
    private static final List<String> AVATAR_TYPE=new ArrayList<>();
    static {
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/bmp");
        AVATAR_TYPE.add("image/gif");
    }
    @RequestMapping("/change_avatar")
    public JsonResult<String> editAvatar(HttpSession session, MultipartFile file, HttpServletRequest request){
        Integer uid=getUidFromSession(session);
        String username=getUnameFromSession(session);
        JsonResult<String> jsonResult=new JsonResult<>();
        if(file.isEmpty()){
            throw  new FileEmptyException("文件为空!");
        }
        if(file.getSize()>AVATAR_SIZE){
            throw new FileSizeException("文件超过上传大小！");
        }
        if(!AVATAR_TYPE.contains(file.getContentType())){
            throw new FileTypeException("文件上传类型不符！");
        }
        File rootFile= null;
        try {
            rootFile = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        File realPath=new File(rootFile.getAbsolutePath(),"/static/images/upload");
        System.out.println(rootFile);
        System.out.println(realPath);
        if(!realPath.exists()){
            realPath.mkdirs();
        }
        String name=file.getOriginalFilename();
        String fileType=name.substring(name.indexOf("."));
        String filename= UUID.randomUUID().toString().toUpperCase()+fileType;
        File file2=new File(realPath,filename);
        System.out.println(file2.getAbsoluteFile());
        try {
            file.transferTo(file2);
        }catch (IOException e) {
            throw new FileUploadIoException("文件读写异常!");
        }
        String avatar="/images/upload/"+filename;
        userService.editAvatar(uid,avatar,username);
        jsonResult.setState(200);
        jsonResult.setMessage("上传成功！");
        jsonResult.setData(avatar);
        return jsonResult;
    }
}
