package cn.rui0.core.model.vo.common;

import cn.rui0.core.model.po.common.Exam;
import com.sun.org.apache.xml.internal.resolver.readers.ExtendedXMLCatalogReader;
import lombok.Data;

@Data
public class ExamVo {
    private long id;
    private int amount;
    private String content;
    private String etime;
    private String stime;
    private String name;
    private String time;
    private String number;

    public ExamVo(Exam exam){
        this.id=exam.getExam_id();
        this.amount=exam.getAmount();
        this.content=exam.getContent();
        this.etime=exam.getEtime();
        this.stime=exam.getStime();
        this.name=exam.getName();
        this.time=exam.getTime();
        this.number=exam.getNumber();
    }
}
