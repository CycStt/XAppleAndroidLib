package com.xapple.gankio;

/**
 * 项目名称：XAppleGankIO
 * 类描述：
 * 创建人：wengyiming
 * 创建时间：2016/8/26 16:15
 * 修改人：wengyiming
 * 修改时间：2016/8/26 16:15
 * 修改备注：
 */
public final class FakeCrashLibrary {
    /** Not a real crash reporting library! */
    public static void log(int priority, String tag, String message) {
        // TODO add log entry to circular buffer.
    }

    public static void logWarning(Throwable t) {
        // TODO report non-fatal warning.
    }

    public static void logError(Throwable t) {
        // TODO report non-fatal error.
    }

    private FakeCrashLibrary() {
        throw new AssertionError("No instances.");
    }
}
