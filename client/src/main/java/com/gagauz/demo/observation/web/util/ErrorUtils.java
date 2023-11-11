package com.gagauz.demo.observation.web.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ErrorUtils {
    public static String getStacktrace(Throwable throwable) {
        var string = new StringWriter();
        var writer = new PrintWriter(string);
        throwable.printStackTrace(writer);
        System.out.println(string.toString());
        return string.toString();
    }
}
