package com.hu.video.mapper;

import com.hu.video.entity.TState;
import com.hu.video.entity.TVideo;
import com.hu.video.entity.TVideotype;
import com.hu.video.entity.dto.VideoInfoDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TVideoMapper {
    int deleteByPrimaryKey(Long videoId);

    int insert(TVideo record);

    int insertSelective(TVideo record);

    TVideo selectByPrimaryKey(Long videoId);

    int updateByPrimaryKeySelective(TVideo record);

    int updateByPrimaryKey(TVideo record);

    int updateVideoViewSumByVideoId(Long videoId);

    TVideo selectOneByVideoId(Long videoId);

    @Select("select count(*) from t_video")
    int selectVideoCount();

    List<TVideo> selectRecommendVideo(@Param("curIndex") int curIndex, @Param("pageSize") int pageSize, @Param("userId") Long userId);

    @Update("update t_video set pp_num = pp_num + 1 where  video_id = #{videoId}")
    int updateVideoPpSumByVideoId(@Param("videoId") Long videoId);

    int addVideo(TVideo video);

    @Select("SELECT video_id from t_video where video_title=#{videoTitle}")
    Long getVideoLastId(String videoTitle);

    int updateVideoPath(@Param("path") String path, @Param("videoId") Long videoId);

    int updateVideoImage(@Param("s") String s, @Param("videoId") Long videoId);

    List<VideoInfoDTO> videoList();

    int rdeleteVideo(@Param("videoId") Long videoId);

    List<VideoInfoDTO> underVideoList();

    int restoreVideo(Long id);

    VideoInfoDTO getVideoById(Long id);

    List<TState> getStateList();

    List<TVideotype> getVideoTypeList();

    int adminUpdateVideoImage(@Param("s") String s, @Param("videoId") Long videoId);

    List<TVideo> queryByVideoTitle(String videoTitle);

    List<TVideo> getVideoListByUserId(@Param("userId") Long userId, @Param("videoStateId") Long videoStateId);

    List<TVideo> getVideoByVideoTypeId(Long videoTypeId);

    List<TVideo> getIndexRecommendVideo();

    List<TVideo> getIndexLastVideo();

    List<VideoInfoDTO> getiVideoByTitle(@Param("videoTitle") String videoTitle);

    void deleteByUserId(Long id);
}
