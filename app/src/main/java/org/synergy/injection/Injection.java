package org.synergy.injection;

import org.synergy.base.Log;

import java.io.DataOutputStream;

public final class Injection {

    static {
        System.loadLibrary("synergy-jni");
    }

    private Injection() {
    }

    public static native void keydown(int key, int mask);

    public static native void keyup(int key, int mask);

    public static final native void movemouse(final int x, final int y);

    public static native void mousedown(int buttonId);

    public static native void mouseup(int buttonId);

    public static native void mousewheel(int x, int y);

    public static void setPermissionsForInputDevice() {
        Log.debug("Starting injection");
        try {
            Process process = Runtime.getRuntime().exec("su");
            DataOutputStream dout = new DataOutputStream(process.getOutputStream());
            dout.writeBytes("chmod 666 /dev/uinput");
            dout.flush();
            dout.close();
            process.waitFor();
            Log.debug("Access to /dev/uinput granted");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void startInjection(String deviceName) {
        changeSELinuxMode(false);
        start(deviceName);
        changeSELinuxMode(true);
    }

    private static void changeSELinuxMode(boolean enable) {
        int code = enable ? 1 : 0;  // setenforce 1 == enable/enforce selinux while 0 is to disable
        Log.debug("Changing SELinux Mode to " + code);
        try {
            Process process = Runtime.getRuntime().exec("su");
            DataOutputStream dout = new DataOutputStream(process.getOutputStream());
            dout.writeBytes("setenforce " + code);
            dout.flush();
            dout.close();
            process.waitFor();
            Log.debug("SELinux Mode successfully changed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Functions imported from synergy-jni library
     */
    public static final native void start(String deviceName);

    public static void stopInjection() {
        stop();
    }

    public static native void stop();
}
