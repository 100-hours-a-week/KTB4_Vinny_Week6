package com.vinny.project.post;

import com.vinny.project.enums.PostStatus;
import com.vinny.project.post.dto.request.PostCreateRequest;
import com.vinny.project.post.dto.request.PostUpdateRequest;
import com.vinny.project.post.dto.response.PostDetailResponse;
import com.vinny.project.post.dto.response.PostListResponse;
import com.vinny.project.post.exception.PostNotFoundException;
import com.vinny.project.post.image.PostImage;
import com.vinny.project.post.image.PostImageRepository;
import com.vinny.project.post.image.PostImageResponse;
import com.vinny.project.post.like.PostLikeRepository;
import com.vinny.project.user.User;
import com.vinny.project.user.UserService;
import com.vinny.project.user.dto.response.AuthorSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;
    private final PostImageRepository postImageRepository;
    private final PostLikeRepository postLikeRepository;

    public Post findById(Long postId) {
        return postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
    }

    public Post getReferenceById(Long postId) {
        return postRepository.getReferenceById(postId);
    }

    @Transactional
    public PostDetailResponse createPost(Long userId, PostCreateRequest request) {
        User author = userService.findById(userId);

        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .status(PostStatus.ACTIVE)
                .author(author)
                .build();

        Post savedPost = postRepository.save(post);
        List<PostImageResponse> imageResponses = new ArrayList<>();

        for(int i = 0; i < request.getImages().size(); i++){
            MultipartFile file = request.getImages().get(i);
            PostImage postImage = PostImage.builder()
                    .post(savedPost)
                    .imageUrl(file.getOriginalFilename()) // TODO: S3랑 연결
                    .sequence(i)
                    .status(PostStatus.ACTIVE)
                    .build();
            PostImage savedPostImages = postImageRepository.save(postImage);
            imageResponses.add(PostImageResponse.from(savedPostImages));

            if(savedPostImages.getSequence() == 0){
                savedPost.changeMainImage((savedPostImages));
            }
        }

        return PostDetailResponse.of(savedPost, AuthorSummary.from(author), imageResponses);
    }

    public List<PostListResponse> getPosts() {
        List<Post> posts = postRepository.findAll();

        return posts.stream()
                .map(post -> PostListResponse.of(post, AuthorSummary.from(post.getAuthor())))
                .collect(Collectors.toList());
    }

    @Transactional
    public PostDetailResponse getPost(Long id) {
        Post post =  postRepository.findById(id).orElseThrow(PostNotFoundException::new);

        List<PostImageResponse> imageResponses = post.getPostImages().stream()
                .map(PostImageResponse::from)
                .collect(Collectors.toList());

        post.increaseViewCount();

        return PostDetailResponse.of(post, AuthorSummary.from(post.getAuthor()), imageResponses);
    }

    @Transactional
    public PostDetailResponse patch(Long postId, PostUpdateRequest request) {
        Post post = findById(postId);

        post.changeTitle(request.getTitle());
        post.changeContent(request.getContent());

        //TODO 이미지 수정 구현
        return PostDetailResponse.of(post, AuthorSummary.from(post.getAuthor()), List.of());
    }

    @Transactional
    public void delete(Long postId) {
        Post post = findById(postId);

        postLikeRepository.deleteAllByPost(post);
        postLikeRepository.flush();

        post.deletePost();
    }
}
