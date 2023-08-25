package com.shin.ricu.dto.page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
public class PageRequestDTO
{
    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int size = 10;

    private String keyword;
    private String type;
    private String link;

    public Pageable getPageable(String...props)
    {
        return PageRequest.of(this.page - 1, this.size, Sort.by(props).descending());
    }

    public String getLink()
    {
        if(link == null)
        {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("page=" + this.page);
            stringBuilder.append("&size=" + this.size);
            if(type != null && type.length() > 0)
            {
                stringBuilder.append("&type" + type);
            }
            if(keyword != null)
                try
                {
                    stringBuilder.append("&keyword=" + URLEncoder.encode(keyword, "UTF-8"));
                }catch (UnsupportedEncodingException e)
                {

                }
            link = stringBuilder.toString();
        }
        return link;
    }
}
