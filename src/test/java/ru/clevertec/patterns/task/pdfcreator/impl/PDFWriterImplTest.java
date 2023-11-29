package ru.clevertec.patterns.task.pdfcreator.impl;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.patterns.task.entity.dto.ClientDto;
import ru.clevertec.patterns.task.pdfcreator.PDFWriter;
import ru.clevertec.patterns.task.pdfcreator.impl.PDFWriterImpl;
import util.ClientTestData;
import util.ClientsTestData;
import util.ConstantsForTest;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class PDFWriterImplTest {

	private PDFWriter pdfWriter;

	@BeforeEach
	void setup() {
		pdfWriter = new PDFWriterImpl();
	}

	@Test
	void shouldWriteClientsToPDF() throws IOException {
		// given
		String expected = ConstantsForTest.EXPECTED_PDF_TEXT_CLIENTS;
		List<ClientDto> clients = ClientsTestData.getListOfClientsDto();

		// when
		pdfWriter.writeClientsToPDF(clients, ConstantsForTest.PATH_PDF_CLIENTS, ConstantsForTest.FILE_NAME_PDF_CLIENTS);
		String actual = readFromPDF(ConstantsForTest.PATH_PDF_CLIENTS, ConstantsForTest.FILE_NAME_PDF_CLIENTS);

		// then
		assertEquals(expected, actual.substring(0, actual.length()-35));
	}

	@Test
	void shouldWriteClientToPDF() throws IOException {
		// given
		String expected = ConstantsForTest.EXPECTED_PDF_TEXT_CLIENT;
		ClientDto client = ClientTestData.builder()
										   .build()
										   .buildClientDto();

		// when
		pdfWriter.writeClientToPDF(client, ConstantsForTest.PATH_PDF_CLIENT, ConstantsForTest.FILE_NAME_PDF_CLIENT);
		String actual = readFromPDF(ConstantsForTest.PATH_PDF_CLIENT, ConstantsForTest.FILE_NAME_PDF_CLIENT);

		// then
		assertEquals(expected, actual.substring(0, actual.length()-35));
	}

	private String readFromPDF(String path, String fileName) throws IOException {
		PdfReader reader = new PdfReader(path + "/" + fileName + ".pdf");
		int pages = reader.getNumberOfPages();
		StringBuilder text = new StringBuilder();
		for (int i = 1; i <= pages; i++) {
			text.append(PdfTextExtractor.getTextFromPage(reader, i));
		}
		reader.close();
		return text.toString();
	}

}