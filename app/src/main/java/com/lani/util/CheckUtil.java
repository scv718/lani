package com.lani.util;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CheckUtil {

	public static List<String> getClassNames() {
		String packageName = "com.lani.request";
        List<String> classNames = new ArrayList<>();
        String path = packageName.replace(".", "/");
        File packageDirectory = new File(ClassLoader.getSystemResource(path).getFile());
        File[] files = packageDirectory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".class")) {
                    String className = packageName + "." + file.getName().replace(".class", "");
                    classNames.add(className);
                }
            }
        }
        return classNames;
    }

	public static boolean nullCheck(Object value) {

		Class<?> clazz = value.getClass();

		for(Field field : clazz.getDeclaredFields()) {
			try{
				field.setAccessible(true);
				if(field.get(value) == null) {
					return false;
				}
			}catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}


	public static boolean parameterCheck(Object value) {

		List<String> classNames = getClassNames();

		for (String className : classNames) {
            try {
                Class<?> clazz = Class.forName(className);
                if (clazz.isInstance(value)) {

                    return nullCheck(value);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

		return false;

	}
}
