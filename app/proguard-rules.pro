# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-keepattributes *Annotation*,EnclosingMethod,Signature,Exceptions

-dontwarn java.lang.invoke.*

-dontwarn android.databinding.**

# Android & Google
-dontwarn com.google.**
-dontwarn androidx.**
-keep class * extends android.app.Activity { *; }
-keep class * extends android.app.Application { *; }
-keep class * extends android.app.Service
-keep class * extends android.content.BroadcastReceiver
-keep class com.google.** { *; }
-keep class android.** { *; }
-keep class androidx.** { *; }
-keep interface androidx.** { *; }
-keep interface com.google.** { *; }
-keepclassmembers class  *  {
    void $$clinit();
}

# Enum
-keepclassmembers class * extends java.lang.Enum {
    <fields>;
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Serializable
-keepnames class * implements java.io.Serializable
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    !private <fields>;
    !private <methods>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

#Retrofit
-dontwarn retrofit.**
-dontwarn okio.**
-dontwarn com.squareup.okhttp.**

#OkHttp3
-dontwarn okhttp3.**
-dontnote okhttp3.**

-keep class io.rezyfr.tmdb.data.response.** { *; }
-keep class io.rezyfr.tmdb.domain.model.** { *; }