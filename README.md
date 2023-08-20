# social-media

Nhóm 7 - Social Media For OU Alumni

## Get started

```bash
git clone https://github.com/dauxuanhoanghung/social-media.git
```

Change profile Database in database.properties

## FrontEnd

[Source](https://github.com/chuongdinh59/social-app)

## Overview

### Client API

#### API User

| Method   | Endpoint                       | Payload       | Description   |
| :---     | :---                           |   :----:      | :---          |
| GET      | /api/users/{id}                | None          | Fetch to get user by ID |
| POST     | /api/users/                    | { alumniId, displayName, avatarFile, email} | Register new client |

#### API Comments

| Method   | Endpoint                       | Payload       | Description   |
| :---     | :---                           |   :----:      | :---          |
| POST     | /api/comments/                 | { content, postId}                          | Post a comment      |
| POST     | /api/comments/addReply/        | { content, comment (commentId á)}           | Reply a comment     |
| POST     | /api/comments/actionOnComment/ | { actionId? }                               | Save action a post  |
| DELETE   | /api/comments/{id}/delete/     | None          | Delete a comment, including all its sub, actions  |
| DELETE   | /api/comments/{id}/deleteSub/  | None          | Delete a reply, including all its actions |

#### API Post

| Method   | Endpoint                       | Payload       | Description   |
| :---     | :---                           |   :----:      | :---          |
| GET      | /api/post/                     | None          | Get a list of posts |
| GET      | /api/post/{id}                 | None          | Get a post by ID    |
| POST     | /api/post/                     | { content, images }                          | Create a post |
