package com.iss.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.iss.db.entity.Device;
import com.iss.util.DateUtil;
import com.iss.util.ExcelJudgeUtil;

@Service
public class ExcelService {
	public void exportExcel(HttpServletRequest request, HttpServletResponse resp, List listContent) throws IOException {

		XSSFWorkbook wb = new XSSFWorkbook();
		request.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/x-download");

		String fileName = "待检测设备记录.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		resp.addHeader("Content-Disposition", "attachment;filename=" + fileName);
		XSSFSheet sheet = wb.createSheet("待检测设备");
		sheet.setDefaultRowHeight((short) (2 * 256));

		XSSFFont font = wb.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 16);
		XSSFRow row = sheet.createRow((int) 0);

		XSSFCellStyle style = wb.createCellStyle();
		style.setFont(font);
		style.setAlignment(XSSFCellStyle.ALIGN_CENTER);

		XSSFCell cell = row.createCell(0);
		cell.setCellValue("序号");
		cell.setCellStyle(style);
		sheet.setColumnWidth(0, 2000);

		cell = row.createCell(1);
		cell.setCellValue("科室 ");
		cell.setCellStyle(style);
		sheet.setColumnWidth(1, 4000);

		cell = row.createCell(2);
		cell.setCellValue("仪器名称 ");
		cell.setCellStyle(style);
		sheet.setColumnWidth(2, 7000);

		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue("购买日期");
		sheet.setColumnWidth(3, 5000);

		cell = row.createCell(4);
		cell.setCellStyle(style);
		cell.setCellValue("上次年检时间");
		sheet.setColumnWidth(4, 5000);

		cell = row.createCell(5);
		cell.setCellStyle(style);
		cell.setCellValue("年检周期");
		sheet.setColumnWidth(5, 5000);

		cell = row.createCell(6);
		cell.setCellStyle(style);
		cell.setCellValue("距离下次检测 ");
		sheet.setColumnWidth(6, 5000);

		cell = row.createCell(7);
		cell.setCellStyle(style);
		cell.setCellValue("提前预警 ");
		sheet.setColumnWidth(7, 5000);

		for (int i = 0; i < listContent.size(); i++) {
			XSSFRow row1 = sheet.createRow((int) i + 1);
			Device device = (Device) listContent.get(i);
			row1.createCell(0).setCellValue(i + 1);
			row1.createCell(1).setCellValue(device.getDicCode());
			row1.createCell(2).setCellValue(device.getDeviceName());
			row1.createCell(3).setCellValue(device.getBuyDate());
			row1.createCell(4).setCellValue(device.getCheckDate());
			row1.createCell(5).setCellValue(device.getCheckCircle());
			row1.createCell(6).setCellValue(device.getLeftDay());
			row1.createCell(7).setCellValue(device.getDnumber());
		}
		OutputStream out = resp.getOutputStream();
		wb.write(out);
		out.close();
	}

	public List<Device> importExcel(MultipartFile mfile) throws IOException {

		InputStream is = mfile.getInputStream();
		System.out.println(mfile.getOriginalFilename());
		List dataList = null;

		boolean isExcel2003 = true;
		if (ExcelJudgeUtil.isExcel2007(mfile.getOriginalFilename())) {
			isExcel2003 = false;
		}

		Workbook wb = null;
		if (isExcel2003) {
			wb = new HSSFWorkbook(is);
		} else {// 当excel是2007时
			wb = new XSSFWorkbook(is);
		}
		// 读取Excel里面客户的信息
		dataList = readExcelValue(wb);
		return dataList;
	}

	private List<Device> readExcelValue(Workbook wb) {
		// 得到第一个shell
		Sheet sheet = wb.getSheetAt(0);
		// 得到Excel的行数
		int totalRows = sheet.getPhysicalNumberOfRows();
		// 得到Excel的列数(前提是有行数)
		int totalCells = 0;
		if (totalRows >= 1 && sheet.getRow(0) != null) {
			totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
		}
		List deviceList = new ArrayList();
		Device device;
		// 循环Excel行数,从第二行开始。标题不入库
		for (int r = 1; r < totalRows; r++) {
			Row row = sheet.getRow(r);
			if (row == null)
				continue;
			device = new Device();
			device.setCheckDate(DateUtil.dateToStringHHmm(new Date()));
			// 循环Excel的列
			for (int c = 0; c < totalCells; c++) {
				Cell cell = row.getCell(c);
				if (null != cell) {
					if (c == 0) {// 第一列不读
					} else if (c == 1) {
						device.setDicCode(cell.getStringCellValue());
					} else if (c == 2) {
						device.setAgencyId(cell.getStringCellValue());
					} else if (c == 3) {
						device.setDeviceName(cell.getStringCellValue());
					} else if (c == 4) {
						device.setBuyDate(cell.getStringCellValue());
					} else if (c == 5) {
						device.setCheckCircle(cell.getStringCellValue());
					} else if (c == 6) {
						device.setDnumber(cell.getStringCellValue());
					}
				}
			}
			// 添加客户
			deviceList.add(device);
		}
		return deviceList;
	}
}
