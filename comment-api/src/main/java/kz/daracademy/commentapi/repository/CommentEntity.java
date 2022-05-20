package kz.daracademy.commentapi.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "comments")
public class CommentEntity {


    @Id
    @Field(type = FieldType.Keyword)
    private String commentId;

    @Field(type = FieldType.Keyword)
    private String text;


    @Field(type = FieldType.Keyword)
    private String userId;


    @Field(type = FieldType.Keyword)
    private String eventId;

    @Field(type = FieldType.Keyword)
    private String parentCommentId;
}
