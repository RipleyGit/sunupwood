package cn.chainof.sunup.controller;

import cn.chainof.sunup.controller.api.ProductApi;
import cn.chainof.sunup.controller.dto.data.ProductDTO;
import cn.chainof.sunup.exception.ClientException;
import cn.chainof.sunup.model.Product;
import cn.chainof.sunup.service.ProductService;
import cn.chainof.sunup.utils.AutoConvertUtil;
import cn.chainof.sunup.utils.DateUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.util.StringUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class ProductApiController implements ProductApi {

    @Autowired
    private ProductService productService;

    @Override
    public ResponseEntity<Void> addProductInfo(@ApiParam(value = "" ,required=true )  @Valid @RequestBody ProductDTO productInfo){
        if (StringUtil.isEmpty(productInfo.getName())){
            throw new ClientException("商品名不能为空");
        }
        Product product = AutoConvertUtil.autoConvertTo(productInfo, Product.class);
        product.setImgUrls(JSON.toJSONString(productInfo.getImgUrls()));
        product.setLabels(JSON.toJSONString(productInfo.getLabels()));
        productService.addProductInfo(product);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<Void> deleteProducts(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "ids", required = true) List<String> ids){
        if (ids !=null && ids.size()>0){
            productService.deleteProducts(ids);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<List<ProductDTO>> getProducts(@ApiParam(value = "") @Valid @RequestParam(value = "key", required = false) String key,@ApiParam(value = "当前页数") @Valid @RequestParam(value = "pageIndex", required = false) Integer pageIndex,@ApiParam(value = "页面大小") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize){
        pageIndex = pageIndex == null ? 0 : pageIndex;
        pageSize = pageSize == null ? 10:pageSize;
        List<Product> list = productService.queryProducts(key,pageIndex,pageSize);
        List<ProductDTO> dtoList = new ArrayList<>();
        for (Product product:list) {
            ProductDTO dto = AutoConvertUtil.autoConvertTo(product,ProductDTO.class);
            if (StringUtil.isNotEmpty(product.getImgUrls())){
                List<String> imgUrls = JSON.parseArray(product.getImgUrls(), String.class);
                dto.setImgUrls(imgUrls);
            }
            if (StringUtil.isNotEmpty(product.getLabels())){
                List<String> labels = JSON.parseArray(product.getLabels(), String.class);
                dto.setLabels(labels);
            }
            if (product.getCreateTime() != null) {
                dto.setCreateTime(DateUtil.getDateStr(product.getCreateTime()));
            }
            if (product.getUpdateTime()!=null) {
                dto.setUpdateTime(DateUtil.getDateStr(product.getUpdateTime()));
            }
            dtoList.add(dto);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(dtoList,headers, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<Void> modifyProductInfo(@ApiParam(value = "id,name不能为空" ,required=true )  @Valid @RequestBody ProductDTO productInfo){
        if (StringUtil.isEmpty(productInfo.getId())||StringUtil.isEmpty(productInfo.getName())){
            throw new ClientException("商品ID和商品名称不能为空！");
        }
        Product product = AutoConvertUtil.autoConvertTo(productInfo, Product.class);
        product.setImgUrls(JSON.toJSONString(productInfo.getImgUrls()));
        product.setLabels(JSON.toJSONString(productInfo.getLabels()));
        productService.modifyProductInfo(product);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<List<ProductDTO>> queryListByItem(@NotNull @ApiParam(value = "必填 分类ID", required = true) @Valid @RequestParam(value = "itemId", required = true) String itemId,@ApiParam(value = "当前页数") @Valid @RequestParam(value = "pageIndex", required = false) Integer pageIndex,@ApiParam(value = "页面大小") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize){
        pageIndex = pageIndex == null ? 0 : pageIndex;
        pageSize = pageSize == null ? 10:pageSize;
        List<Product> list = productService.queryListByItem(itemId,pageIndex,pageSize);
        List<ProductDTO> dtoList = new ArrayList<>();
        for (Product product:list) {
            ProductDTO dto = AutoConvertUtil.autoConvertTo(product,ProductDTO.class);
            if (StringUtil.isNotEmpty(product.getImgUrls())){
                List<String> imgUrls = JSON.parseArray(product.getImgUrls(), String.class);
                dto.setImgUrls(imgUrls);
            }
            if (StringUtil.isNotEmpty(product.getLabels())){
                List<String> labels = JSON.parseArray(product.getLabels(), String.class);
                dto.setLabels(labels);
            }
            if (product.getCreateTime() != null) {
                dto.setCreateTime(DateUtil.getDateStr(product.getCreateTime()));
            }
            if (product.getUpdateTime()!=null) {
                dto.setUpdateTime(DateUtil.getDateStr(product.getUpdateTime()));
            }
            dtoList.add(dto);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(dtoList,headers, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<List<ProductDTO>> queryListByLabel(@NotNull @ApiParam(value = "必填 标签ID", required = true) @Valid @RequestParam(value = "labelId", required = true) String labelId,@ApiParam(value = "当前页数") @Valid @RequestParam(value = "pageIndex", required = false) Integer pageIndex,@ApiParam(value = "页面大小") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize){
        pageIndex = pageIndex == null ? 0 : pageIndex;
        pageSize = pageSize == null ? 10:pageSize;
        List<Product> list = productService.queryListByLabel(labelId,pageIndex,pageSize);
        List<ProductDTO> dtoList = new ArrayList<>();
        for (Product product:list) {
            ProductDTO dto = AutoConvertUtil.autoConvertTo(product,ProductDTO.class);
            if (StringUtil.isNotEmpty(product.getImgUrls())){
                List<String> imgUrls = JSON.parseArray(product.getImgUrls(), String.class);
                dto.setImgUrls(imgUrls);
            }
            if (StringUtil.isNotEmpty(product.getLabels())){
                List<String> labels = JSON.parseArray(product.getLabels(), String.class);
                dto.setLabels(labels);
            }
            if (product.getCreateTime() != null) {
                dto.setCreateTime(DateUtil.getDateStr(product.getCreateTime()));
            }
            if (product.getUpdateTime()!=null) {
                dto.setUpdateTime(DateUtil.getDateStr(product.getUpdateTime()));
            }
            dtoList.add(dto);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(dtoList,headers, HttpStatus.OK);
    }

}
