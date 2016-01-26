package org.tpv.print.fiscal.exception;

import org.tpv.print.fiscal.document.Document;
import org.tpv.print.fiscal.msg.MsgRepository;

public class DocumentException extends Exception {

	private Document document;

	/**
	 * @param msg
	 */
	public DocumentException(String msg) {
		super(MsgRepository.get(msg));
	}

	/**
	 * @param msg
	 * @param document
	 */
	public DocumentException(String msg, Document document) {
		this(msg);
		this.document = document;
	}

	/**
	 * @return Returns the document.
	 */
	public Document getDocument() {
		return document;
	}

	/**
	 * @param document The document to set.
	 */
	public void setDocument(Document document) {
		this.document = document;
	}
	
}
