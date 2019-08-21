# Synergy for Android

Cloned from More details on that are in [chankruze's version](https://github.com/chankruze/synergy-android-client)

# What's different
Added code to temporarily set selinux as permissive to enable injection to work on my mtk device.
 
The full gist: 
In the other versions (including chankruze's) the app loads and shows and pretends to work. The issue is that the mouse and keyboard activities doesn't show up on the Android device. After checking logs, I see that the Android system is blocking this "untrusted" app from writing "/dev/uinput". The device is a mediatek (MTK) device running Android 8.1.0. I discovered the app works when I disable selinux. So I narrowed it down to the point where I can disable selinux just before clicking the CONNECT button and re-enable it immediately after for the app to work properly. In the code, I disable selinux just before starting the native Injection (to allow android accept it) and then re-enable it after.
 This may cause a problem for someone who doesn't want selinux to be enforced. This version will automatically re-enable selinux enforcing. So it'd be better to use any of the other versions if you don't have the same problem I had.