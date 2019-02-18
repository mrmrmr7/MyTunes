package com.mrmrmr7.mytunes.entity;

import java.util.List;

public class MusicSelection implements DBInstance {
    private long id;
    private List<Long> composition_id;
    private int selection_id;

    public MusicSelection(long id, List<Long> composition_id, int selection_id) {
        this.id = id;
        this.composition_id = composition_id;
        this.selection_id = selection_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Long> getComposition_id() {
        return composition_id;
    }

    public void setComposition_id(List<Long> composition_id) {
        this.composition_id = composition_id;
    }

    public int getSelection_id() {
        return selection_id;
    }

    public void setSelection_id(int selection_id) {
        this.selection_id = selection_id;
    }
}
