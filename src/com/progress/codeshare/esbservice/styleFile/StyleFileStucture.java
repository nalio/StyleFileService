package com.progress.codeshare.esbservice.styleFile;

public class StyleFileStucture {

	private static final int PADDING_CHARACTER = 1;

	private static final int PADDING_NUMEBER = 2;
		
	private String _fileName = "";

	private String _character = "";

	private String _default = "";

	private String _value = "";

	private int _callFunction = -1;

	private String _typeFile = "";

	private int _length = 0;

	private int _precision = 0;

	public StyleFileStucture() {
	}

	public int getCallFunction() {
		return this._callFunction;
	}

	public void setCallFunction(String functionName) {
		if (functionName.equalsIgnoreCase("padding-character")) {
			this._callFunction = PADDING_CHARACTER;
		}

		if (functionName.equalsIgnoreCase("padding-number")) {
			this._callFunction = PADDING_NUMEBER;
		}
	
	}

	public String getCharacter() {
		return this._character;
	}

	public void setCharacter(String _character) {
		this._character = _character;
	}

	public String getDefault() {
		return this._default;
	}

	public void setDefault(String _default) {
		this._default = _default;
	}

	public String getFileName() {
		return this._fileName;
	}

	public void setFileName(String name) {
		this._fileName = name;
	}

	public int getLength() {
		return this._length;
	}

	public void setLength(int length) {
		this._length = length;
	}

	public int getPrecision() {
		return this._precision;
	}

	public void setPrecision(int precision) {
		this._precision = precision;
	}

	public String getTypeFile() {
		return _typeFile;
	}

	public void setTypeFile(String typeFile) {
		_typeFile = typeFile;
	}

	public String getValue() {
		return _value;
	}

	public void setValue(String value) {
		this._value = value;
	}

}
