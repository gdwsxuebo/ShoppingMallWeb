package com.smw.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.text.StrBuilder;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smw.common.util.DateUtil;
import com.smw.pojo.XfItem;
import com.smw.pojo.XfMall;
import com.smw.pojo.XfStore;
import com.smw.pojo.oldSalesSummary.OldSalesItem;
import com.smw.pojo.oldSalesSummary.OldSalesSummary;
import com.smw.pojo.oldSalesSummary.OldSalesTender;
import com.smw.service.Old_SalesItemService;
import com.smw.service.Old_SalesSummaryService;
import com.smw.service.Old_SalesTenderService;
import com.smw.service.XfItemService;
import com.smw.service.XfMallService;
import com.smw.service.XfStoreService;

/**
 * 可以从http://poi.apache.org/ 这里下载到POI的jar包 POI 创建和读取2003-2007版本Excel文件
 * 
 */
@Controller
@RequestMapping(value = "V60DataToPOS")
public class V60DataToPOS {

	/**
	 * 商场
	 */
	@Resource
	private XfMallService xfMallService;

	/**
	 * 店铺
	 */
	@Resource
	private XfStoreService xfStoreService;

	/**
	 * 商品
	 */
	@Resource
	private XfItemService xfItemService;

	/**
	 * 旧销售
	 */
	@Resource
	private Old_SalesSummaryService old_salesSummaryService;

	/**
	 * 旧销售支付方式
	 */
	@Resource
	private Old_SalesTenderService old_salesTenderService;

	/**
	 * 旧销售商品
	 */
	@Resource
	private Old_SalesItemService old_SalesItemService;

	/**
	 * 60数据到POS
	 * 
	 * @return
	 */
	@RequestMapping(value = "V60DataToPOS")
	@ResponseBody
	public Object V60DataToPOS() {
		// 读取2007Excel文件
		String path2007 = "C:\\Users\\Administrator\\Desktop\\test.xlsx";// 获取项目文件路径
																			// 2007版文件名
		System.out.println("路径：" + path2007);
		File f2007 = new File(path2007);
		try {
			List<List<Object>> readExcel = readExcel(f2007);
			System.out.println(readExcel);
			// 查询商场
			XfMall selectMall = xfMallService.selectMall();
			// 保存店铺
			XfStore xfStore;
			// 商品编号
			XfItem plu;
			for (List<Object> list : readExcel) {
				xfStore = new XfStore();
				// 店铺编号
				xfStore.setXfStorecode(list.get(3).toString());
				// 店铺名称
				xfStore.setXfName(list.get(2).toString());
				// 设置商场
				xfStore.setXfMallid(selectMall);
				// 保存
				xfStoreService.saveOrUpdateSingXfStores(xfStore);
				// 商品编号
				plu = new XfItem();
				plu.setXfPlu(list.get(0).toString());
				plu.setXfLongdesc(null);
				plu.setXfDesci(list.get(1).toString());
				plu.setXfSelwprice(new BigDecimal(0));
				plu.setXfSeluprice(new BigDecimal(0));
				plu.setXfOrguprice(new BigDecimal(0));
				plu.setXfOrgwprice(new BigDecimal(0));
				plu.setXfSalesunit("元");
				plu.setXfStkunit("个");
				plu.setXfExstk2sales(new BigDecimal(1));
				plu.setXfStorecode(xfStore);
				if ("true".equals(V61.isUploadV61)) {
					plu.setItemOrgId(list.get(4).toString());
				}
				xfItemService.saveOrUpdateXfItem(plu);
			}
			return "success!";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "error!";
	}

	/**
	 * 60销售数据到CPOS
	 * 
	 * @return
	 */
	@RequestMapping(value = "sumary_V60DataToPOS")
	@ResponseBody
	public Object sumary_V60DataToPOS() {
		try {
			// 销售数据excel地址
			String sumaryUrl = "C:\\Users\\Administrator\\Desktop\\paid\\sumary.xlsx";
			File f2007 = new File(sumaryUrl);
			List<List<Object>> readExcel = readExcel(f2007);
			// 销售
			OldSalesSummary oldSalesSummary;
			for (List<Object> list : readExcel) {
				if (list.size() == 0) {
					continue;
				}
				oldSalesSummary = new OldSalesSummary();
				// 日期
				oldSalesSummary.setTxdate(DateUtil.getDate(list.get(0).toString(), "yyyy-HH-dd"));
				// 时间
				StrBuilder timeStr = new StrBuilder(list.get(1).toString());
				timeStr.insert(2, ":");
				timeStr.insert(5, ":");
				oldSalesSummary.setTxtime(DateUtil.getSqlTime(timeStr.toString(), "HH:mm:ss"));
				// 店铺
				oldSalesSummary.setStaffcode(list.get(2).toString());
				// 收银机编号
				oldSalesSummary.setTillid(list.get(3).toString());
				// 订单编号
				oldSalesSummary.setTxdocno(list.get(6).toString());
				// 收银员编号
				oldSalesSummary.setCashierId(list.get(9).toString());
				oldSalesSummary.setStaffcode(list.get(9).toString());
				int i = 0;
				if (list.size() < 28) {
					// VIP号
					oldSalesSummary.setVipcode(null);
				} else {
					// VIP号
					oldSalesSummary.setVipcode(list.get(10).toString());
					i = i + 1;
				}
				// 销售总数量
				oldSalesSummary.setNetqty(new BigDecimal(list.get(10 + i).toString()));
				// 销售净金额
				oldSalesSummary.setNetamount(new BigDecimal(list.get(13 + i).toString()));
				// 商场
				oldSalesSummary.setMallid(list.get(18 + i).toString());
				// 保存或者更新
				old_salesSummaryService.saveOrUpdateOSS(oldSalesSummary);
			}
			// 商品数据excel地址
			sumaryUrl = "C:\\Users\\Administrator\\Desktop\\paid\\item.xlsx";
			f2007 = new File(sumaryUrl);
			readExcel = readExcel(f2007);
			OldSalesItem oldSalesItem;
			for (List<Object> list : readExcel) {
				if (list.size() == 0) {
					continue;
				}
				// 关联的销售单号
				oldSalesSummary = old_salesSummaryService.getOSSByTxdocno(list.get(1).toString());
				if (oldSalesSummary != null) {
					oldSalesItem = new OldSalesItem();
					oldSalesItem.setTxdocno(oldSalesSummary);
					// 行号
					oldSalesItem.setLineno(list.get(2).toString());
					// 货号
					oldSalesItem.setPlu(list.get(3).toString());
					// 卖出数量
					oldSalesItem.setQty(new BigDecimal(list.get(4).toString()));
					// 折扣金额
					oldSalesItem.setDiscountamount(new BigDecimal(list.get(5).toString()));
					// 净金额
					oldSalesItem.setNetamount(new BigDecimal(list.get(6).toString()));
					// 获得积分
					oldSalesItem.setBonusearn(new BigDecimal(list.get(7).toString()));
					// 保存或者更新
					old_SalesItemService.saveOrUpdateOSI(oldSalesItem);
				}
			}
			// 商品数据excel地址
			sumaryUrl = "C:\\Users\\Administrator\\Desktop\\paid\\tender.xlsx";
			f2007 = new File(sumaryUrl);
			readExcel = readExcel(f2007);
			OldSalesTender oldSalesTender;
			for (List<Object> list : readExcel) {
				if (list.size() == 0) {
					continue;
				}
				// 关联的销售单号
				oldSalesSummary = old_salesSummaryService.getOSSByTxdocno(list.get(6).toString());
				if (oldSalesSummary != null) {
					oldSalesTender = new OldSalesTender();
					//关联的销售单号
					oldSalesTender.setTxdocno(oldSalesSummary);
					//行号
					oldSalesTender.setLineno(list.get(3).toString());
					//付款方式编码
					oldSalesTender.setTendercode(list.get(11).toString());
					//付款金额
					oldSalesTender.setPayamount(new BigDecimal(list.get(14).toString()));
					//本位币金额,即人民币金额
					oldSalesTender.setBaseamount(new BigDecimal(list.get(15).toString()));
					//超额金额,保留Default: 0(退货时取负值,用于记录现金券等不能找补的付款方式,例如：应付100元，而现金券是120，那么超额金额就是120-100=20)
					oldSalesTender.setExcessamount(new BigDecimal(list.get(17).toString()));
					//保存或者更新
					old_salesTenderService.saveOrUpdateOST(oldSalesTender);
				}
			}
			return "success!";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "error!";
	}

	/**
	 * 取整
	 * 
	 * @param val
	 *            值
	 * @return
	 */
	private static String getIntStr(String val) {
		return val.substring(0, val.lastIndexOf("."));
	}

	public static void main(String[] args) throws Exception {

		/*
		 * creat2003Excel();// 创建2007版Excel文件 creat2007Excel();// 创建2003版Excel文件
		 * //读取2003Excel文件 String path2003 = System.getProperty("user.dir") +
		 * System.getProperty("file.separator") + "style_2003.xls";// 获取项目文件路径
		 * 2003版文件名 System.out.println("路径：" + path2003); File f2003 = new
		 * File(path2003); try { readExcel(f2003); } catch (IOException e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); }
		 */
		// 读取2007Excel文件
		String path2007 = "C:\\Users\\Administrator\\Desktop\\paid\\tender.xlsx";// 获取项目文件路径
																					// 2007版文件名

		System.out.println("路径：" + path2007);
		File f2007 = new File(path2007);
		try {
			List<List<Object>> readExcel = readExcel(f2007);
			System.out.println(readExcel);
			// System.out.println(readExcel);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 创建2007版Excel文件
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static void creat2007Excel() throws FileNotFoundException, IOException {
		// HSSFWorkbook workBook = new HSSFWorkbook();// 创建 一个excel文档对象
		XSSFWorkbook workBook = new XSSFWorkbook();
		XSSFSheet sheet = workBook.createSheet();// 创建一个工作薄对象

		sheet.setColumnWidth(1, 10000);// 设置第二列的宽度为

		XSSFRow row = sheet.createRow(1);// 创建一个行对象

		row.setHeightInPoints(23);// 设置行高23像素

		XSSFCellStyle style = workBook.createCellStyle();// 创建样式对象

		// 设置字体

		XSSFFont font = workBook.createFont();// 创建字体对象

		font.setFontHeightInPoints((short) 15);// 设置字体大小

		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 设置粗体

		font.setFontName("黑体");// 设置为黑体字

		style.setFont(font);// 将字体加入到样式对象

		// 设置对齐方式

		style.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);// 水平居中

		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中

		// 设置边框

		style.setBorderTop(HSSFCellStyle.BORDER_THICK);// 顶部边框粗线

		style.setTopBorderColor(HSSFColor.RED.index);// 设置为红色

		style.setBorderBottom(HSSFCellStyle.BORDER_DOUBLE);// 底部边框双线

		style.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);// 左边边框

		style.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);// 右边边框

		// 格式化日期

		style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));

		XSSFCell cell = row.createCell(1);// 创建单元格

		cell.setCellValue(new Date());// 写入当前日期

		cell.setCellStyle(style);// 应用样式对象

		// 文件输出流

		FileOutputStream os = new FileOutputStream("style_2007.xlsx");

		workBook.write(os);// 将文档对象写入文件输出流

		os.close();// 关闭文件输出流
		System.out.println("创建成功 office 2007 excel");
	}

	/**
	 * 创建2003版本的Excel文件
	 */
	private static void creat2003Excel() throws FileNotFoundException, IOException {
		HSSFWorkbook workBook = new HSSFWorkbook();// 创建 一个excel文档对象

		HSSFSheet sheet = workBook.createSheet();// 创建一个工作薄对象

		sheet.setColumnWidth(1, 10000);// 设置第二列的宽度为

		HSSFRow row = sheet.createRow(1);// 创建一个行对象

		row.setHeightInPoints(23);// 设置行高23像素

		HSSFCellStyle style = workBook.createCellStyle();// 创建样式对象

		// 设置字体

		HSSFFont font = workBook.createFont();// 创建字体对象

		font.setFontHeightInPoints((short) 15);// 设置字体大小

		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 设置粗体

		font.setFontName("黑体");// 设置为黑体字

		style.setFont(font);// 将字体加入到样式对象

		// 设置对齐方式

		style.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);// 水平居中

		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中

		// 设置边框

		style.setBorderTop(HSSFCellStyle.BORDER_THICK);// 顶部边框粗线

		style.setTopBorderColor(HSSFColor.RED.index);// 设置为红色

		style.setBorderBottom(HSSFCellStyle.BORDER_DOUBLE);// 底部边框双线

		style.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);// 左边边框

		style.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);// 右边边框

		// 格式化日期

		style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));

		HSSFCell cell = row.createCell(1);// 创建单元格

		cell.setCellValue(new Date());// 写入当前日期

		cell.setCellStyle(style);// 应用样式对象

		// 文件输出流

		FileOutputStream os = new FileOutputStream("style_2003.xls");

		workBook.write(os);// 将文档对象写入文件输出流

		os.close();// 关闭文件输出流
		System.out.println("创建成功 office 2003 excel");
	}

	/**
	 * 对外提供读取excel 的方法
	 */
	public static List<List<Object>> readExcel(File file) throws IOException {
		String fileName = file.getName();
		String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName.substring(fileName.lastIndexOf(".") + 1);
		if ("xls".equals(extension)) {
			return read2003Excel(file);
		} else if ("xlsx".equals(extension)) {
			System.out.println(file.getName());
			if (file.getName().contains("test")) {
				return read2007Excel(file);
			} else {
				return read2007ExcelTwo(file);
			}
		} else {
			throw new IOException("不支持的文件类型");
		}
	}

	/**
	 * 读取 office 2003 excel
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private static List<List<Object>> read2003Excel(File file) throws IOException {
		List<List<Object>> list = new LinkedList<List<Object>>();
		HSSFWorkbook hwb = new HSSFWorkbook(new FileInputStream(file));
		HSSFSheet sheet = hwb.getSheetAt(0);
		Object value = null;
		HSSFRow row = null;
		HSSFCell cell = null;
		System.out.println("读取office 2003 excel内容如下：");
		for (int i = sheet.getFirstRowNum(); i <= sheet.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			List<Object> linked = new LinkedList<Object>();
			for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
				cell = row.getCell(j);
				if (cell == null) {
					continue;
				}
				DecimalFormat df = new DecimalFormat("0");// 格式化 number String
				// 字符
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
				DecimalFormat nf = new DecimalFormat("0.00");// 格式化数字
				switch (cell.getCellType()) {
				case XSSFCell.CELL_TYPE_STRING:
					// System.out.println(i + "行" + j + " 列 is String type");
					value = cell.getStringCellValue();
					System.out.print("  " + value + "  ");
					break;
				case XSSFCell.CELL_TYPE_NUMERIC:
					// System.out.println(i + "行" + j
					// + " 列 is Number type ; DateFormt:"
					// + cell.getCellStyle().getDataFormatString());
					if ("@".equals(cell.getCellStyle().getDataFormatString())) {
						value = df.format(cell.getNumericCellValue());

					} else if ("General".equals(cell.getCellStyle().getDataFormatString())) {
						value = nf.format(cell.getNumericCellValue());
					} else {
						value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
					}
					System.out.print("  " + value + "  ");
					break;
				case XSSFCell.CELL_TYPE_BOOLEAN:
					// System.out.println(i + "行" + j + " 列 is Boolean type");
					value = cell.getBooleanCellValue();
					System.out.print("  " + value + "  ");
					break;
				case XSSFCell.CELL_TYPE_BLANK:
					// System.out.println(i + "行" + j + " 列 is Blank type");
					value = "";
					System.out.print("  " + value + "  ");
					break;
				default:
					// System.out.println(i + "行" + j + " 列 is default type");
					value = cell.toString();
					System.out.print("  " + value + "  ");
				}
				if (value == null || "".equals(value)) {
					continue;
				}
				linked.add(value);

			}
			System.out.println("");
			list.add(linked);
		}

		return list;
	}

	/**
	 * 读取Office 2007 excel
	 */

	private static List<List<Object>> read2007Excel(File file) throws IOException {

		List<List<Object>> list = new LinkedList<List<Object>>();
		// String path = System.getProperty("user.dir") +
		// System.getProperty("file.separator")+"dd.xlsx";
		// System.out.println("路径："+path);
		// 构造 XSSFWorkbook 对象，strPath 传入文件路径
		XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(file));

		// 读取第一章表格内容
		XSSFSheet sheet = xwb.getSheetAt(0);
		Object value = null;
		XSSFRow row = null;
		XSSFCell cell = null;
		System.out.println("读取office 2007 excel内容如下：");
		for (int i = sheet.getFirstRowNum(); i <= sheet.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			List<Object> linked = new LinkedList<Object>();
			for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
				cell = row.getCell(j);
				if (cell == null) {
					continue;
				}
				DecimalFormat df = new DecimalFormat("0");// 格式化 number String
				// 字符
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
				DecimalFormat nf = new DecimalFormat("0.00");// 格式化数字
				switch (cell.getCellType()) {
				case XSSFCell.CELL_TYPE_STRING:
					// System.out.println(i + "行" + j + " 列 is String type");
					value = cell.getStringCellValue();
					System.out.print("  " + value + "  ");
					break;
				case XSSFCell.CELL_TYPE_NUMERIC:
					// System.out.println(i + "行" + j
					// + " 列 is Number type ; DateFormt:"
					// + cell.getCellStyle().getDataFormatString());
					/*
					 * if
					 * ("@".equals(cell.getCellStyle().getDataFormatString())) {
					 * value = df.format(cell.getNumericCellValue());
					 * 
					 * } else if
					 * ("General".equals(cell.getCellStyle().getDataFormatString
					 * ())) { value = nf.format(cell.getNumericCellValue()); }
					 * else { value = sdf.format(HSSFDateUtil.getJavaDate(cell.
					 * getNumericCellValue())); }
					 */
					value = cell.getRawValue();
					System.out.print("  " + value + "  ");
					break;
				case XSSFCell.CELL_TYPE_BOOLEAN:
					// System.out.println(i + "行" + j + " 列 is Boolean type");
					value = cell.getBooleanCellValue();
					System.out.print("  " + value + "  ");
					break;
				case XSSFCell.CELL_TYPE_BLANK:
					// System.out.println(i + "行" + j + " 列 is Blank type");
					value = "";
					// System.out.println(value);
					break;
				default:
					// System.out.println(i + "行" + j + " 列 is default type");
					value = cell.toString();
					System.out.print("  " + value + "  ");
				}
				if (value == null || "".equals(value)) {
					continue;
				}
				linked.add(value);
			}
			System.out.println("");
			list.add(linked);
		}
		return list;
	}

	/**
	 * 读取Office 2007 excel
	 */

	private static List<List<Object>> read2007ExcelTwo(File file) throws IOException {

		List<List<Object>> list = new LinkedList<List<Object>>();
		// String path = System.getProperty("user.dir") +
		// System.getProperty("file.separator")+"dd.xlsx";
		// System.out.println("路径："+path);
		// 构造 XSSFWorkbook 对象，strPath 传入文件路径
		XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(file));

		// 读取第一章表格内容
		XSSFSheet sheet = xwb.getSheetAt(0);
		Object value = null;
		XSSFRow row = null;
		XSSFCell cell = null;
		System.out.println("读取office 2007 excel内容如下：");
		for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			List<Object> linked = new LinkedList<Object>();
			for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
				cell = row.getCell(j);
				if (cell == null) {
					continue;
				}
				// 以下是判断数据的类型
				switch (cell.getCellType()) {
				case HSSFCell.CELL_TYPE_NUMERIC: // 数字
					value = cell.getNumericCellValue() + "";
					if (HSSFDateUtil.isCellDateFormatted(cell)) {
						Date date = cell.getDateCellValue();
						if (date != null) {
							value = new SimpleDateFormat("yyyy-MM-dd").format(date);
						} else {
							value = "";
						}
					} else {
						value = new DecimalFormat("0").format(cell.getNumericCellValue());
					}
					break;
				case HSSFCell.CELL_TYPE_STRING: // 字符串
					value = cell.getStringCellValue();
					break;
				case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
					value = cell.getBooleanCellValue() + "";
					break;
				case HSSFCell.CELL_TYPE_FORMULA: // 公式
					value = cell.getCellFormula() + "";
					break;
				case HSSFCell.CELL_TYPE_BLANK: // 空值
					value = "";
					break;
				case HSSFCell.CELL_TYPE_ERROR: // 故障
					value = "";
					break;
				default:
					value = "";
					break;
				}
				System.out.print(" " + value + " ");
				if (value == null || "".equals(value)) {
					continue;
				}
				linked.add(value);
			}
			System.out.println("");
			list.add(linked);
		}
		return list;
	}
}
