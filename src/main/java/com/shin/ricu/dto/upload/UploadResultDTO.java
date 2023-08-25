package com.shin.ricu.dto.upload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadResultDTO {

    private String uuid;
    @Builder.Default
    private String fileName = "default";
    private boolean isImage;

    public String getLink()
    {
        if(isImage)
            return "s_" + uuid + "_" + fileName;
        else
            return uuid + "_" + fileName;
    }
}
