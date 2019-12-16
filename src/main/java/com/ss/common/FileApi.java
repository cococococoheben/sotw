package com.ss.common;

import com.ss.common.config.ShengShiConfig;
import com.ss.common.domain.FileDO;
import com.ss.common.service.FileService;
import com.ss.common.utils.FileType;
import com.ss.common.utils.FileUtil;
import com.ss.common.utils.ResultApp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019/9/30 0030.
 */
@CrossOrigin
@Api(value = "/api/file", description = "文件处理模块")
@RestController
@RequestMapping("/api/file")
public class FileApi {

    @Autowired
    private FileService sysFileService;

    @Autowired
    private ShengShiConfig shengShiConfig;


    @ApiOperation(value="文件上传", notes="文件上传 服务器返回文件路径与文件id 如果马上修改文件 则调用/remove")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "文件", required = true ,dataType = "file" ,paramType = "form"),
            @ApiImplicitParam(name = "token", value = "token", required = true ,dataType = "string" ,paramType = "header"),
    })
    @PostMapping("/upload")
    ResultApp upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String fileName = file.getOriginalFilename();
        fileName = FileUtil.renameToUUID(fileName);
        FileDO sysFile = new FileDO(FileType.fileType(fileName), "/files/" + fileName, new Date());
        try {
            FileUtil.uploadFile(file.getBytes(), shengShiConfig.getUploadPath(), fileName);
        } catch (Exception e) {
            return ResultApp.failWithMsg(e.getMessage());
        }

        if (sysFileService.save(sysFile) > 0) {
            if (sysFileService.save(sysFile) > 0) {
                Map<String,Object> map =new HashMap<>();
                map.put("fileName",sysFile.getUrl());
                map.put("fileId",sysFile.getId());
                return ResultApp.successWithData(map);
            }
            return ResultApp.successWithData(sysFile.getUrl());
        }
        return ResultApp.failWithMsg("上传失败,请联系管理员");
    }
    /**
     * 删除
     */
    @ApiOperation(value="文件删除", notes="文件删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true ,dataType = "string" ,paramType = "header"),
            @ApiImplicitParam(name = "id", value = "文件id 接口/upload 返回的fileId", required = true ,dataType = "long" ,paramType = "query"),
    })
    @PostMapping("/remove")
    public ResultApp remove(Long id, HttpServletRequest request) {
        String fileName = shengShiConfig.getUploadPath() + sysFileService.get(id).getUrl().replace("/files/", "");
        if (sysFileService.remove(id) > 0) {
            boolean b = FileUtil.deleteFile(fileName);
            if (!b) {
                return ResultApp.failWithMsg("数据库记录删除成功，文件删除失败");
            }
            return ResultApp.success();
        } else {
            return ResultApp.failWithMsg("删除失败,请联系管理员");
        }
    }

}
