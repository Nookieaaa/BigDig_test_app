package com.nookdev.bigdigtestapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Item {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("picture")
    @Expose
    private String picture;
    @SerializedName("artists")
    @Expose
    private List<Artist> artists = new ArrayList<Artist>();
    @SerializedName("source")
    @Expose
    private long source;
    @SerializedName("youtube_id")
    @Expose
    private String youtubeId;
    @SerializedName("view_count")
    @Expose
    private long viewCount;
    @SerializedName("like_count")
    @Expose
    private long likeCount;

    public Item(Integer id, String slug, String title, String picture, List<Artist> artists,
                long source, String youtubeId, long viewCount, long likeCount) {

        this.id = id;
        this.slug = slug;
        this.title = title;
        this.picture = picture;
        this.artists = artists;
        this.source = source;
        this.youtubeId = youtubeId;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public long getSource() {
        return source;
    }

    public void setSource(long source) {
        this.source = source;
    }

    public String getYoutubeId() {
        return youtubeId;
    }

    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
    }

    public long getViewCount() {
        return viewCount;
    }

    public void setViewCount(long viewCount) {
        this.viewCount = viewCount;
    }

    public long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(long likeCount) {
        this.likeCount = likeCount;
    }

    public String getArtistString(){
        StringBuilder result = new StringBuilder();
        for (Artist artist : artists){
            result.append(artist.toString());
            if (artists.indexOf(artist)!=artists.size()-1){
                result.append(" & ");
            }
        }
        return result.toString();
    }

    public boolean addArtist(Artist artist){
        return artists.add(artist);
    }
    public boolean removeArtist(Artist artist){
        return artists.remove(artist);
    }
}
