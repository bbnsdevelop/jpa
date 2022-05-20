package br.com.bbnsdevelop.hibernate.enums;

public enum TipoFuncionario {
	
	MEIO_PERIODO(1),
	INTEGRAL(2);
	
	private int tipo;
	
	TipoFuncionario(int tipo) {
		this.tipo = tipo;
	}

}
