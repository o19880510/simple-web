package woo.study.web.common.util.file;

import java.io.ByteArrayOutputStream;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.DateFormulaCell;
import jxl.LabelCell;
import jxl.NumberCell;
import jxl.NumberFormulaCell;
import jxl.Sheet;
import jxl.StringFormulaCell;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import woo.study.web.common.util.DateUtils;
import woo.study.web.common.util.IOSystem;

/**
 * Excel帮助类<br>
 * 可以导出excel。也可以导入excel中的数据。
 * 
 * @author tianjp
 *
 */
public class ExcelHelper {

	private static final Log log = LogFactory.getLog(ExcelHelper.class);

	public static byte[] export(ExcelDTO... excelDTOs) {
		try {
			ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
			WritableWorkbook book = Workbook.createWorkbook(byteOutStream);

			int i = 0;
			for (ExcelDTO value : excelDTOs) {
				String sheet = value.getSheet();
				sheet = sheet == null ? ("sheet" + (i + 1)) : sheet;

				WritableSheet sheetBook = book.createSheet(sheet, i);
				createSheet(sheetBook, value.getTitles(), value.getDataList());

				++i;
			}

			book.write();
			book.close();

			return byteOutStream.toByteArray();

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Excel expert error:" + e);
			throw new RuntimeException("Excel expert error:" + e);
		}

	}

	private static void createSheet(WritableSheet sheet, List<String> titles, List<List<Object>> dataList)
			throws RowsExceededException, WriteException {

		// 设置表格表头
		int cols = 0; // 列
		int rows = 0; // 行
		for (String title : titles) {
			Label label = new Label(cols++, rows, title);
			WritableCellFormat cellFormat = new WritableCellFormat();
			cellFormat.setBackground(jxl.format.Colour.GRAY_25);
			label.setCellFormat(cellFormat);
			sheet.addCell(label);
		}

		// 设置表格数据
		for (List<Object> rowDataList : dataList) {

			++rows; // 移动到下一行
			cols = 0; // 从第一列开始
			for (Object oneData : rowDataList) {
				Label label = new Label(cols++, rows, getValue(oneData));
				sheet.addCell(label);
			}
		}

	}

	private static String getValue(Object obj) {

		if (obj instanceof String) {
			return (String) obj;

		} else if (obj instanceof Integer) {
			return String.valueOf((Integer) obj);

		} else if (obj instanceof org.joda.time.DateTime) {
			org.joda.time.DateTime date = (org.joda.time.DateTime) obj;

			return date.toString(DateUtils.FORMAT_DATE);

		} else if (obj instanceof java.sql.Date) {
			return DateUtils.format(DateUtils.FORMAT_DATE, (java.sql.Date) obj);
		} else {

			return obj == null ? "" : obj.toString();
		}
	}

	/**
	 * 导入Excel文件（读取Excel文件）
	 * 
	 * @param is
	 *            输入流
	 * @param titles
	 *            标题行, 多個標題用","分開
	 * @return Map对象
	 */
	public static List<List<String>> importExcel(InputStream is) {
		List<List<String>> recordList = new ArrayList<List<String>>();
		try {
			Workbook book = Workbook.getWorkbook(is);
			Sheet sheet = book.getSheet(0);
			int rows = sheet.getRows();
			int cols = sheet.getColumns();

			for (int i = 0; i < rows; i++) {
				List<String> list = new ArrayList<String>();
				for (int j = 0; j < cols; j++) {
					Cell cell = sheet.getCell(j, i);
					if (cell.getType() == CellType.LABEL) {
						LabelCell labelCell = (LabelCell) cell;
						String cellContent = labelCell.getString();
						list.add(cellContent);

					} else if (cell.getType() == CellType.NUMBER) {
						NumberCell numberCell = (NumberCell) cell;
						String cellContent = numberCell.getContents();
						list.add(cellContent);

					} else if (cell.getType() == CellType.DATE) {
						DateCell dateCell = (DateCell) cell;
						Date dateDemo = dateCell.getDate();
						String cellContent = DateUtils.format(DateUtils.FORMAT_DATE, dateDemo);
						list.add(cellContent);
						
					} else if (cell.getType() == CellType.NUMBER_FORMULA) {
						NumberFormulaCell numberFormulaCell = (NumberFormulaCell) cell;
						double value = numberFormulaCell.getValue();
						list.add(String.valueOf(value));
						
					} else if (cell.getType() == CellType.STRING_FORMULA) {
						StringFormulaCell stringFormulaCell = (StringFormulaCell) cell;
						list.add(stringFormulaCell.getString());
						
					} else if (cell.getType() == CellType.DATE_FORMULA) {
						DateFormulaCell dateFormulaCell = (DateFormulaCell) cell;

						Date dateDemo = dateFormulaCell.getDate();
						String cellContent = DateUtils.format(DateUtils.FORMAT_DATE, dateDemo);
						list.add(cellContent);
						
					} else {
						log.warn(i + ", cell type:" + cell.getType());
						list.add("");
					}
				}
				recordList.add(list);
			}
			book.close();
		} catch (Exception e) {
			throw new RuntimeException("import excel file failure! error:" + e);
		}

		return recordList;
	}

	/**
	 * 读取Excel文件
	 * 
	 * @param filePath
	 *            文件绝对路径
	 * @param titles
	 *            标题
	 * @return Map对象
	 */
	public static List<List<String>> importExcel(String filePath) {
		return importExcel(IOSystem.getInputStream(filePath));
	}
}
