package com.taotao.portal.controller;

import com.taotao.common.pojo.JsonUtils;
import com.taotao.content.service.ContentService;
import com.taotao.pojo.TbContent;
import com.taotao.portal.pojo.Ad1Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private ContentService contentService;
    @Value("${AD1_CID}")
    private Long AD1_CID;
    @Value("${AD1_HEIGHT}")
    private Integer AD1_HEIGHT;
    @Value("${AD1_WIDTH}")
    private Integer AD1_WIDTH;
    @Value("${AD1_HEIGHT_B}")
    private Integer AD1_HEIGHT_B;
    @Value("${AD1_WIDTH_B}")
    private Integer AD1_WIDTH_B;
    @RequestMapping("/index")
    public String showIndex(Model model){
        List<TbContent> tbContents = contentService.findContentByCategoryId(AD1_CID);
        List<Ad1Node> nodes = new ArrayList<>();
        for (TbContent tbcontent:tbContents) {
            Ad1Node node = new Ad1Node();
            node.setHeight(AD1_HEIGHT);
            node.setHeightB(AD1_HEIGHT_B);
            node.setHref(tbcontent.getUrl());
            node.setWidth(AD1_WIDTH);
            node.setWidthB(AD1_WIDTH_B);
            node.setSrc(tbcontent.getPic());
            node.setSrcB(tbcontent.getPic2());
            nodes.add(node);
        }
        model.addAttribute("ad1", JsonUtils.objectToJson(nodes));
        return "index";
    }
}
