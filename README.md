# social-media

Nhóm 7 - Social Media For OU Alumni

## Get started

```bash
git clone https://github.com/dauxuanhoanghung/social-media.git
```

## Properties

1. Change profile of your mail to use Mail Service in src/main/resources/mail.properties
1. Change profile of your database in src/main/resources/database.properties

## FrontEnd

[Source](https://github.com/chuongdinh59/social-app)

## Overview

### Client API

#### API User

| Method | Endpoint                  |                   Payload                   | Description             |
| :----- | :------------------------ | :-----------------------------------------: | :---------------------- |
| GET    | /api/users/?userId=       |                    None                     | Fetch to get user by ID |
| GET    | /api/users/{id}           |                    None                     | Fetch to get user by ID |
| GET    | /api/users/{id}           |                    None                     | Fetch to get user by ID |
| GET    | /api/users/current-user/  |                    None                     | Get info current user   |
| POST   | /api/users/register/      | { alumniId, displayName, avatarFile, email} | Register new client     |
| POST   | /api/users/upload-avatar/ |                  { file }                   | Upload Avatar           |
| POST   | /api/users/upload-bg/     |                  { file }                   | Upload Background image |

#### API Comments

| Method | Endpoint                                   |              Payload              | Description                                      |
| :----- | :----------------------------------------- | :-------------------------------: | :----------------------------------------------- |
| GET    | /api/comments/?postId=&page=               |         { postId, page }          | Get comments by post, limit 5                    |
| GET    | /api/comments/getReplies/?commentId=&page= |        { commentId, page }        | Get replies by comment, limit 5                  |
| POST   | /api/comments/                             |        { content, postId}         | Post a comment                                   |
| POST   | /api/comments/addReply/                    | { content, comment (commentId á)} | Reply a comment                                  |
| POST   | /api/comments/actionOnComment/             |           { actionId? }           | Save action a post                               |
| DELETE | /api/comments/{id}/delete/                 |               None                | Delete a comment, including all its sub, actions |
| DELETE | /api/comments/{id}/deleteSub/              |               None                | Delete a reply, including all its actions        |

#### API Posts

| Method | Endpoint                     |       Payload       | Description             |
| :----- | :--------------------------- | :-----------------: | :---------------------- |
| GET    | /api/posts/?userId=&page=    |        None         | Get a list of posts     |
| GET    | /api/posts/{id}              |        None         | Get a post by ID        |
| POST   | /api/posts/                  | { content, images } | Create a post           |
| PATCH  | /api/posts/{id}/toggle-lock/ |        none         | Toggle the lock comment |

#### API Actions

| Method | Endpoint              |        Payload         | Description              |
| :----- | :-------------------- | :--------------------: | :----------------------- |
| POST   | /api/actions/post/    |    { post, action }    | Change action on post    |
| POST   | /api/actions/comment/ |  { comment, action }   | Change action on comment |
| POST   | /api/actions/reply/   | { subComment, action } | Change action on reply   |
