package gz.sw.util;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * {{文件描述}}
 *
 * @author 缪隽峰
 * @version 1.0
 * @date 2021年08月18日
 */
public abstract class EasypoiUtil {
    public EasypoiUtil() {
    }

    public static void templateExport(TemplateExportParams templateExcel, Map<String, Object> dataMap, String fileName, HttpServletResponse response) throws IOException {
        Workbook workbook = ExcelExportUtil.exportExcel(templateExcel, dataMap);
        if (workbook != null) {
            downLoadExcel(fileName, response, workbook);
        }

    }

    public static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        workbook.write(response.getOutputStream());
    }

    private static String filename(HttpServletRequest request, String filename) throws Exception {
        String userAgent = request.getHeader("User-Agent");
        if (!userAgent.contains("MSIE") && !userAgent.contains("Trident")) {
            filename = new String(filename.getBytes("UTF-8"), "ISO-8859-1");
        } else {
            filename = URLEncoder.encode(filename, "UTF-8");
        }

        return filename;
    }
}
