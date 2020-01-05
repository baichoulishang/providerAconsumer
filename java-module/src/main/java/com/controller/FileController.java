package com.controller;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 陈宜康
 * @date 2019/3/12 20:16
 * @forWhat
 */
@Controller
@RequestMapping("/file")
public class FileController {
    @RequestMapping(value = "/uploadMultipartFile", method = RequestMethod.POST)
    public ModelAndView uploadMultipartFile(@RequestParam("file") CommonsMultipartFile multipartFile) throws IOException, InvalidFormatException {
        ModelAndView modelAndView = new ModelAndView();
        Workbook workbook = fileToExcel(multipartFile);
        Sheet sheetAt = workbook.getSheetAt(0);
        int physicalNumberOfRows = sheetAt.getPhysicalNumberOfRows();
        for (int i = 0; i < physicalNumberOfRows; i++) {
            Row row = sheetAt.getRow(i);
            for (int j = 0; j < row.getLastCellNum(); j++) {
                Cell cell = row.getCell(j);
                if (cell == null) {
                    continue;
                }
                CellType typeEnum = cell.getCellTypeEnum();
                switch (typeEnum) {
                    case NUMERIC:
                        double numericCellValue = cell.getNumericCellValue();
                        System.out.println(numericCellValue);
                        break;
                    case STRING:
                        String stringCellValue = cell.getStringCellValue();
                        System.out.println(stringCellValue);
                        break;
                    case FORMULA:
                        cell.getRichStringCellValue();
                        System.out.println("这个是什么意思呢?");
                        break;
                    case BLANK:
                        RichTextString richStringCellValue = cell.getRichStringCellValue();
                        System.out.println("这个又是什么意思呢?");
                        break;
                    case BOOLEAN:
                        boolean booleanCellValue = cell.getBooleanCellValue();
                        System.out.println(booleanCellValue);
                        break;
                    case ERROR:
                        byte errorCellValue = cell.getErrorCellValue();
                        System.out.println(errorCellValue);
                        break;
                    default:
                        System.out.println("居然匹配不上");

                }
            }
        }
        return modelAndView;
    }

    @RequestMapping("/upload")
    public String uploadMultipartFile(File file) throws IOException {
        System.out.println("shit");
        return null;
    }


    private static Workbook fileToExcel(CommonsMultipartFile multipartFile) throws IOException, InvalidFormatException {
        //方法核心开始代码
        InputStream inputStream = null;
        //构建InputStream对象
        inputStream = multipartFile.getInputStream();
        //构建Workbook对象
        Workbook workbook = WorkbookFactory.create(inputStream);
        return workbook;
    }

    /**
     * 读取excel文件数据
     *
     * @param fileToUpload
     * @param startRow     excel读取的开始行数  从1开始
     * @param colsNum      excel读取的总列数  从1开始
     * @return
     */
    public static Map<String, Object> readExcelFiles(CommonsMultipartFile fileToUpload, int startRow, int colsNum) throws IOException, InvalidFormatException {
        //将要返回的map
        Map<String, Object> map = new HashMap<String, Object>();
        String error = "没有上传文件";
        String error2 = "读取文件失败";
        //判断文件是否为空，是否符合后缀名要求
        String msg = validateUploadFile(fileToUpload, "xls", "xlsx");
        if (msg != null) {
            map.put("success", false);
            map.put("msg", msg);
        }
        Workbook workbook = fileToExcel(fileToUpload);
        //Sheet的下标是从0开始
        //获取第一张Sheet表
        Sheet sheet = workbook.getSheetAt(0);
        return map;
    }

    /**
     * 判断上传文件是否为空，是否符合类型
     *
     * @param fileToUpload
     * @param suffix       文件类型参数
     * @return
     */
    private static String validateUploadFile(CommonsMultipartFile fileToUpload, String... suffix) {

        if (fileToUpload.getSize() == 0) {
            return "文件内容为空，请重新选择文件！";
        }
        if (suffix.length == 0) {
            return null;
        }
        String fileName = fileToUpload.getOriginalFilename();
        if (fileName.contains("\\")) {
            fileName = StringUtils.substringAfterLast(fileName, "\\");
        }
        if (!FilenameUtils.isExtension(fileName, suffix)) {
            return "文件扩展名只能是" + StringUtils.join(suffix, "、") + "，请重新选择文件上传！";
        }
        return null;
    }

}
