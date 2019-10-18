package cn.rui0.core.model.dto.common;

import lombok.Data;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;


@Data
public abstract class SplitPageDTO {

    private Integer page = 0;

    private Integer pageSize = 10;

    private Sort asc = Sort.by(Sort.Direction.DESC, "createTime");

    private Pageable pageable;


//    public Integer getPage() {
//        return page-1;
//    }

    public Pageable getPageable() {
        if (this.pageable==null)
            return PageRequest.of(this.page,this.pageSize,this.asc);
        else
            return this.pageable;
    }


}
