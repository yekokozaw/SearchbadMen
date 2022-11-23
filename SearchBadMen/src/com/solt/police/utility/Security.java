package com.solt.police.utility;

import com.solt.police.entity.Police;

public class Security {
	private static Police police;
	public static Police getPolice() {
		return police;
	}

	public static void setPolice(Police police) {
		Security.police = police;
	}	
}
