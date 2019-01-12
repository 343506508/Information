package com.project.tools;

/**
 * Jun 25, 2012
 */

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * Excel组件
 *
 * @author Snowolf
 * @version 1.0
 * @since 1.0
 */
public abstract class ExcelHelper {

	/**
	 * Excel 2003
	 */
	private final static String XLS = "xls";
	/**
	 * Excel 2007
	 */
	private final static String XLSX = "xlsx";
	/**
	 * 分隔符
	 */
	private final static String SEPARATOR = "|";

	/**
	 * 由Excel文件的Sheet导出至List
	 *
	 * @param file
	 * @param sheetNum
	 * @return
	 */
	public static List<String> exportListFromExcel(File file, int sheetNum)
			throws IOException {
		return exportListFromExcel(new FileInputStream(file),
				FilenameUtils.getExtension(file.getName()), sheetNum);
	}

	/**
	 * 由Excel流的Sheet导出至List
	 *
	 * @param is
	 * @param extensionName
	 * @param sheetNum
	 * @return
	 * @throws IOException
	 */
	public static List<String> exportListFromExcel(InputStream is,
												   String extensionName, int sheetNum) throws IOException {

		Workbook workbook = null;

		if (extensionName.toLowerCase().equals(XLS)) {
			workbook = new HSSFWorkbook(is);
		} else if (extensionName.toLowerCase().equals(XLSX)) {
			workbook = new XSSFWorkbook(is);
		}

		return exportListFromExcel(workbook, sheetNum);
	}

	/**
	 * 由指定的Sheet导出至List
	 *
	 * @param workbook
	 * @param sheetNum
	 * @return
	 * @throws IOException
	 */
	private static List<String> exportListFromExcel(Workbook workbook,
													int sheetNum) {
		List<String> list = new ArrayList<String>();
		if(workbook!=null){
			Sheet sheet = workbook.getSheetAt(sheetNum);

			// 解析公式结果
			FormulaEvaluator evaluator = workbook.getCreationHelper()
					.createFormulaEvaluator();



			int minRowIx = sheet.getFirstRowNum();
			int maxRowIx = sheet.getLastRowNum();
			Row row2 = sheet.getRow(minRowIx);
			short maxColIx2 = row2.getLastCellNum();

			for (int rowIx = minRowIx; rowIx <= maxRowIx; rowIx++) {
				Row row = sheet.getRow(rowIx);
				StringBuilder sb = new StringBuilder();
				short minColIx = 0;
//			short minColIx = row.getFirstCellNum();
//			short maxColIx = row.getLastCellNum();
				short maxColIx = maxColIx2;
				for (short colIx = minColIx; colIx <= maxColIx; colIx++) {
					Cell cell = row.getCell(Integer.valueOf(colIx));
					CellValue cellValue = evaluator.evaluate(cell);

					if (cellValue == null) {

						//为空值时
						sb.append(SEPARATOR+"null");
						continue;
					}else{
						// 经过公式解析，最后只存在Boolean、Numeric和String三种数据类型，此外就是Error了
						// 其余数据类型，根据官方文档，完全可以忽略http://poi.apache.org/spreadsheet/eval.html

						switch (cellValue.getCellType()) {
							case Cell.CELL_TYPE_BOOLEAN:
								sb.append(SEPARATOR + cellValue.getBooleanValue());
								break;
							case Cell.CELL_TYPE_NUMERIC:
								// 这里的日期类型会被转换为数字类型，需要判别后区分处理
								if (DateUtil.isCellDateFormatted(cell)) {
									SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

									sb.append(SEPARATOR + format.format(cell.getDateCellValue()));
								} else {
									sb.append(SEPARATOR + new DecimalFormat("0").format(cellValue.getNumberValue()));
								}
								break;
							case Cell.CELL_TYPE_STRING:
								sb.append(SEPARATOR + cellValue.getStringValue());
								break;
							case Cell.CELL_TYPE_FORMULA:
								break;
							case Cell.CELL_TYPE_BLANK:
								break;
							case Cell.CELL_TYPE_ERROR:
								break;
							default:
								break;
						}
					}

				}
				list.add(sb.toString());
			}
		}

		return list;
	}

//	public static void main(String[] args) {
////		String str = "null|发发发|null";
////		String[] strArray=str.substring(0,2).split("\\|");
////		System.out.println("------1-----"+strArray[0]);
////		System.out.println("------1-----"+strArray[1]);
////		System.out.println("------1-----"+strArray[2]);
//		String path = "G://user.xlsx";
//		List<String> list = null;
//		try {
//
//			list = ExcelHelper.exportListFromExcel(new File(path), 0);
//
//			for (int i = 0; i < list.size(); i++) {
//				String[] strArray = null;
//				strArray = list.get(i).split("\\|"); //拆分字符为"," ,然后把结果交给数组strArray
//				//System.out.print("----"+strArray);
//
////				for(int j=1; j<strArray.length; j++) {
////					System.out.println("--ff--"+strArray[j]);     	  //  strArray[i] = i;
////				}
////				System.out.println("+++++++++++++++");
//			}
////			System.out.println(list);
////					assertNotNull(list);
//		} catch (IOException e) {
////					fail();
//		}
//
//
//	}
}
