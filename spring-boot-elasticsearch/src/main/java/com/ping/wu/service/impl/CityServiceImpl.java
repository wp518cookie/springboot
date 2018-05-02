package com.ping.wu.service.impl;

import com.ping.wu.constant.ParamConstant;
import com.ping.wu.model.City;
import com.ping.wu.service.CityService;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.search.SearchHits;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


/**
 * Created by ping.wu on 2018/4/28.
 */
@Service
public class CityServiceImpl implements CityService {
    private static final Logger log = LoggerFactory.getLogger(CityServiceImpl.class);

    private final TransportClient elasticsearchClient;

    public CityServiceImpl(TransportClient elasticsearchClient) {
        this.elasticsearchClient = elasticsearchClient;
    }

    public String initial() {
        try {
            CreateIndexRequestBuilder cib = elasticsearchClient.admin().indices().prepareCreate(ParamConstant.CITY_INDEX);
            XContentBuilder mapping = XContentFactory.jsonBuilder()
                    .startObject()
                    .startObject("properties")
                    .startObject("id")
                    .field("type","long") //设置数据类型
                    .endObject()
                    .startObject("provinceId")
                    .field("type","long")
                    .endObject()
                    .startObject("cityName")
                    .field("type","text")
                    .endObject()
                    .startObject("description")
                    .field("type","text")
                    .endObject()
                    .endObject()
                    .endObject();
            cib.addMapping(ParamConstant.CITY_CONTENT, mapping);

            CreateIndexResponse res=cib.execute().actionGet();
            System.out.println("----------添加映射成功----------");
        } catch (Exception e) {
            e.printStackTrace();
            log.info("添加索引失败!");
        }
        return  null;
    }

    public String save(City city) {
        try {
            IndexResponse response = elasticsearchClient.prepareIndex(ParamConstant.CITY_INDEX, ParamConstant.CITY_CONTENT)
                    .setSource(XContentFactory.jsonBuilder().startObject()
                            .field("id", city.getId())
                            .field("provinceId", city.getProvinceId())
                            .field("cityName", city.getCityName())
                            .field("description", city.getDescription())
                            .endObject())
                    .get();
            System.out.println("添加索引成功,版本号:" + response.getId());
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    public String listCity() {
        SearchResponse response = elasticsearchClient.prepareSearch(ParamConstant.CITY_INDEX).setTypes(ParamConstant.CITY_CONTENT).get();
        SearchHits hits = response.getHits();
        if (hits.getHits().length > 0) {
            return "查询到:" + hits.getHits().length;
        } else {
            return "未查询到任何结果！";
        }
    }

    public String getCityById(Long id) {
        GetResponse response = elasticsearchClient.prepareGet(ParamConstant.CITY_INDEX, ParamConstant.CITY_CONTENT, id.toString()).execute()
                .actionGet();
        String json = response.getSourceAsString();
        if (!StringUtils.isEmpty(json)) {
            log.info(json);
            return json;
        } else {
            log.info("未查询到任何结果！");
            return("未查询到任何结果！");
        }
    }
}
