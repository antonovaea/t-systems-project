package project.spaceshop.util;

import java.util.Base64;

public class ImageDtoUtil {
    public String getImgDtoData(byte[] byteData) {
        return Base64.getMimeEncoder().encodeToString(byteData);
    }
}
