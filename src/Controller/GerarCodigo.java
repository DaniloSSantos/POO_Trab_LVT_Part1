package Controller;

public class GerarCodigo {
	private static int id = 20;
	
	public static int getProximoCodigo() {
		return id++;
	}
}
