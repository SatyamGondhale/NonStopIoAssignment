package com.nonstopioassignment.model;

import java.util.ArrayList;

public class Paths {
    private String pathId;
    private String pathTitle;
    ArrayList<Subpaths> subPaths;

    public String getPathId() {
        return pathId;
    }

    public void setPathId(String pathId) {
        this.pathId = pathId;
    }

    public String getPathTitle() {
        return pathTitle;
    }

    public void setPathTitle(String pathTitle) {
        this.pathTitle = pathTitle;
    }

    public ArrayList<Subpaths> getSubPaths() {
        return subPaths;
    }

    public void setSubPaths(ArrayList<Subpaths> subPaths) {
        this.subPaths = subPaths;
    }
}
