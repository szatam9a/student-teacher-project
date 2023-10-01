package learningprojectbackend.studies.controller.course;

import lombok.Getter;

@Getter
public class PaginationRequest {
    private Filter filters;
    private int pageNumber;
    private int pageSize;
    private String sort;
}
