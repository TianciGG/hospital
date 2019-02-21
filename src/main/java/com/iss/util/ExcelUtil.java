package com.iss.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
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
import org.springframework.web.multipart.MultipartFile;

import com.iss.db.entity.Device;

public class ExcelUtil {

	private int totalRows = 0;
	private int totalCells = 0;
	private String errorMsg;

	public int getTotalRows() {
		return totalRows;
	}

	public int getTotalCells() {
		return totalCells;
	}

	public String getErrorInfo() {
		return errorMsg;
	}

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
		/*
		 * sheet.createRow((int) 1); sheet.createRow((int) 2);
		 * sheet.createRow((int) 3); sheet.createRow((int) 4);
		 * sheet.createRow((int) 5); sheet.createRow((int) 6);
		 * sheet.createRow((int) 7); sheet.createRow((int) 8);
		 * sheet.createRow((int) 9); sheet.createRow((int) 10);
		 * sheet.createRow((int) 11);
		 */
		XSSFCellStyle style = wb.createCellStyle();
		style.setFont(font);
		style.setAlignment(XSSFCellStyle.ALIGN_CENTER);

		XSSFCell cell = row.createCell(0);
		cell.setCellValue("序号");
		cell.setCellStyle(style);
		sheet.setColumnWidth(0, 2000);

		cell = row.createCell(1);
		cell.setCellValue("科室");
		cell.setCellStyle(style);
		sheet.setColumnWidth(1, 4000);

		cell = row.createCell(2);
		cell.setCellValue("仪器名称");
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
		cell.setCellValue("距离下次检测");
		sheet.setColumnWidth(6, 5000);

		cell = row.createCell(7);
		cell.setCellStyle(style);
		cell.setCellValue("提前预警");
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

	public void newImportExcel(MultipartFile excelFile) {
		boolean isExcel2003 = true;
		if (ExcelJudgeUtil.isExcel2007(excelFile.getOriginalFilename())) {
			isExcel2003 = false;
		}
	}

	public List<Device> getExcelInfo(MultipartFile mfile) throws IOException {
		InputStream is = mfile.getInputStream();
		List<Device> TDeviceList = null;
		try {

			boolean isExcel2003 = true;
			if (ExcelJudgeUtil.isExcel2007(mfile.getOriginalFilename())) {
				isExcel2003 = false;
			}

			Workbook wb = null;

			if (isExcel2003) {
				wb = new HSSFWorkbook(is);
			} else {
				wb = new XSSFWorkbook(is);
			}
			TDeviceList = readExcelValue(wb);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return TDeviceList;
	}

	private List<Device> readExcelValue(Workbook wb) {
		Sheet sheet = wb.getSheetAt(0);

		this.totalRows = sheet.getPhysicalNumberOfRows();

		if (totalRows >= 1 && sheet.getRow(0) != null) {
			this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
		}

		List<Device> deviceList = new ArrayList<Device>();
		Device device;
		for (int r = 1; r < totalRows; r++) {
			Row row = sheet.getRow(r);
			if (row == null)
				continue;
			device = new Device();

			for (int c = 0; c < this.totalCells; c++) {
				Cell cell = row.getCell(c);
				if (null != cell) {
					if (c == 0) {
					} else if (c == 1) {
						device.setDicCode(cell.getStringCellValue());
					} else if (c == 2) {
						device.setDeviceName(cell.getStringCellValue());
					} else if (c == 3) {
						device.setBuyDate(cell.getStringCellValue());
					} else if (c == 4) {
						device.setCheckCircle(cell.getStringCellValue());
					} else if (c == 5) {
						device.setDnumber(cell.getStringCellValue());
					}
				}
			}
			deviceList.add(device);
		}
		return deviceList;
	}

	public void importExcel(MultipartFile excelFile) throws IOException {
		XSSFWorkbook wb = new XSSFWorkbook(new BufferedInputStream(excelFile.getInputStream()));

		DecimalFormat df = (DecimalFormat) NumberFormat.getPercentInstance();

		XSSFSheet sheet = wb.getSheetAt(0);
		for (int rows = 0; rows < sheet.getLastRowNum(); rows++) {
			XSSFRow row = sheet.getRow(rows);
			String[] s = new String[5];

			for (int columns = 0; columns < row.getLastCellNum(); columns++) {
				XSSFCell cell = row.getCell(columns);
				if (cell != null) {
					switch (cell.getCellType()) {
					case XSSFCell.CELL_TYPE_STRING:
						s[columns] = cell.getStringCellValue();
						if (s[columns] == null) {
							s[columns] = " ";
						}
						break;
					case XSSFCell.CELL_TYPE_NUMERIC:
						double strCell = cell.getNumericCellValue();
						if (String.valueOf(strCell) == null) {
							s[columns] = " ";
						}

						df.applyPattern("0");
						s[columns] = df.format(strCell);
						if (Double.parseDouble(s[columns]) != strCell) {
							df.applyPattern(Double.toString(strCell));
							s[columns] = df.format(strCell);
						}

						break;
					case XSSFCell.CELL_TYPE_BLANK:
						s[columns] = " ";
						break;
					default:
						System.out.print("\n---单元格格式不支持---");
						break;
					}
				}
			}
		}
	}
}