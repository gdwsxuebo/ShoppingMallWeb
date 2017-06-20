package com.smw.web;

import java.io.File;
import java.util.Date;
import java.util.Iterator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.smw.common.util.DateUtil;
import com.smw.pojo.Sets;
import com.smw.service.SetService;
import com.smw.utils.XfStoreToPush;

/**
 * 设置
 * @author suen
 * @date 2016年5月21日-下午6:09:38
 * @version 1.0
 */
@Controller
@RequestMapping("web/setings")
public class Setings {
	Logger logger = LoggerFactory.getLogger(Setings.class);

	@Resource
	private SetService setService;
	/**
	 * 改变
	 */
	@RequestMapping(value = "setings")
	@ResponseBody
	public Object set(HttpServletRequest request, Integer type, String name) {
		if (type == 0) {
			request.getSession().setAttribute("setingIsShow", name);
		} else if (type == 1) {
			request.getSession().setAttribute("skin", name);
		} else if (type == 2) {
			request.getSession().setAttribute("gdtht", name);
		} else if (type == 3) {
			request.getSession().setAttribute("gdhdt", name);
		} else if (type == 4) {
			request.getSession().setAttribute("gdmbx", name);
		} else if (type == 5) {
			request.getSession().setAttribute("qhdzb", name);
		} else if (type == 6) {
			request.getSession().setAttribute("qhzp", name);
		}
		return true;
	}

	/**
	 * 获取打印小票信息
	 */
	@RequestMapping(value = "getPrintReceipt")
	public Object getPrintReceipt(HttpServletRequest request) {
		// 获取打印数量
		Sets sets = setService.getSets("printReceiptCount");
		request.setAttribute("printReceiptCount", sets == null ? 1 : sets.getValue());
		// 小尾巴
		sets = setService.getSets("printReceiptTail");
		request.setAttribute("printReceiptTail", sets != null ? sets.getValue() : "");
		//获取logoURL
		sets=setService.getSets("logoUrl");
		request.setAttribute("logoUrl", sets != null ? sets.getValue() : "");
		//获取打印小票logo图片
		sets = setService.getSets("printLogo");
		request.setAttribute("printLogo", sets != null ? sets.getValue() : "");
		//获取小票二维码图片
		sets = setService.getSets("printEWM");
		request.setAttribute("printEWM", sets != null ? sets.getValue() : "");
		// 设置菜单选中项
		request.getSession().setAttribute("select", "getPrintReceipt");
		return "printReceiptSet/printReceiptSet";
	}

	/**
	 * 设置打印小票信息
	 */
	@RequestMapping("/setPrintReceipt")
	@ResponseBody
	public Object setPrintReceipt(HttpServletRequest request, String printReceiptCount, String printReceiptTail) {
		// 获取打印数量
		Sets sets = setService.getSets("printReceiptCount");
		if (sets == null) {
			sets = new Sets();
			sets.setId("printReceiptCount");
			sets.setValue(printReceiptCount);
		} else {
			sets.setValue(printReceiptCount);
		}
		setService.saveOrUpdateSets(sets);
		// 小尾巴
		sets = setService.getSets("printReceiptTail");
		if (sets == null) {
			sets = new Sets();
			sets.setId("printReceiptTail");
			sets.setValue(printReceiptTail);
		} else {
			sets.setValue(printReceiptTail);
		}
		setService.saveOrUpdateSets(sets);
		return true;
	}

	/**
	 * 获取客显示文件
	 */
	@RequestMapping(value = "getUploadGuestShowFile")
	public Object getUploadGuestShowFile(HttpServletRequest request) {
		// 相文件保存的相对路径
		String realPath = request.getSession().getServletContext().getRealPath("/");
		// 获取图片
		Sets sets = setService.getSets("imageUrl");
		if (sets != null && new File(realPath + sets.getValue()).exists()) {
			request.setAttribute("imageUrl", sets.getValue());
		} else {
			request.setAttribute("imageUrl", "");
		}
		// 获取视频地址
		sets = setService.getSets("videoUrl");
		if (sets != null && new File(realPath + sets.getValue()).exists()) {
			request.setAttribute("videoUrl", sets.getValue());
		} else {
			request.setAttribute("videoUrl", "");
		}
		// 获取logo地址
		sets = setService.getSets("logoUrl");
		if (sets != null && new File(realPath + sets.getValue()).exists()) {
			request.setAttribute("logoUrl", sets.getValue());
		} else {
			request.setAttribute("logoUrl", "");
		}
		//获取最后一次上传的客显格式类型，1为视频，0为图片。
		Sets imgOrvideo = setService.getSets("imgOrvideo");
		if(imgOrvideo!=null){
			if(imgOrvideo.getValue().equals("0")){
				request.setAttribute("fileType", "0");
			}else{
				request.setAttribute("fileType", "1");
			}
		}else{
			request.setAttribute("fileType", "1");
		}
		request.getSession().setAttribute("select", "getUploadGuestShowFi");
		return "uploadGuestShowFile/uploadGuestShowFile";
	}

	/**
	 * 上传文件
	 */
	@RequestMapping("/uploadFile")
	public Object saveUploadFile(HttpServletRequest request, Integer fileType,String fileLogo,String fileEwm) {
		try {
			// 创建一个通用的多部分解析器
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
			// 判断 request 是否有文件上传,即多部分请求
			if (multipartResolver.isMultipart(request)) {
				// 转换成多部分request
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				// 取得request中的所有文件名
				Iterator<String> iter = multiRequest.getFileNames();
				// 相文件保存的相对路径
				String realPath = request.getSession().getServletContext().getRealPath("/");
				while (iter.hasNext()) {
					// 取得上传文件
					MultipartFile file = multiRequest.getFile(iter.next());
					if (file != null) {
						// 取得当前上传文件的文件名称
						String myFileName = file.getOriginalFilename();
						// 如果名称不为"",说明该文件存在，否则说明该文件不存在
						if (myFileName.trim() != "") {
							// 验证图片类型 JPG、PNG、GIF、PS、JPEG
							 
							if (fileType == 0 && (myFileName.endsWith(".jpg")||myFileName.endsWith(".png"))) {
								// 图片
								String imageUrl = "resource/upload/images/";
								// 重命名上传后的文件名
								String fileName = DateUtil.getCurrRanStr()
										+ myFileName.substring(myFileName.lastIndexOf("."), myFileName.length());
								// 定义上传路径
								String path = realPath + imageUrl + fileName;
								// 保存图片相对路径
								Sets sets = setService.getSets("imageUrl");
								File localFile;
								if (sets != null) {
									// 先删除文件
									localFile = new File(realPath + sets.getValue());
									if (localFile.exists()) {
										localFile.delete();
									}
								} else {
									sets = new Sets();
									// 主键
									sets.setId("imageUrl");
								}
								// 图片相对路径
								sets.setValue(imageUrl + fileName);
								// 保存
								setService.saveOrUpdateSets(sets);
								// 写文件
								localFile = new File(path);
								file.transferTo(localFile);
								//更新时间
								Sets printReceiptTime = setService.getSets("updateGuestTime");
								if(printReceiptTime != null){
									printReceiptTime.setValue(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));  //时间
									setService.saveOrUpdateSets(printReceiptTime);
								}else{
									sets = new Sets();
									sets.setId("updateGuestTime");
									sets.setValue(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));  //时间
									setService.saveOrUpdateSets(sets);
								}
								
								//最后上传图片或视频标记，0为图片，1为视频
								Sets imgOrvideo = setService.getSets("imgOrvideo");
								if(imgOrvideo!=null){
									imgOrvideo.setValue("0");
									setService.saveOrUpdateSets(imgOrvideo);
								}else{
									sets = new Sets();
									sets.setId("imgOrvideo");
									sets.setValue("0");
									setService.saveOrUpdateSets(sets);
								}
								XfStoreToPush.ToPushAll("PrintReceipt");//收银机更新，推送所有店铺。
								return getUploadGuestShowFile(request);
							}
							// logo （以后可能要加格式判断固单独放在这个判断里，虽然与第一个if判断区别不大）
							else if (fileType == 2 && (myFileName.endsWith(".jpg")||myFileName.endsWith(".png"))) {
								// 图片
								String imageUrl = "resource/upload/images/";
								// 重命名上传后的文件名
								String fileName = DateUtil.getCurrRanStr()
										+ myFileName.substring(myFileName.lastIndexOf("."), myFileName.length());
								// 定义上传路径
								String path = realPath + imageUrl + fileName;
								// 保存图片相对路径
								Sets sets = setService.getSets("logoUrl");
								File localFile;
								if (sets != null) {
									// 先删除文件
									localFile = new File(realPath + sets.getValue());
									if (localFile.exists()) {
										localFile.delete();
									}
								} else {
									sets = new Sets();
									// 主键
									sets.setId("logoUrl");
								}
								// 图片相对路径
								sets.setValue(imageUrl + fileName);
								// 保存
								setService.saveOrUpdateSets(sets);
								// 写文件
								localFile = new File(path);
								file.transferTo(localFile);
								//更新时间
								Sets printReceiptTime = setService.getSets("updateGuestTime");
								if(printReceiptTime != null){
									printReceiptTime.setValue(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));  //时间
									setService.saveOrUpdateSets(printReceiptTime);
								}else{
									sets = new Sets();
									sets.setId("updateGuestTime");
									sets.setValue(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));  //时间
									setService.saveOrUpdateSets(sets);
								}
								XfStoreToPush.ToPushAll("PrintReceipt");//收银机更新，推送所有店铺。
								return getUploadGuestShowFile(request);
							}
							// 上传视频资源
							 
							else if (fileType == 1 && (myFileName.endsWith(".mp4"))) {
								// 视频
								String videoUrl = "resource/upload/video/";
								// 重命名上传后的文件名
								String fileName = DateUtil.getCurrRanStr()
										+ myFileName.substring(myFileName.lastIndexOf("."), myFileName.length());
								// 定义上传路径
								String path = realPath + videoUrl + fileName;
								// 保存图片相对路径
								Sets sets = setService.getSets("videoUrl");
								File localFile;
								if (sets != null) {
									// 先删除文件
									localFile = new File(realPath + sets.getValue());
									if (localFile.exists()) {
										localFile.delete();
									}
								} else {
									sets = new Sets();
									// 主键
									sets.setId("videoUrl");
								}
								// 视频相对路径
								sets.setValue(videoUrl + fileName);
								// 保存
								setService.saveOrUpdateSets(sets);
								// 写文件
								localFile = new File(path);
								
								//更新时间
								Sets printReceiptTime = setService.getSets("updateGuestTime");
								if(printReceiptTime != null){
									printReceiptTime.setValue(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));  //时间
									setService.saveOrUpdateSets(printReceiptTime);
								}else{
									sets = new Sets();
									sets.setId("updateGuestTime");
									sets.setValue(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));  //时间
									setService.saveOrUpdateSets(sets);
								}
								
								file.transferTo(localFile);
								
								//最后上传图片或视频标记，0为图片，1为视频
								Sets imgOrvideo = setService.getSets("imgOrvideo");
								if(imgOrvideo!=null){
									imgOrvideo.setValue("1");
									setService.saveOrUpdateSets(imgOrvideo);
								}else{
									sets = new Sets();
									sets.setId("imgOrvideo");
									sets.setValue("1");
									setService.saveOrUpdateSets(sets);
								}
								XfStoreToPush.ToPushAll("PrintReceipt");//收银机更新，推送所有店铺。
								return getUploadGuestShowFile(request);
							}
							//打印设置logo图片printLogo
							else if (fileType == 3 && (myFileName.endsWith(".jpg")||myFileName.endsWith(".png"))) {
								// 图片
								String imageUrl = "resource/upload/images/";
								// 重命名上传后的文件名
								String fileName = DateUtil.getCurrRanStr()
										+ myFileName.substring(myFileName.lastIndexOf("."), myFileName.length());
								// 定义上传路径
								String path = realPath + imageUrl + fileName;
								// 保存图片相对路径
								Sets sets = setService.getSets("printLogo");
								File localFile;
								if (sets != null) {
									// 先删除文件
									localFile = new File(realPath + sets.getValue());
									if (localFile.exists()) {
										localFile.delete();
									}
								} else {
									sets = new Sets();
									// 主键
									sets.setId("printLogo");
								}
								// 图片相对路径
								sets.setValue(imageUrl + fileName);
								// 保存
								setService.saveOrUpdateSets(sets);
								// 写文件
								localFile = new File(path);
								file.transferTo(localFile);
								
								//更新时间
								Sets printReceiptTime = setService.getSets("updatePrintReceiptTime");
								if(printReceiptTime != null){
									printReceiptTime.setValue(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));  //时间
									setService.saveOrUpdateSets(printReceiptTime);
								}else{
									sets = new Sets();
									sets.setId("updatePrintReceiptTime");
									sets.setValue(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));  //时间
									setService.saveOrUpdateSets(sets);
								}
								XfStoreToPush.ToPushAll("PrintReceipt");//收银机更新，推送所有店铺。
								return getPrintReceipt(request);
							}
							//打印设置二维码图片printEWM
							else if (fileType == 4 && (myFileName.endsWith(".jpg")||myFileName.endsWith(".png"))) {
								// 图片
								String imageUrl = "resource/upload/images/";
								// 重命名上传后的文件名
								String fileName = DateUtil.getCurrRanStr()
										+ myFileName.substring(myFileName.lastIndexOf("."), myFileName.length());
								// 定义上传路径
								String path = realPath + imageUrl + fileName;
								// 保存图片相对路径
								Sets sets = setService.getSets("printEWM");
								File localFile;
								if (sets != null) {
									// 先删除文件
									localFile = new File(realPath + sets.getValue());
									if (localFile.exists()) {
										localFile.delete();
									}
								} else {
									sets = new Sets();
									// 主键
									sets.setId("printEWM");
								}
								// 图片相对路径
								sets.setValue(imageUrl + fileName);
								// 保存
								setService.saveOrUpdateSets(sets);
								// 写文件
								localFile = new File(path);
								file.transferTo(localFile);
								//更新时间
								Sets printReceiptTime = setService.getSets("updatePrintReceiptTime");
								if(printReceiptTime != null){
									printReceiptTime.setValue(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));  //时间
									setService.saveOrUpdateSets(printReceiptTime);
								}else{
									sets = new Sets();
									sets.setId("updatePrintReceiptTime");
									sets.setValue(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));  //时间
									setService.saveOrUpdateSets(sets);
								}
								XfStoreToPush.ToPushAll("PrintReceipt");//收银机更新，推送所有店铺。
								return getPrintReceipt(request);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("上传客显文件失败！ " + e.getMessage());
		}
		return getPrintReceipt(request);
	}

	/**
	 * 删除文件
	 * @param fileType 文件类型 1图片 2视频
	 */
	@RequestMapping(value = "deleteFile")
	public Object deleteFile(HttpServletRequest request, Integer fileType) {
		try {
			// 相文件保存的相对路径
			String realPath = request.getSession().getServletContext().getRealPath("/");
			Sets sets = null;
			// 删除图片
			if (1 == fileType) {
				sets = setService.getSets("imageUrl");
			}
			// 删除视频
			else if (2 == fileType) {
				sets = setService.getSets("videoUrl");
			}
			if (sets != null) {
				// 根据设置的ID删除设置信息
				setService.deleteSetsById(sets.getId());
				// 删除文件
				File localFile = new File(realPath + sets.getValue());
				if (localFile.exists()) {
					localFile.delete();
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
