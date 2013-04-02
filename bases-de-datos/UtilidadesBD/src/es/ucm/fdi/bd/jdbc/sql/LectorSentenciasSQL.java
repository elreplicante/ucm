package es.ucm.fdi.bd.jdbc.sql;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class LectorSentenciasSQL {

	private Reader reader;

	private StringBuilder buffer;
	
	public LectorSentenciasSQL(InputStream is) {
		this.reader = new InputStreamReader(is);
		this.buffer = new StringBuilder();
	}
	
	public String siguienteInstruccion() throws IOException {
		String instruccion = null;

		if (buffer.length() > 0) {
			int pos = buscaChar(buffer, 0, buffer.length(), ';');
			if (pos > 0) {
				instruccion = buffer.substring(0, pos);
				buffer = new StringBuilder(buffer.substring(pos+1, buffer.length()));
			}
		} else {			
			char[] cbuf = new char[4*1024];
			int leidos = 0;
			while((instruccion == null) &&((leidos = reader.read(cbuf)) != -1)) {
				leidos = reemplazaSeparadorDeLinea(' ', cbuf, 0, leidos);
				int pos = buscaChar(cbuf, 0, leidos, ';');
				if (pos > 0) {
					buffer.append(cbuf, 0, pos);
					instruccion = buffer.toString();
					buffer.setLength(0);
					buffer.append(cbuf, pos+1, leidos);
				} else {
					buffer.append(cbuf, 0, leidos);
				}
			}
			if (leidos == -1 && buffer.length() > 0) {
				instruccion = buffer.toString();
				buffer.setLength(0);
			}
		}
		return instruccion;
	}

	private int reemplazaSeparadorDeLinea(char reemplazo, char[] cbuf, int fromIndex, int toIndex) {
		String separador = System.getProperty("line.separator");
		int newToIndex = toIndex;
		
		int pos = buscaSeparador(separador, cbuf, fromIndex, toIndex);
		while (pos != -1) {
			newToIndex = reemplazaSeparador(reemplazo, separador.length(), cbuf, pos, newToIndex);
			pos = buscaSeparador(separador, cbuf, fromIndex, newToIndex);
		}
		
		return newToIndex;
	}

	private int buscaSeparador(String separador, char[] cbuf, int fromIndex, int toIndex) {
		int pos = -1;
		int numChars = separador.length();
		int i = fromIndex;
		while ((pos == -1)&&(i < toIndex)) {
			int numEncontrados = 0;
			while ((numEncontrados < numChars)
					&&((numEncontrados+fromIndex) < toIndex)
						&&(cbuf[numEncontrados+i] == separador.charAt(numEncontrados))) {				
				numEncontrados++;
			}
			if (numEncontrados == numChars) {
				pos = i;
			} else {
				i++;
			}
		}
		return pos;
	}

	private int reemplazaSeparador(char reemplazo, int longSeparador, char[] cbuf, int fromIndex, int toIndex) {
		cbuf[fromIndex] = reemplazo;
		for(int i=fromIndex+1; (i+longSeparador-1) < toIndex; i++) {
			cbuf[i] = cbuf[i+longSeparador-1];
		}
		return toIndex - (longSeparador - 1);
	}

	private int buscaChar(CharSequence cbuf, int fromIndex, int toIndex, char c) {
		for(int i = fromIndex; i < toIndex; i++ ) {
			if (cbuf.charAt(i) == c) {
				return i;
			}
		}
		return -1;
	}

	private int buscaChar(char[] cbuf, int fromIndex, int toIndex, char c) {
		for(int i = fromIndex; i < toIndex; i++ ) {
			if (cbuf[i] == c) {
				return i;
			}
		}
		return -1;
	}
}
