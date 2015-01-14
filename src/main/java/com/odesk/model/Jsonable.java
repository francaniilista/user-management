package com.odesk.model;

import java.io.IOException;


public interface Jsonable {
	public String toJSON() throws IOException;
}
