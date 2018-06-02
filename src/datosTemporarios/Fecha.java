package datosTemporarios;

import java.util.Calendar;

public class Fecha {

	private String[] meses = { "enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre",
								"octubre", "noviembre", "diciebre" };
	private Calendar calendar;
	
	public Fecha() {
		calendar = Calendar.getInstance();
	}
	
	public String getMes() {
		return meses[calendar.get(Calendar.MONTH)];		//Enero es el mes 0
	}

	public Integer getDia() {
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	public Integer getAnio() {
		return calendar.get(Calendar.YEAR);
	}
	
}
