package com.social.utils;

/**
 *
 * @author LENOVO
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CloudinaryUtil {

    private static final String CLOUDINARY_URL_PATTERN = "(?:https?:)?//res.cloudinary.com/([^/]+)/image/upload/([^\\.]+)";

    public static String extractPublicIdFromUrl(String imageUrl) {
        Pattern pattern = Pattern.compile(CLOUDINARY_URL_PATTERN);
        Matcher matcher = pattern.matcher(imageUrl);

        if (matcher.find()) {
            String cloudName = matcher.group(1);
            String publicId = matcher.group(2);
            return cloudName + "/" + publicId;
        }

        // Return null if the URL does not match the expected Cloudinary URL pattern
        return null;
    }
}
