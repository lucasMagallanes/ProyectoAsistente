package datosTemporarios;

import java.util.Calendar;

public class HoraYMinutos {

	private Calendar calendar;
	
	public HoraYMinutos() {
		calendar = Calendar.getInstance();
	}
	
	public String getHoraYMinutos() {
		return calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE) + ((calendar.get(Calendar.AM_PM) == Calendar.PM) ? " PM":" AM");
	}
}
