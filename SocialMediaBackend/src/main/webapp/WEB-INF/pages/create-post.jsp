<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h4 class="fw-bold py-3 mb-4">
    <span class="text-muted fw-light">
        <spring:message code="view.pages.create-post.post" /> /
    </span> 
    <spring:message code="view.pages.create-post.create" />
</h4>

<div class="card">
    <div class="card-header d-flex justify-content-between">
        <div>
            <h5 class="align-items-center"><spring:message code="view.pages.create-post.title" /></h5>
        </div>
    </div>
    <div class="d-flex m-2">
        <input type="text" placeholder="Tên bài khảo sát" id="title-survey" class="form-control me-2"/>
        <button onclick="script.createSurvey()" class="btn btn-success">
            <spring:message code="view.pages.create-post.posting" />
        </button>
    </div>
    <div class="container">
        <h1 class="align-items-center">Tạo câu hỏi</h1>
        <input
            placeholder="Nội dung câu hỏi"
            id="question"
            onkeyup="script.checkDisable(this.value)" 
            class="form-control"/>
        <button disabled id="btnDone" onclick="script.createAnswer()" class="btn btn-warning">
            Hoàn thành câu hỏi
        </button>

        <div id="option">
            <div>
                <c:forEach items="${questionTypes}" var="type">
                    <label class="form-check-label">
                        <input
                            type="radio"
                            value="${type}"
                            class="questionType"
                            name="questionType" 
                            class="form-check-input"
                            />
                        ${type}
                    </label>
                </c:forEach>
            </div>
            <button disabled id="btnAdd" onclick="script.renderCreateAnswer()" class="btn btn-info">
                Thêm câu trả lời
            </button>
            <div id="answerContainer">

            </div>
        </div>
    </div>
    <form id="form"></form>
</div>
<script>
    let questions = [];
    const root = document.getElementById("form");
    const title = document.querySelector("#title-survey");
    let currCheckedType = null;
    let currContentQuestion = "";
    let currentAnswers = [];
    let currentQuestion = {};
    let id = 1;
    let btnAdd = document.querySelector("#btnAdd");
    let btnDone = document.querySelector("#btnDone");
    let contentQuestion = document.querySelector("#question");
    let answerContainer = document.querySelector("#answerContainer");
    const myRadio = document.querySelectorAll(".questionType");
    const script = {
        render() {
            root.innerHTML = "";
            document.querySelector("#question").innerHTML = "";
            let a = document.querySelectorAll(".answer");
            a.forEach((i) => i.remove());
            const htmlList = questions.map((questionItem) => {
                // Map the 'answers' array of each question to HTML elements
                const answersHtml = questionItem.answers
                        .map((answerItem) => {
                            return `<li>Content: ${answerItem.content}</li>`;
                        })
                        .join("");
                return `
                <li>Question: ${questionItem.content}, Type: ${questionItem.questionType}</li>
                    <button class="btn btn-danger" onclick="script.deleteQuestion(${questionItem.id})">Xoá câu hỏi</button>
                    <button class="btn" onclick="script.updateQuestion(${questionItem.id})" >Sửa câu hỏi</button>
                <ul>${answersHtml}</ul> `;
            });

            const listHTML = htmlList.join("");
            root.insertAdjacentHTML("beforeend", `<ul>${listHTML}</ul>`);
        },
        checkDisable(value) {
            let questionType = document.querySelector(
                    'input[name="questionType"]:checked'
                    );
            if (!value) {
                btnAdd.disabled = true;
                btnDone.disabled = true;
                return;
            }
            // checkbox or radio
            if (!!value && questionType && questionType.value !== "text") {
                btnAdd.disabled = false;
                btnDone.disabled = false;
            } else if (questionType && questionType.value === "text") {
                btnAdd.disabled = true;
                btnDone.disabled = false;
            }
        },
        deleteQuestion(id) {
            const event = window.event;
            event.preventDefault();
            questions = questions.filter((question) => question.id !== id);
            this.render();
        },
        updateQuestion(id) {
            const event = window.event;
            event.preventDefault();
            let q = questions.find((item) => item.id === id);
            console.log(q);
        },
        renderQuestion(q) {},
        renderMyInput(type) {
            switch (type) {
                case "checkbox":
                    console.log("checkbox");
                    // return input of type
                    break;
                case "radio":
                    console.log("radio");
                    break;
                case "text":
                    console.log("text");
                    break;
                default:
                    break;
            }
        },
        createAnswer(e) {
            const event = e || window.event;
            event.preventDefault();
            // lấy câu hỏi
            currContentQuestion = document.querySelector("#question").value;
            // lấy các câu trả lời nếu có
            const answers = document.querySelectorAll(".answer");
            const t = [];
            const myAnswers = answers.forEach((item) => {
                const b = item.querySelector(".answer-content");
                t.push({
                    content: b.value,
                });
            });
            currentQuestion = {
                id: id++,
                questionType: currCheckedType.toUpperCase(),
                content: currContentQuestion,
                answers: t,
            };
            console.log(currentQuestion);
            questions.push(currentQuestion);
            answerContainer.innerHTML = "";
            myRadio.forEach((element) => {
                element.checked = false;
            });
            btnAdd.disabled = true;
            btnDone.disabled = true;
            document.querySelector("#question").value = "";
            this.render();
        },
        renderCreateAnswer(e) {
            const event = e || window.event;
            event.preventDefault();
            // const option = document.querySelector("#option");
            answerContainer.insertAdjacentHTML(
                    "beforebegin",
                    !!currCheckedType && currCheckedType !== "text"
                    ? `<div class="answer">
                <input type="text" class="answer-content" />
                </div>`
                    : ``
                    );
        },
        createSurvey() {
            if (questions.length === 0) {
                alert("Chưa có câu hỏi nào");
                return;
            }
            const rs = {
                content: !!title.value ? title.value : "",
                questions,
            };
            fetch('<c:url value="/admin/post"/>', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(rs)
            }).then(res => window.location.href = res.url)
                    .catch(err => console.log(err))
            console.log(rs);
        },
        start() {
            this.render();
        },
    };
    script.start();

    myRadio.forEach((item) => {
        item.addEventListener("change", (e) => {
            currCheckedType = e.currentTarget.value;
            console.log(currCheckedType);
            // không có ndung câu hỏi --> disable
            if (!contentQuestion.value) {
                btnDone.disabled = true;
                return;
            }
            // text -> không cho add & cho complete
            if (currCheckedType === "text") {
                btnAdd.disabled = true;
                btnDone.disabled = false;
            }
            // checkbox & radio -> cho add & cho complete
            else {
                btnAdd.disabled = false;
                btnDone.disabled = false;
            }
        });
    });
</script>