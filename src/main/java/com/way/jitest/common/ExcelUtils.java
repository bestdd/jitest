package com.way.jitest.common;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import org.apache.poi.openxml4j.util.ZipSecureFile;

import java.util.List;
import java.util.Map;

/**
 * Excel读写工具类
 * @author xkm
 * @date 2020/2/20
 */
public class ExcelUtils {

    /**
     * 获取Excel中的数据内容
     *
     * @param xlsxPath Excel文件位置
     * @return
     */
    public static List<Map<String, Object>> getExcelData(String xlsxPath) {
        ZipSecureFile.setMinInflateRatio(-1.0d);
        String rootDir = System.getProperty("user.dir");
        ExcelReader reader = ExcelUtil.getReader(xlsxPath);
        List<Map<String, Object>> readAll = reader.readAll();
        return readAll;
    }

    /**
     *
     * @param  x 要修改数据的行位置：起始值=0
     * @param  y 要修改数据的列位置：起始值=0
     * @param  value 目标值
     * @param  xlsxPath Excel文件位置
     * @param  shellName Shell页名称
     * @return
     */
    public static boolean updateExcelDate(int x, int y, Object value, String xlsxPath, String shellName){
        // 通过工具类创建writer
        ZipSecureFile.setMinInflateRatio(-1.0d);
        ExcelWriter writer = ExcelUtil.getWriter(xlsxPath,shellName);
        writer.writeCellValue(x,y,value);
        // 关闭writer，释放内存
        writer.close();
        return true;
    }


}
