package com.leablogs.controller;

import java.awt.Color;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import com.leablogs.pdf.PdfExportService;
import com.leablogs.pdf.PdfView;
import com.leablogs.pojo.User;
import com.leablogs.service.UserService;
import com.lowagie.text.Chunk;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

@Controller
@RequestMapping("/exportpdf")
public class ExportPdf {
	@Autowired
	private UserService userService = null;

	@GetMapping("/export/pdf")
	public ModelAndView exportPdf(Integer id) {
		List<User> users = userService.getAll();
		View view = new PdfView(pdfExportService());
		ModelAndView mv = new ModelAndView();
		mv.setView(view);
		mv.addObject("userList", users);
		System.out.println(users.toString());
		return mv;

	}

	@SuppressWarnings("unchecked")
	private PdfExportService pdfExportService() {
		return (model, document, write, request, response) -> {
			try {
				document.setPageSize(PageSize.A4);
				document.addTitle("UserInfo");
				document.add(new Chunk("\n"));
				PdfPTable table = new PdfPTable(3);
				Font f8 = new Font();
				f8.setColor(Color.BLUE);
				f8.setStyle(Font.BOLD);
				PdfPCell cell = null;

				cell = new PdfPCell(new Paragraph("id", f8));
				cell.setHorizontalAlignment(1);
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph("user_name", f8));
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph("note", f8));
				table.addCell(cell);
				List<User> userList = (List<User>) model.get("userList");
				for (User user : userList) {
					document.add(new Chunk("\n"));
					cell = new PdfPCell(new Paragraph(user.getId() + ""));
					table.addCell(cell);
					cell = new PdfPCell(new Paragraph(user.getUsername() + ""));
					table.addCell(cell);
					String node = user.getNote() == null ? "" : user.getNote();
					cell = new PdfPCell(new Paragraph(node + ""));
					table.addCell(cell);

				}
				document.add(table);
			} catch (Exception e) {
				e.printStackTrace();
			}
		};
	}

}
