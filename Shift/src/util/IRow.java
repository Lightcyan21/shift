package util;

import java.util.Vector;

public interface IRow {
	public IRow copyRow();
	public Vector<Object> toVector();
}
