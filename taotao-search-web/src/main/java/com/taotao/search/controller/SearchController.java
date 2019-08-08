package com.taotao.search.controller;

import com.taotao.common.pojo.SearchResult;
import com.taotao.search.service.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;

@Controller
public class SearchController {
    @Autowired
    private SearchItemService searchItemService;
    @RequestMapping("/search")
    public String search(@RequestParam("q") String queryString, @RequestParam(defaultValue = "1") Integer page, Model model){
        try {
            byte[] bytes  = queryString.getBytes("ISO-8859-1");
            String q = new String(bytes,"utf-8");
            SearchResult searchResult = searchItemService.search(q,page,30);
            model.addAttribute("query", q);
            model.addAttribute("totalPages", searchResult.getPageCount());
            model.addAttribute("itemList", searchResult.getSearchItems());
            model.addAttribute("page", page);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "search";
    }
}
