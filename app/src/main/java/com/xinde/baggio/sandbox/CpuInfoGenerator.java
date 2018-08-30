package com.xinde.baggio.sandbox;

import android.content.Context;
import android.util.Log;

import com.xinde.baggio.Constant;
import com.xinde.baggio.util.IoUtil;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * author: Shawn
 * time  : 2018/7/6 13:37
 * desc  :
 * update: Shawn 2018/7/6 13:37
 */
public class CpuInfoGenerator extends AbstractGenerator {
    private static final String TAG = "CpuInfoGenerator";

    public CpuInfoGenerator(Context context) {
        super(context);
    }

    @Override
    public void run() {
        setCpuInfo();
        setCpuRuntime();

        Log.i(TAG, TAG + " running...");
    }

    private void setCpuInfo() {
        String info =
                "processor       : 0\n" +
                        "model name      : ARMv7 Processor rev 3 (v7l)\n" +
                        "Features        : swp half thumb fastmult vfp edsp neon vfpv3 tls vfpv4 idiva idivt\n" +
                        "CPU implementer : 0x41\n" +
                        "CPU architecture: 7\n" +
                        "CPU variant     : 0x3\n" +
                        "CPU part        : 0xc0f\n" +
                        "CPU revision    : 3\n" +
                        "\n" +
                        "processor       : 1\n" +
                        "model name      : ARMv7 Processor rev 3 (v7l)\n" +
                        "Features        : swp half thumb fastmult vfp edsp neon vfpv3 tls vfpv4 idiva idivt\n" +
                        "CPU implementer : 0x41\n" +
                        "CPU architecture: 7\n" +
                        "CPU variant     : 0x3\n" +
                        "CPU part        : 0xc0f\n" +
                        "CPU revision    : 3\n" +
                        "\n" +
                        "processor       : 2\n" +
                        "model name      : ARMv7 Processor rev 3 (v7l)\n" +
                        "Features        : swp half thumb fastmult vfp edsp neon vfpv3 tls vfpv4 idiva idivt\n" +
                        "CPU implementer : 0x41\n" +
                        "CPU architecture: 7\n" +
                        "CPU variant     : 0x3\n" +
                        "CPU part        : 0xc0f\n" +
                        "CPU revision    : 3\n" +
                        "\n" +
                        "processor       : 3\n" +
                        "model name      : ARMv7 Processor rev 3 (v7l)\n" +
                        "Features        : swp half thumb fastmult vfp edsp neon vfpv3 tls vfpv4 idiva idivt\n" +
                        "CPU implementer : 0x41\n" +
                        "CPU architecture: 7\n" +
                        "CPU variant     : 0x3\n" +
                        "CPU part        : 0xc0f\n" +
                        "CPU revision    : 3\n" +
                        "\n" +
                        "Hardware        : mocha\n" +
                        "Revision        : 0000\n" +
                        "Serial          : 06f4044c03410700\n" +
                        "Processor       : ARMv7 Processor rev 3 (v7l)\n";
        String filePath = String.format("%s/%s", Constant.FAKE_PATH, Constant.CPUINFO_FILE_NAME);
        IoUtil.writeFile(filePath, info);
    }

    private void setCpuRuntime() {
        String absPath = Constant.FAKE_PATH + "/" + "cpu";
        File cpuDir = new File(absPath);
        if (!cpuDir.exists()) cpuDir.mkdirs();

        String cpu0FreqPath = composeCpuFreqPath(absPath, "cpu0");
        makeDirs(cpu0FreqPath);
        createFile(cpu0FreqPath + "/" + "cpuinfo_max_freq", "2218500\n");
        // 只模拟使用一核，其它核心空闲
        makeDirs(absPath + "/" + "cpu1");
        makeDirs(absPath + "/" + "cpu2");
        makeDirs(absPath + "/" + "cpu3");

        makeDirs(absPath + "/" + "cpufreq");
        makeDirs(absPath + "/" + "cpuidle");
        makeDirs(absPath + "/" + "cpuquiet");
        makeDirs(absPath + "/" + "power");

        createFile(absPath + "/" + "online", "0\n");
        createFile(absPath + "/" + "offline", "1-3\n");
        createFile(absPath + "/" + "possible", "0-3\n");
        createFile(absPath + "/" + "present", "0-3\n");
        createFile(absPath + "/" + "uevent", "");
        createFile(absPath + "/" + "kernel_max", "3\n");
    }

    private String composeCpuFreqPath(String parent, String kernalPath) {
        return String.format("%s/%s/%s", parent, kernalPath, "cpufreq");
    }

    private void createFile(String path, String content) {
        File newFile = new File(path);
        try {
            if (!newFile.exists()) newFile.createNewFile();

            RandomAccessFile raf = new RandomAccessFile(newFile, "rwd");
//            raf.seek(newFile.length());
            raf.seek(0);
            raf.write(content.getBytes());
            raf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void makeDirs(String path) {
        File newDir = new File(path);
        if (!newDir.exists()) newDir.mkdirs();
    }
}
