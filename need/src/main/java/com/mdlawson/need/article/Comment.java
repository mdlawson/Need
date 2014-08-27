package com.mdlawson.need.article;

import java.util.ArrayList;
import java.util.List;

public abstract class Comment {
    private int depth;

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getDepth() {
        return depth;
    }

    public abstract String getText();
}