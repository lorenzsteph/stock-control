package com.stock.control;

public enum ExcelEnum {

	PALLA("Palla", 0), CLAVETTE("Clavete", 1), MEZZEPUNTE("Mezzepunte", 2), CERCHIO("Cerchio", 3), NASTRO("Nastro", 4), BASTONCINO("Bastoncino", 5), FUNE("Fune", 6), NASTRINI_DECORATIVI("Nastrini decorativi", 7), GADGET("Gadget",
			8), CAPELLI("Capelli", 9), ACCESSORI("Accessori", 10), PORTATTREZZI("Portattrezzi", 11), ABBIGLIAMENTO("Abbigliamento", 12);

	private final String name;
	private final int sheet;

	ExcelEnum(String name, int sheet) {
		this.name = name;
		this.sheet = sheet;
	}

	public static ExcelEnum getExcelEnumByName(String name) {
		ExcelEnum f = null;
		for (ExcelEnum e : ExcelEnum.values()) {
			if (e.getName().equals(name)) {
				f = e;
			}
		}

		return f;
	}

	public static ExcelEnum getExcelEnumBySheet(int sheet) {
		ExcelEnum f = null;
		for (ExcelEnum e : ExcelEnum.values()) {
			if (e.getSheet() == sheet) {
				f = e;
			}
		}

		return f;
	}

	public String getName() {
		return name;
	}

	public int getSheet() {
		return sheet;
	}

}
