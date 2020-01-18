package com.leablogs.pdf;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;

public interface PdfExportService {
	public void make(Map<String, Object> model, Document document, com.lowagie.text.pdf.PdfWriter writer,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);
}
