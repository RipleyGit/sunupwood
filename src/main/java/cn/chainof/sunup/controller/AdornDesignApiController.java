/**
 * NOTE: This class is auto generated by the swagger code generator program (2.3.1).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package cn.chainof.sunup.controller;

import cn.chainof.sunup.controller.api.AdornDesignApi;
import cn.chainof.sunup.controller.dto.data.AdornDesignDTO;
import cn.chainof.sunup.exception.ClientException;
import cn.chainof.sunup.service.AdornDesignService;
import cn.chainof.sunup.service.ProductDesignService;
import com.github.pagehelper.util.StringUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
@Slf4j
@RestController
public class AdornDesignApiController implements AdornDesignApi  {

    @Autowired
    private AdornDesignService adornDesignService;

    @Override
    public ResponseEntity<Void> addAdorn(@ApiParam(value = "装饰设计内容", required = true) @Valid @RequestBody AdornDesignDTO adornDto){
        if (StringUtil.isEmpty(adornDto.getLordImg())|| StringUtil.isEmpty(adornDto.getName())){
            throw new ClientException("主图和名称不能为空");
        }
        adornDesignService.addAdorn(adornDto);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<Void> deleteById(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "id", required = true) String id){
        adornDesignService.deleteById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }



    @Override
    public ResponseEntity<AdornDesignDTO> getAdornInfo(@NotNull @ApiParam(value = "唯一ID", required = true) @Valid @RequestParam(value = "id", required = true) String id){
        AdornDesignDTO dto = adornDesignService.getAdornInfoById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(dto,headers, HttpStatus.OK);
    }



    @Override
    public ResponseEntity<List<AdornDesignDTO>> queryAdornList(@ApiParam(value = "当前页数", defaultValue = "0") @Valid @RequestParam(value = "pageIndex", required = false, defaultValue = "0") Integer pageIndex, @ApiParam(value = "页面大小", defaultValue = "3") @Valid @RequestParam(value = "pageSize", required = false, defaultValue = "3") Integer pageSize){
        List<AdornDesignDTO> dtoList = adornDesignService.queryAdornList(pageIndex,pageSize);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(dtoList,headers, HttpStatus.OK);
    }



    @Override
    public ResponseEntity<Void> updateAdorn(@ApiParam(value = "装饰设计内容", required = true) @Valid @RequestBody AdornDesignDTO adornDto){
        if (StringUtil.isEmpty(adornDto.getLordImg())|| StringUtil.isEmpty(adornDto.getName())){
            throw new ClientException("主图和名称不能为空");
        }
        adornDesignService.updateAdorn(adornDto);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }


}
