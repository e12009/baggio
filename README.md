# baggio

## 部署
* genymotion 5.1，已安装 xposed 框架
* 生成 apk 直接安装

## 架构
* xposed 程序入口 ./Hacker.java
* ./sandbox/*Generator 负责生成真机模拟信息
* ./hook/*Hook 负责 hook 系统信息并 hack


## other
new FileInputStream("/proc/net/tcp")
/system/bin/androVM-prop
emulator_adb xposedemo 中
build 信息的统一
audio/video/gpu


## 支付宝源码阅读记录
* com.alipay.security.mobile.module.deviceinfo.b
* com.alipay.mobile.nebula.util.H5DeviceHelper
