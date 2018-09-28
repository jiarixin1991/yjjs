package com.tdts.action;


import com.opensymphony.xwork2.Action;

public class loginAction  extends BaseAction{
    public String login(){


        System.out.println("登录");

        String path = getRequest().getSession().getServletContext().getRealPath("/upload");


        return Action.SUCCESS;
    }




}
