package com.jjj.crm.commons.util;

import com.jjj.crm.workbench.pojo.Activity;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellType;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @className: com.jjj.crm.commons.util.PoiUtils
 * @description:
 * @author: 江骏杰
 * @create: 2022-10-09 17:37
 */
public class PoiUtils {
    /**
     * 获取对应的HSSFWorkbook对象,主要是导出
     * @return excel文件对象
     */
    public static HSSFWorkbook getHSSFWorkbook() {
        return new HSSFWorkbook();
    }

    /**
     * 获取对应的HSSFWorkbook对象,主要是导入
     * @param is 输入流
     * @return
     * @throws IOException
     */
    public static HSSFWorkbook getHSSFWorkbook(InputStream is) throws IOException {
        return new HSSFWorkbook(is);
    }

    /**
     * 封装市场活动表头
     * @param workbook HSSFWorkbook对象
     */
    public static void createActivityHeader(HSSFWorkbook workbook) {
        // 获取第一页,默认名字为'activity_sheet'
        HSSFSheet sheet = workbook.createSheet("activity_sheet");
        // 获取第一行
        HSSFRow row = sheet.createRow(0);
        // 封装每一列的信息
        row.createCell(0).setCellValue("id");
        row.createCell(1).setCellValue("所有者");
        row.createCell(2).setCellValue("活动名字");
        row.createCell(3).setCellValue("开始日期");
        row.createCell(4).setCellValue("结束日期");
        row.createCell(5).setCellValue("花费");
        row.createCell(6).setCellValue("描述");
        row.createCell(7).setCellValue("创建时间");
        row.createCell(8).setCellValue("创建者");
        row.createCell(9).setCellValue("修改时间");
        row.createCell(10).setCellValue("修改者");
    }

    /**
     * 动态拼接集合中的数据
     * @param list 数据库返回的记过
     * @param workbook excel文件对象
     */
    public static void createActivityDynamicData(List<Activity> list, HSSFWorkbook workbook) {
        if (list != null && list.size() > 0) {
            HSSFSheet sheet = workbook.getSheet("activity_sheet");
            HSSFRow row = null;
            for (int i = 0; i < list.size(); i++) {
                row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(list.get(i).getId());
                row.createCell(1).setCellValue(list.get(i).getOwner());
                row.createCell(2).setCellValue(list.get(i).getName());
                row.createCell(3).setCellValue(list.get(i).getStartDate());
                row.createCell(4).setCellValue(list.get(i).getEndDate());
                row.createCell(5).setCellValue(list.get(i).getCost());
                row.createCell(6).setCellValue(list.get(i).getDescription());
                row.createCell(7).setCellValue(list.get(i).getCreateTime());
                row.createCell(8).setCellValue(list.get(i).getCreateBy());
                row.createCell(9).setCellValue(list.get(i).getEditTime());
                row.createCell(10).setCellValue(list.get(i).getEditBy());
            }
        }
    }

    /**
     *
     * @param workbook
     * @param host 创建者的UUID
     * @return
     */
    public static List<Activity> getListFromExcel(HSSFWorkbook workbook, String host) {
        // 获取第一页
        HSSFSheet sheet = workbook.getSheetAt(0);
        HSSFRow row = null;
        HSSFCell cell = null;
        Activity activity = null;
        ArrayList<Activity> activities = new ArrayList<>();
        // 遍历行
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            row = sheet.getRow(i);
            activity = new Activity();
            activity.setId(UUIDUtils.getUUID());
            activity.setOwner(host);
            activity.setCreateTime(DateUtils.formatDateTime());
            activity.setCreateBy(host);
            for (int j = 0; j < row.getLastCellNum(); j++) {
                cell = row.getCell(j);
                buildActivity(activity, j, cell);
            }
            activities.add(activity);
        }
        return activities;
    }

    /**
     * 动态获取参数值,根据excel数据类型
     * @param cell
     * @return
     */
    public static String parseValue(HSSFCell cell) {
        String res = "";
        if (cell.getCellType() == CellType.STRING) {
            res = cell.getStringCellValue();
        }else if (cell.getCellType() == CellType.BOOLEAN) {
            res = cell.getBooleanCellValue() + "";
        }else if (cell.getCellType() == CellType.NUMERIC) {
            res = cell.getNumericCellValue() + "";
        }else if (cell.getCellType() == CellType.FORMULA) {
            res = cell.getStringCellValue();
        }else {
            res = " ";
        }
        return res;
    }

    public static void buildActivity(Activity activity, int index, HSSFCell cell) {
        String value = parseValue(cell);
        switch (index){
            case 0 : activity.setName(value);
            case 1 : activity.setStartDate(value);
            case 2 : activity.setEndDate(value);
            case 3 : activity.setCost(value);
            case 4 : activity.setDescription(value);
        }
    }


}
