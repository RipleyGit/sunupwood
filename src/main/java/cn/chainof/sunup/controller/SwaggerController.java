package cn.chainof.sunup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SwaggerController {

    @Autowired
    private ResourceLoader resourceLoader;

    @RequestMapping(value = "/swagger/yamls",
            method = RequestMethod.GET)
    public List<String> getYamlUrl() throws IOException {
        List<String> list = new ArrayList<>();
        Resource[] resources = ResourcePatternUtils.getResourcePatternResolver(resourceLoader).getResources
                ("classpath:static/swagger/yaml/*.yaml");

        for (Resource resource : resources) {
            list.add(resource.getFilename());
        }

        return list;
    }
}
