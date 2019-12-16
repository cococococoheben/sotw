package com.ss.common.utils.excel;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class WriteExcelUtils {

    private static XSSFWorkbook wb;

    private static XSSFSheet sheet;

    private static Integer rowIndex;


    public WriteExcelUtils(){
        wb = new XSSFWorkbook();
        sheet = wb.createSheet();
        // 写入行 下标
        rowIndex = 0;
    }

    /**
     * 写入行 下标
     * @return
     */
    private static Integer getRowIndex(){
        rowIndex++;
        return rowIndex-1;
    }

    /**
     * 写表格表头的内容
     * @param titles
     */
    public static void writeExcelTitle(String[] titles){
        //创建Row对象
        XSSFRow row = sheet.createRow(getRowIndex());
        for(int i = 0 ; i < titles.length ; i++){
            String title = titles[i];
            XSSFCell cell =  row.createCell(i);
            cell.setCellValue(title);
        }
    }

    /**
     * 写入内容
     * Map<1开始 1代表第一行, Map<0开始 对应titles 列 ,Object>>
     *
     * @param rows Map<1开始 1代表第一行, Map<0开始 对应titles 列 ,Object>>
     */
    public static void writeExcelContent(Map<Integer, Map<Integer,Object>> rows){

        for(int i = 1 ; i<=rows.size(); i++){
            //创建行
            XSSFRow row = sheet.createRow(getRowIndex());
            //拿出一条数据准备写入
            Map<Integer, Object> cells = rows.get(i);

            for(int j = 0 ; j< cells.size() ; j++){

                Object obj = cells.get(j);
                if (obj == null) {
                    obj = "";
                }
                XSSFCell cell = row.createCell(j);

                cell.setCellValue(obj.toString());

            }

        }

    }

    /**
     * 写入InputStream
     * 不需要保存为实际的文件
     * 利用ByteArrayOutputStream来做缓存
     * 将动态生成的文件内容写入这个缓存，然后再将缓存中的数据转化为自己数组
     * 再利用ByteArrayInputStream转化为输入流
     * @return
     */
    public static InputStream writeToInputStream(){
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        InputStream in;
        try {
            wb.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte [] bookByteAry = out.toByteArray();
        in = new ByteArrayInputStream(bookByteAry);
        return in;
    }

}
