package com.progress.codeshare.esbservice.styleFile;

import com.sonicsw.xq.XQConstants;
import com.sonicsw.xq.XQEnvelope;
import com.sonicsw.xq.XQInitContext;
import com.sonicsw.xq.XQMessage;
import com.sonicsw.xq.XQParameters;
import com.sonicsw.xq.XQService;
import com.sonicsw.xq.XQServiceContext;
import com.sonicsw.xq.XQServiceException;

public final class StyleFileService implements XQService {

	private static final String PARAM_NAME_LAYOUT_PART = "layoutPart";

	private static final String PARAM_NAME_MESSAGE_PART = "messagePart";

	private static final int PADDING_CHARACTER = 1;

	private static final int PADDING_NUMEBER = 2;

	public void destroy() {
	}

	public void init(XQInitContext initCtx) {
	}

	public void service(final XQServiceContext servCtx)
			throws XQServiceException {

		try {

			while (servCtx.hasNextIncoming()) {
				final XQEnvelope env = servCtx.getNextIncoming();

				final XQMessage origMsg = env.getMessage();

				//final Iterator addressIterator = env.getAddresses();

				// carrega coleção de StyleFileStructure - Header or All
				// carrega coleção de StyleFileStructure - Detail or All
				// carrega coleção de StyleFileStructure - Trailer or All

				for (int partMessage = 0; partMessage < origMsg.getPartCount(); partMessage++) {

					// carrega os valores do service
					final XQParameters params = servCtx.getParameters();

					final String layoutPart = params.getParameter(
							PARAM_NAME_LAYOUT_PART, XQConstants.PARAM_STRING);

					final String messagePart = params.getParameter(
							PARAM_NAME_MESSAGE_PART, XQConstants.PARAM_STRING);

					StringBuffer returnValue = new StringBuffer();

					/* Decide whether to process the part or not */
					if ((partMessage == Integer.parseInt(messagePart))
							|| (XQConstants.ALL_PARTS == Integer
									.parseInt(messagePart))) {

						// carrega parte da mensagem em um xml

						// start loop do xml da mensagem

						// for (iterable_type iterable_element : iterable) {

						// Header or All 
						if (layoutPart.equals("Header")
								|| layoutPart.equals("All")) {

							// loop coleção de StyleFileStructure
							StyleFileStucture SFSHeader = new StyleFileStucture();

							// loop structure
							returnValue.append(structure(SFSHeader));
							// end loop structure

							// end loop coleção de StyleFileStructure

						}

						// Detail or All
						if (layoutPart.equals("Detail")
								|| layoutPart.equals("All")) {
							// loop coleção de StyleFileStructure
							StyleFileStucture SFSDetail = new StyleFileStucture();

							// loop structure
							returnValue.append(structure(SFSDetail));
							// end loop structure

							// end loop coleção de StyleFileStructure
						}

						// Trailer or All
						if (layoutPart.equals("Trailer")
								|| layoutPart.equals("All")) {

							// loop coleção de StyleFileStructure
							StyleFileStucture SFSTrailer = new StyleFileStucture();

							// loop structure
							returnValue.append(structure(SFSTrailer));
							// end loop structure

							// end loop coleção de StyleFileStructure
						}

					}

					// end loop do xml da mensagem
				}
			}
		} catch (final Exception e) {
			throw new XQServiceException(e);
		}

	}

	public static String padding_character(String value, int length,
			String character, String valueDefault) {

		StringBuffer result = new StringBuffer();

		if (value == null) {
			value = valueDefault;
		}

		if (value.trim().length() == 0) {
			value = valueDefault;
		}

		result.append(value);
		for (int i = 0; i < length - value.length(); i++) {
			result.append(character);
		}

		return result.toString().substring(0, length);

	}

	public static String padding_number(String value, int length,
			int precision, int character, int valueDefault) throws Exception {
		return padding_number(value, length, precision, String
				.valueOf(character), String.valueOf(valueDefault));
	}

	public static String structure(StyleFileStucture structure)
			throws Exception {
		String returnValue;

		switch (structure.getCallFunction()) {
		case PADDING_CHARACTER:
			returnValue = (padding_character(structure.getValue(), structure
					.getLength(), structure.getCharacter(), structure
					.getDefault()));
			break;

		case PADDING_NUMEBER:

			returnValue = (padding_number(structure.getValue(), structure
					.getLength(), structure.getPrecision(), structure
					.getCharacter(), structure.getValue()));
			break;

		default:
			throw new Exception("Name the function not definition.");
		}

		return returnValue;
	}

	public static String padding_number(String value, int length,
			int precision, String character, String valueDefault)
			throws Exception {

		StringBuffer result = new StringBuffer();
		int operator = (int) Math.pow(10, precision);

		if (value == null) {
			value = valueDefault;
		}

		if (value.trim().length() == 0) {
			value = valueDefault;
		}

		double parseDouble = Math.floor(Double.parseDouble(value) * operator);

		value = String.valueOf(Math.round(parseDouble));

		for (int i = 0; i < length - value.length(); i++) {
			result.append(character);
		}

		result.append(value);

		return result.toString().substring(
				Math.abs(length - result.toString().length()), length);
	}

}