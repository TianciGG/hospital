package com.iss.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.iss.db.entity.Attenchment;
import com.iss.service.UploadService;

@Controller
@RequestMapping("/logic")
public class UploadController {
	@Autowired
	private UploadService uploadService;

	// ��Ӧ�̹���ҳ�棬��ҳ������ĸ����б�ͼ���ҳ�����ذ�ť����ɵ�����
	@RequestMapping(value = "/myDownLoadFile.do")
	@ResponseBody
	public void downLoadFile(Attenchment att, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			String realURL = request.getSession().getServletContext().getRealPath("uploadFile");
			String saveDir = realURL + File.separator + att.getAttname();
			System.out.println("--------------------------------------------");
			System.out.println(saveDir);
			File file = new File(saveDir);
			String filename = file.getName();
			if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
				filename = URLEncoder.encode(filename, "UTF-8");
			} else {
				filename = new String(filename.getBytes("UTF-8"), "ISO8859-1");
			}
			InputStream fis = new BufferedInputStream(new FileInputStream(saveDir));
			response.reset();
			response.setContentType("bin");
			response.addHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
			byte[] buffer = new byte[fis.available()];
			int len;
			while ((len = fis.read(buffer)) > 0)
				response.getOutputStream().write(buffer, 0, len);
			fis.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	// ��Ӧ�̹���ҳ�棬��ҳ������ĸ����б�ͼ���ҳ���ϴ���ť����ɵ�����
	@RequestMapping(value = "/myUploadFile.do")
	@ResponseBody
	public void uploadFile(@RequestParam(value = "myFileName", required = false) MultipartFile file,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String result = "";
		String tid = request.getParameter("myObjectID");
		System.out.println("-----------------------------------------------------");
		System.out.println(tid);
		String attType = request.getParameter("attType");
		System.out.println(attType);
		System.out.println("-----------------------------------------------------");
		String savePath = request.getSession().getServletContext().getRealPath("uploadFile") + File.separator;
		System.out.println(savePath);
		System.out.println("-----------------------------------------------------");
		String fileURL = request.getContextPath() + "/uploadFile" + "/";
		System.out.println(fileURL);
		System.out.println("-----------------------------------------------------");
		String fileName = file.getOriginalFilename();
		System.out.println(fileName);
		System.out.println("-----------------------------------------------------");
		File targetFile = new File(savePath, fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		try {
			file.transferTo(targetFile);
			Attenchment att = new Attenchment();
			att.setAttname(fileName);
			att.setAtturl(fileURL);
			att.setType(attType);
			att.setId(tid);
			att.setAttid(UUID.randomUUID().toString());
			if (uploadService.uploadFile(att)) {
				response.getWriter().write("allok");
			} else {
				response.getWriter().write("myfail");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ��Ӧ�̹���ҳ�棬��ҳ������ĸ����б�ͼ���ҳ��ɾ��ͼ���ҳ����ύ��ť����ɵ�����
	@RequestMapping(value = "/deleteOneAtt.do")
	@ResponseBody
	public void deleteOneAttJSON(Attenchment attment, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw = response.getWriter();
		pw.write(uploadService.deleteFile(attment) == true ? "allok" : "myfail");
		pw.flush();
		pw.close();
	}

	// ��������ҳ�棬�ϴ���ť����ɵ�����
	@RequestMapping(value = "/myUpload.do")
	public void upload(@RequestParam(value = "devicePicName", required = false) MultipartFile file,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		String deviceId = request.getParameter("deviceId");
		System.out.println("-----------------------------------------------------");
		System.out.println(deviceId);
		String tAttid = request.getParameter("mytAttid");
		System.out.println("-----------------------------------------------------");
		System.out.println(tAttid);
		String attType = request.getParameter("attType");
		System.out.println("-----------------------------------------------------");
		System.out.println(attType);
		String savePath = request.getSession().getServletContext().getRealPath("uploadFile") + File.separator;
		System.out.println("-----------------------------------------------------");
		System.out.println(savePath);
		String fileURL = request.getContextPath() + "/uploadFile" + "/";
		System.out.println("-----------------------------------------------------");
		System.out.println(fileURL);
		String fileName = file.getOriginalFilename();
		System.out.println("-----------------------------------------------------");
		System.out.println(fileName);
		File targetFile = new File(savePath, fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		try {
			file.transferTo(targetFile);
			Attenchment att = new Attenchment();
			att.setAttname(fileName);
			att.setAtturl(fileURL);
			att.setType(attType);
			att.setId(deviceId);
			if (uploadService.checkFile(deviceId)) {
				att.setAttid(tAttid);
				if (uploadService.updateFile(att)) {
					response.getWriter().write(JSON.toJSONString(att));
				} else {
					response.getWriter().write("myfail");
				}
			} else {
				att.setAttid(UUID.randomUUID().toString());
				if (uploadService.uploadFile(att)) {
					response.getWriter().write(JSON.toJSONString(att));
				} else {
					response.getWriter().write("myfail");
				}
			}
			response.setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
