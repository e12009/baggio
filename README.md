# baggio

## 部署
* genymotion 5.1，已安装 xposed 框架
* 生成 apk 直接安装

## 架构
* xposed 程序入口 ./Hacker.java
* ./sandbox/*Generator 负责生成真机模拟信息
* ./hook/*Hook 负责 hook 系统信息并 hack

## 版本
### TaobaoHook

|版本|描述|
|:---:|:---|
| v1.0 | hook 更行对话框 |
| v1.1 | 改进更新对话框 hook 逻辑；增加首页活动弹窗 hook 逻辑 |