package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.*;
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
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private Destination topicDestination;

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
    //商品删除的方法 需要加入索引同步
    @Override
    public TaotaoResult deleteItems(final Long[] ids) {
       int i = itemMapper.deleteItems(ids);
        if (i>=0){
            jmsTemplate.send(topicDestination, new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    ActiveMq activeMq = new ActiveMq();
                    activeMq.setOp("delete");
                    activeMq.setIds(ids);
                    ObjectMessage objectMessage = session.createObjectMessage(activeMq);
                    return objectMessage;
                }
            });
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
    //商品添加的方法 需要加入索引同步
    @Override
    public TaotaoResult addItems(TbItem tbItem, String desc) {
        final Long itemId = IDUtils.genItemId();
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
            jmsTemplate.send(topicDestination, new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    ActiveMq activeMq = new ActiveMq();
                    activeMq.setOp("add");
                    activeMq.setId(itemId);
                    ObjectMessage objectMessage = session.createObjectMessage(activeMq);
                    return objectMessage;
                }
            });
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
        final Long itemId  =tbItem.getId();
        int descCount = itemMapper.updateDesc(desc,itemId);
        tbItem.setUpdated(date);
        int itemCount = itemMapper.updateItem(tbItem);
        if(descCount!=0&&itemCount!=0){
            jmsTemplate.send(topicDestination, new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    ActiveMq activeMq = new ActiveMq();
                    activeMq.setOp("update");
                    activeMq.setId(itemId);
                    ObjectMessage objectMessage = session.createObjectMessage(activeMq);
                    return objectMessage;
                }
            });
            return TaotaoResult.ok();
        }
        return null;
    }
}
