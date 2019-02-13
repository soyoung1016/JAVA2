package day0107;

public class TableVO {
	private String column_name, data_type, data_precision, constraint_name;
	
	public TableVO() {
		
	}

	public TableVO(String column_name, String data_type, String data_precision, String constraint_name) {
		this.column_name = column_name;
		this.data_type = data_type;
		this.data_precision = data_precision;
		this.constraint_name = constraint_name;
	}

	public String getColumn_name() {
		return column_name;
	}

	public String getData_type() {
		return data_type;
	}

	public String getData_precision() {
		return data_precision;
	}

	public String getConstraint_name() {
		return constraint_name;
	}
	
}//class
