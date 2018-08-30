### 一、 strace 日志分析

	open("/proc/version", O_RDONLY)         = 301
	access("/system/bin/su", F_OK)          = -1 ENOENT (No such file or directory)
	access("/system/xbin/su", F_OK) 

	// 读这两个文件会不会是对比 CPU 核数能不能对的上
	open("/proc/cpuinfo", O_RDONLY)         = 301
	open("/sys/devices/system/cpu", O_RDONLY|O_DIRECTORY) = 301      

	sendto(241, "=graphics 2147483647 0 0 0\n", 27, MSG_NOSIGNAL, NULL, 0) = 27
	close(300)                              = 0
	ioctl(213, 0xc0104806, 0xbee05090)      = 0
	open("/dev/tegra-throughput", O_RDWR)   = 300
	ioctl(300, GADGETFS_FIFO_STATUS, 0)     = 0
	clock_gettime(CLOCK_MONOTONIC, {37914, 797265710}) = 0
	sendto(241, "=graphics 1 0 2000 0\n", 21, MSG_NOSIGNAL, NULL, 0) = 21
	clock_gettime(CLOCK_MONOTONIC, {37914, 797456793}) = 0
	sendto(241, "=graphics 1 0 2000 0\n", 21, MSG_NOSIGNAL, NULL, 0) = 21

	open("/dev/tegra-throughput", O_RDWR)   = 300


### 二、 Java 层 hook 总结

#### 2.1 build info
	06-26 02:17:20.975 1772-1772/com.eg.android.AlipayGphone I/JavaChecker: afterHookedMethod: persist.sys.timezone
	06-26 02:17:21.318 1772-1833/com.eg.android.AlipayGphone I/JavaChecker: afterHookedMethod: ro.secure
	06-26 02:17:21.698 1772-1772/com.eg.android.AlipayGphone I/JavaChecker: afterHookedMethod: ro.yunos.version
	06-26 02:17:21.698 1772-1772/com.eg.android.AlipayGphone I/JavaChecker: afterHookedMethod: java.vm.name
	06-26 02:17:53.305 1772-1846/com.eg.android.AlipayGphone I/JavaChecker: afterHookedMethod: ro.yunos.build.version
	06-26 02:17:53.305 1772-1846/com.eg.android.AlipayGphone I/JavaChecker: afterHookedMethod: ro.yunos.build.version
	06-26 02:17:53.305 1772-1846/com.eg.android.AlipayGphone I/JavaChecker: afterHookedMethod: ro.yunos.product.chip
	06-26 02:17:53.305 1772-1846/com.eg.android.AlipayGphone I/JavaChecker: afterHookedMethod: ro.yunos.product.chip
	06-26 02:17:53.306 1772-1846/com.eg.android.AlipayGphone I/JavaChecker: afterHookedMethod: ro.yunos.hardware
	06-26 02:17:53.306 1772-1846/com.eg.android.AlipayGphone I/JavaChecker: afterHookedMethod: ro.yunos.hardware
	06-26 02:17:53.316 1772-1846/com.eg.android.AlipayGphone I/JavaChecker: afterHookedMethod: gsm.sim.operator.alpha
	06-26 02:17:53.316 1772-1846/com.eg.android.AlipayGphone I/JavaChecker: afterHookedMethod: gsm.sim.operator.alpha
	06-26 02:17:53.319 1772-1846/com.eg.android.AlipayGphone I/JavaChecker: afterHookedMethod: gsm.sim.operator.numeric
	06-26 02:17:53.319 1772-1846/com.eg.android.AlipayGphone I/JavaChecker: afterHookedMethod: gsm.sim.operator.numeric


#### 2.2 File

	I/JavaChecker: afterHookedMethod: /data/app/com.eg.android.AlipayGphone-1/base.apk
    I/JavaChecker: afterHookedMethod: /dev/null
    I/JavaChecker: afterHookedMethod: /proc/1571/cmdline
    I/JavaChecker: afterHookedMethod: /data/app/com.eg.android.AlipayGphone-1/lib/arm/libsgmain.so
    I/JavaChecker: afterHookedMethod: /data/app/com.eg.android.AlipayGphone-1/lib/arm
    I/JavaChecker: afterHookedMethod: /data/app/com.eg.android.AlipayGphone-1/lib/arm/libsgmainso-6.4.56.so
    I/JavaChecker: afterHookedMethod: exists -> /data/app/com.eg.android.AlipayGphone-1/lib/arm/libsgmainso-6.4.56.so
    I/JavaChecker: afterHookedMethod: /data/app/com.eg.android.AlipayGphone-1/lib/arm
    I/JavaChecker: afterHookedMethod: /vendor/lib
    I/JavaChecker: afterHookedMethod: /system/lib
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/possible
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /data/app/com.eg.android.AlipayGphone-1/lib/arm
    I/JavaChecker: afterHookedMethod: /data/app/com.eg.android.AlipayGphone-1/lib/arm/libsgsecuritybodyso-6.4.40.so
    I/JavaChecker: afterHookedMethod: exists -> /data/app/com.eg.android.AlipayGphone-1/lib/arm/libsgsecuritybodyso-6.4.40.so
    I/JavaChecker: afterHookedMethod: /data/app/com.eg.android.AlipayGphone-1/lib/arm
    I/JavaChecker: afterHookedMethod: /vendor/lib
    I/JavaChecker: afterHookedMethod: /system/lib
    
    I/JavaChecker: afterHookedMethod: /dev/urandom
    I/JavaChecker: afterHookedMethod: /proc/meminfo
    I/JavaChecker: afterHookedMethod: /system/app/webview/webview.apk
    I/JavaChecker: afterHookedMethod: /system/app/webview/webview.apk
    I/JavaChecker: afterHookedMethod: /system/app/webview/lib/x86
    I/JavaChecker: afterHookedMethod: /vendor/lib
    I/JavaChecker: afterHookedMethod: /system/lib
    I/JavaChecker: afterHookedMethod: /system/app/webview/webview.apk
    I/JavaChecker: afterHookedMethod: /data/local/tmp/webview-command-line
    I/JavaChecker: afterHookedMethod: /system/app/webview/lib/x86/libwebviewchromium.so
    I/JavaChecker: afterHookedMethod: /system/app/webview/lib/x86/libwebviewchromium.so
    I/JavaChecker: afterHookedMethod: /data/misc/keychain/pins
    I/JavaChecker: afterHookedMethod: /system/app/webview/lib/x86/libwebviewchromium_plat_support.so
    I/JavaChecker: afterHookedMethod: /system/app/webview/lib/x86/libwebviewchromium_plat_support.so
    I/JavaChecker: afterHookedMethod: /vendor/lib/libwebviewchromium_plat_support.so
    I/JavaChecker: afterHookedMethod: /vendor/lib/libwebviewchromium_plat_support.so
    I/JavaChecker: afterHookedMethod: /system/lib/libwebviewchromium_plat_support.so
    I/JavaChecker: afterHookedMethod: /system/lib/libwebviewchromium_plat_support.so
    I/JavaChecker: afterHookedMethod: /system/app/webview/webview.apk
    I/JavaChecker: afterHookedMethod: /sys/class/net
    I/JavaChecker: afterHookedMethod: list -> /sys/class/net
    I/JavaChecker: afterHookedMethod: /sys/class/net/wlan0/address
    I/JavaChecker: afterHookedMethod: list -> /sys/class/net
    I/JavaChecker: afterHookedMethod: /data/misc/user/0/cacerts-added/d24eba4e.0
    I/JavaChecker: afterHookedMethod: /data/misc/user/0/cacerts-added/d24eba4e.0
    I/JavaChecker: afterHookedMethod: /system/etc/security/cacerts/d24eba4e.0
    I/JavaChecker: afterHookedMethod: /system/etc/security/cacerts/d24eba4e.0
    I/JavaChecker: afterHookedMethod: /data/misc/user/0/cacerts-added/25011729.0
    I/JavaChecker: afterHookedMethod: /data/misc/user/0/cacerts-added/25011729.0
    I/JavaChecker: afterHookedMethod: /system/etc/security/cacerts/25011729.0
    I/JavaChecker: afterHookedMethod: /system/etc/security/cacerts/25011729.0
    I/JavaChecker: afterHookedMethod: /data/misc/user/0/cacerts-added/b0f3e76e.0
    I/JavaChecker: afterHookedMethod: /data/misc/user/0/cacerts-added/b0f3e76e.0
    I/JavaChecker: afterHookedMethod: /system/etc/security/cacerts/b0f3e76e.0
    I/JavaChecker: afterHookedMethod: /system/etc/security/cacerts/b0f3e76e.0
    I/JavaChecker: afterHookedMethod: /data/misc/user/0/cacerts-removed/b0f3e76e.0
    I/JavaChecker: afterHookedMethod: /data/misc/user/0/cacerts-removed/b0f3e76e.0
    I/JavaChecker: afterHookedMethod: exists -> /data/misc/user/0/cacerts-removed/b0f3e76e.0
    I/JavaChecker: afterHookedMethod: /data/misc/keychain/pubkey_blacklist.txt
    I/JavaChecker: afterHookedMethod: /data/misc/keychain/serial_blacklist.txt
    I/JavaChecker: afterHookedMethod: /vendor/lib
    I/JavaChecker: afterHookedMethod: /system/lib
    I/JavaChecker: afterHookedMethod: list -> /sys/class/net
    I/JavaChecker: afterHookedMethod: /sys/block/mmcblk0/device/cid
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu
    I/JavaChecker: afterHookedMethod: list -> /sys/devices/system/cpu
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu1
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu1
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu2
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu2
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu3
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu3
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpufreq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpufreq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpuidle
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpuidle
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/power
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/power
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/modalias
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/modalias
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/kernel_max
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/kernel_max
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/possible
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/possible
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/online
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/online
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/offline
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/offline
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/isolated
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/isolated
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/uevent
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/uevent
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/microcode
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/microcode
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/present
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/present
    I/JavaChecker: afterHookedMethod: listFiles -> /sys/devices/system/cpu
    I/JavaChecker: afterHookedMethod: listFiles -> /sys/devices/system/cpu
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /proc/meminfo
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /data/app/com.eg.android.AlipayGphone-1/base.apk
    I/JavaChecker: afterHookedMethod: /data/app/com.eg.android.AlipayGphone-1/base.apk
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    D/HOOK: AssetManager isUpToDate:true
    D/HOOK: AssetManager isUpToDate:true
    I/JavaChecker: afterHookedMethod: /data/app/com.eg.android.AlipayGphone-1/lib/arm/libAPSE_1.4.5.so
    I/JavaChecker: afterHookedMethod: /data/app/com.eg.android.AlipayGphone-1/lib/arm/libAPSE_1.4.5.so
    I/JavaChecker: afterHookedMethod: /proc/version
    I/JavaChecker: afterHookedMethod: /system/bin/su
    I/JavaChecker: afterHookedMethod: exists -> /system/bin/su
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu
    I/JavaChecker: afterHookedMethod: list -> /sys/devices/system/cpu
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu1
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu1
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu2
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu2
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu3
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu3
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpufreq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpufreq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpuidle
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpuidle
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/power
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/power
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/modalias
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/modalias
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/kernel_max
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/kernel_max
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/possible
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/possible
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/online
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/online
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/offline
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/offline
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/isolated
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/isolated
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/uevent
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/uevent
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/microcode
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/microcode
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/present
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/present
    I/JavaChecker: afterHookedMethod: listFiles -> /sys/devices/system/cpu
    I/JavaChecker: afterHookedMethod: listFiles -> /sys/devices/system/cpu
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: list -> /sys/class/net
    I/JavaChecker: afterHookedMethod: /data/app/com.eg.android.AlipayGphone-1/lib/arm/libpre_install.so
    I/JavaChecker: afterHookedMethod: /data/app/com.eg.android.AlipayGphone-1/lib/arm/libpre_install.so
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    D/HOOK: AssetManager isUpToDate:true
    I/JavaChecker: afterHookedMethod: /sbin/su
    I/JavaChecker: afterHookedMethod: exists -> /sbin/su
    I/JavaChecker: afterHookedMethod: /system/bin/su
    I/JavaChecker: afterHookedMethod: exists -> /system/bin/su
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /vendor/lib
    I/JavaChecker: afterHookedMethod: /system/lib
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /proc/1571/status
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /data/anr
    I/JavaChecker: afterHookedMethod: list -> /data/anr
    I/JavaChecker: afterHookedMethod: /data/anr/traces.txt
    I/JavaChecker: afterHookedMethod: /data/anr/traces.txt
    I/JavaChecker: afterHookedMethod: listFiles -> /data/anr
    I/JavaChecker: afterHookedMethod: /data/anr/traces.txt
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /data/misc/user/0/cacerts-added/76bd68fe.0
    I/JavaChecker: afterHookedMethod: /data/misc/user/0/cacerts-added/76bd68fe.0
    I/JavaChecker: afterHookedMethod: /system/etc/security/cacerts/76bd68fe.0
    I/JavaChecker: afterHookedMethod: /system/etc/security/cacerts/76bd68fe.0
    I/JavaChecker: afterHookedMethod: /data/misc/user/0/cacerts-added/4bcd7fc4.0
    I/JavaChecker: afterHookedMethod: /data/misc/user/0/cacerts-added/4bcd7fc4.0
    I/JavaChecker: afterHookedMethod: /system/etc/security/cacerts/4bcd7fc4.0
    I/JavaChecker: afterHookedMethod: /system/etc/security/cacerts/4bcd7fc4.0
    I/JavaChecker: afterHookedMethod: /data/misc/user/0/cacerts-added/399e7759.0
    I/JavaChecker: afterHookedMethod: /data/misc/user/0/cacerts-added/399e7759.0
    I/JavaChecker: afterHookedMethod: /system/etc/security/cacerts/399e7759.0
    I/JavaChecker: afterHookedMethod: /system/etc/security/cacerts/399e7759.0
    I/JavaChecker: afterHookedMethod: /data/misc/user/0/cacerts-removed/399e7759.0
    I/JavaChecker: afterHookedMethod: /data/misc/user/0/cacerts-removed/399e7759.0
    I/JavaChecker: afterHookedMethod: exists -> /data/misc/user/0/cacerts-removed/399e7759.0
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: list -> /sys/class/net
    I/JavaChecker: afterHookedMethod: /data/misc/user/0/cacerts-added/8c2125e5.0
    I/JavaChecker: afterHookedMethod: /data/misc/user/0/cacerts-added/8c2125e5.0
    I/JavaChecker: afterHookedMethod: /system/etc/security/cacerts/8c2125e5.0
    I/JavaChecker: afterHookedMethod: /system/etc/security/cacerts/8c2125e5.0
    I/JavaChecker: afterHookedMethod: /data/misc/user/0/cacerts-added/0f6a1bf3.0
    I/JavaChecker: afterHookedMethod: /data/misc/user/0/cacerts-added/0f6a1bf3.0
    I/JavaChecker: afterHookedMethod: /system/etc/security/cacerts/0f6a1bf3.0
    I/JavaChecker: afterHookedMethod: /system/etc/security/cacerts/0f6a1bf3.0
    I/JavaChecker: afterHookedMethod: /data/misc/user/0/cacerts-added/facacbc6.0
    I/JavaChecker: afterHookedMethod: /data/misc/user/0/cacerts-added/facacbc6.0
    I/JavaChecker: afterHookedMethod: /system/etc/security/cacerts/facacbc6.0
    I/JavaChecker: afterHookedMethod: /system/etc/security/cacerts/facacbc6.0
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /data/misc/user/0/cacerts-removed/facacbc6.0
    I/JavaChecker: afterHookedMethod: /data/misc/user/0/cacerts-removed/facacbc6.0
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: exists -> /data/misc/user/0/cacerts-removed/facacbc6.0
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /system/bin/su
    I/JavaChecker: afterHookedMethod: exists -> /system/bin/su
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu
    I/JavaChecker: afterHookedMethod: list -> /sys/devices/system/cpu
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu1
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu1
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu2
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu2
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu3
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu3
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpufreq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpufreq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpuidle
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpuidle
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/power
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/power
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/modalias
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/modalias
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/kernel_max
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/kernel_max
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/possible
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/possible
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/online
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/online
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/offline
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/offline
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/isolated
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/isolated
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/uevent
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/uevent
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/microcode
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/microcode
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/present
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/present
    I/JavaChecker: afterHookedMethod: listFiles -> /sys/devices/system/cpu
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: listFiles -> /sys/devices/system/cpu
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    D/HOOK: AssetManager isUpToDate:true
    I/JavaChecker: afterHookedMethod: /proc/meminfo
    I/JavaChecker: afterHookedMethod: /dev/qemu_pipe
    I/JavaChecker: afterHookedMethod: exists -> /dev/qemu_pipe
    I/JavaChecker: afterHookedMethod: /dev/socket/qemud
    I/JavaChecker: afterHookedMethod: exists -> /dev/socket/qemud
    I/JavaChecker: afterHookedMethod: /system/lib/libc_malloc_debug_qemu.so
    I/JavaChecker: afterHookedMethod: exists -> /system/lib/libc_malloc_debug_qemu.so
    I/JavaChecker: afterHookedMethod: /sys/qemu_trace
    I/JavaChecker: afterHookedMethod: exists -> /sys/qemu_trace
    I/JavaChecker: afterHookedMethod: /system/bin/qemu-props
    I/JavaChecker: afterHookedMethod: exists -> /system/bin/qemu-props
    I/JavaChecker: afterHookedMethod: /dev/socket/genyd
    I/JavaChecker: afterHookedMethod: exists -> /dev/socket/genyd
    I/JavaChecker: afterHookedMethod: /dev/socket/baseband_genyd
    I/JavaChecker: afterHookedMethod: exists -> /dev/socket/baseband_genyd
    I/JavaChecker: afterHookedMethod: /system/build.prop
    I/JavaChecker: afterHookedMethod: /proc/tty/drivers
    D/HOOK: AssetManager isUpToDate:true
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /proc/stat
    I/JavaChecker: afterHookedMethod: /proc/1571/stat
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /proc/version
    I/JavaChecker: afterHookedMethod: /system/bin/su
    I/JavaChecker: afterHookedMethod: exists -> /system/bin/su
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu
    I/JavaChecker: afterHookedMethod: list -> /sys/devices/system/cpu
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu1
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu1
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu2
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu2
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu3
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu3
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpufreq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpufreq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpuidle
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpuidle
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/power
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/power
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/modalias
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/modalias
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/kernel_max
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/kernel_max
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/possible
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/possible
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/online
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/online
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/offline
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/offline
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/isolated
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/isolated
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/uevent
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/uevent
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/microcode
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/microcode
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/present
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/present
    I/JavaChecker: afterHookedMethod: listFiles -> /sys/devices/system/cpu
    I/JavaChecker: afterHookedMethod: listFiles -> /sys/devices/system/cpu
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: exists -> /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo

#### Runtime

    getprop ro.product.cpu.abi
    cat /proc/cpuinfo | grep Serial
    args -> [ps]
    arg -> null
    arg -> null
    args -> [ps]
    args -> [getprop, ro.product.cpu.abi]
    arg -> null
    arg -> null
    arg -> getprop ro.product.cpu.abi
    arg -> null
    arg -> null
    arg -> getprop ro.product.cpu.abi
    ecker: afterHookedMethod: args -> [sh, -c, type su]
    ecker: afterHookedMethod: arg -> null
    ecker: afterHookedMethod: arg -> null
    ecker: afterHookedMethod: args -> [sh, -c, type su]
    ecker: afterHookedMethod: args -> [ls, -l, /system/bin/su]
    ecker: afterHookedMethod: arg -> null
    ecker: afterHookedMethod: arg -> null
    ecker: afterHookedMethod: args -> [ls, -l, /system/bin/su]
    ecker: afterHookedMethod: args -> [cat, /proc/cpuinfo, |, grep, Serial]
    ecker: afterHookedMethod: arg -> null
    ecker: afterHookedMethod: arg -> null
    ecker: afterHookedMethod: arg -> cat /proc/cpuinfo | grep Serial
    ecker: afterHookedMethod: arg -> null
    ecker: afterHookedMethod: arg -> null
    ecker: afterHookedMethod: arg -> cat /proc/cpuinfo | grep Serial


#### alipay

    07-07 02:58:05.347 11027-11286/com.eg.android.AlipayGphone W/Bundle: Key login_refresh_feature expected String but value was a java.lang.Boolean.  The default value <null> was returned.
    07-07 02:58:05.348 11027-11286/com.eg.android.AlipayGphone W/Bundle: Attempt to cast generated internal exception:
                                                                         java.lang.ClassCastException: java.lang.Boolean cannot be cast to java.lang.String
                                                                             at android.os.BaseBundle.getString(BaseBundle.java:921)
                                                                             at com.alipay.mobile.common.transport.utils.MiscUtils.getBooleanFromMetaData(MiscUtils.java:1148)
                                                                             at com.alipay.mobile.common.transport.zfeatures.LoginRefreshManager.isEnabledLoginRefresh(LoginRefreshManager.java:85)
                                                                             at com.alipay.mobile.common.transport.zfeatures.LoginRefreshHelper.recordRpc(LoginRefreshHelper.java:20)
                                                                             at com.alipay.mobile.common.transport.http.HttpWorker.call(HttpWorker.java:481)
                                                                             at com.alipay.mobile.common.transport.http.HttpWorker.call(HttpWorker.java:93)
                                                                             at java.util.concurrent.FutureTask.run(FutureTask.java:237)
                                                                             at com.alipay.mobile.common.transport.concurrent.ZFutureTask.run(ZFutureTask.java:53)
                                                                             at com.alipay.mobile.common.transport.http.HttpTask.run(HttpTask.java:31)
                                                                             at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1112)
                                                                             at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:587)
                                                                             at java.lang.Thread.run(Thread.java:818)
    07-07 02:58:05.389 11027-11027/com.eg.android.AlipayGphone D/HOOK: AssetManager isUpToDate:true
    07-07 02:58:05.406 11027-11027/com.eg.android.AlipayGphone D/HOOK: AssetManager isUpToDate:true
    07-07 02:58:05.476 11027-11298/com.eg.android.AlipayGphone D/houdini: [11298] Added shared library /data/app/com.eg.android.AlipayGphone-1/lib/arm/libpre_install.so for ClassLoader by Native Bridge.
    07-07 02:58:05.482 11027-11042/com.eg.android.AlipayGphone I/art: Background sticky concurrent mark sweep GC freed 54639(4MB) AllocSpace objects, 23(691KB) LOS objects, 24% free, 14MB/19MB, paused 5.699ms total 33.592ms
    07-07 02:58:05.498 11027-11309/com.eg.android.AlipayGphone E/ActivityThread: Failed to find provider info for authentication.information
    07-07 02:58:05.500 11027-11309/com.eg.android.AlipayGphone E/ActivityThread: Failed to find provider info for authentication.information
    07-07 02:58:05.514 11027-11027/com.eg.android.AlipayGphone D/HOOK: AssetManager isUpToDate:true
    07-07 02:58:05.583 11027-11336/com.eg.android.AlipayGphone E/ActivityThread: Failed to find provider info for com.alipay.setting
    07-07 02:58:05.625 11027-11042/com.eg.android.AlipayGphone I/art: Background sticky concurrent mark sweep GC freed 33808(3MB) AllocSpace objects, 24(716KB) LOS objects, 17% free, 16MB/19MB, paused 9.818ms total 42.830ms
    07-07 02:58:05.629 11027-11171/com.eg.android.AlipayGphone W/EGL_emulation: eglSurfaceAttrib not implemented
    07-07 02:58:05.629 11027-11171/com.eg.android.AlipayGphone W/OpenGLRenderer: Failed to set EGL_SWAP_BEHAVIOR on surface 0xd9c113c0, error=EGL_SUCCESS
    07-07 02:58:05.869 11027-11371/com.eg.android.AlipayGphone E/BluetoothAdapter: Bluetooth binder is null
    07-07 02:58:05.922 11027-11371/com.eg.android.AlipayGphone W/Settings: Setting airplane_mode_on has moved from android.provider.Settings.System to android.provider.Settings.Global, returning read-only value.
    07-07 02:58:06.028 11027-11390/com.eg.android.AlipayGphone D/houdini: [11390] Added shared library /data/app/com.eg.android.AlipayGphone-1/lib/arm/libijkengine-gif.so for ClassLoader by Native Bridge.
    07-07 02:58:06.028 11027-11390/com.eg.android.AlipayGphone V/MMEngine: Engine JNI_OnLoad####
    07-07 02:58:06.045 11027-11390/com.eg.android.AlipayGphone D/houdini: [11390] Added shared library /data/app/com.eg.android.AlipayGphone-1/lib/arm/libijkffmpeg.so for ClassLoader by Native Bridge.
    07-07 02:58:06.071 11027-11390/com.eg.android.AlipayGphone D/houdini: [11390] Added shared library /data/app/com.eg.android.AlipayGphone-1/lib/arm/libijkmmengine.so for ClassLoader by Native Bridge.
    07-07 02:58:06.071 11027-11390/com.eg.android.AlipayGphone V/MMEngine: MMEngine JNI_OnLoad####
    07-07 02:58:06.072 11027-11390/com.eg.android.AlipayGphone V/MMEngine: mmengine set jvm ret: 0
    07-07 02:58:06.115 11027-11027/com.eg.android.AlipayGphone W/QSPOWER: [35mW tf7797ea0 /power-sdk/build/android/jni/../../../core/src/power_controller_interface.c:392 Cannot unload library, power controller interface isn't initialized.[0m
    07-07 02:58:06.115 11027-11027/com.eg.android.AlipayGphone I/QSPOWER: [33mI tf7797ea0 /power-sdk/build/android/jni/../../../core/src/power_impl.c:460 Power Optimization SDK disabled.[0m
    07-07 02:58:06.559 11027-11117/com.eg.android.AlipayGphone D/houdini: [11117] Added shared library /data/data/com.eg.android.AlipayGphone/app_SGLib/app_1530082802/main/libsgmiscso-6.4.26.so for ClassLoader by Native Bridge.
    07-07 02:58:07.946 11027-11027/com.eg.android.AlipayGphone D/HOOK: AssetManager isUpToDate:true
    07-07 02:58:07.955 11027-11027/com.eg.android.AlipayGphone D/HOOK: AssetManager isUpToDate:true
    07-07 02:58:08.026 11027-11171/com.eg.android.AlipayGphone W/EGL_emulation: eglSurfaceAttrib not implemented
    07-07 02:58:08.026 11027-11171/com.eg.android.AlipayGphone W/OpenGLRenderer: Failed to set EGL_SWAP_BEHAVIOR on surface 0xd69bda00, error=EGL_SUCCESS
    07-07 02:58:10.086 11027-11027/com.eg.android.AlipayGphone D/HOOK: AssetManager isUpToDate:true
    07-07 02:58:10.088 11027-11027/com.eg.android.AlipayGphone D/HOOK: AssetManager isUpToDate:true
    07-07 02:58:10.736 11027-11027/com.eg.android.AlipayGphone W/QSPOWER: [35mW tf7797ea0 /power-sdk/build/android/jni/../../../core/src/power_controller_interface.c:392 Cannot unload library, power controller interface isn't initialized.[0m
    07-07 02:58:10.737 11027-11027/com.eg.android.AlipayGphone W/QSPOWER: [35mW tf7797ea0 /power-sdk/build/android/jni/../../../core/src/global_manager.c:161 Can not free memory: global manager isn't initialized.[0m
    07-07 02:58:10.737 11027-11027/com.eg.android.AlipayGphone I/QSPOWER: [33mI tf7797ea0 /power-sdk/build/android/jni/../../../core/src/power_impl.c:460 Power Optimization SDK disabled.[0m
    07-07 02:58:15.171 11027-11195/com.eg.android.AlipayGphone E/BluetoothAdapter: Bluetooth binder is null
    07-07 02:58:15.788 11027-11027/com.eg.android.AlipayGphone D/HOOK: AssetManager isUpToDate:true
    07-07 02:58:15.792 11027-11027/com.eg.android.AlipayGphone D/HOOK: AssetManager isUpToDate:true
    07-07 02:58:18.007 11027-11545/com.eg.android.AlipayGphone I/art: Explicit concurrent mark sweep GC freed 33056(2MB) AllocSpace objects, 16(559KB) LOS objects, 39% free, 21MB/35MB, paused 435us total 20.298ms
    07-07 02:58:34.558 11027-11088/com.eg.android.AlipayGphone E/BluetoothAdapter: Bluetooth binder is null
    07-07 02:58:34.572 11027-11088/com.eg.android.AlipayGphone E/BluetoothAdapter: Bluetooth binder is null


#### NetworkInfo.getType()
#### BuletoothAdapter.getDefaultAdapter() = null
#### WifiInfo

    JavaChecker: afterHookedMethod: WifiInfo.getMacAddress -> 08:00:27:42:bf:5b
    JavaChecker: afterHookedMethod: WifiInfo.getMacAddress -> 08:00:27:42:bf:5b
    JavaChecker: afterHookedMethod: WifiInfo.getMacAddress -> 08:00:27:42:bf:5b
    JavaChecker: afterHookedMethod: WifiInfo.getSSID -> "WiredSSID"
    JavaChecker: afterHookedMethod: WifiInfo.getMacAddress -> 08:00:27:42:bf:5b
    JavaChecker: afterHookedMethod: WifiInfo.getMacAddress -> 08:00:27:42:bf:5b
    JavaChecker: afterHookedMethod: WifiInfo.getSSID -> "WiredSSID"
    JavaChecker: afterHookedMethod: WifiInfo.getSSID -> "WiredSSID"
    JavaChecker: afterHookedMethod: WifiInfo.getMacAddress -> 08:00:27:42:bf:5b
    
    
#### TelephonyManager

    // genymotion
    com.eg.android.AlipayGphone I/FakeMethodHook: android.telephony.TelephonyManager.getDeviceId(NoneParam) = 000000000000000
    com.eg.android.AlipayGphone I/FakeMethodHook: android.telephony.TelephonyManager.getSubscriberId(NoneParam) = 310270000000000
    com.eg.android.AlipayGphone I/FakeMethodHook: android.telephony.TelephonyManager.getNetworkType(NoneParam) = 3
    com.eg.android.AlipayGphone I/FakeMethodHook: android.telephony.TelephonyManager.getLine1Number(NoneParam) = 15555218135
    com.eg.android.AlipayGphone I/FakeMethodHook: android.telephony.TelephonyManager.getSimSerialNumber(NoneParam) = 89014103211118510720
    com.eg.android.AlipayGphone I/FakeMethodHook: android.telephony.TelephonyManager.getNetworkOperatorName(NoneParam) = Android
    com.eg.android.AlipayGphone I/FakeMethodHook: android.telephony.TelephonyManager.getSimOperatorName(NoneParam) = Android


#### OpenGL

    D/OpenGLRenderer: Use EGL_SWAP_BEHAVIOR_PRESERVED: true
                      
                      [ 07-09 22:41:14.972  4224: 4224 D/         ]
                      HostConnection::get() New Host Connection established 0xd955f9d0, tid 4224
    D/libEGL: loaded /system/lib/egl/libEGL_emulation.so
    D/libEGL: loaded /system/lib/egl/libGLESv1_CM_emulation.so
    D/libEGL: loaded /system/lib/egl/libGLESv2_emulation.so
              
              [ 07-09 22:41:15.085  4224: 4527 D/         ]
              HostConnection::get() New Host Connection established 0xd799f340, tid 4527
    I/OpenGLRenderer: Initialized EGL, version 1.4
    W/EGL_emulation: eglSurfaceAttrib not implemented
    W/OpenGLRenderer: Failed to set EGL_SWAP_BEHAVIOR on surface 0xd7997980, error=EGL_SUCCESS
    W/EGL_emulation: eglSurfaceAttrib not implemented
    W/OpenGLRenderer: Failed to set EGL_SWAP_BEHAVIOR on surface 0xd79979a0, error=EGL_SUCCESS
    W/EGL_emulation: eglSurfaceAttrib not implemented
    W/OpenGLRenderer: Failed to set EGL_SWAP_BEHAVIOR on surface 0xd4519760, error=EGL_SUCCESS
    W/EGL_emulation: eglSurfaceAttrib not implemented
    W/OpenGLRenderer: Failed to set EGL_SWAP_BEHAVIOR on surface 0xd4519760, error=EGL_SUCCESS


#### Display

    I/FakeMethodHook: before hack --> android.view.Display.getMetrics(DisplayMetrics{density=2.0, width=768, height=1184, scaledDensity=2.0, xdpi=320.0, ydpi=320.0}) = null
    I/FakeMethodHook: after hack --> android.view.Display.getMetrics(DisplayMetrics{density=2.0, width=768, height=1184, scaledDensity=2.0, xdpi=320.0, ydpi=320.0}) = null
    I/FakeMethodHook: before hack --> android.view.Display.getRealMetrics(DisplayMetrics{density=2.0, width=768, height=1280, scaledDensity=2.0, xdpi=320.0, ydpi=320.0}) = null
    I/FakeMethodHook: after hack --> android.view.Display.getRealMetrics(DisplayMetrics{density=2.0, width=768, height=1280, scaledDensity=2.0, xdpi=320.0, ydpi=320.0}) = null
    I/FakeMethodHook: before hack --> android.view.Display.getMetrics(DisplayMetrics{density=2.0, width=768, height=1184, scaledDensity=2.0, xdpi=320.0, ydpi=320.0}) = null
    I/FakeMethodHook: after hack --> android.view.Display.getMetrics(DisplayMetrics{density=2.0, width=768, height=1184, scaledDensity=2.0, xdpi=320.0, ydpi=320.0}) = null
    I/FakeMethodHook: before hack --> android.view.Display.getMetrics(DisplayMetrics{density=2.0, width=768, height=1184, scaledDensity=2.0, xdpi=320.0, ydpi=320.0}) = null
    I/FakeMethodHook: after hack --> android.view.Display.getMetrics(DisplayMetrics{density=2.0, width=768, height=1184, scaledDensity=2.0, xdpi=320.0, ydpi=320.0}) = null
    
    
    // Genymotion
    Display id 0: DisplayInfo{"Built-in Screen", uniqueId "local:0", app 768 x 1184, real 768 x 1280, largest app 1196 x 1134, smallest app 768 x 718, 60.000004 fps, supportedRefreshRates [60.000004], rotation 0, density 320 (320.0 x 320.0) dpi, layerStack 0, appVsyncOff 0, presDeadline 17666666, type BUILT_IN, state ON, FLAG_SECURE, FLAG_SUPPORTS_PROTECTED_BUFFERS}, DisplayMetrics{density=2.0, width=768, height=1184, scaledDensity=2.0, xdpi=320.0, ydpi=320.0}, isValid=true
    DisplayMetrics{density=2.0, width=768, height=1184, scaledDensity=2.0, xdpi=320.0, ydpi=320.0}
    DisplayMetrics{density=2.0, width=768, height=1280, scaledDensity=2.0, xdpi=320.0, ydpi=320.0}
    
    // smartisan
    Display id 0: DisplayInfo{"内置屏幕", uniqueId "local:0", app 1080 x 1920, real 1080 x 1920, largest app 1920 x 1842, smallest app 1080 x 1002, mode 1, defaultMode 1, modes [{id=1, width=1080, height=1920, fps=60.000004}], colorMode 0, supportedColorModes [0], hdrCapabilities android.view.Display$HdrCapabilities@a69d6308, rotation 0, density 480 (403.411 x 403.041) dpi, layerStack 0, appVsyncOff 1000000, presDeadline 16666666, type BUILT_IN, state ON, FLAG_SECURE, FLAG_SUPPORTS_PROTECTED_BUFFERS}, DisplayMetrics{density=3.0, width=1080, height=1920, scaledDensity=3.0, xdpi=403.411, ydpi=403.041}, isValid=true
    DisplayMetrics{density=3.0, width=1080, height=1920, scaledDensity=3.0, xdpi=403.411, ydpi=403.041}
    DisplayMetrics{density=3.0, width=1080, height=1920, scaledDensity=3.0, xdpi=403.411, ydpi=403.041}


#### Location

    I/FakeMethodHook: before hack --> android.telephony.TelephonyManager.getCellLocation() = [0,0,2147483647]
    I/FakeMethodHook: after hack --> android.telephony.TelephonyManager.getCellLocation() = [0,0,2147483647]
    I/FakeMethodHook: before hack --> android.location.LocationManager.getLastKnownLocation(gps) = null
    
    

#### PackageManager

    I/FakeMethodHook: before hack --> android.app.ApplicationPackageManager.hasSystemFeature('android.hardware.wifi') = true
    I/FakeMethodHook: after hack --> android.app.ApplicationPackageManager.hasSystemFeature('android.hardware.wifi') = true
    I/FakeMethodHook: before hack --> android.app.ApplicationPackageManager.hasSystemFeature('android.hardware.bluetooth') = false
    I/FakeMethodHook: after hack --> android.app.ApplicationPackageManager.hasSystemFeature('android.hardware.bluetooth') = true
    I/FakeMethodHook: before hack --> android.app.ApplicationPackageManager.hasSystemFeature('android.hardware.bluetooth_le') = false
    I/FakeMethodHook: after hack --> android.app.ApplicationPackageManager.hasSystemFeature('android.hardware.bluetooth_le') = true
    I/FakeMethodHook: before hack --> android.app.ApplicationPackageManager.hasSystemFeature('android.hardware.telephony') = true
    I/FakeMethodHook: after hack --> android.app.ApplicationPackageManager.hasSystemFeature('android.hardware.telephony') = true
    I/FakeMethodHook: before hack --> android.app.ApplicationPackageManager.hasSystemFeature('android.hardware.usb.accessory') = true
    I/FakeMethodHook: after hack --> android.app.ApplicationPackageManager.hasSystemFeature('android.hardware.usb.accessory') = true
    I/FakeMethodHook: before hack --> android.app.ApplicationPackageManager.hasSystemFeature('android.hardware.location.gps') = true
    I/FakeMethodHook: after hack --> android.app.ApplicationPackageManager.hasSystemFeature('android.hardware.location.gps') = true
    I/FakeMethodHook: before hack --> android.app.ApplicationPackageManager.hasSystemFeature('android.hardware.nfc') = false
    I/FakeMethodHook: after hack --> android.app.ApplicationPackageManager.hasSystemFeature('android.hardware.nfc') = true
    I/FakeMethodHook: before hack --> android.app.ApplicationPackageManager.hasSystemFeature('android.hardware.type.television') = false
    I/FakeMethodHook: after hack --> android.app.ApplicationPackageManager.hasSystemFeature('android.hardware.type.television') = true
    
    
#### battery

    I/FakeMethodHook: before hack --> android.content.Intent.getIntExtra('isUpgrade', '0') = 0
    I/FakeMethodHook: after hack --> android.content.Intent.getIntExtra('isUpgrade', '0') = 0
    I/FakeMethodHook: before hack --> android.content.Intent.getIntExtra('level', '-1') = 80
    I/FakeMethodHook: after hack --> android.content.Intent.getIntExtra('level', '-1') = 68
    I/FakeMethodHook: before hack --> android.content.Intent.getIntExtra('voltage', '-1') = 10000
    I/FakeMethodHook: after hack --> android.content.Intent.getIntExtra('voltage', '-1') = 6456
    I/FakeMethodHook: before hack --> android.content.Intent.getIntExtra('temperature', '-1') = 0
    I/FakeMethodHook: after hack --> android.content.Intent.getIntExtra('temperature', '-1') = 42
    
    
#### SensorManager

    // -1: all; 1: 加速度; 4: 陀螺仪; 9: 重力
    I/FakeMethodHook: before hack --> android.hardware.SystemSensorManager.getSensorList('9') = []
    I/FakeMethodHook: after hack --> android.hardware.SystemSensorManager.getSensorList('9') = []
    I/FakeMethodHook: before hack --> android.hardware.SystemSensorManager.getSensorList('4') = []
    I/FakeMethodHook: after hack --> android.hardware.SystemSensorManager.getSensorList('4') = []
    I/FakeMethodHook: before hack --> android.hardware.SystemSensorManager.getSensorList('-1') = [{Sensor name="Genymotion Light", vendor="Genymobile", version=1, type=5, maxRange=30000.0, resolution=1.0, power=0.1, minDelay=0}, {Sensor name="Genymotion Pressure", vendor="Genymobile", version=1, type=6, maxRange=30000.0, resolution=1.0, power=0.1, minDelay=0}, {Sensor name="Genymotion Temperature", vendor="Genymobile", version=1, type=13, maxRange=3000.0, resolution=1.0, power=0.1, minDelay=0}, {Sensor name="Genymotion Humidity", vendor="Genymobile", version=1, type=12, maxRange=100.0, resolution=1.0, power=0.1, minDelay=0}, {Sensor name="Genymotion Proximity", vendor="Genymobile", version=1, type=8, maxRange=3000.0, resolution=0.5, power=0.1, minDelay=0}, {Sensor name="Genymotion Accelerometer", vendor="Genymobile", version=1, type=1, maxRange=19.6133, resolution=0.009576807, power=0.57, minDelay=5000}]
    I/FakeMethodHook: after hack --> android.hardware.SystemSensorManager.getSensorList('-1') = [{Sensor name="Genymotion Light", vendor="Genymobile", version=1, type=5, maxRange=30000.0, resolution=1.0, power=0.1, minDelay=0}, {Sensor name="Genymotion Pressure", vendor="Genymobile", version=1, type=6, maxRange=30000.0, resolution=1.0, power=0.1, minDelay=0}, {Sensor name="Genymotion Temperature", vendor="Genymobile", version=1, type=13, maxRange=3000.0, resolution=1.0, power=0.1, minDelay=0}, {Sensor name="Genymotion Humidity", vendor="Genymobile", version=1, type=12, maxRange=100.0, resolution=1.0, power=0.1, minDelay=0}, {Sensor name="Genymotion Proximity", vendor="Genymobile", version=1, type=8, maxRange=3000.0, resolution=0.5, power=0.1, minDelay=0}, {Sensor name="Genymotion Accelerometer", vendor="Genymobile", version=1, type=1, maxRange=19.6133, resolution=0.009576807, power=0.57, minDelay=5000}]
    I/FakeMethodHook: before hack --> android.hardware.SystemSensorManager.getSensorList('-1') = [{Sensor name="Genymotion Light", vendor="Genymobile", version=1, type=5, maxRange=30000.0, resolution=1.0, power=0.1, minDelay=0}, {Sensor name="Genymotion Pressure", vendor="Genymobile", version=1, type=6, maxRange=30000.0, resolution=1.0, power=0.1, minDelay=0}, {Sensor name="Genymotion Temperature", vendor="Genymobile", version=1, type=13, maxRange=3000.0, resolution=1.0, power=0.1, minDelay=0}, {Sensor name="Genymotion Humidity", vendor="Genymobile", version=1, type=12, maxRange=100.0, resolution=1.0, power=0.1, minDelay=0}, {Sensor name="Genymotion Proximity", vendor="Genymobile", version=1, type=8, maxRange=3000.0, resolution=0.5, power=0.1, minDelay=0}, {Sensor name="Genymotion Accelerometer", vendor="Genymobile", version=1, type=1, maxRange=19.6133, resolution=0.009576807, power=0.57, minDelay=5000}]
    I/FakeMethodHook: after hack --> android.hardware.SystemSensorManager.getSensorList('-1') = [{Sensor name="Genymotion Light", vendor="Genymobile", version=1, type=5, maxRange=30000.0, resolution=1.0, power=0.1, minDelay=0}, {Sensor name="Genymotion Pressure", vendor="Genymobile", version=1, type=6, maxRange=30000.0, resolution=1.0, power=0.1, minDelay=0}, {Sensor name="Genymotion Temperature", vendor="Genymobile", version=1, type=13, maxRange=3000.0, resolution=1.0, power=0.1, minDelay=0}, {Sensor name="Genymotion Humidity", vendor="Genymobile", version=1, type=12, maxRange=100.0, resolution=1.0, power=0.1, minDelay=0}, {Sensor name="Genymotion Proximity", vendor="Genymobile", version=1, type=8, maxRange=3000.0, resolution=0.5, power=0.1, minDelay=0}, {Sensor name="Genymotion Accelerometer", vendor="Genymobile", version=1, type=1, maxRange=19.6133, resolution=0.009576807, power=0.57, minDelay=5000}]
    I/FakeMethodHook: before hack --> android.hardware.SystemSensorManager.getSensorList('1') = [{Sensor name="Genymotion Accelerometer", vendor="Genymobile", version=1, type=1, maxRange=19.6133, resolution=0.009576807, power=0.57, minDelay=5000}]
    I/FakeMethodHook: after hack --> android.hardware.SystemSensorManager.getSensorList('1') = [{Sensor name="Genymotion Accelerometer", vendor="Genymobile", version=1, type=1, maxRange=19.6133, resolution=0.009576807, power=0.57, minDelay=5000}]
    I/FakeMethodHook: before hack --> android.hardware.SystemSensorManager.getSensorList('4') = []
    I/FakeMethodHook: after hack --> android.hardware.SystemSensorManager.getSensorList('4') = []



#### FileInputStream

    I/JavaChecker: afterHookedMethod: /dev/null
    I/JavaChecker: afterHookedMethod: /dev/null
    I/JavaChecker: afterHookedMethod: /dev/null
    I/JavaChecker: afterHookedMethod: /dev/null
    I/JavaChecker: afterHookedMethod: /dev/null
    I/JavaChecker: afterHookedMethod: /dev/null
    I/JavaChecker: afterHookedMethod: /dev/null
    I/JavaChecker: afterHookedMethod: /dev/null
    I/JavaChecker: afterHookedMethod: /dev/null
    I/JavaChecker: afterHookedMethod: /dev/null
    I/JavaChecker: afterHookedMethod: /dev/null
    I/JavaChecker: afterHookedMethod: /dev/null
    I/JavaChecker: afterHookedMethod: /dev/null
    I/JavaChecker: afterHookedMethod: /dev/null
    I/JavaChecker: afterHookedMethod: /dev/null
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/possible
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /storage/emulated/0/.UTSystemConfig/Global/7934039a7252be16
    I/JavaChecker: afterHookedMethod: /data/data/com.eg.android.AlipayGphone/files/.7934039a7252be16
    I/JavaChecker: afterHookedMethod: /proc/meminfo
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /proc/4405/status
    I/JavaChecker: afterHookedMethod: /proc/version
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /proc/4405/status
    I/JavaChecker: afterHookedMethod: /data/data/com.eg.android.AlipayGphone/files/nebulaInstallApps
    I/JavaChecker: afterHookedMethod: /data/data/com.eg.android.AlipayGphone/files/nebulaInstallApps
    I/JavaChecker: afterHookedMethod: /data/data/com.eg.android.AlipayGphone/files/nebulaInstallApps
    I/JavaChecker: afterHookedMethod: /data/data/com.eg.android.AlipayGphone/files/nebulaInstallApps
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /proc/meminfo
    I/JavaChecker: afterHookedMethod: /system/build.prop
    I/JavaChecker: afterHookedMethod: /proc/tty/drivers
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /storage/emulated/0/alipay/multimedia/ad921d60486366258809553a
    I/JavaChecker: afterHookedMethod: /storage/emulated/0/alipay/multimedia/ad921d60486366258809553a
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /proc/version
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /storage/emulated/0/.UTSystemConfig/Global/322a309482c4dae6
    I/JavaChecker: afterHookedMethod: /sys/block/mmcblk0/device/cid
    I/JavaChecker: afterHookedMethod: /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    I/JavaChecker: afterHookedMethod: /proc/meminfo
    I/JavaChecker: afterHookedMethod: /proc/cpuinfo
    I/JavaChecker: afterHookedMethod: /storage/emulated/0/.UTSystemConfig/Global/cec06585501c9775
    
    // 未 hack
    I/JavaChecker: afterHookedMethod: /sys/block/mmcblk0/device/cid
    I/JavaChecker: afterHookedMethod: /proc/net/xt_qtaguid/stats

#### OpenGL

    Failed to set EGL_SWAP_BEHAVIOR on surface 0xd7972620, error=EGL_SUCCESS

### 需要改的文件
* build.prop
* cpuinfo





