package com.taotao.search.dao;

import com.taotao.common.pojo.SearchItem;
import com.taotao.common.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class SearchDao {
    @Autowired
    private SolrServer solrServer;

    public SearchResult search(SolrQuery query) throws SolrServerException {
        QueryResponse queryResponse = solrServer.query(query);
        SolrDocumentList solrDocuments = queryResponse.getResults();
        List<SearchItem> searchItems = new ArrayList<>();
        for (SolrDocument solrDocument: solrDocuments) {
            SearchItem searchItem = new SearchItem();
            searchItem.setId((String) solrDocument.get("id"));
            searchItem.setCategory_name((String) solrDocument.get("item_category_name"));
            searchItem.setImage((String) solrDocument.get("item_image"));
            searchItem.setPrice((long) solrDocument.get("item_price"));
            searchItem.setSell_point((String) solrDocument.get("item_sell_point"));
            Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
            List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
            String itemTitle = "";
            if (list!=null&&list.size()>0){
                itemTitle = list.get(0);
            }else {
                itemTitle = (String) solrDocument.get("item_title");
            }
            searchItem.setTitle(itemTitle);
            searchItems.add(searchItem);
        }
        SearchResult searchResult = new SearchResult();
        searchResult.setSearchItems(searchItems);
        searchResult.setRecordCount(solrDocuments.getNumFound());
        return searchResult;
    }
}
