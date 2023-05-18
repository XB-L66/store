package com.xb.store.controller;

import jakarta.servlet.http.HttpSession;

public class BaseController {
    protected final Integer getUidFromSession(HttpSession session){
        return Integer.parseInt(session.getAttribute("uid").toString());
    }
    protected final String getUnameFromSession(HttpSession session){
        return session.getAttribute("username").toString();
    }
}
