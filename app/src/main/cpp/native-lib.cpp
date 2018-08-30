#include <jni.h>
#include <string>


extern "C"
JNIEXPORT jstring JNICALL
Java_com_xinde_baggio_NativeHelper_stringFromJNI(JNIEnv *env, jobject instance) {

    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}