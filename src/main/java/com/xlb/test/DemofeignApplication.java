package com.xlb.test;

import java.util.List;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@SpringBootApplication
@RestController
public class DemofeignApplication {

    private static Map<String, Contributor> map = Maps.newConcurrentMap();
    static {
        Contributor c1 = new Contributor("张三", 1);
        Contributor c2 = new Contributor("李四", 2);
        Contributor c3 = new Contributor("王五", 3);
        map.put("001", c1);
        map.put("002", c2);
        map.put("003", c3);
    }

    @RequestMapping(value = "/{owner}", method = RequestMethod.GET)
    public List<Contributor> contributors(@PathVariable String owner) {
        List<Contributor> contributors = Lists.newArrayList();
        if (map.get(owner) == null) {
            throw new ServiceException("未找到资源");
        } else {
            contributors.add(map.get(owner));
            return contributors;
        }

    }

    public static void main(String[] args) {
        SpringApplication.run(DemofeignApplication.class, args);
    }
}
