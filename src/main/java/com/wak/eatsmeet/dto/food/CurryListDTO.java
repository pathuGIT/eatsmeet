package com.wak.eatsmeet.dto.food;

import java.time.LocalDate;
import java.util.List;

public class CurryListDTO {

    private List<Integer> curryId;
    private LocalDate date;
    private String times;
    private String img_url;

    public CurryListDTO(List<Integer> curryId, LocalDate date, String times, String img_url) {
        this.curryId = curryId;
        this.date = date;
        this.times = times;
        this.img_url = img_url;
    }

    public CurryListDTO() {
    }

    public List<Integer> getCurryId() {
        return curryId;
    }

    public void setCurryId(List<Integer> curryId) {
        this.curryId = curryId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
