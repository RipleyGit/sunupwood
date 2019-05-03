package cn.chainof.sunup.controller;

import cn.chainof.sunup.constant.Const;
import cn.chainof.sunup.controller.api.ProductItemApi;
import cn.chainof.sunup.controller.dto.data.ItemDTO;
import cn.chainof.sunup.exception.ClientException;
import cn.chainof.sunup.model.ProductItem;
import cn.chainof.sunup.service.ProductItemService;
import cn.chainof.sunup.utils.AutoConvertUtil;
import com.github.pagehelper.util.StringUtil;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class ProductItemApiController implements ProductItemApi {

    @Autowired
    private ProductItemService productItemService;

    @Override
    public ResponseEntity<Void> addItem(@ApiParam(value = "", required = true) @Valid @RequestBody ItemDTO item) {
        if (item == null) {
            throw new ClientException("分类不能为空");
        }
        if (StringUtil.isEmpty(item.getItemName())) {
            throw new ClientException("分类名不能为空");
        }
        ProductItem productItem = productItemService.getItemByName(item.getItemName(),item.getParentId());
        if (productItem != null) {
            throw new ClientException("该分类已存在");
        }
        if (item.getIsRoot() != 0 && item.getParentId() == null) {
            throw new ClientException("非根分类需要选择上级分类");
        }else if (StringUtil.isEmpty(item.getParentId())) {
            item.setIsRoot(1);
        }
        ProductItem newItem = AutoConvertUtil.autoConvertTo(item, ProductItem.class);
        newItem.setItemParent(item.getParentId());
        newItem.setIsRoot(item.getIsRoot().byteValue());
        productItemService.addItem(newItem);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deletedItem(@NotNull @ApiParam(value = "分类ID", required = true) @Valid @RequestParam(value = "id", required = true) String id) {
        if (StringUtil.isEmpty(id)) {
            throw new ClientException("入参为空");
        }
        ProductItem item = productItemService.getItemById(id);
        if (item == null){
            throw new ClientException("删除的分类不存在");
        }
        productItemService.deletedItem(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ItemDTO>> getItems(@ApiParam(value = "分类关键字，分类名，描述，模糊查询") @Valid @RequestParam(value = "key", required = false) String key) {
        List<ProductItem> list = productItemService.queryListByKey(key);
        List<ItemDTO> dtoList = new ArrayList<>();
        for (ProductItem item:list) {
            ItemDTO dto = AutoConvertUtil.autoConvertTo(item, ItemDTO.class);
            String parentId = item.getItemParent();
            dto.setParentId(parentId);
            ProductItem parentItem = productItemService.getItemById(parentId);
            if (parentItem!= null) {
                dto.setParentName(parentItem.getItemName());
            }
            dto.setIsRoot(item.getIsRoot().intValue());
            dtoList.add(dto);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(dtoList,headers, HttpStatus.OK);
    }

    @Override
    public     ResponseEntity<List<ItemDTO>> getItemsByParentId(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "parentId", required = true) String parentId){
        List<ProductItem> list = productItemService.getItemsByParentId(parentId);
        List<ItemDTO> dtoList = new ArrayList<>();
        for (ProductItem item:list) {
            ItemDTO dto = AutoConvertUtil.autoConvertTo(item, ItemDTO.class);
            String parent = item.getItemParent();
            dto.setParentId(parent);
            ProductItem parentItem = productItemService.getItemById(parent);
            if (parentItem!= null) {
                dto.setParentName(parentItem.getItemName());
            }
            dto.setIsRoot(item.getIsRoot().intValue());
            dtoList.add(dto);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(dtoList,headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ItemDTO>> getRootItems() {
        List<ProductItem> list = productItemService.getRootItems();
        List<ItemDTO> dtoList = AutoConvertUtil.convert2List(list,ItemDTO.class);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(dtoList,headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> modifyItem(@ApiParam(value = "", required = true) @Valid @RequestBody ItemDTO item) {
        if (item == null || StringUtil.isEmpty(item.getId())|| StringUtil.isEmpty(item.getItemName())){
            throw new ClientException("修改的分类不能为空！");
        }
        ProductItem productItem = productItemService.getItemByName(item.getItemName(),item.getParentId());
        if (productItem != null) {
            throw new ClientException("该分类已存在");
        }
        if (item.getIsRoot() != 0 && item.getParentId() == null) {
            throw new ClientException("非根分类需要选择上级分类");
        }else if (StringUtil.isEmpty(item.getParentId())) {
            item.setIsRoot(Const.ONE);
        }
        ProductItem updateItem = AutoConvertUtil.autoConvertTo(item, ProductItem.class);
        updateItem.setItemParent(item.getParentId());
        updateItem.setIsRoot(item.getIsRoot().byteValue());
        productItemService.updateItem(updateItem);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }
}
