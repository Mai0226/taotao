package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIResult;
import com.taotao.common.pojo.IDUtils;
import com.taotao.common.pojo.SearchItem;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.ItemMapper;
import com.taotao.mapper.SearchItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.service.ItemService;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private SolrServer solrServer;
    @Autowired
    private SearchItemMapper searchItemMapper;
    @Override
    public TbItem findiItemById(Long id) {
        return itemMapper.findItemById(id);
    }

    @Override
    public EasyUIResult findPageItems(int page, int rows) {
        //分页显示初始化
        PageHelper.startPage(page,rows);
        //查询所有的商品信息
        List<TbItem> items = itemMapper.findAllItems();
        PageInfo<TbItem> pageInfo = new PageInfo<>(items);
        EasyUIResult easyUIResult = new EasyUIResult();
        //使用插件获取总条数
        easyUIResult.setTotal(pageInfo.getTotal());
        easyUIResult.setRows(items);
        return easyUIResult;
    }

    @Override
    public TaotaoResult deleteItems(Long[] ids) {
       int i = itemMapper.deleteItems(ids);
        if (i>=0){
            return TaotaoResult.ok();
        }
        return null;
    }


    @Override
    public TaotaoResult instocByIds(Long[] ids) {
        int i = itemMapper.instocByIds(ids);
        if (i>=0){
            return TaotaoResult.ok();
        }
        return null;
    }

    @Override
    public TaotaoResult reshelfByIds(Long[] ids) {
        int i = itemMapper.reshelfByIds(ids);
        if (i>=0){
            return TaotaoResult.ok();
        }
        return null;
    }

    @Override
    public TaotaoResult addItems(TbItem tbItem, String desc) {
        Long itemId = IDUtils.genItemId();
        Date date = new Date();
        tbItem.setId(itemId);
        tbItem.setCreated(date);
        tbItem.setUpdated(date);
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(itemId);
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setCreated(date);
        tbItemDesc.setUpdated(date);
        int itemCount= itemMapper.addItem(tbItem);
        int itemDescCount = itemMapper.addItemDesc(tbItemDesc);
        if (itemCount!=0&&itemDescCount!=0){
            try {
                SolrInputDocument document = new SolrInputDocument();
                SearchItem searchItem = searchItemMapper.getItemsById(itemId);
                document.addField("id", searchItem.getId());
                document.addField("item_title", searchItem.getTitle());
                document.addField("item_sell_point", searchItem.getSell_point());
                document.addField("item_price", searchItem.getPrice());
                document.addField("item_image", searchItem.getImage());
                document.addField("item_category_name", searchItem.getCategory_name());
                document.addField("item_desc", searchItem.getItem_desc());
                solrServer.add(document);
                solrServer.commit();
            } catch (SolrServerException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return TaotaoResult.ok();
        }
        return null;
    }

    @Override
    public TaotaoResult getItemDesc(Long id) {
        TbItemDesc tbItemDesc = itemMapper.getItemDesc(id);
        return TaotaoResult.ok(tbItemDesc);
    }

    @Override
    public TaotaoResult updateItem(TbItem tbItem, String desc) {
        Date date = new Date();
        Long itemId  =tbItem.getId();
        int descCount = itemMapper.updateDesc(desc,itemId);
        tbItem.setUpdated(date);
        int itemCount = itemMapper.updateItem(tbItem);
        if(descCount!=0&&itemCount!=0){
            return TaotaoResult.ok();
        }
        return null;
    }
}
