package br.capgemini.desafio.agencia.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Util {

	private static final Logger LOGGER = LogManager.getLogger(Util.class.getName());

	private Util() {
	}

	public static boolean isNotNullOrEmpty(Object pObj) {

		if (pObj == null) {
			return false;
		}

		if (pObj instanceof String) {
			return !((String) pObj).trim().equals("");
		}

		if (pObj instanceof Object[]) {
			Object[] array = (Object[]) pObj;
			if (array.length > 0) {
				boolean vazio = false;
				for (int i = 0; i < array.length; i++) {
					if (array[i] != null) {
						vazio = true;
						break;
					}
				}
				return vazio;
			} else {
				return false;
			}

		}

		return true;
	}

	public static boolean isNullOrEmpty(Object pObj) {

		if (pObj == null) {
			return true;
		}

		if (pObj instanceof String) {
			return ((String) pObj).trim().equals("");
		}

		if (pObj instanceof Object[]) {
			Object[] array = (Object[]) pObj;
			if (array.length > 0) {
				boolean vazio = true;
				for (int i = 0; i < array.length; i++) {
					if (array[i] != null) {
						vazio = false;
						break;
					}
				}
				return vazio;
			} else {
				return true;
			}

		}

		return false;
	}

	public static Date getConverteStringToDate(String pData) {
		String lFormato = "dd/MM/yyyy";

		Date lDataFormatada = null;

		try {
			if (pData != null && pData.length() == 10 && pData.indexOf('/') == 2) {
				lDataFormatada = new SimpleDateFormat(lFormato).parse(pData.trim());
			}
		} catch (ParseException pe) {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("Erro ao converter string para data: ");
			stringBuilder.append(pe.getMessage());
			LOGGER.warn(stringBuilder.toString());
			lDataFormatada = null;
		}
		return lDataFormatada;
	}

	public static String getConverteDateToString(Date pData) {
		String lFormato = "dd/MM/yyyy";

		DateFormat dateFormat = new SimpleDateFormat(lFormato);
		String dataFormatada = dateFormat.format(pData);
		return dataFormatada;
	}

	public static String getDataFormatada(Date pData) {

		if (pData == null)
			return "";

		String lFormato = "dd/MM/yyyy";
		SimpleDateFormat lFormatter = new SimpleDateFormat(lFormato);

		return (lFormatter.format(pData));
	}

	public static boolean isDateValidDefault(String date) {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		df.setLenient(false);
		try {
			df.parse(date);
			return true;
		} catch (ParseException ex) {
			return false;
		}
	}

	public static boolean validatePrecedenciaDatas(Date dataInicio, Date dataFim) {
		return !dataFim.before(dataInicio);
	}

	public static boolean isNumerico(String valor) {
		boolean valido = true;

		if (isNullOrEmpty(valor)) {
			return false;
		}

		byte count = 0;
		for (int i = 0; i < valor.length(); i++) {
			Character caractere = valor.charAt(i);
			if (!(Character.isDigit(caractere) || caractere == '.')) {
				valido = false;
				break;
			}

			if (caractere == '.') {
				++count;
				if (count == 2) {
					valido = false;
				}
			}
		}

		return valido;
	}

	public static String removeExceededWhiteSpace(String texto) {
		if (isNullOrEmpty(texto)) {
			return null;
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < texto.length(); i++) {

			if (!isNullOrEmpty(texto.charAt(i))) {
				if (i != 0) {

					if (!(texto.charAt(i) == ' ' && texto.charAt(i - 1) == ' ')) {
						sb.append(texto.charAt(i));
					}

				} else {

					sb.append(texto.charAt(i));
				}
			}

		}

		for (int i = 0; i < sb.length(); i++) {
			if (i == sb.length() - 1 && sb.charAt(i) == ' ') {
				sb.deleteCharAt(i);
			}
		}

		return sb.toString();
	}

}
