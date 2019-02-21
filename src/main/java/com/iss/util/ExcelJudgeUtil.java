package com.iss.util;

public class ExcelJudgeUtil {
	public static boolean isExcel2003(String filePath) {
		String[] fp1 = filePath.split("\\.");
		if ("xls".equals(fp1[1])) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isExcel2007(String filePath) {
		String[] fp2 = filePath.split("\\.");
		if ("xlsx".equals(fp2[1])) {
			return true;
		} else {
			return false;
		}
	}
}
