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

<div class="card py-3">
    <div class="card-header d-flex justify-content-between">
        <div>
            <h5 class="align-items-center"><spring:message code="view.pages.create-post.title" /></h5>
        </div>
    </div>
    <div class="d-flex m-2">
        <input type="text" 
               placeholder="<spring:message code="view.pages.create-post.survey.placeholder"/>" 
               id="title-survey" class="form-control me-2"/>
        <button onclick="script.createSurvey()" class="btn btn-success">
            <spring:message code="view.pages.create-post.posting" />
        </button>
    </div>
    <div class="container">
        <h1 class="align-items-center">
            <spring:message code="view.pages.create-post.createQuestion"/>
        </h1>
        <input
            placeholder="<spring:message code="view.pages.create-post.question.placeholder"/>"
            id="question"
            onkeyup="script.checkDisable(this.value)" 
            class="form-control"/>
        <button disabled id="btnDone" onclick="script.createAnswer()" class="btn btn-warning">
            <spring:message code="view.pages.create-post.completeQuestion"/>
        </button>

        <div id="option">
            <div>
                <c:forEach items="${questionTypes}" var="type" varStatus="status">
                    <label class="form-check-label">
                        <input
                            type="radio"
                            value="${type}"
                            class="questionType"
                            name="questionType" 
                            class="form-check-input"
                            <c:if test="${status.index == 2}" >checked</c:if>
                                />
                        ${type}
                    </label>
                </c:forEach>
            </div>
            <button disabled id="btnAdd" onclick="script.renderCreateAnswer()" class="btn btn-info">
                <spring:message code="view.pages.create-post.addNewAnswer"/>
            </button>
            <div id="answerContainer">

            </div>
        </div>
    </div>

</div>

<div class="card mt-4">
    <div class="card-header">
        <h1></h1>
    </div>
    <form id="form"></form>
</div>
<script>
    let questions = [];
    const root = document.getElementById("form");
    const title = document.querySelector("#title-survey");
    let currCheckedType = "TEXT";
    let currContentQuestion = "";
    let currentAnswers = [];
    let currentQuestion = {};
    let id = 1;
    let btnAdd = document.querySelector("#btnAdd");
    let btnDone = document.querySelector("#btnDone");
    // input for get question content
    let contentQuestion = document.querySelector("#question");
    // container have answers of current question
    let answerContainer = document.querySelector("#answerContainer");
    const myRadio = document.querySelectorAll(".questionType");
    const script = {
        // render when loading page and after add a question to list questions
        render() {
            root.innerHTML = "";
            document.querySelector("#question").innerHTML = "";
            let a = document.querySelectorAll(".answer");
            a.forEach((i) => i.remove());
            const htmlList = questions.map((questionItem) => {
                // Map the 'answers' array of each question to HTML elements
                const answersHtml = questionItem.answers.map((answerItem) => {
                    return `<li>                    
                        <input type="\${questionItem.questionType}" value='\${answerItem.content}' name='question-id-\${questionItem.id}'/>
                        <label>\${answerItem.content}</label>
                    </li>`;
                }).join("");
                return `
                <li>Question: \${questionItem.content}, Type: \${questionItem.questionType}</li>
                <ul>\${answersHtml}</ul>
                <div>
                    <button class="btn btn-danger" onclick="script.deleteQuestion(\${questionItem.id})">Xoá câu hỏi</button>
                    <button class="btn" onclick="script.updateQuestion(\${questionItem.id})" >Sửa câu hỏi</button>
                </div>`;
            });
            const listHTML = htmlList.join("");
            root.insertAdjacentHTML("beforeend", `<ul>\${listHTML}</ul>`);
        },
        // Check when change input questions, if there's choose radio -> enable btn
        checkDisable(value) {
            let questionType = document.querySelector('input[name="questionType"]:checked');
            if (!value) {
                btnAdd.disabled = true;
                btnDone.disabled = true;
                return;
            }
            // checkbox or radio
            if (!!value && questionType && questionType.value !== "TEXT") {
                btnAdd.disabled = false;
                btnDone.disabled = false;
            } else if (questionType && questionType.value === "TEXT") {
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
        // add a question to list questions
        createAnswer(e) {
            const event = e || window.event;
            event.preventDefault();
            // lấy câu hỏi nội dung
            currContentQuestion = contentQuestion.value;
            // lấy các câu trả lời nếu có, lấy input
            const answers = answerContainer.querySelectorAll(".answer-content");
            const t = [];
            const myAnswers = answers.forEach((item) => {
                t.push({
                    content: item.value
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
                element.checked = element.value == "TEXT";
            });
            btnAdd.disabled = true;
            btnDone.disabled = true;
            document.querySelector("#question").value = "";
            this.render();
        },
        // add input to typing answer for question 
        renderCreateAnswer(e) {
            const event = e || window.event;
            event.preventDefault();
            // const option = document.querySelector("#option");
            answerContainer.insertAdjacentHTML(
                    "beforeend",
                    !!currCheckedType && currCheckedType !== "text" ?
                    `<input type="text" 
                            class="answer-content form-control" 
                            placeholder='<spring:message code="view.pages.create-post.answer.placeholder" />' />`
                    : ``);
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
            fetch('<c:url value="/admin/post/create"/>', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(rs),
                redirect: 'follow'
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
            // text -> không cho add & cho complete
            if (currCheckedType === "TEXT") {
                btnAdd.disabled = true;
                btnDone.disabled = false;
                answerContainer.innerHTML = "";
            }
            // checkbox & radio -> cho add & cho complete
            else {
                btnAdd.disabled = false;
                btnDone.disabled = false;
                answerContainer.innerHTML = `
                    <input type="text" 
                            class="answer-content form-control" 
                            placeholder='<spring:message code="view.pages.create-post.answer.placeholder" />' />
                    <input type="text" 
                            class="answer-content form-control" 
                            placeholder='<spring:message code="view.pages.create-post.answer.placeholder" />' />
                `;
            }

            // không có ndung câu hỏi --> disable
            if (!contentQuestion.value) {
                btnDone.disabled = true;
                btnAdd.disabled = true;
            }
        });
    });
</script>