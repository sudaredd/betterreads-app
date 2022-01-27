package app.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Utils {

    @Value("${cover.large.url}")
    private String coverLargeUrl;

    @Value("${cover.medium.url}")
    private String coverMediumUrl;
    String defaultCoverImageUrl = "images/no-image-available.jpeg";

    public String getCoverLargeImageUrl(String coverId, boolean isLarge) {
        if(isLarge)
            return coverId != null ? coverLargeUrl.replace("{id}", coverId) : defaultCoverImageUrl;
        else
            return coverId != null ? coverMediumUrl.replace("{id}", coverId) : defaultCoverImageUrl;
    }
}
