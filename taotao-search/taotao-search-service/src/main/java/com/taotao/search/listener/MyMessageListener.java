package com.taotao.search.listener;

import com.taotao.common.pojo.ActiveMq;
import com.taotao.search.service.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.*;

public class MyMessageListener implements MessageListener {
    @Autowired
    private SearchItemService searchItemService;
    @Override
    public void onMessage(Message message) {
        try {
            ObjectMessage objectMessage = (ObjectMessage) message;
            ActiveMq activeMq = (ActiveMq) objectMessage.getObject();
            if ("add".equals(activeMq.getOp())){
                searchItemService.addDocument(activeMq.getId());
            }else if ("delete".equals(activeMq.getOp())){
                for (Long itemId:activeMq.getIds()) {
                searchItemService.deleteDocument(itemId);
                }
            }else if ("update".equals(activeMq.getOp())){
                searchItemService.addDocument(activeMq.getId());
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
