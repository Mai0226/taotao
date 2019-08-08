package com.taotao.search.service.impl;

import com.taotao.common.pojo.SearchItem;
import com.taotao.common.pojo.SearchResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.SearchItemMapper;
import com.taotao.search.dao.SearchDao;
import com.taotao.search.service.SearchItemService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
@Service
public class SearchItemServiceImpl implements SearchItemService {
    @Autowired
    private SearchItemMapper searchItemMapper;
    @Autowired
    private SolrServer solrServer;
    @Autowired
    private SearchDao searchDao;
    @Override
    public TaotaoResult getAllItems() {
        List<SearchItem> searchItems = searchItemMapper.getAllItems();
        for (SearchItem searchItem:searchItems) {
            try {
                // 5、向索引库中添加文档。
                SolrInputDocument document = new SolrInputDocument();
                document.addField("id", searchItem.getId());
                document.addField("item_title", searchItem.getTitle());
                document.addField("item_sell_point", searchItem.getSell_point());
                document.addField("item_price", searchItem.getPrice());
                document.addField("item_image", searchItem.getImage());
                document.addField("item_category_name", searchItem.getCategory_name());
                document.addField("item_desc", searchItem.getItem_desc());
                solrServer.add(document);
            } catch (SolrServerException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            solrServer.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return TaotaoResult.ok();
    }

    @Override
    public SearchResult search(String queryString, int page, int rows) {
        SolrQuery query = new SolrQuery();
        if (queryString!=null&&!"".equals(queryString)){
            query.setQuery(queryString);
            System.out.println(queryString);
        }else {
            query.setQuery("*:*");
        }
        query.setStart((page-1)*rows);
        query.setRows(rows);
        query.set("df", "item_keywords");
        query.setHighlight(true);
        query.addHighlightField("item_title");
        query.setHighlightSimplePre("<span style='color:red'>");
        query.setHighlightSimplePost("</span>");
        try {
            SearchResult searchResult = searchDao.search(query);
            Long recordCount = searchResult.getRecordCount();
            Long pageCount = recordCount%rows==0?recordCount/rows:recordCount/rows+1;
            searchResult.setPageCount(pageCount);
            return searchResult;
        } catch (SolrServerException e) {
            e.printStackTrace();
        }

        return null;
    }
}
