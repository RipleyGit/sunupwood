package cn.chainof.sunup.controller;

import cn.chainof.sunup.controller.api.ProjectLabelApi;
import cn.chainof.sunup.controller.dto.data.LabelDTO;
import cn.chainof.sunup.exception.ClientException;
import cn.chainof.sunup.model.ProjectLabel;
import cn.chainof.sunup.service.ProjectLabelService;
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
import java.util.List;

@Slf4j
@RestController
public class ProjectLabelApiController implements ProjectLabelApi {

    @Autowired
    private ProjectLabelService projectLabelService;

    @Override
    public ResponseEntity<Void> addLabel(@ApiParam(value = "" ,required=true )  @Valid @RequestBody LabelDTO label){
        if (StringUtil.isEmpty(label.getName())||StringUtil.isEmpty(label.getLabelCode())){
            throw new ClientException("标签名或代码不能为空！");
        }
        if (label.getName().length() >25){
            throw new ClientException("标签名不能过长！");
        }
        ProjectLabel projectLabel = projectLabelService.getLabelByName(label.getName());
        if (projectLabel != null){
            throw new ClientException("该标签已存在！");
        }
        ProjectLabel code = projectLabelService.getLabelByCode(label.getLabelCode());
        if (code != null){
            throw new ClientException("代码不允许重复！");
        }
        projectLabel = new ProjectLabel();
        projectLabel.setName(label.getName());
        projectLabel.setLabelCode(label.getLabelCode());
        projectLabel.setLabelDeclare(label.getLabelDeclare());
        projectLabelService.addLable(projectLabel);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<Void> deletedLable(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "id", required = true) String id){
        if (StringUtil.isEmpty(id)||id.length()>20){
            throw new ClientException("该标签不合法！");
        }
        projectLabelService.deletedLabel(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<LabelDTO> getLabel(@Valid String id) {
        ProjectLabel label = projectLabelService.getLabelById(id);
        LabelDTO dto = AutoConvertUtil.autoConvertTo(label, LabelDTO.class);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(dto,headers, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<List<LabelDTO>> getLabels( @ApiParam(value = "",  required = false) @Valid @RequestParam(value = "keyword", required = false) String keyword){
        List<ProjectLabel> list = projectLabelService.queryListByKey(keyword);
        List<LabelDTO> dtoList = AutoConvertUtil.convert2List(list,LabelDTO.class);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(dtoList,headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> modifyLabel(@ApiParam(value = "" ,required=true )  @Valid @RequestBody LabelDTO label){
        if (StringUtil.isEmpty(label.getName())||StringUtil.isEmpty(label.getId())){
            throw new ClientException("标签名不能为空！");
        }
        ProjectLabel labelById = projectLabelService.getLabelById(label.getId());
        if (labelById == null){
            throw new ClientException("该标签不存在！");
        }
        labelById.setLabelDeclare(label.getLabelDeclare());
        labelById.setName(label.getName());
        projectLabelService.updateLabel(labelById);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }


}
