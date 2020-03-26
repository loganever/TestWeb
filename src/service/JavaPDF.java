package service;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import entity.Commodity;
import entity.User;

public class JavaPDF {
	public static void exPDF(Vector<Commodity> commoditys,User user,String path) throws Exception 
	{

		File file = new File(path);
		FileOutputStream outs = new FileOutputStream(file); // 获取输出流
	
		Document document = new Document(PageSize.A4);

		PdfWriter.getInstance(document, outs);
		document.open();
		// 设置中文字体
		BaseFont bfChinese = BaseFont.createFont("STSong-Light",
				"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);

		Font t1 = new Font(bfChinese, 20, Font.BOLD); // 设置一级标题字体
		Font t2 = new Font(bfChinese, 15, Font.BOLD); // 设置二级标题字体
		Font f1 = new Font(bfChinese, 12, Font.NORMAL); // 设置正文字体

		Paragraph pragraph = new Paragraph("甩手掌柜购物清单", t1);
		pragraph.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(pragraph);

		pragraph = new Paragraph("用户:"+user.getUsername()+"   时间:"+getDate(), t2);
		pragraph.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(pragraph);

		// 建立一个表格
		PdfPTable table = new PdfPTable(5);
		table.setSpacingBefore(40f);// 设置表格上面空白宽度

		// 生成表头
		String[] bt = new String[] { "商品名称", "商品价格", "商品所在学校", "商品格子铺位置","卖家邮箱" };
		for (int i = 0; i < bt.length; i++) {
			PdfPCell cell = new PdfPCell(new Paragraph(bt[i], f1)); // 建立一个单元格
			cell.setBackgroundColor(Color.GRAY);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER); // 设置内容水平居中显示
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
			table.addCell(cell);
		}

		for (int i = 0; i < commoditys.size(); i++) {
			PdfPCell nameCell = new PdfPCell(new Paragraph(commoditys.get(i).getCommodityName(), f1)); // 建立一个单元格
			nameCell.setHorizontalAlignment(Element.ALIGN_CENTER); // 设置内容水平居中显示
			nameCell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
			PdfPCell priceCell = new PdfPCell(new Paragraph(String.valueOf(commoditys.get(i).getPrice()),f1)); // 建立一个单元格
			priceCell.setHorizontalAlignment(Element.ALIGN_CENTER); // 设置内容水平居中显示
			priceCell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
			PdfPCell schoolCell = new PdfPCell(new Paragraph(commoditys.get(i).getSchool(), f1)); // 建立一个单元格
			schoolCell.setHorizontalAlignment(Element.ALIGN_CENTER); // 设置内容水平居中显示
			schoolCell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
			PdfPCell locationCell = new PdfPCell(new Paragraph(commoditys.get(i).getLocation(), f1)); // 建立一个单元格
			locationCell.setHorizontalAlignment(Element.ALIGN_CENTER); // 设置内容水平居中显示
			locationCell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
			PdfPCell mailCell = new PdfPCell(new Paragraph(commoditys.get(i).getSalerName(), f1)); // 建立一个单元格
			locationCell.setHorizontalAlignment(Element.ALIGN_CENTER); // 设置内容水平居中显示
			locationCell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
			table.addCell(nameCell);
			table.addCell(priceCell);
			table.addCell(schoolCell);
			table.addCell(locationCell);
			table.addCell(mailCell);
		}
		document.add(table);
		document.close();
		outs.close();

	}
	public static String getDate() 
    {
    	Date d=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //格式化为日期/时间字符串
        String cc=sdf.format(d);
        return cc;
	}
}
