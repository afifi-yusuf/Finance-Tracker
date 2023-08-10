package com.example.CS_IA_2;
import java.util.HashMap;

public class IDandPasswords {

    HashMap<String,String> logininfo = new HashMap<String,String>();

    IDandPasswords(){
        logininfo.put("M.Afifi","123");
        logininfo.put("123","456");
    }

    public HashMap getLoginInfo(){
        return logininfo;
    }
}