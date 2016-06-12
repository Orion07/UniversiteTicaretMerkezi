# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Users\Hakan\AppData\Local\Android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-keepclassmembers class fqcn.of.javascript.interface.for.webview {
   public *;
}

-keep class * extends java.util.ListResourceBundle {
    protected Object[][] getContents();
}

-keep public class com.google.android.gms.common.internal.safeparcel.SafeParcelable {
    public static final *** NULL;
}

-keepnames @com.google.android.gms.common.annotation.KeepName class *
-keepclassmembernames class * {
    @com.google.android.gms.common.annotation.KeepName *;
}

-keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
}

-keep class com.google.android.gms.internal.** {
    *;
}

-dontwarn org.apache.lang.**
-dontwarn org.apache.commons.**
-keep class org.apache.http.** { *; }
-dontwarn org.apache.http.**
-dontwarn com.instagram.common.json.**
-dontwarn com.fasterxml.jackson.databind.ext.DOMSerializer
-dontwarn com.squareup.javawriter.JavaWriter
-dontwarn com.google.common.primitives.UnsignedBytes*
-dontwarn com.google.code.**
-dontwarn oauth.signpost.**
-dontwarn twitter4j.**
-dontwarn javax.management.**
-dontwarn javax.xml.**
-dontwarn org.apache.**
-dontwarn org.slf4j.**
-dontwarn com.google.android.gms.internal.zzhu
-dontwarn com.sun.mail.**
-dontwarn javax.activation.**
-dontwarn android.content.**
-dontwarn android.graphics.**
-dontwarn android.util.**
-dontwarn android.view.**
