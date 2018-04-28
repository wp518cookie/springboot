package com.ping.wu.service.impl;

import com.ping.wu.constant.ParamConstant;
import com.ping.wu.model.City;
import com.ping.wu.service.CityService;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by ping.wu on 2018/4/28.
 */
@Service
public class CityServiceImpl implements CityService {
    private static final Logger log = LoggerFactory.getLogger(CityServiceImpl.class);
    @Autowired
    private TransportClient elasticsearchClient;

    public String save(City city) {
        try {
            log.info("save start...");
            CreateIndexRequestBuilder cib=elasticsearchClient.admin().indices().prepareCreate(ParamConstant.CITY_INDEX);
            XContentBuilder mapping = XContentFactory.jsonBuilder()
                    .startObject()
                    .startObject("properties") //设置之定义字段
                    .startObject("id")
                    .field("type","long") //设置数据类型
                    .endObject()
                    .startObject("provinceId")
                    .field("type","id")
                    .endObject()
                    .startObject("cityName")
                    .field("type","string")
                    .endObject()
                    .startObject("description")
                    .field("type","string")
                    .endObject()
                    .endObject();
            cib.addMapping(ParamConstant.CITY_CONTENT, mapping);
            CreateIndexResponse res=cib.execute().actionGet();
        } catch (Exception e) {
            log.info("添加索引失败!");
        }
        return "success";
    }

    public String listCity() {
        GetResponse response = elasticsearchClient.prepareGet(ParamConstant.CITY_INDEX, ParamConstant.CITY_CONTENT,"1").execute()
                .actionGet();
        String json = response.getSourceAsString();
        if (null != json) {
            System.out.println(json);
        } else {
            System.out.println("未查询到任何结果！");
        }
        return "success";
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
