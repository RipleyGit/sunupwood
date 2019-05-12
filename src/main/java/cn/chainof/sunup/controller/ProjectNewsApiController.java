package cn.chainof.sunup.controller;

import cn.chainof.sunup.controller.api.ProjectNewsApi;
import cn.chainof.sunup.controller.dto.data.NewsDTO;
import cn.chainof.sunup.exception.ClientException;
import cn.chainof.sunup.model.ProjectNews;
import cn.chainof.sunup.service.ProjectNewsService;
import cn.chainof.sunup.utils.AutoConvertUtil;
import cn.chainof.sunup.utils.DateUtil;
import com.github.pagehelper.util.StringUtil;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin
public class ProjectNewsApiController implements ProjectNewsApi {

    @Autowired
    private ProjectNewsService projectNewsService;

    @Override
    public ResponseEntity<Void> addNews(@ApiParam(value = "" ,required=true )  @Valid @RequestBody NewsDTO newsDto){
        if (StringUtil.isEmpty(newsDto.getTitle())){
            throw new ClientException("标题不能为空");
        }
        if (StringUtil.isEmpty(newsDto.getStye())){
            throw new ClientException("新闻类型不能为空");
        }
        ProjectNews news = AutoConvertUtil.autoConvertTo(newsDto, ProjectNews.class);
        if (StringUtil.isNotEmpty(news.getSubject())){
            String subject = news.getSubject();
            if (subject.length()>200){
                news.setIntro(subject.substring(0,200));
            }else{
                news.setIntro(news.getSubject());
            }
        }
        projectNewsService.addProjectNews(news);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteByIds(@NotNull @Valid String id) {
        projectNewsService.deleteById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<NewsDTO> getNewsInfo(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "id", required = true) String id){
        ProjectNews news = projectNewsService.getNewsInfo(id);
        NewsDTO dto = AutoConvertUtil.autoConvertTo(news, NewsDTO.class);
        if (news.getCreateTime() != null) {
            dto.setCreateTime(DateUtil.getDateStr(news.getCreateTime()));
        }
        if (news.getUpdateTime() != null) {
            dto.setUpdateTime(DateUtil.getDateStr(news.getUpdateTime()));
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(dto,headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<NewsDTO>> getNewsInfoList(@ApiParam(value = "") @Valid @RequestParam(value = "key", required = false) String key,@ApiParam(value = "当前页数") @Valid @RequestParam(value = "pageIndex", required = false ,defaultValue="0") Integer pageIndex,@ApiParam(value = "页面大小") @Valid @RequestParam(value = "pageSize", required = false,defaultValue="6") Integer pageSize){
        List<NewsDTO> dtoList = new ArrayList<>();
        List<ProjectNews> list = projectNewsService.queryList(key,pageIndex,pageSize);
        for (ProjectNews news:list) {
            NewsDTO dto = AutoConvertUtil.autoConvertTo(news, NewsDTO.class);
            if (news.getCreateTime() != null) {
                dto.setCreateTime(DateUtil.getDateStr(news.getCreateTime()));
            }
            if (news.getUpdateTime() != null) {
                dto.setUpdateTime(DateUtil.getDateStr(news.getUpdateTime()));
            }
            dtoList.add(dto);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(dtoList,headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> modifyNewsInfo(@ApiParam(value = "" ,required=true )  @Valid @RequestBody NewsDTO newsDto) {
        if (StringUtil.isEmpty(newsDto.getTitle())){
            throw new ClientException("标题不能为空");
        }
        if (StringUtil.isEmpty(newsDto.getStye())){
            throw new ClientException("新闻类型不能为空");
        }
        ProjectNews news = AutoConvertUtil.autoConvertTo(newsDto, ProjectNews.class);
        if (StringUtil.isNotEmpty(news.getSubject())){
            String subject = news.getSubject();
            if (subject.length()>200){
                news.setIntro(subject.substring(0,200));
            }else{
                news.setIntro(news.getSubject());
            }
        }
        projectNewsService.updateProjectNews(news);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }
}
