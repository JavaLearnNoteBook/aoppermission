package com.molly.aoppermission.aoppermission.mapper;

import com.molly.aoppermission.aoppermission.Model.User;

import java.util.ArrayList;
import java.util.List;

public class QueryPermissionMapper {

    public static List<String> permissions(User user) {
        List<String> anc = new ArrayList<>();
        if (user.getName().equals("aaa")) {
            anc.add("123");
            anc.add("124");
            anc.add("125");
        }else {
            anc.add("126");
        }
        return anc;
    }
}