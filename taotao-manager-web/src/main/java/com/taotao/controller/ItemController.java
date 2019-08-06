package com.taotao.controller;

import com.taotao.common.pojo.*;
import com.taotao.common.pojo.EasyUIResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;

    @RequestMapping("/item/{id}")
    @ResponseBody
    public TbItem getItemById(@PathVariable Long id) {
        TbItem tbItem = itemService.findiItemById(id);
        return tbItem;
    }

    @RequestMapping("/item/list")
    @ResponseBody
    public EasyUIResult findPage(Integer page, Integer rows) {
        EasyUIResult easyUIResult = itemService.findPageItems(page, rows);
        return easyUIResult;
    }

    /*批量删除商品*/
    @RequestMapping("/rest/item/delete")
    @ResponseBody
    public TaotaoResult deleteItems(Long[] ids) {
        TaotaoResult taotaoResult = itemService.deleteItems(ids);
        return taotaoResult;
    }

    /*商品下架*/
    @RequestMapping("/rest/item/instock")
    @ResponseBody
    public TaotaoResult instoc(Long[] ids) {
        TaotaoResult taotaoResult = itemService.instocByIds(ids);
        return taotaoResult;
    }

    /*商品上架*/
    @RequestMapping("/rest/item/reshelf")
    @ResponseBody
    public TaotaoResult reshelf(Long[] ids) {
        TaotaoResult taotaoResult = itemService.reshelfByIds(ids);
        return taotaoResult;
    }
    /*商品添加*/
    @RequestMapping("/item/save")
    @ResponseBody
    public TaotaoResult addItems(TbItem  tbItem,String desc){
        TaotaoResult taotaoResult = itemService.addItems(tbItem,desc);
        return taotaoResult;
    }
    /*回显商品描述*/
    @RequestMapping("/rest/item/query/item/desc/{id}")
    @ResponseBody
    public TaotaoResult update(@PathVariable  Long id){
        TaotaoResult taotaoResult = itemService.getItemDesc(id);
        System.out.println(taotaoResult.getStatus());
        System.out.println("--------------");
        TbItemDesc tbItemDesc = (TbItemDesc) taotaoResult.getData();
        System.out.println(tbItemDesc);
        return taotaoResult;
    }
    /*更新商品信息*/
    @RequestMapping("/rest/item/update")
    @ResponseBody
    public TaotaoResult updateItem(TbItem tbItem,String desc){
        TaotaoResult taotaoResult = itemService.updateItem(tbItem,desc);
        return taotaoResult;
    }
}
