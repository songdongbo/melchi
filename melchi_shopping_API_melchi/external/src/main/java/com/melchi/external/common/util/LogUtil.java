package com.melchi.external.common.util;

import org.slf4j.MDC;

public class LogUtil {
	public static void setGroup(String group, String action) {
		MDC.put("group", group);
		MDC.put("action", action);
	}
	
	public static void clearGroup() {
		MDC.clear();
	}

}
