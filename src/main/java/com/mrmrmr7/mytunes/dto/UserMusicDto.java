package com.mrmrmr7.mytunes.dto;

import com.mrmrmr7.mytunes.entity.Album;
import com.mrmrmr7.mytunes.entity.MusicSelectionInfo;

import java.util.List;

public class UserMusicDto {
    public List<CompositionDto> compositionDtoList;
    public List<MusicSelectionInfo> musicSelectionInfoList;
    public List<AlbumDto> albumDtoList;

    public List<CompositionDto> getCompositionDtoList() {
        return compositionDtoList;
    }

    public void setCompositionDtoList(List<CompositionDto> compositionDtoList) {
        this.compositionDtoList = compositionDtoList;
    }

    public List<MusicSelectionInfo> getMusicSelectionInfoList() {
        return musicSelectionInfoList;
    }

    public void setMusicSelectionInfoList(List<MusicSelectionInfo> musicSelectionInfoList) {
        this.musicSelectionInfoList = musicSelectionInfoList;
    }

    public List<AlbumDto> getAlbumDtoList() {
        return albumDtoList;
    }

    public void setAlbumDtoList(List<AlbumDto> albumDtoList) {
        this.albumDtoList = albumDtoList;
    }


}
