package Helper;

public class Item {
	
	private int key;
	private String value;
	
	/**
	 * 
	 * @constructor
	 * @param key
	 * @param value
	 * 
	 */
	public Item(int key, String value) {
		setKey(key);
		setValue(value);
	}
	/**
	 * 
	 * @return
	 * 
	 */
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return this.value;
	}
}
