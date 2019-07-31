package com.taotao.controller;

import com.alibaba.dubbo.common.URL;
import com.taotao.commom.pojo.EasyUIResult;
import com.taotao.commom.pojo.QueryVo;
import com.taotao.commom.pojo.ResultStatus;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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

    @RequestMapping("/rest/item/delete")
    @ResponseBody
    public ResultStatus deleteItems(Long[] ids) {
       /*QueryVo vo = new QueryVo();
        vo.setIds(ids);
        itemService.deleteItems(vo);*/
        for (int i = 0; i < ids.length; i++) {
            itemService.deleteItemById(ids[i]);
        }
        ResultStatus status = new ResultStatus();
        status.setStatus(200);
        return status;
    }
    @RequestMapping("/rest/item/instock")
    @ResponseBody
    public ResultStatus instoc(Long[] ids){
        /*System.out.println(ids);
        QueryVo vo = new QueryVo();
        vo.setIds(ids);*/
        for (int i = 0; i <ids.length; i++) {
            itemService.instocById(ids[i]);
        }
        /*itemService.reshelfItem(vo);*/
        ResultStatus status = new ResultStatus();
        status.setStatus(200);
        return status;
    }

    @RequestMapping("/rest/item/reshelf")
    @ResponseBody
    public ResultStatus reshelf(Long[] ids){
        /*System.out.println(ids);
        QueryVo vo = new QueryVo();
        vo.setIds(ids);*/
        for (int i = 0; i <ids.length; i++) {
            itemService.reshelfById(ids[i]);
        }
        /*itemService.reshelfItem(vo);*/
        ResultStatus status = new ResultStatus();
        status.setStatus(200);
        return status;
    }


    @RequestMapping("/rest/page/item-edit")
    @ResponseBody
    public ResultStatus itemEdit(@RequestBody TbItem tbItem){
        System.out.println(tbItem);
        ResultStatus status = new ResultStatus();
        status.setStatus(200);
        return status;
    }
}
