package com.way.jitest.common;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.alibaba.fastjson.JSON;

import java.util.List;
import java.util.Map;

/**
 * @author xkm
 * @date 2020/2/20
 */
public class ExcelUtils {

    /**
     * 获取Excel中的数据内容
     * @param  xlsxPath  Excel文件位置
     * @return
     */
    public static List<Map<String, Object>> getExcelData(String xlsxPath) {
        String rootDir = System.getProperty("user.dir");
        ExcelReader reader = ExcelUtil.getReader( xlsxPath );
        List<Map<String, Object>> readAll = reader.readAll();
        return readAll;
    }

}
