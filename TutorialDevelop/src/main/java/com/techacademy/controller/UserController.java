package com.techacademy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam; 
import java.util.Set;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techacademy.service.UserService;

import org.springframework.web.bind.annotation.ModelAttribute; // 追加
import org.springframework.web.bind.annotation.PostMapping; 
import com.techacademy.entity.User; 
@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService service;

    /** 一覧画面を表示 */
    @GetMapping("/list")
    public String getList(Model model) {
        // 全件検索結果をModelに登録
        model.addAttribute("userlist", service.getUserList());
        // user/list.htmlに画面遷移
        return "user/list";
    }
    // ----- 追加:ここから -----
    /** User登録画面を表示 */
    @GetMapping("/register")
    public String getRegister(@ModelAttribute User user) {
        // User登録画面に遷移
        return "user/register";
    }

    /** User登録処理 */
    @PostMapping("/register")
    public String postRegister(User user) {
        // User登録
        service.saveUser(user);
        // 一覧画面にリダイレクト
        return "redirect:/user/list";
    }
    /** User削除処理 */
    @PostMapping(path="list", params="deleteRun")
    public String deleteRun(@RequestParam(name="idck") Set<Integer> idck, Model model) {
        // Userを一括削除
        service.deleteUser(idck);
        // 一覧画面にリダイレクト
        return "redirect:/user/list";
    }
}
