package org.boot.service;

import org.boot.dao.PostDAO;
import org.boot.model.PostModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("postService")
@Transactional(propagation = Propagation.REQUIRED)
public class PostServiceImpl implements PostService {

    private static final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);

    @Autowired
    @Qualifier("postDAO")
    private PostDAO postDAO;

    @Override
    public PostModel postInfo(PostModel postModel) {
        logger.info("게시글 상세보기");
        return postDAO.selectPostInfo(postModel);
    }

    @Override
    public int postCount(PostModel postModel) {
        logger.info("게시글 개수 조회");
        return postDAO.selectPostCount(postModel);
    }

    @Override
    public List<PostModel> postList(PostModel postModel, int offset, int limitRow) {
        logger.info("게시글 목록 조회");
        return postDAO.selectPostList(postModel, offset, limitRow);
    }

    @Override
    public int insertPost(PostModel postModel) throws Exception {
        logger.info("게시글 작성");
        return postDAO.insertPost(postModel);
    }

    @Override
    public int updatePost(PostModel postModel) throws Exception {
        logger.info("게시글 수정");
        return postDAO.updatePost(postModel);
    }

    @Override
    public int deletePost(PostModel postModel) throws Exception {
        logger.info("게시글 삭제");
        return postDAO.deletePost(postModel);
    }
}
