package com.xinde.baggio.hook.douyin;

import java.util.concurrent.Executor;
import java.util.concurrent.Future;

public interface K<V> extends Future<V> {
    void a(Runnable runnable, Executor executor);
}