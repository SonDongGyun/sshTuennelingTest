package org.boot.dao;

import org.apache.ibatis.annotations.Mapper;
import org.boot.model.PostModel;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

// MyBatis는 @Mapper Annotation을 사용하여 interface를 Mapper로 인식하고 SQL 매핑 파일과 연결한다.
@Mapper

// DAO 클래스임을 나타내는 Annotation으로, DataBase와 상호작용을 수행하는 클래스에 사용된다.
// 지정된 이름( 예 : postDAO )을 통해 여러 DAO 클래스들 중에서 특정 DAO에 명시적으로 주입한다.
@Repository("postDAO")
public interface PostDAO {

    PostModel selectPostInfo(PostModel postModel) throws DataAccessException;

    int selectPostCount(PostModel postModel) throws DataAccessException;

    List<PostModel> selectPostList(PostModel postModel, int offset, int limitRow) throws DataAccessException;

    int insertPost(PostModel postModel) throws Exception;

    int updatePost(PostModel postModel) throws Exception;

    int deletePost(PostModel postModel) throws Exception;
}