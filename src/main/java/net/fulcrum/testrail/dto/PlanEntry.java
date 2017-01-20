package net.fulcrum.testrail.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Test Plan entry
 *
 * @author Sharanya
 */
public class PlanEntry {

    public String id;
    @JsonProperty("suite_id")
    public int suiteId;
    public String name;
    public List<Run> runs;

}
