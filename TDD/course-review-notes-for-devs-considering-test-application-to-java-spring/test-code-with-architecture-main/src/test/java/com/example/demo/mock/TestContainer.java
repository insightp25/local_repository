package com.example.demo.mock;

import com.example.demo.common.service.ClockHolder;
import com.example.demo.common.service.UuidHolder;
import com.example.demo.post.controller.PostController;
import com.example.demo.post.controller.PostCreateController;
import com.example.demo.post.controller.port.PostService;
import com.example.demo.post.service.PostServiceImpl;
import com.example.demo.post.service.port.PostRepository;
import com.example.demo.user.controller.UserController;
import com.example.demo.user.controller.UserCreateController;
import com.example.demo.user.controller.port.AuthenticationService;
import com.example.demo.user.controller.port.UserCreateService;
import com.example.demo.user.controller.port.UserReadService;
import com.example.demo.user.controller.port.UserUpdateService;
import com.example.demo.user.service.CertificationService;
import com.example.demo.user.service.UserServiceImpl;
import com.example.demo.user.service.port.MailSender;
import com.example.demo.user.service.port.UserRepository;
import lombok.Builder;

public class TestContainer {

    public final MailSender mailSender;

    public final UserRepository userRepository;

    public final CertificationService certificationService;

    public final UserReadService userReadService;
    public final UserCreateService userCreateService;
    public final UserUpdateService userUpdateService;
    public final AuthenticationService authenticationService;

    public final UserController userController;
    public final UserCreateController userCreateController;

    public final PostRepository postRepository;

    public final PostService postService;

    public final PostController postController;
    public final PostCreateController postCreateController;

    @Builder
    public TestContainer(ClockHolder clockHolder, UuidHolder uuidHolder) {

        //User domain
        //infrastructure layer
        this.mailSender = new FakeMailSender();

        this.userRepository = new FakeUserRepository();

        //service layer
        this.certificationService = new CertificationService(this.mailSender);

        UserServiceImpl userService = UserServiceImpl.builder()
            .uuidHolder(uuidHolder)
            .clockHolder(clockHolder)
            .certificationService(new CertificationService(this.mailSender))
            .userRepository(this.userRepository)
            .build();
        this.userReadService = userService;
        this.userCreateService = userService;
        this.userUpdateService = userService;
        this.authenticationService = userService;

        //controller layer
        this.userController = UserController.builder()
            .userReadService(this.userReadService)
            .userUpdateService(this.userUpdateService)
            .authenticationService(this.authenticationService)
            .build();

        this.userCreateController = UserCreateController.builder()
            .userCreateService(this.userCreateService)
            .build();

        //Post domain
        //infrastructure layer
        this.postRepository = new FakePostRepository();

        // service layer
        this.postService = PostServiceImpl.builder()
            .postRepository(this.postRepository)
            .userRepository(this.userRepository)
            .clockHolder(clockHolder)
            .build();

        //controller layer
        this.postController = PostController.builder()
            .postService(this.postService)
            .build();

        this.postCreateController = PostCreateController.builder()
            .postService(this.postService)
            .build();
    }
}
