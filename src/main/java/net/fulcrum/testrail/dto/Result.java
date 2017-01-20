package net.fulcrum.testrail.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Test Result
 *
 * @author Sharanya
 */
public class Result {

    public int id;
    @JsonProperty("test_id")
    public int testId;
    @JsonProperty("status_id")
    public int statusId;
    public String comment;

}
