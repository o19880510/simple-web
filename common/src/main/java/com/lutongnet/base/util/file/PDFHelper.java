package com.lutongnet.base.util.file;

import java.io.ByteArrayOutputStream;

import java.util.List;

import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * PDF文件帮助类
 * 
 * 导出pdf文件
 * 
 * @author tianjp
 */
public class PDFHelper {
	
	/**
	 * 导出pdf文件
	 * @param propertise 属性数组
	 * @param list 数据列表
	 * @return byte[] pdf文件二进制数据
	 */
	public byte[] export(String[] propertise, List<String[]> list) {

		com.itextpdf.text.Document doc = new com.itextpdf.text.Document();
		try {

			// 支持中文
			BaseFont bfChinese = BaseFont.createFont("c://windows//fonts//simsun.ttc,1", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font FontChinese = new Font(bfChinese, 12, Font.NORMAL);

			PdfPTable table = new PdfPTable(propertise.length);
			for (String property : propertise) {

				PdfPCell cell = new PdfPCell();
				cell.addElement(new Paragraph(property, FontChinese));
				cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
				
				table.addCell(cell);
			}

			for (String[] rowDatas : list ) {
				for(String data : rowDatas){
					
					PdfPCell cell = new PdfPCell();
					cell.addElement(new Paragraph(data, FontChinese));
					cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
					table.addCell(cell);
				}
			}
			
			ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
			PdfWriter.getInstance(doc, byteArrayOut);
			return byteArrayOut.toByteArray();

		} catch (Exception ex) {
			throw new RuntimeException("Export PDF error:" + ex);
		}
	}

}
