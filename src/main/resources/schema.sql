CREATE TABLE `withdrawn_users` (
                                   `withdrawn_id`           BIGINT          NOT NULL AUTO_INCREMENT,
                                   `user_id`                BIGINT          NOT NULL,
                                   `joined_at`              TIMESTAMP       NOT NULL,
                                   `withdrawn_at`           TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                   `withdraw_reason_type`   TINYINT         NULL,
                                   `withdraw_reason_detail` VARCHAR(255)    NULL,
                                   CONSTRAINT `PK_WITHDRAWN_USERS` PRIMARY KEY (`withdrawn_id`),
                                   CONSTRAINT `CK_WITHDRAWN_USERS_REASON_TYPE` CHECK (`withdraw_reason_type` >= 0 OR `withdraw_reason_type` IS NULL)

);

CREATE TABLE `users` (
                         `user_id`                BIGINT          NOT NULL AUTO_INCREMENT,
                         `email`                  VARCHAR(255)    NOT NULL,
                         `password`               VARCHAR(128)    NOT NULL,
                         `nickname`               VARCHAR(20)     NOT NULL,
                         `profile_image_url`      VARCHAR(255)    NULL,
                         `status`                 VARCHAR(20)     NOT NULL DEFAULT 'ACTIVE'
                             CHECK (`status` IN ('ACTIVE', 'SLEEPER', 'WITHDRAW_PENDING', 'SUSPENDED')),
                         `created_at`             TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         `updated_at`             TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         `last_login_at`          TIMESTAMP       NULL,
                         `delete_scheduled_at`    TIMESTAMP       NULL,
                         `withdraw_reason_type`   TINYINT         NULL,
                         `withdraw_reason_detail` VARCHAR(255)    NULL,
                         CONSTRAINT `PK_USERS` PRIMARY KEY (`user_id`),
                         CONSTRAINT `UQ_USERS_EMAIL` UNIQUE (`email`),
                         CONSTRAINT `UQ_USERS_NICKNAME` UNIQUE (`nickname`),
                         CONSTRAINT `CK_USERS_REASON_TYPE` CHECK (`withdraw_reason_type` >= 0 OR `withdraw_reason_type` IS NULL)
);

CREATE TABLE `terms` (
                         `terms_id`               BIGINT          NOT NULL AUTO_INCREMENT,
                         `title`                  VARCHAR(100)    NOT NULL,
                         `content_url`            VARCHAR(255)    NOT NULL,
                         `is_required`            BOOLEAN         NOT NULL DEFAULT FALSE,
                         `version`                VARCHAR(10)     NOT NULL,
                         `created_at`             TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         CONSTRAINT `PK_TERMS` PRIMARY KEY (`terms_id`),
                         CONSTRAINT `UQ_TERMS_TITLE_VERSION` UNIQUE (`title`, `version`)
);

CREATE TABLE `posts` (
                         `post_id`                BIGINT          NOT NULL AUTO_INCREMENT,
                         `user_id`                BIGINT          NULL,
                         `title`                  VARCHAR(100)    NOT NULL DEFAULT '',
                         `content`                TEXT            NOT NULL,
                         `main_image_id`          BIGINT          NULL,
                         `created_at`             TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         `updated_at`             TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         `status`                 TINYINT         NOT NULL DEFAULT 0,
                         `like_count`             INT             NOT NULL DEFAULT 0,
                         `comment_count`          INT             NOT NULL DEFAULT 0,
                         `view_count`             INT             NOT NULL DEFAULT 0,
                         `deleted_at`             TIMESTAMP       NULL,
                         CONSTRAINT `PK_POSTS` PRIMARY KEY (`post_id`),
                         CONSTRAINT `CK_POSTS_STATUS` CHECK (`status` IN (0, 1, 2, 3))
);

CREATE TABLE `post_images` (
                               `image_id`               BIGINT          NOT NULL AUTO_INCREMENT,
                               `post_id`                BIGINT          NOT NULL,
                               `image_url`              VARCHAR(255)    NOT NULL,
                               `sequence`               TINYINT         NOT NULL DEFAULT 0,
                               `status`                 TINYINT         NOT NULL DEFAULT 0,
                               `created_at`             TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
                               `deleted_at`             TIMESTAMP       NULL,
                               CONSTRAINT `PK_POST_IMAGES` PRIMARY KEY (`image_id`),
                               CONSTRAINT `CK_POST_IMAGES_SEQUENCE` CHECK (`sequence` >= 0),
                               CONSTRAINT `CK_POST_IMAGES_STATUS` CHECK (`status` IN (0, 1, 2, 3))
);

CREATE TABLE `comments` (
                            `comment_id`             BIGINT          NOT NULL AUTO_INCREMENT,
                            `user_id`                BIGINT          NULL,
                            `post_id`                BIGINT          NOT NULL,
                            `parent_comment_id`      BIGINT          NULL,
                            `content`                TEXT            NOT NULL,
                            `status`                 TINYINT         NOT NULL DEFAULT 0,
                            `created_at`             TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            `updated_at`             TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                            `deleted_at`             TIMESTAMP       NULL,
                            CONSTRAINT `PK_COMMENTS` PRIMARY KEY (`comment_id`),
                            CONSTRAINT `CK_COMMENTS_STATUS` CHECK (`status` IN (0, 1, 2))
);

CREATE TABLE `post_likes` (
                              `like_id`                BIGINT          NOT NULL AUTO_INCREMENT,
                              `user_id`                BIGINT          NOT NULL,
                              `post_id`                BIGINT          NOT NULL,
                              `created_at`             TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              CONSTRAINT `PK_POST_LIKES` PRIMARY KEY (`like_id`),
                              CONSTRAINT `UQ_POST_LIKES_USER_POST` UNIQUE (`user_id`, `post_id`)
);

CREATE TABLE `terms_agreements` (
                                    `agreement_id`           BIGINT          NOT NULL AUTO_INCREMENT,
                                    `user_id`                BIGINT          NOT NULL,
                                    `terms_id`               BIGINT          NOT NULL,
                                    `is_agreed`              BOOLEAN         NOT NULL,
                                    `agreed_at`              TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                    CONSTRAINT `PK_TERMS_AGREEMENTS` PRIMARY KEY (`agreement_id`),
                                    CONSTRAINT `UQ_TERMS_COMPOSITE` UNIQUE (`user_id`, `terms_id`)
);

ALTER TABLE `posts`
    ADD CONSTRAINT `FK_USERS_TO_POSTS`
        FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
            ON DELETE SET NULL;

ALTER TABLE `comments`
    ADD CONSTRAINT `FK_USERS_TO_COMMENTS`
        FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
            ON DELETE SET NULL;

ALTER TABLE `comments`
    ADD CONSTRAINT `FK_POSTS_TO_COMMENTS`
        FOREIGN KEY (`post_id`) REFERENCES `posts` (`post_id`)
            ON DELETE CASCADE;

ALTER TABLE `comments`
    ADD CONSTRAINT `FK_COMMENTS_TO_COMMENTS`
        FOREIGN KEY (`parent_comment_id`) REFERENCES `comments` (`comment_id`)
            ON DELETE SET NULL;

ALTER TABLE `post_likes`
    ADD CONSTRAINT `FK_USERS_TO_POST_LIKES`
        FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
            ON DELETE CASCADE;

ALTER TABLE `post_likes`
    ADD CONSTRAINT `FK_POSTS_TO_POST_LIKES`
        FOREIGN KEY (`post_id`) REFERENCES `posts` (`post_id`)
            ON DELETE CASCADE;

ALTER TABLE `terms_agreements`
    ADD CONSTRAINT `FK_USERS_TO_AGREEMENTS`
        FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
            ON DELETE CASCADE;

ALTER TABLE `terms_agreements`
    ADD CONSTRAINT `FK_TERMS_TO_AGREEMENTS`
        FOREIGN KEY (`terms_id`) REFERENCES `terms` (`terms_id`)
            ON DELETE CASCADE;