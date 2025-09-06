package model;

import java.io.Serial;
import java.io.Serializable;

public class PrintJob implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final Integer idJob;
    private final String fileName;
    private final Integer pageCount;

    public PrintJob(Integer idJob, String fileName, Integer pageCount) {
        this.idJob = idJob;
        this.fileName = fileName;
        this.pageCount = pageCount;
    }

    public String getFileName() {
        return fileName;
    }

    public Integer getIdJob() {
        return idJob;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    @Override
    public String toString() {
        return "[Job " + idJob + "] " + fileName + " (" + pageCount + " páginas)";
    }
}
