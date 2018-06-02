package datosTemporarios;

import java.util.Calendar;

public class DiaDeLaSemana {
	
	private String[] dias = { "domingo", "lunes", "martes", "miercoles", "jueves", "viernes", "sabado" };
	private Calendar calendar;
	
	public DiaDeLaSemana() {
		calendar = Calendar.getInstance();
	}
	
	public String getDiaDeLaSemana() {
		return dias[calendar.get(Calendar.DAY_OF_WEEK) - 1];
	}
}
