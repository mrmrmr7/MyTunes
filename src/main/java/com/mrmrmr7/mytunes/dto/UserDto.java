package com.mrmrmr7.mytunes.dto;

import com.mrmrmr7.mytunes.entity.*;

import java.util.ArrayList;
import java.util.List;

public class UserDto {
    private String login;
    private String first_name;
    private String second_name;
    private String email;
    private Role role;
    private Status status;
    private long balance;

    private List<Composition> compositionList = new ArrayList<>();
    private List<Album> albumList = new ArrayList<>();
    private List<MusicSelection> musicSelectionList = new ArrayList<>();
    private List<Bonus> bonusList = new ArrayList<>();

    public UserDto(){}

    public List<MusicSelection> getMusicSelectionList() {
        return musicSelectionList;
    }

    public void setMusicSelectionList(List<MusicSelection> musicSelectionList) {
        this.musicSelectionList = musicSelectionList;
    }

    public List<Bonus> getBonusList() {
        return bonusList;
    }

    public void setBonusList(List<Bonus> bonusList) {
        this.bonusList = bonusList;
    }

    public void addBonus(Bonus bonus) {
        this.bonusList.add(bonus);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserDto(String login, String first_name,
                   String second_name, Role role,
                   Status status, long balance,
                   List<Composition> compositionList,
                   List<Album> albumList, List<MusicSelection> musicSelectionList) {

        this.login = login;
        this.first_name = first_name;
        this.second_name = second_name;
        this.role = role;
        this.status = status;
        this.balance = balance;
        this.compositionList = compositionList;
        this.albumList = albumList;
        this.musicSelectionList = musicSelectionList;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public void addComposition(Composition composition) {
        compositionList.add(composition);
    }

    public void addAlbum(Album album) {
        albumList.add(album);
    }

    public void addMusicSelection(MusicSelection musicSelection){
        musicSelectionList.add(musicSelection);
    }

    public List<Composition> getCompositionList() {
        return compositionList;
    }

    public void setCompositionList(List<Composition> compositionList) {
        this.compositionList = compositionList;
    }

    public List<Album> getAlbumList() {
        return albumList;
    }

    public void setAlbumList(List<Album> albumList) {
        this.albumList = albumList;
    }
}
