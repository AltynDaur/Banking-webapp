package com.epam.javalab.webapp.servlet;

public class ActionResult {
    String path;
    boolean isRedirect = false;

    public ActionResult(String path, boolean isRedirect) {
        this.path = path;
        this.isRedirect = isRedirect;
    }

    public ActionResult(String path) {
        this.path = path;
    }

    public ActionResult() {
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setRedirect(boolean isRedirect) {
        this.isRedirect = isRedirect;
    }

    public String getPath() {
        return path;
    }

    public boolean isRedirect() {
        return isRedirect;
    }
}
