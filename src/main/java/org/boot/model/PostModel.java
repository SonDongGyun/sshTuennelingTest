package org.boot.model;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;

@Getter
@Setter
@Component("postModel")
public class PostModel {

    private static final Logger logger = LoggerFactory.getLogger(PostModel.class);

    private int rowNum;
    private String postUuid;
    private String writeId;
    private String postTitle;
    private String postContent;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date registryDate;

    /*
     * lombok 사용으로인한 Getter Setter 메서드 사용하지 않음
     */
    // public int getRowNum() {
    //      return rowNum;
    // }

    // public void setRowNum(int rowNum) {
    //      this.rowNum = rowNum;
    // }

    // public String getPostUuid() {
    //      return postUuid;
    // }

    // public void setPostUuid(String postUuid) {
    //      this.postUuid = postUuid;
    // }

    // public String getWriteId() {
    //      return writeId;
    // }

    // public void setWriteId(String writeId) {
    //      this.writeId = writeId;
    // }

    // public String getPostTitle() {
    //      return postTitle;
    // }

    // public void setPostTitle(String postTitle) {
    //     this.postTitle = postTitle;
    // }

    // public String getPostContent() {
    //      return postContent;
    // }

    // public void setPostContent(String postContent) {
    //      this.postContent = postContent;
    // }

    //  public Date getRegistryDate() {
    //      return registryDate;
    // }

    // public void setRegistryDate(Date registryDate) {
    //      this.registryDate = registryDate;
    // }

    public PostModel() {
        logger.info("PostVO 생성자 호출");
    }
}