package gz.sw.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.alibaba.fastjson.JSONObject;
import gz.sw.common.RetVal;
import gz.sw.entity.write.*;
import gz.sw.exception.ParamException;
import gz.sw.service.write.DischargeService;
import gz.sw.service.write.RainRunService;
import gz.sw.service.write.RainService;
import gz.sw.service.write.UnitLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.*;

/**
 * {{文件描述}}
 *
 * @author 缪隽峰
 * @version 1.0
 * @date 2021年08月18日
 */
@Controller
@RequestMapping("excel")
public class ExcelController {

    @Autowired
    private UnitLineService unitLineService;

    @Autowired
    private DischargeService dischargeService;

    @Autowired
    private RainService rainService;

    @Autowired
    private RainRunService rainRunService;

    @PostMapping("importUnitLine")
    @ResponseBody
    @Transactional
    public JSONObject importUnitLine(
            @RequestParam("name") String name,
            @RequestParam("fileType") Integer fileType,
            @RequestParam("file") MultipartFile file) throws Exception {
        String fileName = file.getOriginalFilename();
        boolean fileIsExcel = Objects.requireNonNull(fileName).matches("^.+\\.(?i)(xls)$") || fileName.matches("^.+\\.(?i)(xlsx)$");
        if (!fileIsExcel) {
            throw new ParamException("文件类型错误");
        }
        InputStream inputStream = file.getInputStream();
        ImportParams importParams = new ImportParams();
        importParams.setTitleRows(0);
        importParams.setHeadRows(1);
        if( fileType.equals(1) ) {
            ExcelImportResult<UnitLine> requestList = ExcelImportUtil.importExcelMore(inputStream, UnitLine.class, importParams);
            List<UnitLine> list = requestList.getList();
            Date date = new Date();
            List<UnitLine> insertUnitLineList = new ArrayList<>();
            for (UnitLine ul : list) {
                ul.setName(name);
                ul.setCreateTime(date);
                insertUnitLineList.add(ul);
                if( insertUnitLineList.size() > 500 ){
                    unitLineService.insertBatch(insertUnitLineList);
                    insertUnitLineList.clear();
                }
            }
            unitLineService.insertBatch(insertUnitLineList);
        }else{
            ExcelImportResult<UnitLinePoint> requestList = ExcelImportUtil.importExcelMore(inputStream, UnitLinePoint.class, importParams);
            List<UnitLinePoint> list = requestList.getList();
            List<UnitLinePoint> insertUnitLinePointList = new ArrayList<>();
            for (UnitLinePoint ulp : list) {
                insertUnitLinePointList.add(ulp);
                if( insertUnitLinePointList.size() > 500 ){
                    unitLineService.insertPointBatch(insertUnitLinePointList);
                    insertUnitLinePointList.clear();
                }
            }
            unitLineService.insertPointBatch(insertUnitLinePointList);
        }
        return RetVal.OK();
    }

    @PostMapping("importDischarge")
    @ResponseBody
    @Transactional
    public JSONObject importDischarge(
            @RequestParam("stcd") String stcd,
            @RequestParam("file") MultipartFile file) throws Exception {
        String fileName = file.getOriginalFilename();
        boolean fileIsExcel = Objects.requireNonNull(fileName).matches("^.+\\.(?i)(xls)$") || fileName.matches("^.+\\.(?i)(xlsx)$");
        if (!fileIsExcel) {
            throw new ParamException("文件类型错误");
        }
        InputStream inputStream = file.getInputStream();
        ImportParams importParams = new ImportParams();
        importParams.setTitleRows(0);
        importParams.setHeadRows(1);

        Discharge discharge = new Discharge();
        discharge.setStcd(stcd);
        discharge.setCreateTime(new Date());
        dischargeService.insert(discharge);

        ExcelImportResult<DischargePoint> requestList = ExcelImportUtil.importExcelMore(inputStream, DischargePoint.class, importParams);
        List<DischargePoint> list = requestList.getList();
        Date date = new Date();
        List<DischargePoint> insertDischargePointList = new ArrayList<>();
        for (DischargePoint dp : list) {
            dp.setLid(discharge.getId());
            insertDischargePointList.add(dp);
            if( insertDischargePointList.size() > 500 ){
                dischargeService.insertPointBatch(insertDischargePointList);
                insertDischargePointList.clear();
            }
        }
        dischargeService.insertPointBatch(insertDischargePointList);

        return RetVal.OK();
    }

    @PostMapping("importRain")
    @ResponseBody
    @Transactional
    public JSONObject importRain(
            @RequestParam("name") String name,
            @RequestParam("file") MultipartFile file) throws Exception {
        String fileName = file.getOriginalFilename();
        boolean fileIsExcel = Objects.requireNonNull(fileName).matches("^.+\\.(?i)(xls)$") || fileName.matches("^.+\\.(?i)(xlsx)$");
        if (!fileIsExcel) {
            throw new ParamException("文件类型错误");
        }
        InputStream inputStream = file.getInputStream();
        ImportParams importParams = new ImportParams();
        importParams.setTitleRows(0);
        importParams.setHeadRows(1);

        ExcelImportResult<RainPoint> requestList = ExcelImportUtil.importExcelMore(inputStream, RainPoint.class, importParams);
        List<RainPoint> list = requestList.getList();
        Date date = new Date();
        Map<String, List<RainPoint>> rainPointMap = new HashMap<>();
        for (RainPoint rp : list){
            if( !rainPointMap.containsKey(rp.getFstcd()) ){
                String fstcd = rp.getFstcd();
                List<RainPoint> rpList = new ArrayList<>();
                for (RainPoint rp2 : list){
                    if( fstcd.equals(rp2.getFstcd()) ){
                        rpList.add(rp2);
                    }
                }
                rainPointMap.put(fstcd, rpList);
            }
        }
        for (String stcd : rainPointMap.keySet()) {
            Rain rain = new Rain();
            rain.setName(name);
            rain.setStcd(stcd);
            rain.setCreateTime(date);
            rainService.insert(rain);
            List<RainPoint> rpList = rainPointMap.get(stcd);
            for (RainPoint rp : rpList){
                rp.setRain(rain.getId());
            }
            rainService.insertPointBatch(rpList);
        }
        return RetVal.OK();
    }

    @PostMapping("importRainRun")
    @ResponseBody
    @Transactional
    public JSONObject importRainRun(
            @RequestParam("name") String name,
            @RequestParam("fileType") Integer fileType,
            @RequestParam("file") MultipartFile file) throws Exception {
        String fileName = file.getOriginalFilename();
        boolean fileIsExcel = Objects.requireNonNull(fileName).matches("^.+\\.(?i)(xls)$") || fileName.matches("^.+\\.(?i)(xlsx)$");
        if (!fileIsExcel) {
            throw new ParamException("文件类型错误");
        }
        InputStream inputStream = file.getInputStream();
        ImportParams importParams = new ImportParams();
        importParams.setTitleRows(0);
        importParams.setHeadRows(1);
        if( fileType.equals(1) ) {
            ExcelImportResult<RainRun> requestList = ExcelImportUtil.importExcelMore(inputStream, RainRun.class, importParams);
            List<RainRun> list = requestList.getList();
            Date date = new Date();
            Map<String, List<RainRun>> rainRunMap = new HashMap<>();
            for (RainRun rr : list) {
                if (!rainRunMap.containsKey(rr.getStcd())) {
                    String fstcd = rr.getStcd();
                    List<RainRun> rrList = new ArrayList<>();
                    for (RainRun rr2 : list) {
                        if (fstcd.equals(rr2.getStcd())) {
                            rrList.add(rr2);
                        }
                    }
                    rainRunMap.put(fstcd, rrList);
                }
            }
            for (String stcd : rainRunMap.keySet()) {
                RainRun rainRun = new RainRun();
                rainRun.setName(name);
                rainRun.setStcd(stcd);
                rainRun.setCreateTime(date);
                rainRunService.insert(rainRun);
                List<RainRun> rrList = rainRunMap.get(stcd);
                for (RainRun rr : rrList) {
                    rr.setPid(rainRun.getId());
                    rr.setCreateTime(date);
                }
                rainRunService.insertLineBatch(rrList);
            }
        }else{
            ExcelImportResult<RainRunPoint> requestList = ExcelImportUtil.importExcelMore(inputStream, RainRunPoint.class, importParams);
            List<RainRunPoint> list = requestList.getList();
            Date date = new Date();
            List<RainRunPoint> insertRainRunPointList = new ArrayList<>();
            for (RainRunPoint rrp : list) {
                insertRainRunPointList.add(rrp);
                if( insertRainRunPointList.size() > 500 ){
                    rainRunService.insertPointBatch(insertRainRunPointList);
                    insertRainRunPointList.clear();
                }
            }
            rainRunService.insertPointBatch(insertRainRunPointList);
        }
        return RetVal.OK();
    }
//
//    //从项目根路径导出
//    @PostMapping("export")
//    public void exportExcel(HttpServletResponse response) throws IOException {
//        ClassPathResource resource = new ClassPathResource("excel/工单.xlsx");
//        Workbook workbook = new XSSFWorkbook(resource.getInputStream());
//        try {
//            EasypoiUtil.downLoadExcel("WorkOrderTemplate", response, workbook);
//        } catch (ChiticException e) {
//            throw e;
//        } catch (Exception e) {
//            throw ChiticException.of(SsoResponseCode.RESOURCE_FILE_EXPORT_FAIL.getCode(), SsoResponseCode.RESOURCE_FILE_EXPORT_FAIL.getMessage());
//        }
//    }
//
//    //从数据库查询导出
//    @PostMapping("exportData")
//    public void exportWaterData(@RequestBody @Valid HistoryDataExportRequest request, HttpServletResponse response) {
//        List<DataWaterHistoryExport> listData = dataWaterHistoryService.exportWaterData(request);
//        ExportParams params = new ExportParams();
//        params.setSheetName("水质历史数据");
//        Workbook workbook = ExcelExportUtil.exportExcel(params, DataWaterHistoryExport.class, listData);
//        try {
//            EasypoiUtil.downLoadExcel("water.xls", response, workbook);
//        } catch (ChiticException e) {
//            throw e;
//        } catch (Exception e) {
//            throw ChiticException.of(SsoResponseCode.RESOURCE_FILE_EXPORT_FAIL.getCode(), SsoResponseCode.RESOURCE_FILE_EXPORT_FAIL.getMessage());
//        }
//    }

//    //模板路径  resources/model/model-inv.xls
//    String url = "model/model-inv.xls";
//    TemplateExportParams exportParams = new TemplateExportParams(url);
//    exportParams.setHeadingRows(6);
//    Map<String, Object> dataMap=new HashMap<String, Object>();
//    dataMap.put("pname",plantinfo.getPlantname());
//    dataMap.put("punit",request.getPlantunit());
//    dataMap.put("time", DateStringUtil.Date2String(new Date(),"yyyy-MM-dd HH:mm:ss"));
//    dataMap.put("list", list);
//    dataMap.put("sign", "历史");
//    Workbook workbook = ExcelExportUtil.exportExcel(exportParams, dataMap);
//    EasypoiUtil.downLoadExcel("DEVICE_HISDATA" + System.currentTimeMillis() + ".xls", response, workbook);
}
