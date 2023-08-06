<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h1>Tạo câu hỏi</h1>
<input
    placeholder="Nội dung câu hỏi"
    id="question"
    onkeyup="script.checkDisable(this.value)" />
<button disabled id="btnAdd" onclick="script.renderCreateAnswer()">
    Thêm câu trả lời
</button>
<button disabled id="btnDone" onclick="script.createAnswer()">
    Hoàn thành câu hỏi
</button>

<div id="option">
    <div>
        <input
            type="radio"
            value="checkbox"
            class="questionType"
            name="questionType" />
        <label>checkbox</label>
    </div>
    <div>
        <input
            type="radio"
            value="radio"
            class="questionType"
            name="questionType" />
        <label>radio</label>
    </div>
    <div>
        <input
            type="radio"
            value="text"
            class="questionType"
            name="questionType" />
        <label>text</label>
    </div>
    <!-- <button onclick="script.renderCreateAnswer()">Thêm câu trả lời</button> -->
</div>
<form id="form"></form>
</body>

<script>
    let questions = [
        {
            id: 1,
            content: "Hỏi cái cc",
            questionType: "checkbox",
            answers: [
                {
                    content: "abc",
                },
                {
                    content: "abc",
                },
                {
                    content: "abc",
                },
            ],
        },
        {
            id: 2,
            content: "Hỏi cái cc2",
            questionType: "checkbox",
            answers: [
                {
                    content: "abc",
                },
                {
                    content: "abc",
                },
                {
                    content: "abc",
                },
            ],
        },
    ];
    const root = document.getElementById("form");
    let currCheckedType = null;
    let currContentQuestion = "";
    let currentAnswers = [];
    let currentQuestion = {};
    let id = 1;
    let btnAdd = document.querySelector("#btnAdd");
    let btnDone = document.querySelector("#btnDone");
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
                return `<li>Question: ${questionItem.content}, Type: ${questionItem.questionType}</li>
         <button onclick="script.deleteQuestion(${questionItem.id})">Xoá câu hỏi</button>
        <button onclick="script.updateQuestion(${questionItem.id})" >Sửa câu hỏi</button>
        <ul>${answersHtml}</ul> `;
            });

            const listHTML = htmlList.join("");
            root.insertAdjacentHTML("beforeend", `<ul>${listHTML}</ul>`);
        },
        checkDisable(value) {
            if (!!value) {
                btnAdd.disabled = false;
                btnDone.disabled = false;
            } else {
                btnAdd.disabled = true;
                btnDone.disabled = true;
            }
        },
        renderAddQuestion() {
            const myRadio = document.querySelectorAll(".questionType");
            myRadio.forEach((item) => {
                item.addEventListener("change", (e) => {
                    currCheckedType = e.currentTarget.value;
                    if (currCheckedType === "text")
                        btnAdd.disabled = true;
                    this.render();
                });
            });
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
            let id = 1;
            currContentQuestion = document.querySelector("#question").value;
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
                questionType: currCheckedType,
                content: currContentQuestion,
                answers: t,
            };
            console.log(currentQuestion);
            questions.push(currentQuestion);
            const a = document.querySelectorAll(".answer");
            a.forEach((element) => {
                element.remove();
            });
            this.renderAddQuestion();
            document.querySelector("#question").value = "";
            this.render();
        },
        renderCreateAnswer(e) {
            const event = e || window.event;
            event.preventDefault();
            // const option = document.querySelector("#option");
            root.insertAdjacentHTML(
                    "beforebegin",
                    !!currCheckedType && currCheckedType !== "text"
                    ? `<div class="answer">
              <input type = "text" class="answer-content" />
              </div>`
                    : ``
                    );
        },
        handleClickBtnAdd() {
            this.renderAddQuestion();
        },
        start() {
            this.render();
            this.renderAddQuestion();
        },
    };
    script.start();
</script>