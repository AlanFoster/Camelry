package me.alanfoster.camelry;

import com.intellij.CommonBundle;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.PropertyKey;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.ResourceBundle;

/**
 * Use this class for access to internationalised strings for i18n support.
 */
public class CamelryBundle {
    private static Reference<ResourceBundle> ourBundle;

    @NonNls
    private static final String PATH_TO_BUNDLE = "CamelryBundle";

    private CamelryBundle(){}

    public static String message(@PropertyKey(resourceBundle = PATH_TO_BUNDLE) final String key, final String... params) {
        return CommonBundle.message(getBundle(), key, params);
    }

    private static ResourceBundle getBundle() {
        ResourceBundle bundle = null;
        if(ourBundle != null) {
            bundle = ourBundle.get();
        }
        if(bundle == null) {
            bundle = ResourceBundle.getBundle(PATH_TO_BUNDLE);
            ourBundle = new SoftReference<ResourceBundle>(bundle);
        }
        return bundle;
    }
}
