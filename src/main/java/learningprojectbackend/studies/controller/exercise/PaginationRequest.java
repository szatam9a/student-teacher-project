package learningprojectbackend.studies.controller.exercise;

import lombok.Getter;

@Getter
public class PaginationRequest {

    private FilterRequestDetails filter;
    private int pageNumber;
    private int pageSize;

}
