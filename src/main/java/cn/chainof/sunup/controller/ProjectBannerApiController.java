/**
 * NOTE: This class is auto generated by the swagger code generator program (2.3.1).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package cn.chainof.sunup.controller;

import cn.chainof.sunup.controller.api.ProjectBannerApi;
import cn.chainof.sunup.controller.dto.data.BannerDTO;
import cn.chainof.sunup.exception.ClientException;
import cn.chainof.sunup.service.ProjectBannerService;
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
@CrossOrigin
public class ProjectBannerApiController extends CrossOriginBase implements ProjectBannerApi {

    @Autowired
    private ProjectBannerService projectBannerService;

    @Override
    public ResponseEntity<Void> addBanner(@ApiParam(value = "", required = true) @Valid @RequestBody BannerDTO banner){
        if (StringUtil.isEmpty(banner.getImgUrl())){
            throw new ClientException("图片不能为空");
        }
        if (banner.getRank() == null){
            throw new ClientException("序号不能为空");
        }
        projectBannerService.addBanner(banner);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<Void> deleteBannerById(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "id", required = true) String id){
        projectBannerService.deleteBannerById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BannerDTO> getBannerById(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "id", required = true) String id){
        BannerDTO dto = projectBannerService.getBannerById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(dto,headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<BannerDTO>> getBannerList(@ApiParam(value = "当前页数", defaultValue = "0") @Valid @RequestParam(value = "pageIndex", required = false, defaultValue = "0") Integer pageIndex, @ApiParam(value = "页面大小", defaultValue = "3") @Valid @RequestParam(value = "pageSize", required = false, defaultValue = "3") Integer pageSize){
        List<BannerDTO> dtoList = projectBannerService.queryList(pageIndex,pageSize);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(dtoList,headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> updateBanner(@ApiParam(value = "", required = true) @Valid @RequestBody BannerDTO banner){
        if (StringUtil.isEmpty(banner.getId())){
            throw new ClientException("更新时ID不能为空");
        }
        if (StringUtil.isEmpty(banner.getImgUrl())){
            throw new ClientException("图片不能为空");
        }
        if (banner.getRank() == null){
            throw new ClientException("序号不能为空");
        }
        projectBannerService.updateBanner(banner);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }
}
